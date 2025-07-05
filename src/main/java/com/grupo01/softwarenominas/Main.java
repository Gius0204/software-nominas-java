
package com.grupo01.softwarenominas;

import com.grupo01.softwarenominas.CapaPresentacion.frmMenu;

/**
 *
 * @author Usuario
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando aplicacion");
        
        //CConexion objetoConexion = new CConexion();
        
        //Connection conectar = objetoConexion.establecerConexion();
        
        frmMenu ventana = new frmMenu();
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true); 
    }
}