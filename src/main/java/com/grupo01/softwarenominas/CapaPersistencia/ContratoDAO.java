package com.grupo01.softwarenominas.capapersistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.APELLIDO_MATERNO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.APELLIDO_PATERNO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.AREA_NOMBRE;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.CARGO_NOMBRE;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.DESCRIPCION;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.DOCUMENTO_IDENTIDAD;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.ESPECIALIDAD;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.ESTADO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.FECHA_FIN;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.FECHA_INICIO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.FECHA_REGISTRO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.HORAS_TOTALES;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.ID_CARGO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.ID_CONTRATO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.ID_ESPECIALIDAD;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.ID_TIPO_CONTRATO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.NOMBRES;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.TIPO_CONTRATO_NOMBRE;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.ERROR;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.ID_AREA;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDContrato.NOMBRE;

@Getter
@Setter
@AllArgsConstructor
public class ContratoDAO {
    private final CConexion conexion;
    public ContratoDAO() {
        this.conexion = new CConexion();
    }

    private DefaultTableModel crearModeloTabla(JTable tabla, String[] columnas) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        for (String col : columnas) {
            modelo.addColumn(col);
        }
        return modelo;
    }

    private int llenarTabla(DefaultTableModel modelo, ResultSet rs, String[] columnas) throws SQLException {
        int total = 0;
        while (rs.next()) {
            Object[] fila = new Object[columnas.length];
            for (int i = 0; i < columnas.length; i++) {
                fila[i] = rs.getObject(columnas[i]);
            }
            modelo.addRow(fila);
            total++;
        }
        return total;
    }

    @FunctionalInterface
    interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    private <T> void cargarComboBox(JComboBox<T> comboBox, CallableStatement stmt, ResultSetMapper<T> mapper, T opcionInicial) throws SQLException {
        comboBox.removeAllItems();
        comboBox.addItem(opcionInicial);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            comboBox.addItem(mapper.map(rs));
        }
    }
    

    public void cargarAreas(JComboBox<Area> comboBoxArea) {
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ListarAreas}")) {
            cargarComboBox(comboBoxArea, stmt,
                rs -> new Area(rs.getInt("IdArea"), rs.getString(NOMBRE)),
                new Area(0, "-- Area --", "", true, new Date()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error cargando áreas.");
        }
    }


    public void cargarEspecialidadesPorArea(JComboBox<Especialidad> comboBoxEspecialidad, int idArea) {
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ListarEspecialidadesPorArea(?)}")) {
            stmt.setInt(1, idArea);
            cargarComboBox(comboBoxEspecialidad, stmt,
                rs -> new Especialidad(rs.getInt("IdEspecialidad"), idArea, rs.getString(NOMBRE)),
                new Especialidad(0, "-- Especialidad --", "", true, new Date()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error cargando especialidades por área.");
        }
    }

    public void cargarTiposContrato(JComboBox<TipoContrato> comboBox) {
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ListarTiposContrato}")) {
            cargarComboBox(comboBox, stmt,
                rs -> new TipoContrato(rs.getInt(ID_TIPO_CONTRATO), rs.getString(NOMBRE), "", true, new Date()),
                new TipoContrato(0, "-- Tipo de Contrato --", "", true, new Date()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tipos de contrato: " + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarCargos(JComboBox<Cargo> comboBox) {
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ListarCargos}")) {
            cargarComboBox(comboBox, stmt,
                rs -> new Cargo(rs.getInt(ID_CARGO), rs.getString(NOMBRE), "", true, new Date()),
                new Cargo(0, "-- Cargo --", "", true, new Date()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar cargos: " + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ResultadoOperacion registrarContrato(Contrato c) {
        int idGenerado;
        String mensaje;
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_CrearContrato(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            stmt.setInt(1, c.getIdTrabajador());
            stmt.setInt(2, c.getIdTipoContrato());
            stmt.setInt(3, c.getIdCargo());
            stmt.setDate(4, java.sql.Date.valueOf(c.getFechaInicio().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
            stmt.setDate(5, java.sql.Date.valueOf(c.getFechaFin().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
            stmt.setDouble(6, c.getSalarioBase());
            stmt.setInt(7, c.getHorasTotales());
            stmt.setString(8, c.getDescripcion());
            stmt.setInt(9, c.getIdArea());
            stmt.setInt(10, c.getIdEspecialidad());
            stmt.registerOutParameter(11, java.sql.Types.INTEGER);

            stmt.execute();
            idGenerado = stmt.getInt(11);

            mensaje = (idGenerado > 0) ? "Contrato registrado correctamente." : "No se pudo registrar el contrato.";
            return new ResultadoOperacion(idGenerado > 0, idGenerado, mensaje);

        } catch (SQLException e) {
            return new ResultadoOperacion(false, -1, e.getMessage());
        }
    }

    public boolean actualizarContrato(Contrato c) {
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ActualizarContrato(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
            stmt.setInt(1, c.getIdContrato());
            stmt.setInt(2, c.getIdTipoContrato());
            stmt.setInt(3, c.getIdCargo());
            stmt.setDate(4, java.sql.Date.valueOf(c.getFechaInicio().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
            stmt.setDate(5, java.sql.Date.valueOf(c.getFechaFin().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()));
            stmt.setDouble(6, c.getSalarioBase());
            stmt.setInt(7, c.getHorasTotales());
            stmt.setString(8, c.getDescripcion());
            stmt.setInt(9, c.getIdArea());
            stmt.setInt(10, c.getIdEspecialidad());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar contrato: " + e.getMessage());
            return false;
        }
    }

    public void listarContratoPeriodosPorContrato(JTable tabla, int idContrato) {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                String estado = (String) getValueAt(row, 5);
                return column == 4 && !estado.equalsIgnoreCase("PAGADO") && !estado.equalsIgnoreCase("CANCELADO");
            }
        };
        tabla.setModel(modelo);

        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerContratoPeriodosPorContrato(?)}")) {

            stmt.setInt(1, idContrato);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                modelo.addColumn(meta.getColumnLabel(i));
            }
            while (rs.next()) {
                Object[] fila = new Object[meta.getColumnCount()];
                for (int i = 0; i < meta.getColumnCount(); i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar Contrato Periodos: " + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int listarContratosFiltrado(JTable tabla, Date fechaInicio, Date fechaFin, String documentoIdentidad, String nombres) {
        String[] columnas = {FECHA_INICIO, FECHA_FIN, HORAS_TOTALES, DOCUMENTO_IDENTIDAD, NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, AREA_NOMBRE, ESPECIALIDAD, TIPO_CONTRATO_NOMBRE, CARGO_NOMBRE};
        DefaultTableModel modelo = crearModeloTabla(tabla, columnas);

        int total = 0;
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerContratosFiltrados(?, ?, ?, ?)}")) {
            stmt.setDate(1, fechaInicio != null ? new java.sql.Date(fechaInicio.getTime()) : null);
            stmt.setDate(2, fechaFin != null ? new java.sql.Date(fechaFin.getTime()) : null);
            stmt.setString(3, documentoIdentidad.isEmpty() ? null : documentoIdentidad);
            stmt.setString(4, nombres.isEmpty() ? null : nombres);
            total = llenarTabla(modelo, stmt.executeQuery(), columnas);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al filtrar contratos:\n" + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }
        return total;
    }

    
    public void guardarHorasTrabajadasDesdeTabla(JTable tabla) {
        try (Connection conn = this.conexion.establecerConexion()) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                String estado = tabla.getValueAt(i, 5).toString();
                if (estado.equalsIgnoreCase("PAGADO") || estado.equalsIgnoreCase("CANCELADO")) {
                    continue;
                }

                int idContratoPeriodo = Integer.parseInt(tabla.getValueAt(i, 0).toString());
                int horasTrabajadas = Integer.parseInt(tabla.getValueAt(i, 4).toString());

                try (CallableStatement stmt = conn.prepareCall("{call sp_ActualizarHorasContratoPeriodo(?, ?)}")) {
                    stmt.setInt(1, idContratoPeriodo);
                    stmt.setInt(2, horasTrabajadas);
                    stmt.execute();
                }
            }
            JOptionPane.showMessageDialog(null, "Horas actualizadas correctamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar horas trabajadas.");
        }
    }
    
    
    public int listarContratosPorPeriodo(JTable tabla, int idPeriodo) {
        String[] columnas = {
            FECHA_INICIO, FECHA_FIN, HORAS_TOTALES, "HorasTrabajadas", "EstadoPago",
            DOCUMENTO_IDENTIDAD, NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO,
            AREA_NOMBRE, ESPECIALIDAD, TIPO_CONTRATO_NOMBRE, CARGO_NOMBRE
        };

        DefaultTableModel modelo = crearModeloTabla(tabla, columnas);
        int total = 0;

        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerContratosPorPeriodo(?)}")) {
            stmt.setInt(1, idPeriodo);
            total = llenarTabla(modelo, stmt.executeQuery(), columnas);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar contratos por periodo:\n" + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }
        return total;
    }

    public ResultadoOperacion registrarDetalleContrato(DetalleContrato detalle) {
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_CrearDetalleContrato(?, ?, ?, ?, ?, ?)}")) {

            stmt.setInt(1, detalle.getIdContrato());
            stmt.setString(2, detalle.getTipoSeguroSalud());
            stmt.setBoolean(3, detalle.isTieneSeguroDeVida());
            stmt.setBoolean(4, detalle.isTieneSeguroDeAccidentes());
            stmt.setBoolean(5, detalle.isTieneAsignacionFamiliar());
            stmt.setString(6, detalle.getDescripcion());
            stmt.execute();

            return new ResultadoOperacion(true, detalle.getIdContrato(), "Detalle de contrato registrado correctamente.");
        } catch (SQLException e) {
            return new ResultadoOperacion(false, -1, "Error al registrar detalle contrato: " + e.getMessage());
        }
    }

    public boolean actualizarDetalleContrato(DetalleContrato detalle) {
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ActualizarDetalleContrato(?, ?, ?, ?, ?, ?)}")) {

            stmt.setInt(1, detalle.getIdDetalleContrato());
            stmt.setString(2, detalle.getTipoSeguroSalud());
            stmt.setBoolean(3, detalle.isTieneSeguroDeVida());
            stmt.setBoolean(4, detalle.isTieneSeguroDeAccidentes());
            stmt.setBoolean(5, detalle.isTieneAsignacionFamiliar());
            stmt.setString(6, detalle.getDescripcion());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar detalle del contrato: " + e.getMessage());
            return false;
        }
    }

    public Contrato obtenerContratoPorId(int idContrato) {
        Contrato contrato = null;
        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerContratoPorId(?)}")) {

            stmt.setInt(1, idContrato);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contrato = new Contrato();
                contrato.setIdContrato(rs.getInt(ID_CONTRATO));
                contrato.setIdTrabajador(rs.getInt("IdTrabajador"));
                contrato.setFechaInicio(rs.getDate(FECHA_INICIO));
                contrato.setFechaFin(rs.getDate(FECHA_FIN));
                contrato.setSalarioBase(rs.getDouble("SalarioBase"));
                contrato.setHorasTotales(rs.getInt(HORAS_TOTALES));
                contrato.setDescripcion(rs.getString(DESCRIPCION));
                contrato.setIdArea(rs.getInt(ID_AREA));
                contrato.setIdEspecialidad(rs.getInt(ID_ESPECIALIDAD));
                contrato.setIdTipoContrato(rs.getInt(ID_TIPO_CONTRATO));
                contrato.setIdCargo(rs.getInt(ID_CARGO));
                contrato.setEstadoContrato(rs.getString("EstadoContrato"));
                contrato.setEstado(rs.getBoolean(ESTADO));
                contrato.setFechaRegistro(rs.getTimestamp(FECHA_REGISTRO));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener contrato por ID: " + e.getMessage());
        }
        return contrato;
    }

    
    
    public Contrato obtenerContratoPorDocumentoIdentidad(String documentoIdentidad) {
        Contrato contrato = null;


        try (
            Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerContratoPorDocumentoIdentidad(?)}");
        )
        {
            
            stmt.setString(1, documentoIdentidad);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contrato = new Contrato();
                contrato.setIdContrato(rs.getInt(ID_CONTRATO));
                contrato.setIdTrabajador(rs.getInt("IdTrabajador"));
                contrato.setIdTipoContrato(rs.getInt(ID_TIPO_CONTRATO));
                contrato.setIdCargo(rs.getInt(ID_CARGO));
                contrato.setFechaInicio(rs.getDate(FECHA_INICIO));
                contrato.setFechaFin(rs.getDate(FECHA_FIN ));
                contrato.setSalarioBase(rs.getDouble("SalarioBase"));
                contrato.setHorasTotales(rs.getInt(HORAS_TOTALES));
                contrato.setDescripcion(rs.getString(DESCRIPCION));
                contrato.setIdArea(rs.getInt(ID_AREA));
                contrato.setIdEspecialidad(rs.getInt(ID_ESPECIALIDAD));
                contrato.setEstado(rs.getBoolean(ESTADO));
                contrato.setFechaRegistro(rs.getTimestamp(FECHA_REGISTRO));
                
                contrato.setTipoContrato(new TipoContrato(rs.getInt(ID_TIPO_CONTRATO), rs.getString("NombreTipoContrato")));
                contrato.setCargo(new Cargo(rs.getInt(ID_CARGO), rs.getString("NombreCargo")));
                contrato.setArea(new Area(rs.getInt(ID_AREA), rs.getString("NombreArea")));
                contrato.setEspecialidad(new Especialidad(rs.getInt(ID_ESPECIALIDAD), rs.getString("NombreEspecialidad")));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener contrato: " + e.getMessage());
        }
        return contrato;
    }
    
    public DetalleContrato obtenerDetalleContratoPorDocumentoIdentidad(String documentoIdentidad) {
        DetalleContrato detalle = null;
        
        try (
            Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerDetalleContratoPorDocumentoIdentidad(?)}");
        )
        {
            
            stmt.setString(1, documentoIdentidad);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                detalle = new DetalleContrato();
                detalle.setIdDetalleContrato(rs.getInt("IdDetalleContrato"));
                detalle.setIdContrato(rs.getInt(ID_CONTRATO));
                detalle.setTipoSeguroSalud(rs.getString("TipoSeguroSalud"));
                detalle.setTieneSeguroDeVida(rs.getBoolean("TieneSeguroDeVida"));
                detalle.setTieneSeguroDeAccidentes(rs.getBoolean("TieneSeguroDeAccidentes"));
                detalle.setTieneAsignacionFamiliar(rs.getBoolean("TieneAsignacionFamiliar"));
                detalle.setDescripcion(rs.getString(DESCRIPCION));
                detalle.setEstado(rs.getBoolean(ESTADO));
                detalle.setFechaRegistro(rs.getTimestamp(FECHA_REGISTRO));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener detalle de contrato: " + e.getMessage());
        }

        return detalle;
    }
    
    
    public double obtenerSalarioBase(int idArea, int idEspecialidad, int idCargo, int idTipoContrato) {
        double salario = -1;

        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerSalarioBase(?, ?, ?, ?)}");
        ) {
            
            stmt.setInt(1, idArea);
            stmt.setInt(2, idEspecialidad);
            stmt.setInt(3, idCargo);
            stmt.setInt(4, idTipoContrato);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                salario = rs.getDouble("Monto");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener salario base: " + e.getMessage());
        }

        return salario;
    }
}