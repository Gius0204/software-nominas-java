/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaentidad.Nomina;
import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;
import javax.swing.JComboBox;
import javax.swing.JTable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class NominaDAOTest {
    
    public NominaDAOTest() {
    }

    /**
     * Test of cargarPeriodosPago method, of class NominaDAO.
     */
    @Test
    public void testCargarPeriodosPago() {
        System.out.println("cargarPeriodosPago");
        JComboBox<PeriodoPago> comboBoxPeriodo = null;
        NominaDAO instance = new NominaDAO();
        instance.cargarPeriodosPago(comboBoxPeriodo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existePeriodoAnteriorPendientePorContrato method, of class NominaDAO.
     */
    @Test
    public void testExistePeriodoAnteriorPendientePorContrato() throws Exception {
        System.out.println("existePeriodoAnteriorPendientePorContrato");
        int idContrato = 0;
        int idPeriodoActual = 0;
        NominaDAO instance = new NominaDAO();
        boolean expResult = false;
        boolean result = instance.existePeriodoAnteriorPendientePorContrato(idContrato, idPeriodoActual);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existePeriodoAnteriorPendiente method, of class NominaDAO.
     */
    @Test
    public void testExistePeriodoAnteriorPendiente() throws Exception {
        System.out.println("existePeriodoAnteriorPendiente");
        int idPeriodo = 0;
        NominaDAO instance = new NominaDAO();
        boolean expResult = false;
        boolean result = instance.existePeriodoAnteriorPendiente(idPeriodo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerTipoContratoPorId method, of class NominaDAO.
     */
    @Test
    public void testObtenerTipoContratoPorId() {
        System.out.println("obtenerTipoContratoPorId");
        int idTipoContrato = 0;
        NominaDAO instance = new NominaDAO();
        TipoContrato expResult = null;
        TipoContrato result = instance.obtenerTipoContratoPorId(idTipoContrato);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarNominaCompleta method, of class NominaDAO.
     */
    @Test
    public void testInsertarNominaCompleta() {
        System.out.println("insertarNominaCompleta");
        Nomina nomina = null;
        NominaDAO instance = new NominaDAO();
        ResultadoOperacion expResult = null;
        ResultadoOperacion result = instance.insertarNominaCompleta(nomina);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarNominasPorPeriodo method, of class NominaDAO.
     */
    @Test
    public void testListarNominasPorPeriodo() {
        System.out.println("listarNominasPorPeriodo");
        JTable tabla = null;
        int idPeriodo = 0;
        NominaDAO instance = new NominaDAO();
        int expResult = 0;
        int result = instance.listarNominasPorPeriodo(tabla, idPeriodo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
