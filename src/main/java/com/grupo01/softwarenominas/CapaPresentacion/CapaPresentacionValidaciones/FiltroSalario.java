package com.grupo01.softwarenominas.CapaPresentacion.CapaPresentacionValidaciones;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class FiltroSalario extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        Document doc = fb.getDocument();
        String original = doc.getText(0, doc.getLength());

        StringBuilder nuevoTexto = new StringBuilder(original);
        nuevoTexto.replace(offset, offset + length, text);

        String textoFinal = nuevoTexto.toString();

        if (textoFinal.isEmpty()) {
            super.replace(fb, offset, length, text, attrs);
            return;
        }

        if (textoFinal.matches("\\d{0,6}(\\.\\d{0,2})?")) {
            super.replace(fb, offset, length, text, attrs);
            
        }
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        replace(fb, offset, 0, string, attr);
    }
}



