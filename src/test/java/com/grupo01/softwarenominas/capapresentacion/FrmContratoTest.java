/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion;

import java.util.Date;
import javax.swing.JTable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class FrmContratoTest {
    
    public FrmContratoTest() {
    }

    /**
     * Test of inicializarTablaContrato method, of class FrmContrato.
     */
    @Test
    public void testInicializarTablaContrato() {
        System.out.println("inicializarTablaContrato");
        FrmContrato instance = new FrmContrato();
        instance.inicializarTablaContrato();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validarHoras method, of class FrmContrato.
     */
    @Test
    public void testValidarHoras() {
        System.out.println("validarHoras");
        String horasStr = "";
        FrmContrato instance = new FrmContrato();
        boolean expResult = false;
        boolean result = instance.validarHoras(horasStr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarContratosTabla method, of class FrmContrato.
     */
    @Test
    public void testListarContratosTabla() {
        System.out.println("listarContratosTabla");
        JTable tabla = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        String documentoIdentidad = "";
        String nombres = "";
        FrmContrato instance = new FrmContrato();
        instance.listarContratosTabla(tabla, fechaInicio, fechaFin, documentoIdentidad, nombres);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class FrmContrato.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        FrmContrato.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
