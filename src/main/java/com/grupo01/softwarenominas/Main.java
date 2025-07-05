
package com.grupo01.softwarenominas;

import com.grupo01.softwarenominas.CapaPresentacion.frmMenu;

public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando aplicacion");
        
        frmMenu ventana = new frmMenu();
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true); 
    }
}