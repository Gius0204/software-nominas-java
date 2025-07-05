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

public class FiltroHoras extends DocumentFilter {
    
    public FiltroHoras(){
        
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        
        if (text.matches("\\d+") && fb.getDocument().getLength() - length + text.length() <= 3) {
            super.replace(fb, offset, length, text, attrs);
            try {
                int valor = Integer.parseInt(text);
                if (valor >= 80 && valor <= 200) {
                    super.replace(fb, offset, length, text, attrs);
                }
            } catch (NumberFormatException ignored) {}
        }
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        
        if (string.matches("\\d+") && fb.getDocument().getLength() + string.length() <= 3) {
            super.insertString(fb, offset, string, attr);
        }
    }
}


