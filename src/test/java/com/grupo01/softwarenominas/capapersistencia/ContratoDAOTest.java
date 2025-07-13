/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
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
    private ResultSet resultSetMock;
    private ContratoDAO contratoDAO;

    @Before
    public void setUp() throws Exception {
        conexionMock = mock(CConexion.class);
        connectionMock = mock(Connection.class);
        stmtMock = mock(CallableStatement.class);
        resultSetMock = mock(ResultSet.class);

        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        contratoDAO = new ContratoDAO(conexionMock);
    }

    @Test
    public void testRegistrarContrato_success() throws Exception {
        Contrato contrato = new Contrato();
        contrato.setIdTrabajador(1);
        contrato.setIdTipoContrato(2);
        contrato.setIdCargo(3);
        contrato.setFechaInicio(new java.util.Date());
        contrato.setFechaFin(new java.util.Date());
        contrato.setSalarioBase(2500.0);
        contrato.setHorasTotales(160);
        contrato.setDescripcion("Contrato prueba");
        contrato.setIdArea(4);
        contrato.setIdEspecialidad(5);

        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);
        doNothing().when(stmtMock).registerOutParameter(eq(11), anyInt());
        when(stmtMock.getInt(11)).thenReturn(123); // ID generado

        ResultadoOperacion resultado = contratoDAO.registrarContrato(contrato);

        assertTrue(resultado.isExito());
        assertEquals(123, resultado.getIdGenerado());
        assertEquals("Contrato registrado correctamente.", resultado.getMensaje());

        verify(stmtMock).execute();
    }

    @Test
    public void testActualizarContrato_success() throws Exception {
        Contrato contrato = new Contrato();
        contrato.setIdContrato(1);
        contrato.setIdTipoContrato(2);
        contrato.setIdCargo(3);
        contrato.setFechaInicio(new java.util.Date());
        contrato.setFechaFin(new java.util.Date());
        contrato.setSalarioBase(3000.0);
        contrato.setHorasTotales(200);
        contrato.setDescripcion("Actualizar");
        contrato.setIdArea(5);
        contrato.setIdEspecialidad(6);

        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        boolean actualizado = contratoDAO.actualizarContrato(contrato);
        assertTrue(actualizado);
        verify(stmtMock).execute();
    }

    @Test
    public void testObtenerContratoPorId_found() throws Exception {
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("IdContrato")).thenReturn(1);
        when(resultSetMock.getInt("IdTrabajador")).thenReturn(101);
        when(resultSetMock.getDate("FechaInicio")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(resultSetMock.getDate("FechaFin")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(resultSetMock.getDouble("SalarioBase")).thenReturn(2200.0);
        when(resultSetMock.getInt("HorasTotales")).thenReturn(160);
        when(resultSetMock.getString("Descripcion")).thenReturn("Contrato válido");
        when(resultSetMock.getInt("IdArea")).thenReturn(2);
        when(resultSetMock.getInt("IdEspecialidad")).thenReturn(3);
        when(resultSetMock.getInt("IdTipoContrato")).thenReturn(4);
        when(resultSetMock.getInt("IdCargo")).thenReturn(5);
        when(resultSetMock.getString("EstadoContrato")).thenReturn("ACTIVO");
        when(resultSetMock.getBoolean("Estado")).thenReturn(true);
        when(resultSetMock.getTimestamp("FechaRegistro")).thenReturn(new java.sql.Timestamp(System.currentTimeMillis()));

        Contrato contrato = contratoDAO.obtenerContratoPorId(1);
        assertNotNull(contrato);
        assertEquals(1, contrato.getIdContrato());
        assertEquals(101, contrato.getIdTrabajador());
        assertEquals(2200.0, contrato.getSalarioBase(), 0.01);
    }

    @Test
    public void testRegistrarContrato_sqlException() throws Exception {
        Contrato contrato = new Contrato();
        when(connectionMock.prepareCall(anyString())).thenThrow(new SQLException("Error DB"));
        ResultadoOperacion resultado = contratoDAO.registrarContrato(contrato);
        assertFalse(resultado.isExito());
        assertTrue(resultado.getMensaje().contains("Error DB"));
    }
}
