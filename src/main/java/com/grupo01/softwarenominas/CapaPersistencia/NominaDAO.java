package com.grupo01.softwarenominas.CapaPersistencia;

import com.grupo01.softwarenominas.CapaConexion.CConexion;
import com.grupo01.softwarenominas.CapaEntidad.Nomina2;
import com.grupo01.softwarenominas.CapaEntidad.DetalleNomina2;
import com.grupo01.softwarenominas.CapaEntidad.DetalleNomina1;
import com.grupo01.softwarenominas.CapaEntidad.Nomina1;
import com.grupo01.softwarenominas.CapaEntidad.PeriodoPago;
import com.grupo01.softwarenominas.CapaEntidad.TipoContrato;

import com.grupo01.softwarenominas.CapaEntidad.Nomina;
import com.grupo01.softwarenominas.CapaNegocio.ContratoNegocio.ResultadoOperacion;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NominaDAO {

    public boolean registrarNomina(Nomina2 n) {
        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();

        try {
            CallableStatement stmt = conn.prepareCall("{call sp_CrearNomina(?, ?, ?, ?, ?, ?)}");

            stmt.setInt(1, n.getIdContrato());
            stmt.setInt(2, n.getIdPeriodo());
            stmt.setDouble(3, n.getSueldoNeto());
            stmt.setString(4, n.getEstadoPago());
            stmt.setString(5, n.getMetodoPago());
            stmt.setString(6, n.getDescripcion());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar nómina: " + e.getMessage());
            return false;
        }
    }
    
    public boolean registrarDetalleNomina(DetalleNomina2 d) {
        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();

        try {
            CallableStatement stmt = conn.prepareCall("{call sp_CrearDetalleNomina(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            stmt.setInt(1, d.getIdNomina());
            stmt.setDouble(2, d.getGratificacionLegal());
            stmt.setDouble(3, d.getAsignacionFamiliar());
            stmt.setDouble(4, d.getCts());
            stmt.setDouble(5, d.getDescuentoSeguroSalud());
            stmt.setDouble(6, d.getDescuentoSeguroVida());
            stmt.setDouble(7, d.getDescuentoSeguroAccidentes());
            stmt.setDouble(8, d.getDescuentoAFP());
            stmt.setDouble(9, d.getDescuentoRenta());
            stmt.setDouble(10, d.getTotalIngresos());
            stmt.setDouble(11, d.getTotalDescuentos());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar detalle de nómina: " + e.getMessage());
            return false;
        }
    }

    public void listarNominas(JTable tabla) {
        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        try {
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerNominas()}");
            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            int columnas = meta.getColumnCount();

            for (int i = 1; i <= columnas; i++) {
                modelo.addColumn(meta.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar nóminas: " + e.getMessage());
        }
    }

    public DetalleNomina2 obtenerDetalleNominaPorId(int idNomina) {
        DetalleNomina2 d = null;
        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();

        try {
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerDetalleNomina(?)}");
            stmt.setInt(1, idNomina);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                d = new DetalleNomina2();
                d.setIdDetalle(rs.getInt("IdDetalle"));
                d.setIdNomina(rs.getInt("IdNomina"));
                d.setGratificacionLegal(rs.getDouble("GratificacionLegal"));
                d.setAsignacionFamiliar(rs.getDouble("AsignacionFamiliar"));
                d.setCts(rs.getDouble("CTS"));
                d.setDescuentoSeguroSalud(rs.getDouble("DescuentoSeguroSalud"));
                d.setDescuentoSeguroVida(rs.getDouble("DescuentoSeguroVida"));
                d.setDescuentoSeguroAccidentes(rs.getDouble("DescuentoSeguroAccidentes"));
                d.setDescuentoAFP(rs.getDouble("DescuentoAFP"));
                d.setDescuentoRenta(rs.getDouble("DescuentoRenta"));
                d.setTotalIngresos(rs.getDouble("TotalIngresos"));
                d.setTotalDescuentos(rs.getDouble("TotalDescuentos"));
                d.setFechaRegistro(rs.getString("FechaRegistro"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener detalle de nómina: " + e.getMessage());
        }

        return d;
    }    
    
    public void cargarPeriodosPago(JComboBox<PeriodoPago> comboBoxPeriodo) {//si
        comboBoxPeriodo.removeAllItems();
        comboBoxPeriodo.addItem(new PeriodoPago(0, null, null, "-- Periodo de Pago --", "", true, null));

        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();

        try {
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerPeriodosPago()}");

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
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error cargando periodos de pago.");
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    public int insertarNomina(Nomina1 nomina) {
    int idNomina = -1;
    try (Connection conn = new CConexion().establecerConexion()) {
        CallableStatement stmt = conn.prepareCall("{call sp_InsertarNominaCompleta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        stmt.setInt(1, nomina.getContrato().getIdContrato());
        stmt.setInt(2, nomina.getPeriodo().getIdPeriodoPago());
        stmt.setDouble(3, nomina.getSueldoNeto());
        stmt.setString(4, nomina.getMetodoPago());
        stmt.setString(5, nomina.getDescripcion());

        DetalleNomina1 d = nomina.getDetalle();
        stmt.setDouble(6, d.getGratificacionLegal());
        stmt.setDouble(7, d.getAsignacionFamiliar());
        stmt.setDouble(8, d.getCts());
        stmt.setDouble(9, d.getDescuentoSeguroSalud());
        stmt.setDouble(10, d.getDescuentoSeguroVida());
        stmt.setDouble(11, d.getDescuentoSeguroAccidentes());
        stmt.setDouble(12, d.getDescuentoAFP());
        stmt.setDouble(13, d.getDescuentoRenta());
        stmt.setDouble(14, d.getTotalIngresos());
        stmt.setDouble(15, d.getTotalDescuentos());

        stmt.registerOutParameter(16, Types.INTEGER);
        stmt.execute();

        idNomina = stmt.getInt(16);
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al insertar la nómina.");
    }
    return idNomina;
    }
    
    public boolean existePeriodoAnteriorPendientePorContrato(int idContrato, int idPeriodoActual) throws Exception {//si
        
        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();
        
        String sql = "{call sp_ExistePeriodoAnteriorPendientePorContrato(?, ?, ?)}";

        try {
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, idContrato);
            cs.setInt(2, idPeriodoActual);
            cs.registerOutParameter(3, Types.BIT);

            cs.execute();

            return cs.getBoolean(3);
        }catch(SQLException e){
            return false;
        }
    }
    
    public boolean existePeriodoAnteriorPendiente(int idPeriodo) throws Exception {//si
        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();
        String sql = "{call sp_ExistePeriodoAnteriorPendiente(?, ?)}";

        try {
            CallableStatement cs = conn.prepareCall(sql);

            cs.setInt(1, idPeriodo);
            cs.registerOutParameter(2, Types.BIT);

            cs.execute();

            return cs.getBoolean(2);
        }catch(SQLException e){
            return false;
        }
    }
 
    public TipoContrato obtenerTipoContratoPorId(int idTipoContrato) {//si
        TipoContrato tipoContrato = null;

        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();

        try {
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTipoContratoPorId(?)}");
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

    public void listarNominas1(JTable tabla) {
        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();

        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        try {
            CallableStatement stmt = conn.prepareCall("{call sp_ListarNominas()}");
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnas = meta.getColumnCount();

            for (int i = 1; i <= columnas; i++) {
                modelo.addColumn(meta.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar nóminas: " + e.getMessage());
        }
    }
   
    public ResultadoOperacion insertarNominaCompleta(Nomina nomina) {//si
        ResultadoOperacion resultado = new ResultadoOperacion(false, -1, "");

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
  
    public int listarNominasPorPeriodo(JTable tabla, int idPeriodo) {//si
        CConexion objetoConexion = new CConexion();
        Connection conn = objetoConexion.establecerConexion();

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

        try {
            CallableStatement stmt = conn.prepareCall("{call sp_ListarNominasPorPeriodo(?)}");
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