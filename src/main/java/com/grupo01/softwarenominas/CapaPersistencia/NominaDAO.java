package com.grupo01.softwarenominas.capapersistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.Nomina;
import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ResultadoOperacion;

public class NominaDAO {

    public void cargarPeriodosPago(JComboBox<PeriodoPago> comboBoxPeriodo) {
        comboBoxPeriodo.removeAllItems();
        comboBoxPeriodo.addItem(new PeriodoPago(0, null, null, "-- Periodo de Pago --", "", true, null));

        CConexion objetoConexion = new CConexion();
        

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerPeriodosPago()}");
        )
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IdPeriodoPago");
                Date fechaInicio = rs.getDate("FechaInicio");
                Date fechaFin = rs.getDate("FechaFin");
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                boolean estado = rs.getBoolean("Estado");
                Date fechaRegistro = rs.getDate("FechaRegistro");

                comboBoxPeriodo.addItem(new PeriodoPago(id, fechaInicio, fechaFin, nombre, descripcion, estado, fechaRegistro));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error cargando periodos de pago.");
        }
    }
    
    
    public boolean existePeriodoAnteriorPendientePorContrato(int idContrato, int idPeriodoActual) throws SQLException {
        
        CConexion objetoConexion = new CConexion();
        
        
        String sql = "{call sp_ExistePeriodoAnteriorPendientePorContrato(?, ?, ?)}";

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement cs = conn.prepareCall(sql);
        )
        {
            
            cs.setInt(1, idContrato);
            cs.setInt(2, idPeriodoActual);
            cs.registerOutParameter(3, Types.BIT);

            cs.execute();

            return cs.getBoolean(3);
        }catch(SQLException e){
            return false;
        }
    }
    
    public boolean existePeriodoAnteriorPendiente(int idPeriodo) throws SQLException {
        CConexion objetoConexion = new CConexion();
        
        String sql = "{call sp_ExistePeriodoAnteriorPendiente(?, ?)}";

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement cs = conn.prepareCall(sql);
        )
        {
            cs.setInt(1, idPeriodo);
            cs.registerOutParameter(2, Types.BIT);

            cs.execute();

            return cs.getBoolean(2);
        }catch(SQLException e){
            return false;
        }
    }
 
    public TipoContrato obtenerTipoContratoPorId(int idTipoContrato) {
        TipoContrato tipoContrato = null;

        CConexion objetoConexion = new CConexion();
        

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTipoContratoPorId(?)}");
        )
        {
            stmt.setInt(1, idTipoContrato);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tipoContrato = new TipoContrato();
                tipoContrato.setIdTipoContrato(rs.getInt("IdTipoContrato"));
                tipoContrato.setNombre(rs.getString("Nombre"));
                tipoContrato.setDescripcion(rs.getString("Descripcion"));
                tipoContrato.setEstado(rs.getBoolean("Estado"));
                tipoContrato.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener tipo de contrato: " + e.getMessage());
        }

        return tipoContrato;
    }

    public ResultadoOperacion insertarNominaCompleta(Nomina nomina) {
        ResultadoOperacion resultado;

        String sql = "{call sp_InsertarNominaCompleta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = new CConexion().establecerConexion();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, nomina.getContratoPeriodo().getIdContratoPeriodo());
            stmt.setDouble(2, nomina.getSueldoNeto());
            stmt.setString(3, nomina.getMetodoPago());
            stmt.setString(4, nomina.getDescripcion());

            stmt.setDouble(5, nomina.getDetalle().getPagoHorasExtras());
            stmt.setDouble(6, nomina.getDetalle().getGratificacionLegal());
            stmt.setDouble(7, nomina.getDetalle().getAsignacionFamiliar());
            stmt.setDouble(8, nomina.getDetalle().getCts());

            stmt.setDouble(9, nomina.getDetalle().getDescuentoHorasNoCompletadas());
            stmt.setDouble(10, nomina.getDetalle().getDescuentoSeguroSalud());
            stmt.setDouble(11, nomina.getDetalle().getDescuentoSeguroVida());
            stmt.setDouble(12, nomina.getDetalle().getDescuentoSeguroAccidentes());
            stmt.setDouble(13, nomina.getDetalle().getDescuentoAFP());
            stmt.setDouble(14, nomina.getDetalle().getDescuentoRenta());

            stmt.setDouble(15, nomina.getDetalle().getTotalIngresos());
            stmt.setDouble(16, nomina.getDetalle().getTotalDescuentos());

            stmt.registerOutParameter(17, java.sql.Types.INTEGER);

            stmt.execute();

            int idNomina = stmt.getInt(17);
            if (idNomina > 0) {
                resultado = new ResultadoOperacion(true, idNomina, "Nómina procesada correctamente.");
            } else {
                resultado = new ResultadoOperacion(false, -1, "No se generó la nómina.");
            }

        } catch (SQLException e) {
            String mensaje = e.getMessage();
            resultado = new ResultadoOperacion(false, -1, mensaje);
        }

        return resultado;
    }
  
    public int listarNominasPorPeriodo(JTable tabla, int idPeriodo) {
        CConexion objetoConexion = new CConexion();
        

        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        String[] columnas = {
            "Nombres", "DocumentoIdentidad", "PeriodoPago", "HorasTotales", "HorasTrabajadas", "EstadoPago",
            "SalarioBase", "PagoHorasExtras", "GratificacionLegal","AsignacionFamiliar", "CTS", "TotalIngresos", 
            "DescuentoHorasNoCompletadas", "DescuentoSeguroSalud", "DescuentoSeguroVida","DescuentoSeguroAccidentes", "DescuentoAFP", "DescuentoRenta",
            "TotalDescuentos", "SueldoNeto", "MetodoPago"
        };
             
                
        for (String col : columnas) {
            modelo.addColumn(col);
        }
           
        int totalResultados = 0;

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ListarNominasPorPeriodo(?)}");
        )
        {
            stmt.setInt(1, idPeriodo);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Object[] fila = new Object[columnas.length];
                for (int i = 0; i < columnas.length; i++) {
                    fila[i] = rs.getObject(columnas[i]);
                }
                modelo.addRow(fila);
                totalResultados++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar nóminas pagadas:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return totalResultados;
    }
}