/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class FrmTrabajadorTest {
    
    public FrmTrabajadorTest() {
    }

    /**
     * Test of listarTrabajadoresTabla method, of class FrmTrabajador.
     */
    @Test
    public void testListarTrabajadoresTabla() {
        System.out.println("listarTrabajadoresTabla");
        Date fechaInicio = null;
        Date fechaFin = null;
        FrmTrabajador instance = new FrmTrabajador();
        instance.listarTrabajadoresTabla(fechaInicio, fechaFin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class FrmTrabajador.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        FrmTrabajador.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
