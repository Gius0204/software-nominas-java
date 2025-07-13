/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class ContratoNegocioCalculoTest {
    
    private ContratoDAO contratoDAOMock;
    private ContratoNegocioCalculo instance;

    @Before
    public void setUp() {
        contratoDAOMock = mock(ContratoDAO.class);
        instance = new ContratoNegocioCalculo(contratoDAOMock);
    }

    @Test
    public void testCalcularFechaFinConMeses() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 1);
        Date fechaInicio = cal.getTime();

        Date resultado = instance.calcularFechaFin(fechaInicio, 2);

        cal.add(Calendar.MONTH, 2);
        assertEquals(cal.getTime(), resultado);
    }

    @Test
    public void testCalcularFechaFinConFechaNull() {
        assertNull(instance.calcularFechaFin(null, 2));
    }

    @Test
    public void testCalcularFechaFinConMesesNegativos() {
        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 1);
        Date fechaInicio = cal.getTime();

        assertNull(instance.calcularFechaFin(fechaInicio, -1));
    }

    @Test
    public void testActualizarSalarioSiListo_ConServicioExterno() {
        TipoContrato tipo = new TipoContrato(1, "SERVICIO EXTERNO");
        Cargo cargo = new Cargo(1, "Cargo");
        Area area = new Area(1, "Area");
        Especialidad especialidad = new Especialidad(1, 1, "Esp");

        ContratoNegocioCalculo.Resultado result = instance.actualizarSalarioSiListo(tipo, cargo, area, especialidad);

        assertTrue(result.estado());
        assertEquals("Ingrese el salario manualmente. No debe ser menor al sueldo mínimo.", result.mensaje());
        assertEquals("", result.salario());
    }

    @Test
    public void testActualizarSalarioSiListo_ConSalarioValido() {
        TipoContrato tipo = new TipoContrato(1, "FIJO");
        Cargo cargo = new Cargo(2, "Cargo");
        Area area = new Area(3, "Area");
        Especialidad especialidad = new Especialidad(4, 3, "Esp");

        String expResult = "1500.0";

        when(contratoDAOMock.obtenerSalarioBase(3, 4, 2, 1)).thenReturn(1500.0);

        ContratoNegocioCalculo.Resultado result = instance.actualizarSalarioSiListo(tipo, cargo, area, especialidad);

        assertFalse(result.estado());
        assertEquals("Se muestra el salario base del trabajador", result.mensaje());
        assertEquals(expResult, result.salario());
    }

    @Test
    public void testActualizarSalarioSiListo_SinSalarioConfigurado() {
        TipoContrato tipo = new TipoContrato(1, "FIJO");
        Cargo cargo = new Cargo(2, "Cargo");
        Area area = new Area(3, "Area");
        Especialidad especialidad = new Especialidad(4, 3, "Esp");

        when(contratoDAOMock.obtenerSalarioBase(3, 4, 2, 1)).thenReturn(-1.0);

        ContratoNegocioCalculo.Resultado result = instance.actualizarSalarioSiListo(tipo, cargo, area, especialidad);

        assertFalse(result.estado());
        assertEquals("No se encontró salario base para su elección.", result.mensaje());
        assertEquals("", result.salario());
    }

    @Test
    public void testActualizarSalarioSiListo_ParametrosNulos() {
        ContratoNegocioCalculo.Resultado result = instance.actualizarSalarioSiListo(null, null, null, null);

        assertFalse(result.estado());
        assertEquals("", result.mensaje());
        assertEquals("", result.salario());
    }
    
}
