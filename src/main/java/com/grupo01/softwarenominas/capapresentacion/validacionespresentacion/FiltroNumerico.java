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
        if (string.matches("\\d+") && fb.getDocument().getLength() + string.length() <= max) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text.matches("\\d+") && fb.getDocument().getLength() - length + text.length() <= max) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}

