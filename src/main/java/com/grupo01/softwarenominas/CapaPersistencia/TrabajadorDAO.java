package com.grupo01.softwarenominas.CapaPersistencia;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.grupo01.softwarenominas.CapaConexion.CConexion;
import com.grupo01.softwarenominas.CapaEntidad.Area;
import com.grupo01.softwarenominas.CapaEntidad.Especialidad;
import com.grupo01.softwarenominas.CapaEntidad.Trabajador;
import static com.grupo01.softwarenominas.CapaPersistencia.Utils.ConstantesBDTrabajador.APELLIDO_MATERNO;
import static com.grupo01.softwarenominas.CapaPersistencia.Utils.ConstantesBDTrabajador.APELLIDO_PATERNO;
import static com.grupo01.softwarenominas.CapaPersistencia.Utils.ConstantesBDTrabajador.CORREO_ELECTRONICO;
import static com.grupo01.softwarenominas.CapaPersistencia.Utils.ConstantesBDTrabajador.DOCUMENTO_IDENTIDAD;
import static com.grupo01.softwarenominas.CapaPersistencia.Utils.ConstantesBDTrabajador.NOMBRES;
import static com.grupo01.softwarenominas.CapaPersistencia.Utils.ConstantesBDTrabajador.TELEFONO;
import static com.grupo01.softwarenominas.CapaPersistencia.Utils.ConstantesBDTrabajador.TIPO_DOCUMENTO;

public class TrabajadorDAO {

