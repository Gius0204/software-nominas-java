package com.grupo01.softwarenominas.capapersistencia;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.Trabajador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDTrabajador.APELLIDO_MATERNO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDTrabajador.APELLIDO_PATERNO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDTrabajador.CORREO_ELECTRONICO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDTrabajador.DOCUMENTO_IDENTIDAD;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDTrabajador.NOMBRES;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDTrabajador.TELEFONO;
import static com.grupo01.softwarenominas.capapersistencia.utils.ConstantesBDTrabajador.TIPO_DOCUMENTO;

@Getter
@Setter
@AllArgsConstructor
public class TrabajadorDAO {
    private final CConexion conexion;
    public TrabajadorDAO() {
        this.conexion = new CConexion();
    }

    private static final String[] COLUMNAS_DESEADAS = {
        NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO,
        TIPO_DOCUMENTO, DOCUMENTO_IDENTIDAD, TELEFONO, CORREO_ELECTRONICO
    };

    private void configurarColumnasTabla(DefaultTableModel modelo) {
        for (String col : COLUMNAS_DESEADAS) {
            modelo.addColumn(col);
        }
    }

    private Object[] mapearFila(ResultSet rs) throws SQLException {
        Object[] fila = new Object[COLUMNAS_DESEADAS.length];
        for (int i = 0; i < COLUMNAS_DESEADAS.length; i++) {
            fila[i] = rs.getObject(COLUMNAS_DESEADAS[i]);
        }
        return fila;
    }

    private Trabajador mapearTrabajador(ResultSet rs) throws SQLException {
        Trabajador trabajador = new Trabajador();
        trabajador.setIdTrabajador(rs.getInt("IdTrabajador"));
        trabajador.setNombres(rs.getString(NOMBRES));
        trabajador.setApellidoPaterno(rs.getString(APELLIDO_PATERNO));
        trabajador.setApellidoMaterno(rs.getString(APELLIDO_MATERNO));
        trabajador.setDocumentoIdentidad(rs.getString(DOCUMENTO_IDENTIDAD));
        trabajador.setTipoDocumento(rs.getString(TIPO_DOCUMENTO));
        trabajador.setTelefono(rs.getString(TELEFONO));
        trabajador.setCorreo(rs.getString(CORREO_ELECTRONICO));
        trabajador.setSexo(rs.getString("Sexo"));
        trabajador.setFechaNacimiento(rs.getDate("FechaNacimiento"));
        trabajador.setDireccion(rs.getString("Direccion"));
        trabajador.setDescripcion(rs.getString("Descripcion"));
        trabajador.setEstado(rs.getBoolean("Estado"));
        trabajador.setFechaRegistro(rs.getDate("FechaRegistro"));
        return trabajador;
    }

    private void setParamsTrabajador(CallableStatement stmt, Trabajador t, boolean incluirId) throws SQLException {
        int index = 1;
        if (incluirId) {
            stmt.setInt(index++, t.getIdTrabajador());
        }
        stmt.setString(index++, t.getNombres());
        stmt.setString(index++, t.getApellidoPaterno());
        stmt.setString(index++, t.getApellidoMaterno());
        stmt.setString(index++, t.getDocumentoIdentidad());
        stmt.setString(index++, t.getTipoDocumento());
        stmt.setString(index++, t.getTelefono());
        stmt.setString(index++, t.getCorreo());
        stmt.setString(index++, t.getSexo());
        stmt.setDate(index++, java.sql.Date.valueOf(
            t.getFechaNacimiento().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
        ));
        stmt.setString(index++, t.getDireccion());
        stmt.setString(index++, t.getDescripcion());
    }

    public int listarTrabajadoresFiltrado(JTable paramTablaTrabajadores) {
        DefaultTableModel modelo = new DefaultTableModel();
        paramTablaTrabajadores.setModel(modelo);
        configurarColumnasTabla(modelo);

        int totalResultados = 0;

        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTrabajadores}")) {

            boolean tieneResultados = stmt.execute();

            if (tieneResultados) {
                try (ResultSet rs = stmt.getResultSet()) {
                    while (rs.next()) {
                        modelo.addRow(mapearFila(rs));
                        totalResultados++;
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener trabajadores:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return totalResultados;
    }
    
    public int listarTrabajadoresFiltradoPorFecha(JTable tabla, Date fechaInicio, Date fechaFin) {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);
        configurarColumnasTabla(modelo);

        int totalResultados = 0;

        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTrabajadoresPorFechasRegistro(?, ?)}")) {

            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(mapearFila(rs));
                totalResultados++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener trabajadores por fecha:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return totalResultados;
    }

    public boolean registrarTrabajador(Trabajador t) {

        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_CrearTrabajador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            setParamsTrabajador(stmt, t, false);
            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public boolean actualizarTrabajador(Trabajador t) {

        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ActualizarTrabajador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {

            setParamsTrabajador(stmt, t, true);
            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar trabajador: " + e.getMessage());
            return false;
        }
    }

    
    public Trabajador buscarPorDNI(String dni) {
        Trabajador trabajador = null;
        CConexion conn = new CConexion();

        try (Connection cn = conn.establecerConexion();
            CallableStatement ps = cn.prepareCall("{call sp_BuscarTrabajadorPorDNI(?)}")) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                trabajador = mapearTrabajador(rs);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar trabajador: " + e.getMessage());
        }
        return trabajador;
    }
    
    public Trabajador buscarPorDocumentoIdentidad(String documentoIdentidad){
        Trabajador trabajador = null;

        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTrabajadoresPorDocumentoIdentidad(?)}")) {

            stmt.setString(1, documentoIdentidad);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                trabajador = mapearTrabajador(rs);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar trabajador: " + e.getMessage());
        }
        return trabajador;
    }
    
    
    public boolean eliminarTrabajador(int idTrabajador) {

        try (Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_EliminarTrabajador(?)}")) {

            stmt.setInt(1, idTrabajador);
            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar trabajador: " + e.getMessage());
            return false;
        }
    }
}