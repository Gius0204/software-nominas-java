/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.trabajadornegocio;

import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class TrabajadorNegocioLlenadoTest {
    private TrabajadorDAO daoMock;
    private TrabajadorNegocioLlenado negocio;

    @Before
    public void setUp() {
        daoMock = mock(TrabajadorDAO.class);
        negocio = new TrabajadorNegocioLlenado(daoMock);
    }

    @Test
    public void testBuscarPorDNI_retornaTrabajador() {
        String dni = "12345678";
        Trabajador esperado = new Trabajador();
        esperado.setDocumentoIdentidad(dni);

        when(daoMock.buscarPorDNI(dni)).thenReturn(esperado);

        Trabajador resultado = negocio.buscarPorDNI(dni);

        assertNotNull(resultado);
        assertEquals(dni, resultado.getDocumentoIdentidad());
        verify(daoMock).buscarPorDNI(dni);
    }

    @Test
    public void testBuscarPorDocumentoIdentidad_retornaTrabajador() {
        String documento = "87654321";
        Trabajador esperado = new Trabajador();
        esperado.setDocumentoIdentidad(documento);

        when(daoMock.buscarPorDocumentoIdentidad(documento)).thenReturn(esperado);

        Trabajador resultado = negocio.buscarPorDocumentoIdentidad(documento);

        assertNotNull(resultado);
        assertEquals(documento, resultado.getDocumentoIdentidad());
        verify(daoMock).buscarPorDocumentoIdentidad(documento);
    }

}
