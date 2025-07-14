/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion.utils;

import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioCalculo;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioCalculo.Resultado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioListado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioLlenado;
import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;
import com.grupo01.softwarenominas.capapersistencia.ContratoPeriodoDAO;
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



/**
 *
 * @author Usuario
 */
public class UtilidadesFrmContratoTestSegundo {

    private ContratoDAO contratoDAOMock;
    private ContratoPeriodoDAO contratoPeriodoDAOMock;

    private UtilidadesFrmContrato utilidades;
    private ContratoNegocioListado mockListado;
    private ContratoNegocioCalculo mockCalculo;
    private ContratoNegocioLlenado mockLlenado;

    @Before
    public void setUp() {

        contratoDAOMock = mock(ContratoDAO.class);
        contratoPeriodoDAOMock = mock(ContratoPeriodoDAO.class);

        mockListado = new ContratoNegocioListado(contratoDAOMock);
        mockCalculo = mock(ContratoNegocioCalculo.class);
        mockLlenado = new ContratoNegocioLlenado(contratoDAOMock, contratoPeriodoDAOMock);

        utilidades = new UtilidadesFrmContrato(mockListado,mockLlenado, mockCalculo);

        
    }

    @Test
    public void testListarContratosTabla() {
        JTable tabla = new JTable();
        JLabel lblMensaje = new JLabel();

        when(mockListado.listarContratosFiltrado(any(), any(), any(), anyString(), anyString())).thenReturn(3);

        utilidades.listarContratosTabla(tabla, null, null, "12345678", "Juan", lblMensaje);

        assertTrue(lblMensaje.getText().contains("Se encontraron 3 contratos"));
    }

    @Test
    public void testBuscarTablaConFechasCorrectas() {
        JTable tabla = new JTable();
        JCheckBox habilitarFechas = new JCheckBox();
        habilitarFechas.setSelected(true);

        JDateChooser inicio = new JDateChooser();
        JDateChooser fin = new JDateChooser();
        JLabel lblMensaje = new JLabel();

        Calendar cal = Calendar.getInstance();
        Date fechaInicio = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date fechaFin = cal.getTime();

        inicio.setDate(fechaInicio);
        fin.setDate(fechaFin);

        when(mockListado.listarContratosFiltrado(any(), any(), any(), anyString(), anyString())).thenReturn(1);

        utilidades.buscarTabla("12345678", "Juan", tabla, habilitarFechas, inicio, fin, lblMensaje);

        assertTrue(lblMensaje.getText().contains("Se encontró 1 contrato") || lblMensaje.getText().isEmpty());
    }

    @Test
    public void testBuscarTablaConFechasInvalidas() {
        JTable tabla = new JTable();
        JCheckBox habilitarFechas = new JCheckBox();
        habilitarFechas.setSelected(true);

        JDateChooser inicio = new JDateChooser();
        JDateChooser fin = new JDateChooser();
        JLabel lblMensaje = new JLabel();

        Calendar cal = Calendar.getInstance();
        Date fechaFin = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date fechaInicio = cal.getTime(); // inicio > fin (caso inválido)

        inicio.setDate(fechaInicio);
        fin.setDate(fechaFin);

        utilidades.buscarTabla("12345678", "Juan", tabla, habilitarFechas, inicio, fin, lblMensaje);

        assertEquals("Vuelva a escoger la Fecha Fin para esta Fecha Inicio elegida.", lblMensaje.getText());
    }

    @Test
    public void testActualizarSalarioSiListoConCombosValidos() {
        JComboBox<TipoContrato> tipoContratoBox = new JComboBox<>();
        JComboBox<Cargo> cargoBox = new JComboBox<>();
        JComboBox<Area> areaBox = new JComboBox<>();
        JComboBox<Especialidad> especialidadBox = new JComboBox<>();

        TipoContrato tipo = new TipoContrato(0, "-- Seleccione --");
        Cargo cargo = new Cargo(0, "-- Seleccione --");
        Area area = new Area(0, "-- Seleccione --");
        Especialidad esp = new Especialidad(0, "-- Seleccione --");

        tipoContratoBox.addItem(tipo);
        cargoBox.addItem(cargo);
        areaBox.addItem(area);
        especialidadBox.addItem(esp);

        tipoContratoBox.addItem(new TipoContrato(1, "CAS"));
        cargoBox.addItem(new Cargo(1, "Ingeniero"));
        areaBox.addItem(new Area(1, "Producción"));
        especialidadBox.addItem(new Especialidad(1, "Agroindustria"));

        tipoContratoBox.setSelectedIndex(1);
        cargoBox.setSelectedIndex(1);
        areaBox.setSelectedIndex(1);
        especialidadBox.setSelectedIndex(1);

        Mockito.when(mockCalculo.actualizarSalarioSiListo(
                ArgumentMatchers.any(TipoContrato.class),
                ArgumentMatchers.any(Cargo.class),
                ArgumentMatchers.any(Area.class),
                ArgumentMatchers.any(Especialidad.class)))
            .thenReturn(new Resultado("1000.0", true, "Salario actualizado"));

        Resultado resultado = utilidades.actualizarSalarioSiListo(tipoContratoBox, cargoBox, areaBox, especialidadBox);

        assertNotNull(resultado);
        assertEquals("1000.0", resultado.salario());
        assertEquals("Salario actualizado", resultado.mensaje());
        assertTrue(resultado.estado());
    }

    @Test
    public void testActualizarSalarioSiListoConComboDefault() {

        JComboBox<TipoContrato> tipoContratoBox = new JComboBox<>();
        JComboBox<Cargo> cargoBox = new JComboBox<>();
        JComboBox<Area> areaBox = new JComboBox<>();
        JComboBox<Especialidad> especialidadBox = new JComboBox<>();

        TipoContrato tipo = new TipoContrato(0, "-- Seleccione --");
        Cargo cargo = new Cargo(0, "-- Seleccione --");
        Area area = new Area(0, "-- Seleccione --");
        Especialidad esp = new Especialidad(0, "-- Seleccione --");

        tipoContratoBox.addItem(tipo);
        cargoBox.addItem(cargo);
        areaBox.addItem(area);
        especialidadBox.addItem(esp);

        tipoContratoBox.setSelectedIndex(0);
        cargoBox.setSelectedIndex(0);
        areaBox.setSelectedIndex(0);
        especialidadBox.setSelectedIndex(0);

        Resultado resultado = utilidades.actualizarSalarioSiListo(tipoContratoBox, cargoBox, areaBox, especialidadBox);

        assertNull(resultado);
    }
}

