/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaentidad.ContratoPeriodo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class ContratoPeriodoDAOTest {
    
    public ContratoPeriodoDAOTest() {
    }

    /**
     * Test of obtenerContratoPeriodo method, of class ContratoPeriodoDAO.
     */
    @Test
    public void testObtenerContratoPeriodo() {
        System.out.println("obtenerContratoPeriodo");
        int idContrato = 0;
        int idPeriodo = 0;
        ContratoPeriodoDAO instance = new ContratoPeriodoDAO();
        ContratoPeriodo expResult = null;
        ContratoPeriodo result = instance.obtenerContratoPeriodo(idContrato, idPeriodo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
