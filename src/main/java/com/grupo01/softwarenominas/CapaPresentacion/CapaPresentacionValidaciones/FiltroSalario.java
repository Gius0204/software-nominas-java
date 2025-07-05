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

public class FiltroSalario extends DocumentFilter {
    private final double minimo = 1025.00;
    private final double maximo = 999999.99;

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        Document doc = fb.getDocument();
        String original = doc.getText(0, doc.getLength());

        // Simular el nuevo texto después del cambio
        StringBuilder nuevoTexto = new StringBuilder(original);
        nuevoTexto.replace(offset, offset + length, text);

        String textoFinal = nuevoTexto.toString();

        // Permitir vacío
        if (textoFinal.isEmpty()) {
            super.replace(fb, offset, length, text, attrs);
            return;
        }

        // Permitir solo formato válido: hasta 6 enteros, opcional punto, hasta 2 decimales
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



