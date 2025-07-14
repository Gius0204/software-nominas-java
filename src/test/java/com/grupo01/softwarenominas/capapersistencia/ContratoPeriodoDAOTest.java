/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.ContratoPeriodo;
import com.grupo01.softwarenominas.capaentidad.PeriodoPago;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class ContratoPeriodoDAOTest {
    
    private CConexion conexionMock;
    private Connection connectionMock;
    private CallableStatement stmtMock;
    private ResultSet rsMock;

    private ContratoDAO contratoDAOMock;
    private PeriodoPagoDAO periodoDAOMock;

    private ContratoPeriodoDAO contratoPeriodoDAO;

    @Before
    public void setUp(){
        conexionMock = mock(CConexion.class);
        connectionMock = mock(Connection.class);
        stmtMock = mock(CallableStatement.class);
        rsMock = mock(ResultSet.class);

        contratoDAOMock = mock(ContratoDAO.class);
        periodoDAOMock = mock(PeriodoPagoDAO.class);

        contratoPeriodoDAO = new ContratoPeriodoDAO(conexionMock, contratoDAOMock, periodoDAOMock);
    }

    @Test
    public void testObtenerContratoPeriodo_devuelveContratoPeriodoValido() throws Exception {
        int idContrato = 1;
        int idPeriodo = 2;

        // Simular conexión y ejecución
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerContratoPeriodo(?, ?)}")).thenReturn(stmtMock);

        when(stmtMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true);

        // Simular datos del ResultSet
        when(rsMock.getInt("IdContratoPeriodo")).thenReturn(10);
        when(rsMock.getInt("HorasTrabajadas")).thenReturn(160);
        when(rsMock.getString("EstadoPago")).thenReturn("PAGADO");
        when(rsMock.getBoolean("Estado")).thenReturn(true);
        when(rsMock.getTimestamp("FechaRegistro")).thenReturn(new Timestamp(System.currentTimeMillis()));

        // Simular contrato y periodo desde sus DAOs
        Contrato contratoSimulado = new Contrato();
        contratoSimulado.setIdContrato(idContrato);

        PeriodoPago periodoSimulado = new PeriodoPago();
        periodoSimulado.setIdPeriodoPago(idPeriodo);

        when(contratoDAOMock.obtenerContratoPorId(idContrato)).thenReturn(contratoSimulado);
        when(periodoDAOMock.obtenerPeriodoPorId(idPeriodo)).thenReturn(periodoSimulado);

        // Ejecutar
        ContratoPeriodo resultado = contratoPeriodoDAO.obtenerContratoPeriodo(idContrato, idPeriodo);

        // Verificaciones
        assertNotNull(resultado);
        assertEquals(10, resultado.getIdContratoPeriodo());
        assertEquals(160, resultado.getHorasTrabajadas());
        assertEquals("PAGADO", resultado.getEstadoPago());
        assertTrue(resultado.isEstado());
        assertEquals(idContrato, resultado.getContrato().getIdContrato());
        assertEquals(idPeriodo, resultado.getPeriodo().getIdPeriodoPago());
    }

    
}
