/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class PeriodoPagoDAOTest {
    
    public PeriodoPagoDAOTest() {
    }

    /**
     * Test of obtenerPeriodoPorId method, of class PeriodoPagoDAO.
     */
    @Test
    public void testObtenerPeriodoPorId() {
        System.out.println("obtenerPeriodoPorId");
        int idPeriodo = 0;
        PeriodoPagoDAO instance = new PeriodoPagoDAO();
        PeriodoPago expResult = null;
        PeriodoPago result = instance.obtenerPeriodoPorId(idPeriodo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
