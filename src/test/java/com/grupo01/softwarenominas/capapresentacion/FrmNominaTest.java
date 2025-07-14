/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion;

import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioListado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioLlenado;
import com.grupo01.softwarenominas.capanegocio.nominanegocio.NominaNegocioListado;
import com.grupo01.softwarenominas.capanegocio.nominanegocio.NominaNegocioLlenado;
import com.grupo01.softwarenominas.capanegocio.nominanegocio.NominaNegocioRegistro;
import com.grupo01.softwarenominas.capanegocio.nominanegocio.NominaNegocioVerificacion;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioLlenado;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.swing.JTable;

import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class FrmNominaTest {
    private FrmNomina frm;
    private TrabajadorNegocioLlenado mockTrabajadorLlenado;
    private ContratoNegocioListado mockContratoListado;
    private ContratoNegocioLlenado mockContratoLlenado;
    private NominaNegocioListado mockNominaListado;
    private NominaNegocioRegistro mockNominaRegistro;
    private NominaNegocioLlenado mockNominaLlenado;
    private NominaNegocioVerificacion mockNominaVerificacion;

    @Before
    public void setUp() {
        mockTrabajadorLlenado = mock(TrabajadorNegocioLlenado.class);
        mockContratoListado = mock(ContratoNegocioListado.class);
        mockContratoLlenado = mock(ContratoNegocioLlenado.class);
        mockNominaListado = mock(NominaNegocioListado.class);
        mockNominaRegistro = mock(NominaNegocioRegistro.class);
        mockNominaLlenado = mock(NominaNegocioLlenado.class);
        mockNominaVerificacion = mock(NominaNegocioVerificacion.class);

        frm = new FrmNomina(mockTrabajadorLlenado, mockContratoListado, mockContratoLlenado,
                mockNominaListado, mockNominaRegistro, mockNominaLlenado, mockNominaVerificacion);

        PeriodoPago periodo1 = new PeriodoPago();
        periodo1.setIdPeriodoPago(1);
        periodo1.setDescripcion("Periodo 1");

        PeriodoPago periodo2 = new PeriodoPago();
        periodo2.setIdPeriodoPago(2);
        periodo2.setDescripcion("Periodo 2");

        frm.getCmbPeriodoPago1().addItem(periodo1);
        frm.getCmbPeriodoPago1().addItem(periodo2);
        frm.getCmbPeriodoPago1().setSelectedIndex(1);

        frm.getCmbPeriodoPago2().addItem(periodo1);
        frm.getCmbPeriodoPago2().addItem(periodo2);
        frm.getCmbPeriodoPago2().setSelectedIndex(1);
    }

    @Test
    public void testListarContratosTabla() {
        reset(mockContratoListado);
        when(mockContratoListado.listarContratosPorPeriodo(any(), anyInt())).thenReturn(2);
        frm.getCmbPeriodoPago2().setSelectedIndex(1);
        frm.listarContratosTabla();

        verify(mockContratoListado, times(2)).listarContratosPorPeriodo(any(), anyInt());
    }

    @Test
    public void testInicializarTablaContrato() {
        frm.inicializarTablaContrato();
        assertEquals("Seleccione un Periodo antes, por favor", frm.getLblContratos().getText());
    }

    @Test
    public void testListarNominasTabla() {
        reset(mockContratoListado);
        when(mockNominaListado.listarNominasPorPeriodo(any(), anyInt())).thenReturn(1);
        frm.getCmbPeriodoPago1().setSelectedIndex(1);
        frm.listarNominasTabla();

        verify(mockNominaListado, times(4)).listarNominasPorPeriodo(any(), anyInt());
    }

    @Test
    public void testInicializarTablaNominas() {
        frm.inicializarTablaNominas();
        assertEquals("Seleccione un Periodo antes, por favor", frm.getLblNominas().getText());
    }

    @Test
    public void testMostrarMensaje() {
        frm.mostrarMensaje("Prueba de mensaje");
        assertEquals("Prueba de mensaje", frm.getLblProcesados().getText());
    }

    @Test
    public void testObtenerDocumentoIdentidadFila() {
        JTable tableMock = mock(JTable.class);
        frm.setTableContrato(tableMock);
        when(tableMock.getValueAt(0, 5)).thenReturn("12345678");

        String doc = frm.obtenerDocumentoIdentidadFila(0);
        assertEquals("12345678", doc);
    }

}
