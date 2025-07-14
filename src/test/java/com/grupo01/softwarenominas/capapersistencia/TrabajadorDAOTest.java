/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.Trabajador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class TrabajadorDAOTest {
    
    private CConexion conexionMock;
    private Connection connectionMock;
    private CallableStatement stmtMock;
    private ResultSet rsMock;
    private TrabajadorDAO trabajadorDAO;

    @Before
    public void setUp() throws Exception {
        conexionMock = mock(CConexion.class);
        connectionMock = mock(Connection.class);
        stmtMock = mock(CallableStatement.class);
        rsMock = mock(ResultSet.class);

        trabajadorDAO = new TrabajadorDAO(conexionMock);
    }

    @Test
    public void testListarTrabajadoresFiltrado_agregaFilasATabla() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerTrabajadores}")).thenReturn(stmtMock);
        when(stmtMock.execute()).thenReturn(true);
        when(stmtMock.getResultSet()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getObject(anyString())).thenReturn("dato");

        JTable tablaMock = mock(JTable.class);
        TableColumnModel columnModelMock = mock(TableColumnModel.class);
        when(tablaMock.getColumnModel()).thenReturn(columnModelMock);

        trabajadorDAO.listarTrabajadoresFiltrado(tablaMock);

        verify(tablaMock).setModel(any(DefaultTableModel.class));
    }

    @Test
    public void testListarTrabajadoresFiltradoPorFecha_agregaFilasATabla() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerTrabajadoresPorFechasRegistro(?, ?)}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getObject(anyString())).thenReturn("dato");

        JTable tablaMock = mock(JTable.class);
        TableColumnModel columnModelMock = mock(TableColumnModel.class);
        when(tablaMock.getColumnModel()).thenReturn(columnModelMock);

        trabajadorDAO.listarTrabajadoresFiltradoPorFecha(tablaMock, new Date(), new Date());

        verify(tablaMock).setModel(any(DefaultTableModel.class));
    }

    @Test
    public void testRegistrarTrabajador_trueAlInsertar() throws Exception {
        Trabajador trabajador = mock(Trabajador.class);

        // Simular valores válidos
        when(trabajador.getNombres()).thenReturn("Juan");
        when(trabajador.getApellidoPaterno()).thenReturn("Pérez");
        when(trabajador.getApellidoMaterno()).thenReturn("Gómez");
        when(trabajador.getDocumentoIdentidad()).thenReturn("12345678");
        when(trabajador.getTipoDocumento()).thenReturn("DNI");
        when(trabajador.getTelefono()).thenReturn("987654321");
        when(trabajador.getCorreo()).thenReturn("juan@example.com");
        when(trabajador.getSexo()).thenReturn("M");
        when(trabajador.getFechaNacimiento()).thenReturn(new java.util.Date());
        when(trabajador.getDireccion()).thenReturn("Av. Lima 123");
        when(trabajador.getDescripcion()).thenReturn("Descripción");

        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        boolean resultado = trabajadorDAO.registrarTrabajador(trabajador);

        assertTrue(resultado);
    }

    @Test
    public void testActualizarTrabajador_trueAlActualizar() throws Exception {
        Trabajador trabajador = mock(Trabajador.class);

        when(trabajador.getIdTrabajador()).thenReturn(1);
        when(trabajador.getNombres()).thenReturn("Ana");
        when(trabajador.getApellidoPaterno()).thenReturn("López");
        when(trabajador.getApellidoMaterno()).thenReturn("Martínez");
        when(trabajador.getDocumentoIdentidad()).thenReturn("87654321");
        when(trabajador.getTipoDocumento()).thenReturn("DNI");
        when(trabajador.getTelefono()).thenReturn("912345678");
        when(trabajador.getCorreo()).thenReturn("ana@example.com");
        when(trabajador.getSexo()).thenReturn("F");
        when(trabajador.getFechaNacimiento()).thenReturn(new java.util.Date());
        when(trabajador.getDireccion()).thenReturn("Av. Arequipa 456");
        when(trabajador.getDescripcion()).thenReturn("Otra descripción");

        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        boolean resultado = trabajadorDAO.actualizarTrabajador(trabajador);

        assertTrue(resultado);
    }

    @Test
    public void testBuscarPorDNI_retornaTrabajador() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_BuscarTrabajadorPorDNI(?)}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt("IdTrabajador")).thenReturn(1);
        when(rsMock.getString(anyString())).thenReturn("TextoMock");
        when(rsMock.getDate(anyString())).thenReturn(new java.sql.Date(new Date().getTime()));
        when(rsMock.getBoolean("Estado")).thenReturn(true);

        Trabajador resultado = trabajadorDAO.buscarPorDNI("12345678");

        assertNotNull(resultado);
    }

    @Test
    public void testBuscarPorDocumentoIdentidad_retornaTrabajador() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerTrabajadoresPorDocumentoIdentidad(?)}")).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt("IdTrabajador")).thenReturn(1);
        when(rsMock.getString(anyString())).thenReturn("TextoMock");
        when(rsMock.getDate(anyString())).thenReturn(new java.sql.Date(new Date().getTime()));
        when(rsMock.getBoolean("Estado")).thenReturn(true);

        Trabajador resultado = trabajadorDAO.buscarPorDocumentoIdentidad("87654321");

        assertNotNull(resultado);
    }

    @Test
    public void testEliminarTrabajador_trueAlEliminar() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_EliminarTrabajador(?)}")).thenReturn(stmtMock);

        boolean resultado = trabajadorDAO.eliminarTrabajador(1);

        assertTrue(resultado);
    }

}
