/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class ContratoDAOTest {
    
    private CConexion conexionMock;
    private Connection connectionMock;
    private CallableStatement stmtMock;
    private ResultSet rsMock;

    private ContratoDAO contratoDAO;

    @Before
    public void setUp(){
        conexionMock = mock(CConexion.class);
        connectionMock = mock(Connection.class);
        stmtMock = mock(CallableStatement.class);
        rsMock = mock(ResultSet.class);

        contratoDAO = new ContratoDAO(conexionMock);

    }

    @Test
    public void testCargarAreas_agregaElementosAlComboBox() throws Exception {
        // Simulación del flujo
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ListarAreas}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true, false); // una fila
        when(rsMock.getInt("IdArea")).thenReturn(1);
        when(rsMock.getString("Nombre")).thenReturn("Contabilidad");

        JComboBox<Area> combo = new JComboBox<>();

        contratoDAO.cargarAreas(combo);

        // Verifica
        assertEquals(2, combo.getItemCount());
        assertEquals("-- Area --", combo.getItemAt(0).getNombre());
        assertEquals("Contabilidad", combo.getItemAt(1).getNombre());
    }

    @Test
    public void testCargarEspecialidadesPorArea_agregaElementosAlComboBox() throws Exception {
        int idArea = 5;

        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ListarEspecialidadesPorArea(?)}")).thenReturn(stmtMock);

        // Para simular setInt(1, idArea)
        doNothing().when(stmtMock).setInt(1, idArea);

        when(stmtMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getInt("IdEspecialidad")).thenReturn(12);
        when(rsMock.getString("Nombre")).thenReturn("Contabilidad Tributaria");

        JComboBox<Especialidad> combo = new JComboBox<>();
        contratoDAO.cargarEspecialidadesPorArea(combo, idArea);

        assertEquals(2, combo.getItemCount());
        assertEquals("-- Especialidad --", combo.getItemAt(0).getNombre());
        assertEquals("Contabilidad Tributaria", combo.getItemAt(1).getNombre());
    }

    @Test
    public void testCargarTiposContrato_agregaElementosAlComboBox() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ListarTiposContrato}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getInt("IdTipoContrato")).thenReturn(1);
        when(rsMock.getString("Nombre")).thenReturn("Indeterminado");

        JComboBox<TipoContrato> combo = new JComboBox<>();
        contratoDAO.cargarTiposContrato(combo);

        assertEquals(2, combo.getItemCount());
        assertEquals("-- Tipo de Contrato --", combo.getItemAt(0).getNombre());
        assertEquals("Indeterminado", combo.getItemAt(1).getNombre());
    }

    @Test
    public void testCargarCargos_agregaElementosAlComboBox() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ListarCargos}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getInt("IdCargo")).thenReturn(1);
        when(rsMock.getString("Nombre")).thenReturn("Supervisor");

        JComboBox<Cargo> combo = new JComboBox<>();
        contratoDAO.cargarCargos(combo);

        assertEquals(2, combo.getItemCount());
        assertEquals("-- Cargo --", combo.getItemAt(0).getNombre());
        assertEquals("Supervisor", combo.getItemAt(1).getNombre());
    }


    @Test
    public void testRegistrarContrato_exito() throws Exception {
        Contrato contrato = new Contrato();
        contrato.setIdTrabajador(1);
        contrato.setIdTipoContrato(2);
        contrato.setIdCargo(3);
        contrato.setFechaInicio(new Date());
        contrato.setFechaFin(new Date());
        contrato.setSalarioBase(1500.0);
        contrato.setHorasTotales(160);
        contrato.setDescripcion("Contrato ejemplo");
        contrato.setIdArea(1);
        contrato.setIdEspecialidad(1);

        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        doNothing().when(stmtMock).setInt(anyInt(), anyInt());
        doNothing().when(stmtMock).setDouble(anyInt(), anyDouble());
        doNothing().when(stmtMock).setDate(anyInt(), any(java.sql.Date.class));
        doNothing().when(stmtMock).setString(anyInt(), anyString());
        doNothing().when(stmtMock).registerOutParameter(eq(11), anyInt());

        when(stmtMock.getInt(11)).thenReturn(10);

        ResultadoOperacion result = contratoDAO.registrarContrato(contrato);
        assertTrue(result.isExito());
        assertEquals(10, result.getIdGenerado());
    }

    @Test
    public void testActualizarContrato_exito() throws Exception {
        Contrato contrato = new Contrato();
        contrato.setIdContrato(1);
        contrato.setIdTipoContrato(2);
        contrato.setIdCargo(3);
        contrato.setFechaInicio(new Date());
        contrato.setFechaFin(new Date());
        contrato.setSalarioBase(1800.0);
        contrato.setHorasTotales(160);
        contrato.setDescripcion("Modificado");
        contrato.setIdArea(2);
        contrato.setIdEspecialidad(2);

        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        boolean result = contratoDAO.actualizarContrato(contrato);
        assertTrue(result);
    }

    @Test
    public void testListarContratoPeriodosPorContrato_agregaFilasATabla() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerContratoPeriodosPorContrato(?)}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        // Simular metadatos de la consulta
        ResultSetMetaData metaMock = mock(ResultSetMetaData.class);
        when(rsMock.getMetaData()).thenReturn(metaMock);
        when(metaMock.getColumnCount()).thenReturn(3);
        when(metaMock.getColumnLabel(1)).thenReturn("Col1");
        when(metaMock.getColumnLabel(2)).thenReturn("Col2");
        when(metaMock.getColumnLabel(3)).thenReturn("Col3");

        // Simular dos filas
        when(rsMock.next()).thenReturn(true, true, false);
        when(rsMock.getObject(anyInt())).thenReturn("dato");

        // Mocks para JTable
        JTable tablaMock = mock(JTable.class);
        javax.swing.table.TableColumnModel columnModelMock = mock(javax.swing.table.TableColumnModel.class);
        javax.swing.table.TableColumn columnMock = mock(javax.swing.table.TableColumn.class);
        when(tablaMock.getColumnModel()).thenReturn(columnModelMock);
        when(columnModelMock.getColumn(0)).thenReturn(columnMock);

        // Ejecutar método
        contratoDAO.listarContratoPeriodosPorContrato(tablaMock, 123);

        // Verificar que setModel fue llamado (lo cual implica que se creó el modelo con columnas)
        verify(tablaMock).setModel(any(DefaultTableModel.class));

        // Verificar que el modelo tenga al menos 3 columnas
        ArgumentCaptor<DefaultTableModel> captor = ArgumentCaptor.forClass(DefaultTableModel.class);
        verify(tablaMock).setModel(captor.capture());
        DefaultTableModel modeloReal = captor.getValue();

        assertEquals(3, modeloReal.getColumnCount());
        assertEquals(2, modeloReal.getRowCount()); // se agregaron dos filas

        // Opcional: verificar contenido
        assertEquals("dato", modeloReal.getValueAt(0, 0));
    }

    @Test
    public void testListarContratosFiltrado_devuelveCantidadFilas() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerContratosFiltrados(?, ?, ?, ?)}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);
        
        JTable tabla = new JTable();
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        String documento = "12345678";
        String nombres = "Carlos";

        when(rsMock.next()).thenReturn(true, true, false);
        when(rsMock.getObject(anyString())).thenReturn("dato");

        int total = contratoDAO.listarContratosFiltrado(tabla, fechaInicio, fechaFin, documento, nombres);

        assertEquals(2, total);
    }


    @Test
    public void testGuardarHorasTrabajadasDesdeTabla_actualizaHoras() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ActualizarHorasContratoPeriodo(?, ?)}")).thenReturn(stmtMock);

        JTable tabla = mock(JTable.class);
        when(tabla.getRowCount()).thenReturn(1);
        when(tabla.getValueAt(0, 5)).thenReturn("PENDIENTE");
        when(tabla.getValueAt(0, 0)).thenReturn("1");
        when(tabla.getValueAt(0, 4)).thenReturn("40");

        contratoDAO.guardarHorasTrabajadasDesdeTabla(tabla);

        verify(stmtMock).setInt(1, 1);
        verify(stmtMock).setInt(2, 40);
        verify(stmtMock).execute();
    }


    @Test
    public void testListarContratosPorPeriodo_devuelveCantidadFilas() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerContratosPorPeriodo(?)}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        JTable tabla = new JTable();

        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getObject(anyString())).thenReturn("dato");

        int total = contratoDAO.listarContratosPorPeriodo(tabla, 1);
        assertEquals(1, total);
    }



    @Test
    public void testRegistrarDetalleContrato_exito() throws Exception {
        DetalleContrato detalle = new DetalleContrato();
        detalle.setIdContrato(1);
        detalle.setTipoSeguroSalud("EPS");
        detalle.setTieneSeguroDeVida(true);
        detalle.setTieneSeguroDeAccidentes(false);
        detalle.setTieneAsignacionFamiliar(true);
        detalle.setDescripcion("Detalle test");

        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        ResultadoOperacion result = contratoDAO.registrarDetalleContrato(detalle);
        assertTrue(result.isExito());
        assertEquals(1, result.getIdGenerado());
    }

    @Test
    public void testActualizarDetalleContrato_exito() throws Exception {
        DetalleContrato detalle = new DetalleContrato();
        detalle.setIdDetalleContrato(1);
        detalle.setTipoSeguroSalud("ESSALUD");
        detalle.setTieneSeguroDeVida(true);
        detalle.setTieneSeguroDeAccidentes(true);
        detalle.setTieneAsignacionFamiliar(false);
        detalle.setDescripcion("Modificado");

        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        boolean result = contratoDAO.actualizarDetalleContrato(detalle);
        assertTrue(result);
    }

    @Test
    public void testObtenerSalarioBase_retornaValor() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true);
        when(rsMock.getDouble("Monto")).thenReturn(2500.50);

        double salario = contratoDAO.obtenerSalarioBase(1, 2, 3, 4);
        assertEquals(2500.50, salario, 0.01);
    }

    @Test
    public void testObtenerContratoPorId_devuelveContratoValido() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerContratoPorId(?)}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt(anyString())).thenReturn(1);
        when(rsMock.getString(anyString())).thenReturn("Test");
        when(rsMock.getBoolean(anyString())).thenReturn(true);
        when(rsMock.getDate(anyString())).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(rsMock.getTimestamp(anyString())).thenReturn(new java.sql.Timestamp(System.currentTimeMillis()));
        when(rsMock.getDouble(anyString())).thenReturn(1000.0);

        Contrato contrato = contratoDAO.obtenerContratoPorId(1);

        assertNotNull(contrato);
        assertEquals(1, contrato.getIdContrato());
    }


    @Test
    public void testObtenerContratoPorDocumentoIdentidad_devuelveContratoValido() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerContratoPorDocumentoIdentidad(?)}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt(anyString())).thenReturn(1);
        when(rsMock.getString(anyString())).thenReturn("Test");
        when(rsMock.getBoolean(anyString())).thenReturn(true);
        when(rsMock.getDate(anyString())).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(rsMock.getTimestamp(anyString())).thenReturn(new java.sql.Timestamp(System.currentTimeMillis()));
        when(rsMock.getDouble(anyString())).thenReturn(1200.0);

        Contrato contrato = contratoDAO.obtenerContratoPorDocumentoIdentidad("12345678");

        assertNotNull(contrato);
        assertEquals(1, contrato.getIdContrato());
    }


    @Test
    public void testObtenerDetalleContratoPorDocumentoIdentidad_devuelveDetalle() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerDetalleContratoPorDocumentoIdentidad(?)}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt(anyString())).thenReturn(1);
        when(rsMock.getString(anyString())).thenReturn("EPS");
        when(rsMock.getBoolean(anyString())).thenReturn(true);
        when(rsMock.getTimestamp(anyString())).thenReturn(new java.sql.Timestamp(System.currentTimeMillis()));

        DetalleContrato detalle = contratoDAO.obtenerDetalleContratoPorDocumentoIdentidad("12345678");

        assertNotNull(detalle);
        assertEquals(1, detalle.getIdDetalleContrato());
        assertEquals("EPS", detalle.getTipoSeguroSalud());
    }


    
}
