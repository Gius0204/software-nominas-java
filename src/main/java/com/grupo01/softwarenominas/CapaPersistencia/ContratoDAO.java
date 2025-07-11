package com.grupo01.softwarenominas.capapersistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
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

public class ContratoDAO {
    
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
                String nombre = rs.getString(NOMBRE);
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
                String nombre = rs.getString(NOMBRE);
                comboBoxEspecialidad.addItem(new Especialidad(id, idArea, nombre));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error cargando especialidades por área.");
        }
    }
    
    public ResultadoOperacion registrarContrato(Contrato c) {
        int idGenerado;
        String mensaje;

        try (
            Connection conn = new CConexion().establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_CrearContrato(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        ) {
            

            stmt.setInt(1, c.getIdTrabajador());
            stmt.setInt(2, c.getIdTipoContrato());
            stmt.setInt(3, c.getIdCargo());
            stmt.setDate(4, new java.sql.Date(c.getFechaInicio().getTime()));
            stmt.setDate(5, new java.sql.Date(c.getFechaFin().getTime()));
            stmt.setDouble(6, c.getSalarioBase());
            stmt.setInt(7, c.getHorasTotales());
            stmt.setString(8, c.getDescripcion());
            stmt.setInt(9, c.getIdArea());
            stmt.setInt(10, c.getIdEspecialidad());

            stmt.registerOutParameter(11, java.sql.Types.INTEGER);

            stmt.execute();

            idGenerado = stmt.getInt(11);

            if (idGenerado > 0) {
                mensaje = "Contrato registrado correctamente.";
                return new ResultadoOperacion(true, idGenerado, mensaje);
            } else {
                mensaje = "No se pudo registrar el contrato. Restricción de negocio.";
                return new ResultadoOperacion(false, -1, mensaje);
            }

        } catch (SQLException e) {
            mensaje = e.getMessage();
            return new ResultadoOperacion(false, -1, mensaje);
        }
    }

    public boolean actualizarContrato(Contrato c) {
        CConexion objetoConexion = new CConexion();
        

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ActualizarContrato(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        )
        {
            

            stmt.setInt(1, c.getIdContrato());
            stmt.setInt(2, c.getIdTipoContrato());
            stmt.setInt(3, c.getIdCargo());
            stmt.setDate(4, new java.sql.Date(c.getFechaInicio().getTime()));
            stmt.setDate(5, new java.sql.Date(c.getFechaFin().getTime()));
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
        CConexion objetoConexion = new CConexion();
        

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                String estado = (String) getValueAt(row, 5);
                return column == 4 && !estado.equalsIgnoreCase("PAGADO") && !estado.equalsIgnoreCase("CANCELADO");
            }
        };

        tabla.setModel(modelo);

        try (
            Connection conn = objetoConexion.establecerConexion();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT cp.IdContratoPeriodo, p.Nombre AS Periodo, p.FechaInicio, p.FechaFin, " +
                "cp.HorasTrabajadas, cp.EstadoPago " +
                "FROM ContratoPeriodo cp " +
                "INNER JOIN PeriodoPago p ON cp.IdPeriodo = p.IdPeriodo " +
                "WHERE cp.IdContrato = ? ORDER BY p.FechaInicio"
            );
        )
        {
            
            stmt.setInt(1, idContrato);
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

            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar Contrato Periodos: " + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void guardarHorasTrabajadasDesdeTabla(JTable tabla) {
        CConexion objetoConexion = new CConexion();
        

        try (
            Connection conn = objetoConexion.establecerConexion();
        )
        {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                String estado = tabla.getValueAt(i, 5).toString();
                if (estado.equalsIgnoreCase("PAGADO") || estado.equalsIgnoreCase("CANCELADO")) {
                    continue;
                }

                int idContratoPeriodo = Integer.parseInt(tabla.getValueAt(i, 0).toString());
                int horasTrabajadas = Integer.parseInt(tabla.getValueAt(i, 4).toString());

                try (
                    CallableStatement stmt = conn.prepareCall("{call sp_ActualizarHorasContratoPeriodo(?, ?)}");
                )
                {
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
    
    public int listarContratosFiltrado(JTable tabla, Date fechaInicio, Date fechaFin, String documentoIdentidad, String nombres) {
        CConexion objetoConexion = new CConexion();
        

        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        String[] columnasDeseadas = {
            FECHA_INICIO, FECHA_FIN , HORAS_TOTALES, DOCUMENTO_IDENTIDAD, NOMBRES, 
            APELLIDO_PATERNO, APELLIDO_MATERNO, AREA_NOMBRE, ESPECIALIDAD,
            TIPO_CONTRATO_NOMBRE, CARGO_NOMBRE
        };

        for (String col : columnasDeseadas) {
            modelo.addColumn(col);
        }

        int totalResultados = 0;

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerContratosFiltrados(?, ?, ?, ?)}");
        )
        {
            

            if (fechaInicio != null) {
                stmt.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            } else {
                stmt.setNull(1, Types.DATE);
            }

            if (fechaFin != null) {
                stmt.setDate(2, new java.sql.Date(fechaFin.getTime()));
            } else {
                stmt.setNull(2, Types.DATE);
            }

            if (!documentoIdentidad.isEmpty()) {
                stmt.setString(3, documentoIdentidad);
            } else {
                stmt.setNull(3, Types.VARCHAR);
            }

            if (!nombres.isEmpty()) {
                stmt.setString(4, nombres);
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }

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
            JOptionPane.showMessageDialog(null, "Error al filtrar contratos:\n" + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }

        return totalResultados;
    }
    
    public int listarContratosPorPeriodo(JTable tabla, int idPeriodo) {
        CConexion objetoConexion = new CConexion();
        

        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        String[] columnasDeseadas = {
            FECHA_INICIO, FECHA_FIN , HORAS_TOTALES, "HorasTrabajadas", "EstadoPago", DOCUMENTO_IDENTIDAD, NOMBRES,
            APELLIDO_PATERNO, APELLIDO_MATERNO, AREA_NOMBRE, ESPECIALIDAD,
            TIPO_CONTRATO_NOMBRE, CARGO_NOMBRE
        };

        for (String col : columnasDeseadas) {
            modelo.addColumn(col);
        }
        
        int totalResultados = 0;

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerContratosPorPeriodo(?)}");
        )
        {
            stmt.setInt(1, idPeriodo);

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
            JOptionPane.showMessageDialog(null, "Error al listar contratos por periodo:\n" + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }
        
        return totalResultados;
    }

    public void cargarTiposContrato(JComboBox<TipoContrato> comboBox) {
        comboBox.removeAllItems();
        comboBox.addItem(new TipoContrato(0, "-- Tipo de Contrato --", "", true, new Date()));

        CConexion objetoConexion = new CConexion();
        

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ListarTiposContrato}");
        )
        {
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comboBox.addItem(new TipoContrato(
                        rs.getInt(ID_TIPO_CONTRATO),
                        rs.getString(NOMBRE),
                        "", true, new Date()
                ));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tipos de contrato: " + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarCargos(JComboBox<Cargo> comboBox) {
        comboBox.removeAllItems();
        comboBox.addItem(new Cargo(0, "-- Cargo --", "", true, new Date()));

        CConexion objetoConexion = new CConexion();
        

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ListarCargos}");
        )
        {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comboBox.addItem(new Cargo(
                        rs.getInt(ID_CARGO),
                        rs.getString(NOMBRE),
                        "", true, new Date()
                ));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar cargos: " + e.getMessage(), ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ResultadoOperacion registrarDetalleContrato(DetalleContrato detalle) {
        String mensaje;

        try (Connection conn = new CConexion().establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_CrearDetalleContrato(?, ?, ?, ?, ?, ?)}");
        ) {
            

            stmt.setInt(1, detalle.getIdContrato());
            stmt.setString(2, detalle.getTipoSeguroSalud());
            stmt.setBoolean(3, detalle.isTieneSeguroDeVida());
            stmt.setBoolean(4, detalle.isTieneSeguroDeAccidentes());
            stmt.setBoolean(5, detalle.isTieneAsignacionFamiliar());
            stmt.setString(6, detalle.getDescripcion());

            stmt.execute();

            mensaje = "Detalle de contrato registrado correctamente.";
            return new ResultadoOperacion(true, detalle.getIdContrato(), mensaje);

        } catch (SQLException e) {
            mensaje = "Error al registrar detalle contrato: " + e.getMessage();
            return new ResultadoOperacion(false, -1, mensaje);
        }
    }

    public Contrato obtenerContratoPorDocumentoIdentidad(String documentoIdentidad) {
        Contrato contrato = null;

        CConexion objetoConexion = new CConexion();
        

        try (
            Connection conn = objetoConexion.establecerConexion();
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

        CConexion objetoConexion = new CConexion();
        
        try (
            Connection conn = objetoConexion.establecerConexion();
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
    
    public boolean actualizarDetalleContrato(DetalleContrato detalle) {
        CConexion objetoConexion = new CConexion();
        

        try (
            Connection conn = objetoConexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ActualizarDetalleContrato(?, ?, ?, ?, ?, ?)}");
        )
        {
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
    
    public double obtenerSalarioBase(int idArea, int idEspecialidad, int idCargo, int idTipoContrato) {
        double salario = -1;
        CConexion objetoConexion = new CConexion();

        try (Connection conn = objetoConexion.establecerConexion();
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
    
    public Contrato obtenerContratoPorId(int idContrato) {
        Contrato contrato = null;
        try (
            Connection conn = new CConexion().establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerContratoPorId(?)}");
        ) {
            
            stmt.setInt(1, idContrato);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contrato = new Contrato();
                contrato.setIdContrato(rs.getInt(ID_CONTRATO));
                contrato.setIdTrabajador(rs.getInt("IdTrabajador"));
                contrato.setFechaInicio(rs.getDate(FECHA_INICIO));
                contrato.setFechaFin(rs.getDate(FECHA_FIN ));
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
}