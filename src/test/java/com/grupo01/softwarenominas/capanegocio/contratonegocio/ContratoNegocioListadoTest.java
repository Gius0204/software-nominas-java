/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

import java.util.Date;
import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;

import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class ContratoNegocioListadoTest {
    
  
    private ContratoDAO contratoDAOMock;
    private ContratoNegocioListado negocio;

    @Before
    public void setUp() {
        contratoDAOMock = mock(ContratoDAO.class);
        negocio = new ContratoNegocioListado(contratoDAOMock);
    }

    @Test
    public void testListarContratosPorPeriodo() {
        JTable tabla = new JTable();
        int idPeriodo = 5;

        when(contratoDAOMock.listarContratosPorPeriodo(tabla, idPeriodo)).thenReturn(10);

        int result = negocio.listarContratosPorPeriodo(tabla, idPeriodo);

        assertEquals(10, result);
        verify(contratoDAOMock).listarContratosPorPeriodo(tabla, idPeriodo);
    }

    @Test
    public void testListarContratosFiltrado() {
        JTable tabla = new JTable();
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        String documentoIdentidad = "12345678";
        String nombres = "Juan";

        when(contratoDAOMock.listarContratosFiltrado(tabla, fechaInicio, fechaFin, documentoIdentidad, nombres)).thenReturn(7);

        int result = negocio.listarContratosFiltrado(tabla, fechaInicio, fechaFin, documentoIdentidad, nombres);

        assertEquals(7, result);
        verify(contratoDAOMock).listarContratosFiltrado(tabla, fechaInicio, fechaFin, documentoIdentidad, nombres);
    }

    @Test
    public void testListarContratoPeriodosPorContrato() {
        JTable tabla = new JTable();
        int idContrato = 9;

        negocio.listarContratoPeriodosPorContrato(tabla, idContrato);

        verify(contratoDAOMock).listarContratoPeriodosPorContrato(tabla, idContrato);
    }
    
}
