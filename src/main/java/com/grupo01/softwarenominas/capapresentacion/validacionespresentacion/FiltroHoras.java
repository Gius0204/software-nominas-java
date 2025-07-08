package com.grupo01.softwarenominas.capapresentacion.validacionespresentacion;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class FiltroHoras extends DocumentFilter {
    

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
            } catch (NumberFormatException ignored) {
              JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido entre 80 y 200.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
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


