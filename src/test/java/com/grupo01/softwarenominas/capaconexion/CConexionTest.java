/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capaconexion;

import java.sql.Connection;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class CConexionTest {
    
    /**
     * Test of establecerConexion method, of class CConexion.
     */
    @Test
    public void testEstablecerConexion() {
        System.out.println("establecerConexion");
        CConexion instance = new CConexion();
        Connection result = instance.establecerConexion();
        assertNotNull("La conexión no debe ser nula", result);
    }
    
}
