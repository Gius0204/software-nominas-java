package com.grupo01.softwarenominas.capapresentacion.validacionespresentacion;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FiltroNumerico extends DocumentFilter {
    private int max;

    public FiltroNumerico(int max) {
        this.max = max;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        String nuevoTexto = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()))
                .insert(offset, string)
                .toString();
        if (nuevoTexto.isEmpty() || (nuevoTexto.matches("\\d+") && nuevoTexto.length() <= max)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        StringBuilder builder = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        builder.replace(offset, offset + length, text);
        String nuevoTexto = builder.toString();

        if (nuevoTexto.isEmpty() || (nuevoTexto.matches("\\d+") && nuevoTexto.length() <= max)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}

