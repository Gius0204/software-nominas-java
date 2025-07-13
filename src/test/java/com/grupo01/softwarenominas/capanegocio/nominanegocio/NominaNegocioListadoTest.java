/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.nominanegocio;

import javax.swing.JTable;
import org.junit.Test;

import com.grupo01.softwarenominas.capapersistencia.NominaDAO;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class NominaNegocioListadoTest {
    
    @Test
    public void testListarNominasPorPeriodo() {
        JTable tablaMock = new JTable();
        NominaDAO nominaDAOMock = mock(NominaDAO.class);
        when(nominaDAOMock.listarNominasPorPeriodo(tablaMock, 5)).thenReturn(3);

        NominaNegocioListado negocio = new NominaNegocioListado(nominaDAOMock);

        int resultado = negocio.listarNominasPorPeriodo(tablaMock, 5);

        assertEquals(3, resultado);
        verify(nominaDAOMock).listarNominasPorPeriodo(tablaMock, 5);
    } 
}
