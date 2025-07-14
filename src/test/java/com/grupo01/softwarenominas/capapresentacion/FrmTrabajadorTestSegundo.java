/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion;

import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioListado;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioLlenado;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioRegistro;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioValidacion;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class FrmTrabajadorTestSegundo {
    @InjectMocks
    private FrmTrabajador frm;

    @Mock
    private TrabajadorNegocioListado negocioListado;

    @Mock
    private TrabajadorNegocioRegistro negocioRegistro;

    @Mock
    private TrabajadorNegocioValidacion negocioValidacion;

    @Mock
    private TrabajadorNegocioLlenado negocioTrabajadorLlenado;

    @Before
    public void setUp() {
        negocioListado = mock(TrabajadorNegocioListado.class);
        negocioRegistro = mock(TrabajadorNegocioRegistro.class);
        negocioValidacion = mock(TrabajadorNegocioValidacion.class);
        negocioTrabajadorLlenado = mock(TrabajadorNegocioLlenado.class);
        frm = new FrmTrabajador(negocioListado, negocioRegistro, negocioValidacion, negocioTrabajadorLlenado);

        // Simular componentes para el listener
        frm.setJhcHabilitarFechas(new JCheckBox());
        frm.setJdcFechaInicial(new JDateChooser());
        frm.setJdcFechaFinal(new JDateChooser());
        frm.setTableTrabajador(new JTable(new DefaultTableModel(new Object[][]{
            {"1", "Juan", "Perez", "M", "12345678"}
        }, new Object[]{"ID", "Nombre", "Apellido", "Sexo", "DNI"})));
        frm.setBtnRegistra(new JButton());
        frm.setBtnRegresar(new JButton());
    }

    @Test
    public void testConfigurarListeners_checkFechasValidas() {
        frm.getJhcHabilitarFechas().setSelected(true);

        Date fecha = new Date();
        frm.getJdcFechaInicial().setDate(fecha);
        frm.getJdcFechaFinal().setDate(fecha);

        frm.configurarListeners();

        // Simular clic en checkbox
        for (ActionListener al : frm.getJhcHabilitarFechas().getActionListeners()) {
            al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }

        verify(negocioListado).listarTrabajadoresFiltradoPorFecha(any(JTable.class), eq(fecha), eq(fecha));
    }

    @Test
    public void testConfigurarListeners_checkFechasInvalidas() {
        frm.getJhcHabilitarFechas().setSelected(true);

        // Una fecha es nula
        frm.getJdcFechaInicial().setDate(null);
        frm.getJdcFechaFinal().setDate(new Date());

        frm.configurarListeners();

        for (ActionListener al : frm.getJhcHabilitarFechas().getActionListeners()) {
            al.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }

        assertFalse("El checkbox debe desmarcarse si fechas inválidas", frm.getJhcHabilitarFechas().isSelected());
    }

    @Test
    public void testConfigurarListeners_mouseClickedEnTabla() {
        Trabajador mockTrabajador = new Trabajador();
        mockTrabajador.setDocumentoIdentidad("12345678");
        when(negocioTrabajadorLlenado.buscarPorDocumentoIdentidad("12345678")).thenReturn(mockTrabajador);

        frm.configurarListeners();

        // Selecciona fila simuladamente
        frm.getTableTrabajador().setRowSelectionInterval(0, 0);

        // Lanza evento mouse
        for (MouseListener ml : frm.getTableTrabajador().getMouseListeners()) {
            ml.mouseClicked(new MouseEvent(frm.getTableTrabajador(), MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false));
        }

        assertEquals(mockTrabajador, frm.getTrabajadorActual());
        assertTrue(frm.isModoEdicion());
        assertEquals("EDITAR", frm.getBtnRegistra().getText());
        assertEquals("ELIMINAR", frm.getBtnRegresar().getText());
    }
}
