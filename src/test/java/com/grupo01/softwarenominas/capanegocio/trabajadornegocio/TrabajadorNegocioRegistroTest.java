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
public class TrabajadorNegocioRegistroTest {
    private TrabajadorDAO daoMock;
    private TrabajadorNegocioRegistro registro;

    @Before
    public void setUp() {
        daoMock = mock(TrabajadorDAO.class);
        registro = new TrabajadorNegocioRegistro(daoMock);
    }

    @Test
    public void testRegistrarTrabajador_devuelveTrueCuandoRegistroExitoso() {
        Trabajador trabajador = new Trabajador();
        when(daoMock.registrarTrabajador(trabajador)).thenReturn(true);

        boolean resultado = registro.registrarTrabajador(trabajador);

        assertTrue(resultado);
        verify(daoMock).registrarTrabajador(trabajador);
    }

    @Test
    public void testActualizarTrabajador_devuelveTrueCuandoActualiza() {
        Trabajador trabajador = new Trabajador();
        when(daoMock.actualizarTrabajador(trabajador)).thenReturn(true);

        boolean resultado = registro.actualizarTrabajador(trabajador);

        assertTrue(resultado);
        verify(daoMock).actualizarTrabajador(trabajador);
    }

    @Test
    public void testEliminarTrabajador_devuelveTrueCuandoElimina() {
        int id = 5;
        when(daoMock.eliminarTrabajador(id)).thenReturn(true);

        boolean resultado = registro.eliminarTrabajador(id);

        assertTrue(resultado);
        verify(daoMock).eliminarTrabajador(id);
    }

    @Test
    public void testEliminarTrabajador_devuelveFalseCuandoFalla() {
        int id = 999;
        when(daoMock.eliminarTrabajador(id)).thenReturn(false);

        boolean resultado = registro.eliminarTrabajador(id);

        assertFalse(resultado);
        verify(daoMock).eliminarTrabajador(id);
    }

    
}
