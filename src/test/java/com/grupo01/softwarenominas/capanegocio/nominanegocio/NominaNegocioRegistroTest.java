/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.nominanegocio;

import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.ContratoPeriodo;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capaentidad.Nomina;
import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;
import com.grupo01.softwarenominas.capapersistencia.NominaDAO;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class NominaNegocioRegistroTest {
    private NominaDAO daoMock;
    private NominaNegocioRegistro negocio;
    
    @Before
    public void setUp() {
        daoMock = mock(NominaDAO.class);
        negocio = new NominaNegocioRegistro(daoMock);
    }

    @Test
    public void testProcesarNominaCompleta() {
        Contrato contrato = new Contrato();
        contrato.setSalarioBase(3000.0);
        contrato.setHorasTotales(160);
        contrato.setFechaInicio(new java.util.Date());
        TipoContrato tipo = new TipoContrato();
        tipo.setNombre("REGULAR");
        contrato.setTipoContrato(tipo);

        PeriodoPago periodo = new PeriodoPago();
        periodo.setFechaFin(new java.util.Date());

        ContratoPeriodo cp = new ContratoPeriodo();
        cp.setContrato(contrato);
        cp.setPeriodo(periodo);
        cp.setHorasTrabajadas(170);

        DetalleContrato detalleContrato = new DetalleContrato();
        detalleContrato.setTieneAsignacionFamiliar(true);
        detalleContrato.setTieneSeguroDeVida(true);
        detalleContrato.setTieneSeguroDeAccidentes(true);
        detalleContrato.setTipoSeguroSalud("ESSALUD");

        Nomina nomina = negocio.procesarNominaCompleta(cp, detalleContrato, "Transferencia", "ESSALUD");

        assertNotNull(nomina);
        assertEquals("Transferencia", nomina.getMetodoPago());
        assertNotNull(nomina.getDetalle());
        assertTrue(nomina.getSueldoNeto() > 0);
    }

    @Test
    public void testInsertarNominaCompleta() {
        Nomina nominaMock = new Nomina();
        ResultadoOperacion resultadoMock = new ResultadoOperacion(true, 1, "Éxito");

        when(daoMock.insertarNominaCompleta(nominaMock)).thenReturn(resultadoMock);

        ResultadoOperacion resultado = negocio.insertarNominaCompleta(nominaMock);

        assertEquals(true, resultado.isExito());
        assertEquals("Éxito", resultado.getMensaje());
        verify(daoMock).insertarNominaCompleta(nominaMock);
    }

}
