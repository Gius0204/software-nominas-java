
package com.grupo01.softwarenominas.CapaConexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CConexion {
    String usuario = "user123";
    String contrasenia = "123";
    String bd="NominasDB";
    String ip="localhost";
    String puerto="1433"; 
    String cadena="jdbc:sqlserver://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerConexion(){
        String conexionUrl = "jdbc:sqlserver://"+ip+":"+puerto+";"
                    +"database="+bd+";"
                    +"user="+usuario+";"
                    +"password="+contrasenia+";"
                    +"encrypt=true;trustServerCertificate=true;";
        try{         
            Connection conectar = DriverManager.getConnection(conexionUrl);
            System.out.println("Se conectó correctamente a la base de datos");
            return conectar;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al conectar la base de datos. Error: "+e.toString());
            return null;
        }
    }
}