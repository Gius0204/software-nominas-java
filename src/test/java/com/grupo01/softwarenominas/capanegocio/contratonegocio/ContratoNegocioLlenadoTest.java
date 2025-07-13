/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.ContratoPeriodo;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;
import com.grupo01.softwarenominas.capapersistencia.ContratoPeriodoDAO;

import javax.swing.JComboBox;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class ContratoNegocioLlenadoTest {

    private ContratoDAO contratoDAOMock;
    private ContratoPeriodoDAO contratoPeriodoDAOMock;
    private ContratoNegocioLlenado negocio;
    
    @Before
    public void setUp() {
        contratoDAOMock = mock(ContratoDAO.class);
        contratoPeriodoDAOMock = mock(ContratoPeriodoDAO.class);
        negocio = new ContratoNegocioLlenado(contratoDAOMock, contratoPeriodoDAOMock);
    }

    @Test
    public void testCargarAreas() {
        JComboBox<Area> comboBox = new JComboBox<>();
        negocio.cargarAreas(comboBox);
        verify(contratoDAOMock).cargarAreas(comboBox);
    }

    @Test
    public void testCargarEspecialidadesPorArea() {
        JComboBox<Especialidad> comboBox = new JComboBox<>();
        int idArea = 10;
        negocio.cargarEspecialidadesPorArea(comboBox, idArea);
        verify(contratoDAOMock).cargarEspecialidadesPorArea(comboBox, idArea);
    }

    @Test
    public void testCargarTiposContrato() {
        JComboBox<TipoContrato> comboBox = new JComboBox<>();
        negocio.cargarTiposContrato(comboBox);
        verify(contratoDAOMock).cargarTiposContrato(comboBox);
    }

    @Test
    public void testCargarCargos() {
        JComboBox<Cargo> comboBox = new JComboBox<>();
        negocio.cargarCargos(comboBox);
        verify(contratoDAOMock).cargarCargos(comboBox);
    }

    @Test
    public void testObtenerContratoPorDocumentoIdentidad() {
        String documento = "12345678";
        Contrato esperado = new Contrato();
        when(contratoDAOMock.obtenerContratoPorDocumentoIdentidad(documento)).thenReturn(esperado);

        Contrato resultado = negocio.obtenerContratoPorDocumentoIdentidad(documento);

        assertEquals(esperado, resultado);
        verify(contratoDAOMock).obtenerContratoPorDocumentoIdentidad(documento);
    }

    @Test
    public void testObtenerDetalleContratoPorDocumentoIdentidad() {
        String documento = "12345678";
        DetalleContrato esperado = new DetalleContrato();
        when(contratoDAOMock.obtenerDetalleContratoPorDocumentoIdentidad(documento)).thenReturn(esperado);

        DetalleContrato resultado = negocio.obtenerDetalleContratoPorDocumentoIdentidad(documento);

        assertEquals(esperado, resultado);
        verify(contratoDAOMock).obtenerDetalleContratoPorDocumentoIdentidad(documento);
    }

    @Test
    public void testObtenerContratoPeriodo() {
        int idContrato = 1;
        int idPeriodo = 2;
        ContratoPeriodo esperado = new ContratoPeriodo();
        when(contratoPeriodoDAOMock.obtenerContratoPeriodo(idContrato, idPeriodo)).thenReturn(esperado);

        ContratoPeriodo resultado = negocio.obtenerContratoPeriodo(idContrato, idPeriodo);

        assertEquals(esperado, resultado);
        verify(contratoPeriodoDAOMock).obtenerContratoPeriodo(idContrato, idPeriodo);
    }

}
