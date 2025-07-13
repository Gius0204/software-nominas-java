/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaentidad.Trabajador;
import java.util.Date;
import javax.swing.JTable;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class TrabajadorDAOTest {
    
    public TrabajadorDAOTest() {
    }

    /**
     * Test of listarTrabajadoresFiltrado method, of class TrabajadorDAO.
     */
    @Test
    public void testListarTrabajadoresFiltrado() {
        System.out.println("listarTrabajadoresFiltrado");
        JTable paramTablaTrabajadores = null;
        TrabajadorDAO instance = new TrabajadorDAO();
        int expResult = 0;
        int result = instance.listarTrabajadoresFiltrado(paramTablaTrabajadores);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarTrabajadoresFiltradoPorFecha method, of class TrabajadorDAO.
     */
    @Test
    public void testListarTrabajadoresFiltradoPorFecha() {
        System.out.println("listarTrabajadoresFiltradoPorFecha");
        JTable tabla = null;
        Date fechaInicio = null;
        Date fechaFin = null;
        TrabajadorDAO instance = new TrabajadorDAO();
        int expResult = 0;
        int result = instance.listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarTrabajador method, of class TrabajadorDAO.
     */
    @Test
    public void testRegistrarTrabajador() {
        System.out.println("registrarTrabajador");
        Trabajador t = null;
        TrabajadorDAO instance = new TrabajadorDAO();
        boolean expResult = false;
        boolean result = instance.registrarTrabajador(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarTrabajador method, of class TrabajadorDAO.
     */
    @Test
    public void testActualizarTrabajador() {
        System.out.println("actualizarTrabajador");
        Trabajador t = null;
        TrabajadorDAO instance = new TrabajadorDAO();
        boolean expResult = false;
        boolean result = instance.actualizarTrabajador(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorDNI method, of class TrabajadorDAO.
     */
    @Test
    public void testBuscarPorDNI() {
        System.out.println("buscarPorDNI");
        String dni = "";
        TrabajadorDAO instance = new TrabajadorDAO();
        Trabajador expResult = null;
        Trabajador result = instance.buscarPorDNI(dni);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPorDocumentoIdentidad method, of class TrabajadorDAO.
     */
    @Test
    public void testBuscarPorDocumentoIdentidad() {
        System.out.println("buscarPorDocumentoIdentidad");
        String documentoIdentidad = "";
        TrabajadorDAO instance = new TrabajadorDAO();
        Trabajador expResult = null;
        Trabajador result = instance.buscarPorDocumentoIdentidad(documentoIdentidad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarTrabajador method, of class TrabajadorDAO.
     */
    @Test
    public void testEliminarTrabajador() {
        System.out.println("eliminarTrabajador");
        int idTrabajador = 0;
        TrabajadorDAO instance = new TrabajadorDAO();
        boolean expResult = false;
        boolean result = instance.eliminarTrabajador(idTrabajador);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
