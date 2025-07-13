/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion.utils;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class UtilidadesTest {
    
    @Test
    public void testValidarSalario_valido() {
        assertNull(Utilidades.validarSalario("1500"));
    }

    @Test
    public void testValidarSalario_invalidoFormato() {
        assertEquals("Ingrese un número válido para salario.", Utilidades.validarSalario("abc"));
    }

    @Test
    public void testValidarTexto_valido() {
        assertTrue(Utilidades.validarTexto("Hola123", 10, "^[A-Za-z0-9]+$"));
    }

    @Test
    public void testValidarDNI_valido8() {
        assertTrue(Utilidades.validarDNI("12345678"));
    }

    @Test
    public void testValidarDNI_invalido() {
        assertFalse(Utilidades.validarDNI("12A45678"));
    }

    @Test
    public void testMensajeCantidad_plural() {
        String mensaje = Utilidades.mensajeCantidad(5, "registro", "registros");
        assertEquals("Se encontraron 5 registros.", mensaje);
    }

    @Test
    public void testConstruirMensajeHtmlLista() {
        List<String> lista = Arrays.asList("Carlos", "Luisa");
        String resultado = Utilidades.construirMensajeHtmlLista(lista);
        assertTrue(resultado.contains("Carlos"));
        assertTrue(resultado.contains("Luisa"));
        assertTrue(resultado.contains("<html>"));
    }
}
