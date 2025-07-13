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
public class FiltroNumericoTest {
    private final int maxLength;
    private final String input;
    private final String expected;

    public FiltroNumericoTest(int maxLength, String input, String expected) {
        this.maxLength = maxLength;
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: max={0}, input=\"{1}\", expected=\"{2}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {5, "12345", "12345"},   // válido, 5 dígitos
            {3, "1234", ""},         // excede límite
            {5, "abc", ""}           // no numérico
        });
    }

    @Test
    public void testFiltroNumericoInsertString() throws Exception {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new FiltroNumerico(maxLength));
        doc.insertString(0, input, null);
        assertEquals(expected, doc.getText(0, doc.getLength()));
    }
}
