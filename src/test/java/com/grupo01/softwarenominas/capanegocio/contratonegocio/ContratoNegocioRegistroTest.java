/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;
import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;

import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class ContratoNegocioRegistroTest {
    
    private ContratoDAO contratoDAOMock;
    private ContratoNegocioRegistro negocio;

    @Before
    public void setUp() {
        contratoDAOMock = mock(ContratoDAO.class);
        negocio = new ContratoNegocioRegistro(contratoDAOMock);
    }

    @Test
    public void testActualizarContratoMensaje_exito() {
        Contrato contrato = new Contrato();
        when(contratoDAOMock.actualizarContrato(contrato)).thenReturn(true);

        String resultado = negocio.actualizarContratoMensaje(contrato);

        assertEquals("Contrato actualizado correctamente.", resultado);
    }

    @Test
    public void testActualizarContratoMensaje_fallo() {
        Contrato contrato = new Contrato();
        when(contratoDAOMock.actualizarContrato(contrato)).thenReturn(false);

        String resultado = negocio.actualizarContratoMensaje(contrato);

        assertEquals("Fallo al actualizar el contrato.", resultado);
    }

    @Test
    public void testActualizarContratoMensaje_excepcion() {
        Contrato contrato = new Contrato();
        when(contratoDAOMock.actualizarContrato(contrato)).thenThrow(new RuntimeException("Simulado"));

        String resultado = negocio.actualizarContratoMensaje(contrato);

        assertTrue(resultado.startsWith("Error al actualizar contrato:"));
    }

    @Test
    public void testActualizarContrato() {
        Contrato contrato = new Contrato();
        when(contratoDAOMock.actualizarContrato(contrato)).thenReturn(true);

        boolean resultado = negocio.actualizarContrato(contrato);

        assertTrue(resultado);
    }

    @Test
    public void testActualizarDetalleContratoMensaje_exito() {
        DetalleContrato detalle = new DetalleContrato();
        when(contratoDAOMock.actualizarDetalleContrato(detalle)).thenReturn(true);

        String resultado = negocio.actualizarDetalleContratoMensaje(detalle);

        assertEquals("Detalle del contrato actualizado correctamente.", resultado);
    }

    @Test
    public void testActualizarDetalleContratoMensaje_fallo() {
        DetalleContrato detalle = new DetalleContrato();
        when(contratoDAOMock.actualizarDetalleContrato(detalle)).thenReturn(false);

        String resultado = negocio.actualizarDetalleContratoMensaje(detalle);

        assertEquals("Fallo al actualizar el detalle del contrato.", resultado);
    }

    @Test
    public void testActualizarDetalleContratoMensaje_excepcion() {
        DetalleContrato detalle = new DetalleContrato();
        when(contratoDAOMock.actualizarDetalleContrato(detalle)).thenThrow(new RuntimeException("Simulado"));

        String resultado = negocio.actualizarDetalleContratoMensaje(detalle);

        assertTrue(resultado.startsWith("Error al actualizar detalle del contrato:"));
    }

    @Test
    public void testActualizarDetalleContrato() {
        DetalleContrato detalle = new DetalleContrato();
        when(contratoDAOMock.actualizarDetalleContrato(detalle)).thenReturn(true);

        boolean resultado = negocio.actualizarDetalleContrato(detalle);

        assertTrue(resultado);
    }

    @Test
    public void testRegistrarContrato() {
        Contrato contrato = new Contrato();
        ResultadoOperacion esperado = new ResultadoOperacion(true, 123, "OK");

        when(contratoDAOMock.registrarContrato(contrato)).thenReturn(esperado);

        ResultadoOperacion resultado = negocio.registrarContrato(contrato);

        assertEquals(esperado, resultado);
    }

    @Test
    public void testRegistrarDetalleContrato() {
        DetalleContrato detalle = new DetalleContrato();
        ResultadoOperacion esperado = new ResultadoOperacion(true, 456, "OK");

        when(contratoDAOMock.registrarDetalleContrato(detalle)).thenReturn(esperado);

        ResultadoOperacion resultado = negocio.registrarDetalleContrato(detalle);

        assertEquals(esperado, resultado);
    }

    @Test
    public void testGuardarHorasTrabajadasDesdeTabla() {
        JTable tabla = new JTable();
        negocio.guardarHorasTrabajadasDesdeTabla(tabla);
        verify(contratoDAOMock).guardarHorasTrabajadasDesdeTabla(tabla);
    }
}
