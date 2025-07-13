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
public class FiltroHorasTest {

    @Test
    public void testInsertStringDentroRango() throws Exception {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new FiltroHoras());

        doc.insertString(0, "100", null);
        assertEquals("100", doc.getText(0, doc.getLength()));
    }

    @Test
    public void testInsertStringFueraRango() throws Exception {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new FiltroHoras());

        doc.insertString(0, "300", null);
        assertEquals("300", doc.getText(0, doc.getLength()));  // insert no valida rango
    }

    @Test
    public void testReplaceDentroRango() throws Exception {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new FiltroHoras());

        doc.insertString(0, "100", null);  // Inserta algo antes
        doc.remove(0, doc.getLength());

        doc.insertString(0, "150", null);
        assertEquals("150", doc.getText(0, doc.getLength()));
    }

    @Test
    public void testReplaceFueraRango() throws Exception {
        PlainDocument doc = new PlainDocument();
        doc.setDocumentFilter(new FiltroHoras());

        doc.insertString(0, "100", null);
        doc.remove(0, doc.getLength());

        doc.insertString(0, "250", null);  // Fuera de rango, pero solo validado en replace
        assertEquals("250", doc.getText(0, doc.getLength()));  // No bloqueado en insert
    }

}
