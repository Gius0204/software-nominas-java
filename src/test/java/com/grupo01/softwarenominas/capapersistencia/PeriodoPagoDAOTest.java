/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class PeriodoPagoDAOTest {
    
    private CConexion conexionMock;
    private Connection connectionMock;
    private CallableStatement stmtMock;
    private ResultSet rsMock;
    private PeriodoPagoDAO periodoDAO;

    @Before
    public void setUp() throws Exception {
        conexionMock = mock(CConexion.class);
        connectionMock = mock(Connection.class);
        stmtMock = mock(CallableStatement.class);
        rsMock = mock(ResultSet.class);
        
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        periodoDAO = new PeriodoPagoDAO(conexionMock);
    }

    @Test
    public void testObtenerPeriodoPorId_devuelvePeriodo() throws Exception {
        when(stmtMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt("IdPeriodo")).thenReturn(1);
        when(rsMock.getDate("FechaInicio")).thenReturn(Date.valueOf("2023-01-01"));
        when(rsMock.getDate("FechaFin")).thenReturn(Date.valueOf("2023-01-15"));
        when(rsMock.getString("Nombre")).thenReturn("Enero 2023");
        when(rsMock.getString("Descripcion")).thenReturn("Primera quincena");
        when(rsMock.getBoolean("Estado")).thenReturn(true);
        when(rsMock.getTimestamp("FechaRegistro")).thenReturn(Timestamp.valueOf("2023-01-01 10:00:00"));

        PeriodoPago periodo = periodoDAO.obtenerPeriodoPorId(1);

        assertNotNull(periodo);
        assertEquals("Enero 2023", periodo.getNombre());
        assertEquals("Primera quincena", periodo.getDescripcion());
    }
}
