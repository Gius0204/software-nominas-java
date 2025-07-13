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
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class TrabajadorNegocioValidacionTest {
    private TrabajadorDAO trabajadorDAOMock;
    private TrabajadorNegocioValidacion validador;
    private Trabajador trabajador;

    @Before
    public void setUp() {
        trabajadorDAOMock = mock(TrabajadorDAO.class);
        validador = new TrabajadorNegocioValidacion(trabajadorDAOMock);

        trabajador = new Trabajador();
        trabajador.setIdTrabajador(1);
        trabajador.setNombres("Juan");
        trabajador.setApellidoPaterno("Pérez");
        trabajador.setApellidoMaterno("García");
        trabajador.setTipoDocumento("DNI");
        trabajador.setDocumentoIdentidad("12345678");
        trabajador.setCorreo("juan@example.com");
        trabajador.setTelefono("912345678");
        trabajador.setSexo("Masculino");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -25);
        trabajador.setFechaNacimiento(cal.getTime());

        trabajador.setDireccion("Av. Ejemplo 123");
        trabajador.setDescripcion("Descripción breve.");
    }

    @Test
    public void testValidarTrabajador_valido() {
        when(trabajadorDAOMock.buscarPorDocumentoIdentidad("12345678")).thenReturn(null);

        String resultado = validador.validarTrabajador(trabajador);
        assertNull(resultado); // null significa válido
    }

    @Test
    public void testValidarTrabajador_documentoRepetido() {
        Trabajador existente = new Trabajador();
        existente.setIdTrabajador(99);

        when(trabajadorDAOMock.buscarPorDocumentoIdentidad("12345678")).thenReturn(existente);

        trabajador.setIdTrabajador(1);
        String resultado = validador.validarTrabajador(trabajador);

        assertEquals("Este documento de identidad ya está registrado.", resultado);
    }

    @Test
    public void testValidarTrabajador_nulo() {
        String resultado = validador.validarTrabajador(null);
        assertEquals("El trabajador no puede ser nulo.", resultado);
    }

    @Test
    public void testValidarTrabajador_nombreInvalido() {
        trabajador.setNombres("Juan123");
        String mensaje = validador.validarTrabajador(trabajador);
        assertTrue(mensaje.contains("Nombres solo debe contener"));
    }

    @Test
    public void testVerificarCamposObligatorios_completo() {
        String resultado = validador.verificarCamposObligatorios(trabajador);
        assertNull(resultado);
    }

    @Test
    public void testVerificarCamposObligatorios_faltanCampos() {
        trabajador.setNombres(null);
        String resultado = validador.verificarCamposObligatorios(trabajador);
        assertTrue(resultado.contains("Debe llenar todos los campos obligatorios"));
    }

    @Test
    public void testValidarTrabajador_correoInvalido() {
        trabajador.setCorreo("correo-invalido");
        String mensaje = validador.validarTrabajador(trabajador);
        assertTrue(mensaje.contains("correo electrónico no tiene un formato válido"));
    }

    @Test
    public void testValidarTrabajador_fechaFutura() {
        trabajador.setFechaNacimiento(new Date(System.currentTimeMillis() + 100000000L)); // fecha futura
        String mensaje = validador.validarTrabajador(trabajador);
        assertTrue(mensaje.contains("no puede ser futura"));
    }

    @Test
    public void testValidarTrabajador_menorDeEdad() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -16);
        trabajador.setFechaNacimiento(cal.getTime());

        String mensaje = validador.validarTrabajador(trabajador);
        assertTrue(mensaje.contains("mayor de 18 años"));
    }

    
}
