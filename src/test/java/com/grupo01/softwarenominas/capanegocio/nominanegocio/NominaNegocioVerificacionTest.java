/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.nominanegocio;

import org.junit.Test;

import com.grupo01.softwarenominas.capapersistencia.NominaDAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class NominaNegocioVerificacionTest {
    private NominaDAO daoMock;
    private NominaNegocioVerificacion negocio;

    @Before
    public void setUp() {
        daoMock = mock(NominaDAO.class);
        negocio = new NominaNegocioVerificacion(daoMock);
    }

    @Test
    public void testExistePeriodoAnteriorPendiente_devuelveTrue() throws SQLException {
        when(daoMock.existePeriodoAnteriorPendiente(1)).thenReturn(true);

        boolean result = negocio.existePeriodoAnteriorPendiente(1);
        assertTrue(result);
        verify(daoMock).existePeriodoAnteriorPendiente(1);
    }

    @Test
    public void testExistePeriodoAnteriorPendiente_devuelveFalseSiSQLException() throws SQLException {
        when(daoMock.existePeriodoAnteriorPendiente(99)).thenThrow(new SQLException("DB Error"));

        boolean result = negocio.existePeriodoAnteriorPendiente(99);
        assertFalse(result);
    }

    @Test
    public void testExistePeriodoAnteriorPendientePorContrato_true() throws SQLException {
        when(daoMock.existePeriodoAnteriorPendientePorContrato(10, 20)).thenReturn(true);

        boolean result = negocio.existePeriodoAnteriorPendientePorContrato(10, 20);
        assertTrue(result);
        verify(daoMock).existePeriodoAnteriorPendientePorContrato(10, 20);
    }

    @Test
    public void testExistePeriodoAnteriorPendientePorContrato_falsePorExcepcion() throws SQLException {
        when(daoMock.existePeriodoAnteriorPendientePorContrato(5, 8)).thenThrow(new SQLException("Error"));

        boolean result = negocio.existePeriodoAnteriorPendientePorContrato(5, 8);
        assertFalse(result);
    }

}
