/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.trabajadornegocio;

import java.util.Date;
import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;

import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class TrabajadorNegocioListadoTest {
    private TrabajadorDAO trabajadorDAOMock;
    private TrabajadorNegocioListado negocio;
    
    @Before
    public void setUp() {
        trabajadorDAOMock = mock(TrabajadorDAO.class);
        negocio = new TrabajadorNegocioListado(trabajadorDAOMock);
    }

    @Test
    public void testListarTrabajadoresFiltrado_devuelveCorrecto() {
        JTable tabla = new JTable();
        when(trabajadorDAOMock.listarTrabajadoresFiltrado(tabla)).thenReturn(5);

        int result = negocio.listarTrabajadoresFiltrado(tabla);
        assertEquals(5, result);

        verify(trabajadorDAOMock).listarTrabajadoresFiltrado(tabla);
    }

    @Test
    public void testListarTrabajadoresFiltradoPorFecha_devuelveCorrecto() {
        JTable tabla = new JTable();
        Date fechaInicio = new Date();
        Date fechaFin = new Date();

        when(trabajadorDAOMock.listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin)).thenReturn(3);

        int result = negocio.listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin);
        assertEquals(3, result);

        verify(trabajadorDAOMock).listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin);
    }

}