    public int listarTrabajadoresFiltrado(JTable paramTablaTrabajadores) {
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        paramTablaTrabajadores.setModel(modelo);

        String[] columnasDeseadas = {
            NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, 
            TIPO_DOCUMENTO, DOCUMENTO_IDENTIDAD, TELEFONO, CORREO_ELECTRONICO 
        };

        for (String col : columnasDeseadas) {
            modelo.addColumn(col);
        }
        int totalResultados = 0;
        try (Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTrabajadores}");
        ) {
            boolean tieneResultados = stmt.execute();

            if (tieneResultados) {
                try (ResultSet rs = stmt.getResultSet()) {
                    while (rs.next()) {
                        Object[] fila = new Object[columnasDeseadas.length];
                        for (int i = 0; i < columnasDeseadas.length; i++) {
                            fila[i] = rs.getObject(columnasDeseadas[i]);
                        }
                        modelo.addRow(fila);
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
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        String[] columnasDeseadas = {
            NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, 
            TIPO_DOCUMENTO, DOCUMENTO_IDENTIDAD, TELEFONO, CORREO_ELECTRONICO 
        };

        for (String col : columnasDeseadas) {
            modelo.addColumn(col);
        }
        int totalResultados = 0;

        try (Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTrabajadoresPorFechasRegistro(?, ?)}");
        ){
            
            stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[columnasDeseadas.length];
                for (int i = 0; i < columnasDeseadas.length; i++) {
                    fila[i] = rs.getObject(columnasDeseadas[i]);
                }
                modelo.addRow(fila);
                totalResultados++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener trabajadores por fecha:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return totalResultados;
    }

    public void cargarAreas(JComboBox<Area> comboBoxArea) {
        comboBoxArea.removeAllItems();
        comboBoxArea.addItem(new Area(0, "-- Area --", "", true, new Date()));
        CConexion objetoConexion = new CConexion();
        
        try (Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ListarAreas}");
        ) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IdArea");
                String nombre = rs.getString("Nombre");
                comboBoxArea.addItem(new Area(id, nombre));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error cargando áreas.");
        }
    }

    public void cargarEspecialidadesPorArea(JComboBox<Especialidad> comboBoxEspecialidad, int idArea) {
        comboBoxEspecialidad.removeAllItems();
        comboBoxEspecialidad.addItem(new Especialidad(0, "-- Especialidad --", "", true, new Date()));
        CConexion objetoConexion = new CConexion();
        
        try (Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ListarEspecialidadesPorArea(?)}");)
        {
            
            stmt.setInt(1, idArea);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IdEspecialidad");
                String nombre = rs.getString("Nombre");
                comboBoxEspecialidad.addItem(new Especialidad(id, idArea, nombre));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error cargando especialidades por área.");
        }
    }
    
    public boolean registrarTrabajador(Trabajador t) {
        CConexion objetoConexion = new CConexion();
        

        try(Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_CrearTrabajador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {
            stmt.setString(1, t.getNombres());
            stmt.setString(2, t.getApellidoPaterno());
            stmt.setString(3, t.getApellidoMaterno());
            stmt.setString(4, t.getDocumentoIdentidad());
            stmt.setString(5, t.getTipoDocumento());
            stmt.setString(6, t.getTelefono());
            stmt.setString(7, t.getCorreo());
            stmt.setString(8, t.getSexo());
            stmt.setDate(9, new java.sql.Date(t.getFechaNacimiento().getTime()));
            stmt.setString(10, t.getDireccion());
            stmt.setString(11, t.getDescripcion());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }

    public Trabajador buscarPorDNI(String dni) {
        Trabajador trabajador = null;
        CConexion conn = new CConexion();
        
        String sql = "SELECT * FROM Trabajador WHERE DocumentoIdentidad = ?";

        try (Connection cn = conn.establecerConexion();
            PreparedStatement ps = cn.prepareStatement(sql);
        ) {
            
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                trabajador = new Trabajador();
                trabajador.setIdTrabajador(rs.getInt("IdTrabajador"));
                trabajador.setNombres(rs.getString(NOMBRES));
                trabajador.setApellidoPaterno(rs.getString(APELLIDO_PATERNO));
                trabajador.setApellidoMaterno(rs.getString(APELLIDO_MATERNO));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar trabajador: " + e.getMessage());
        }
        return trabajador;
    }
    
    public Trabajador buscarPorDocumentoIdentidad(String documentoIdentidad){
        Trabajador trabajador = null;
        
        CConexion objetoConexion = new CConexion();
        
        try (Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerTrabajadoresPorDocumentoIdentidad(?)}");
        )
        {
            stmt.setString(1, documentoIdentidad);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                trabajador = new Trabajador();
                trabajador.setIdTrabajador(rs.getInt("IdTrabajador"));
                trabajador.setNombres(rs.getString(NOMBRES));
                trabajador.setApellidoPaterno(rs.getString(APELLIDO_PATERNO));
                trabajador.setApellidoMaterno(rs.getString(APELLIDO_MATERNO));
                trabajador.setDocumentoIdentidad(rs.getString(DOCUMENTO_IDENTIDAD));
                trabajador.setTipoDocumento(rs.getString(TIPO_DOCUMENTO));
                
                trabajador.setTelefono(rs.getString(TELEFONO));
                trabajador.setCorreo(rs.getString(CORREO_ELECTRONICO ));
                trabajador.setSexo(rs.getString("Sexo"));
                trabajador.setFechaNacimiento(rs.getDate("FechaNacimiento"));
                trabajador.setDireccion(rs.getString("Direccion"));
                trabajador.setDescripcion(rs.getString("Descripcion"));
                trabajador.setEstado(rs.getBoolean("Estado"));
                trabajador.setFechaRegistro(rs.getDate("FechaRegistro"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar trabajador: " + e.getMessage());
        }
        return trabajador;
    }
    
    public boolean actualizarTrabajador(Trabajador t) {
        CConexion objetoConexion = new CConexion();
        

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ActualizarTrabajador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        )
        {
            stmt.setInt(1, t.getIdTrabajador());
            stmt.setString(2, t.getNombres());
            stmt.setString(3, t.getApellidoPaterno());
            stmt.setString(4, t.getApellidoMaterno());
            stmt.setString(5, t.getDocumentoIdentidad());
            stmt.setString(6, t.getTipoDocumento());
            stmt.setString(7, t.getTelefono());
            stmt.setString(8, t.getCorreo());
            stmt.setString(9, t.getSexo());
            stmt.setDate(10, new java.sql.Date(t.getFechaNacimiento().getTime()));
            stmt.setString(11, t.getDireccion());
            stmt.setString(12, t.getDescripcion());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar trabajador: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarTrabajador(int idTrabajador) {
        CConexion objetoConexion = new CConexion();
        
        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_EliminarTrabajador(?)}");
        ){
            
            stmt.setInt(1, idTrabajador);

            stmt.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar trabajador: " + e.getMessage());
            return false;
        }
    }
}