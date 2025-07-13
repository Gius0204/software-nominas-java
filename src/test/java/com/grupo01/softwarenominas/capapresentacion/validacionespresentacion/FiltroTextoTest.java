/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion.validacionespresentacion;

import javax.swing.text.PlainDocument;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class FiltroTextoTest {
    
    @Test
    public void testInsertStringValido() throws Exception {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new FiltroTexto());

        doc.insertString(0, "Hola Mundo123", null);
        assertEquals("Hola Mundo123", doc.getText(0, doc.getLength()));
    }

    @Test
    public void testInsertStringInvalidoSimbolo() throws Exception {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new FiltroTexto());

        doc.insertString(0, "Texto!", null);  // contiene '!' no permitido
        assertEquals("", doc.getText(0, doc.getLength()));
    }

    @Test
    public void testInsertStringExcedeLongitud() throws Exception {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new FiltroTexto());

        String largo = "a".repeat(251); // Excede 250 caracteres
        doc.insertString(0, largo, null);
        assertEquals("", doc.getText(0, doc.getLength()));
    }

}
