/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion.validacionespresentacion;

import javax.swing.text.PlainDocument;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Usuario
 */

@RunWith(Parameterized.class)
public class FiltroSalarioTest {
    
    private final String input;
    private final String expected;

    public FiltroSalarioTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: insertar \"{0}\" => \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"123456", "123456"},         // válido entero
            {"123456.78", "123456.78"},   // válido decimal
            {"123456.789", ""},           // inválido, más de 2 decimales
            {"1234567", ""},              // inválido, más de 6 dígitos antes del punto
            {"abc123", ""}                // inválido, caracteres no numéricos
        });
    }

    @Test
    public void testFiltroSalarioInsertString() throws Exception {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new FiltroSalario());

        doc.insertString(0, input, null);

        assertEquals(expected, doc.getText(0, doc.getLength()));
    }

}
