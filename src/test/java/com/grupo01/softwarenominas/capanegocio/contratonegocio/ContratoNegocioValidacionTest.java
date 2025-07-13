/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class ContratoNegocioValidacionTest {

    private final ContratoNegocioValidacion validador = new ContratoNegocioValidacion();
    
    @Test
    public void testValidarDocumentoIdentidad_valido8digitos() {
        assertTrue(validador.validarDocumentoIdentidad("12345678"));
    }

    @Test
    public void testValidarDocumentoIdentidad_valido9digitos() {
        assertTrue(validador.validarDocumentoIdentidad("123456789"));
    }

    @Test
    public void testValidarDocumentoIdentidad_invalidoLetra() {
        assertFalse(validador.validarDocumentoIdentidad("1234ABCD"));
    }

    @Test
    public void testValidarDocumentoIdentidad_invalidoLongitud() {
        assertFalse(validador.validarDocumentoIdentidad("1234567"));
    }

    @Test
    public void testValidarHoras_validoLimiteInferior() {
        assertTrue(validador.validarHoras("80"));
    }

    @Test
    public void testValidarHoras_validoLimiteSuperior() {
        assertTrue(validador.validarHoras("200"));
    }

    @Test
    public void testValidarHoras_fueraDeRango() {
        assertFalse(validador.validarHoras("70"));
    }

    @Test
    public void testValidarHoras_noNumerico() {
        assertFalse(validador.validarHoras("abc"));
    }

    @Test
    public void testValidarDescripcion_valida() {
        assertTrue(validador.validarDescripcion("Descripcion válida 123"));
    }

    @Test
    public void testValidarDescripcion_invalidaCaracteres() {
        assertFalse(validador.validarDescripcion("Texto # inválido!"));
    }

    @Test
    public void testValidarDescripcion_superaLongitud() {
        String texto = "a".repeat(251);
        assertFalse(validador.validarDescripcion(texto));
    }

    @Test
    public void testValidarSalario_valido() {
        assertTrue(validador.validarSalario("1500"));
    }

    @Test
    public void testValidarSalario_menorMinimo() {
        assertFalse(validador.validarSalario("1000"));
    }

    @Test
    public void testValidarSalario_mayorMaximo() {
        assertFalse(validador.validarSalario("1000000"));
    }

    @Test
    public void testValidarSalario_formatoInvalido() {
        assertFalse(validador.validarSalario("mil"));
    }

    @Test
    public void testValidarSalario_conTipoServicioExterno_valido() {
        assertTrue(validador.validarSalario("SERVICIO EXTERNO", "1500"));
    }

    @Test
    public void testValidarSalario_conTipoServicioExterno_invalido() {
        assertFalse(validador.validarSalario("SERVICIO EXTERNO", "800"));
    }

    @Test
    public void testValidarSalario_otroTipoContrato() {
        assertTrue(validador.validarSalario("INDEFINIDO", "0"));
    }
}
