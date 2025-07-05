/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo01.softwarenominas.CapaPresentacion.CapaPresentacionValidaciones;

/**
 *
 * @author Usuario
 */

import javax.swing.text.*;

public class FiltroDescripcion extends DocumentFilter {
    private static final int MAX = 250;

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String nuevoTexto = fb.getDocument().getText(0, fb.getDocument().getLength())
                .substring(0, offset) + text + fb.getDocument().getText(offset + length,
                fb.getDocument().getLength() - offset - length);

        if (nuevoTexto.length() <= MAX && nuevoTexto.matches("[a-zA-Z0-9\\s]*")) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        replace(fb, offset, 0, string, attr);
    }
}

