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
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioLlenado;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
public class UtilidadesFrmContratoTestTercero {

    private JLabel lblMensaje;
    private JTextArea txtDescripcion;

    @Before
    public void setUp() {
        lblMensaje = new JLabel();
        txtDescripcion = new JTextArea();
    }

    @Test
    public void testValidarRangoSalario_conNumeroInvalido() {
        String resultado = UtilidadesFrmContrato.validarRangoSalario("abc");
        assertEquals("Ingrese un número válido para salario.", resultado);
    }

    @Test
    public void testValidarRangoSalario_menorAMinimo() {
        String resultado = UtilidadesFrmContrato.validarRangoSalario("1000");
        assertEquals("El salario debe ser mayor al mínimo de 1025 soles y tener hasta 6 cifras.", resultado);
    }

    @Test
    public void testValidarRangoSalario_valido() {
        String resultado = UtilidadesFrmContrato.validarRangoSalario("2000");
        assertNull(resultado);
    }

    @Test
    public void testValidarDescripcion_conTextoInvalido() {
        txtDescripcion.setText("   ");
        boolean esValido = UtilidadesFrmContrato.validarDescripcion(lblMensaje, txtDescripcion);
        assertFalse(esValido);
        assertNotNull(lblMensaje.getText());
    }

    @Test
    public void testValidarDescripcion_valido() {
        txtDescripcion.setText("Contrato temporal para proyecto A");
        boolean esValido = UtilidadesFrmContrato.validarDescripcion(lblMensaje, txtDescripcion);
        assertTrue(esValido);
        assertEquals("", lblMensaje.getText());
    }

    @Test
    public void testValidarCombos_conPrimerItem() {
        JComboBox<String> combo = new JComboBox<>(new String[]{"--Seleccionar--", "Opción 1"});
        combo.setSelectedIndex(0);
        boolean valido = UtilidadesFrmContrato.validarCombos(combo);
        assertFalse(valido);
    }

    @Test
    public void testValidarCombos_valido() {
        JComboBox<String> combo = new JComboBox<>(new String[]{"--Seleccionar--", "Opción 1"});
        combo.setSelectedIndex(1);
        boolean valido = UtilidadesFrmContrato.validarCombos(combo);
        assertTrue(valido);
    }

    @Test
    public void testConfigurarFechaInicioYFin_fechaFinAntesDeInicio() {
        JDateChooser inicio = new JDateChooser();
        JDateChooser fin = new JDateChooser();
        JLabel mensaje = new JLabel();

        UtilidadesFrmContrato.configurarFechaInicioYFin(inicio, fin, mensaje);

        Date hoy = new Date();
        Date ayer = new Date(hoy.getTime() - 24 * 60 * 60 * 1000);

        // Primero setear la fecha final para que luego el listener se active correctamente
        fin.setDate(ayer);
        inicio.setDate(hoy);

        // Esperar un poco si se ejecuta sobre AWT
        try { Thread.sleep(50); } catch (InterruptedException ignored) {}

        assertNull(fin.getDate());
        assertEquals("Vuelva a escoger la Fecha Fin para esta Fecha Inicio elegida.", mensaje.getText());
    }

    @Test
    public void testValidarCombosMensaje_tipoContratoInvalido() {
        TipoContrato tipoContrato = new TipoContrato();
        tipoContrato.setNombre(ConstantesUIContrato.TEXTO_TIPO_CONTRATO_DEFAULT);

        Cargo cargo = new Cargo();
        cargo.setNombre("Cargo válido");
        Area area = new Area();
        area.setNombre("Área válida");
        Especialidad esp = new Especialidad();
        esp.setNombre("Especialidad válida");

        String mensaje = UtilidadesFrmContrato.validarCombosMensaje(tipoContrato, cargo, area, esp);
        assertEquals("Seleccionar un tipo de contrato.", mensaje);
    }

    @Test
    public void testValidarSalario_tipoServicioExterno_conError() {
        JComboBox<Object> tipoContrato = new JComboBox<>();
        tipoContrato.addItem("SERVICIO EXTERNO");
        tipoContrato.setSelectedIndex(0);

        JComboBox<Object> cmbCargo = new JComboBox<>();
        cmbCargo.addItem("Administrador");
        JComboBox<Object> cmbArea = new JComboBox<>();
        cmbArea.addItem("Contabilidad");
        JComboBox<Object> cmbEspecialidad = new JComboBox<>();
        cmbEspecialidad.addItem("Senior");

        JTextField txtSalario = new JTextField("abc");
        JLabel mensaje = new JLabel();

        boolean resultado = UtilidadesFrmContrato.validarSalario(tipoContrato, cmbCargo, cmbArea, cmbEspecialidad, txtSalario, mensaje);

        assertFalse(resultado);
        assertEquals("Ingrese un número válido para salario.", mensaje.getText());
    }

    @Test
    public void testValidarSalario_tipoRegular_completo() {
        TipoContrato tipo = new TipoContrato();
        tipo.setNombre("CONTRATO INDEFINIDO");
        Cargo cargo = new Cargo();
        cargo.setNombre("ADMINISTRADOR");
        Area area = new Area();
        area.setNombre("CONTABILIDAD");
        Especialidad especialidad = new Especialidad();
        especialidad.setNombre("CONTABILIDAD GENERAL");

        JComboBox<Object> cmbTipoContrato = new JComboBox<>(new Object[]{tipo});
        JComboBox<Object> cmbCargo = new JComboBox<>(new Object[]{cargo});
        JComboBox<Object> cmbArea = new JComboBox<>(new Object[]{area});
        JComboBox<Object> cmbEspecialidad = new JComboBox<>(new Object[]{especialidad});

        JTextField txtSalario = new JTextField("2000");
        JLabel lblMensaje = new JLabel();

        boolean resultado = UtilidadesFrmContrato.validarSalario(cmbTipoContrato, cmbCargo, cmbArea, cmbEspecialidad, txtSalario, lblMensaje);
        assertTrue(resultado);
    }

    @Test
    public void testConfigurarListenersActivadores_checkHabilitaFechas() {
        JCheckBox chkFechas = new JCheckBox();
        JDateChooser jdcInicio = new JDateChooser();
        JDateChooser jdcFin = new JDateChooser();
        JCheckBox chkSalud = new JCheckBox();
        JRadioButton essalud = new JRadioButton();
        JRadioButton eps = new JRadioButton();

        UtilidadesFrmContrato.configurarListenersActivadores(chkFechas, jdcInicio, jdcFin, chkSalud, essalud, eps);

        chkFechas.setSelected(true);
        for (ActionListener l : chkFechas.getActionListeners()) {
            l.actionPerformed(new ActionEvent(chkFechas, ActionEvent.ACTION_PERFORMED, ""));
        }

        assertTrue(jdcInicio.isEnabled());
        assertTrue(jdcFin.isEnabled());
    }

    @Test
    public void testConfigurarListenersCombobox() {
        UtilidadesFrmContrato util = new UtilidadesFrmContrato();
        ContratoNegocioLlenado mockLlenado = mock(ContratoNegocioLlenado.class);
        util.setNegocioContratoLlenado(mockLlenado);

        JComboBox<Area> cmbArea = new JComboBox<>();
        Area area = new Area();
        area.setIdArea(1);
        cmbArea.addItem(area);

        JComboBox<Especialidad> cmbEsp = new JComboBox<>();
        JComboBox<TipoContrato> cmbTipo = new JComboBox<>();
        JComboBox<Cargo> cmbCargo = new JComboBox<>();

        Runnable callback = mock(Runnable.class);

        util.configurarListenersCombobox(cmbArea, cmbEsp, cmbTipo, cmbCargo, callback);

        // Ahora sí disparamos el evento real
        cmbArea.setSelectedIndex(0);

        verify(mockLlenado).cargarEspecialidadesPorArea(cmbEsp, 1);
        verify(callback).run();
    }


    @Test
    public void testConfigurarListenersFechas_conSeleccion() {
        JDateChooser jdcInicio = new JDateChooser();
        JDateChooser jdcFin = new JDateChooser();
        JRadioButton rbtn3 = new JRadioButton();
        JRadioButton rbtn6 = new JRadioButton();
        JRadioButton rbtn12 = new JRadioButton();

        // Seleccionamos 6 meses como duración
        rbtn6.setSelected(true);

        // Aplicamos listeners
        UtilidadesFrmContrato.configurarListenersFechas(jdcInicio, jdcFin, rbtn3, rbtn6, rbtn12);

        // Simulamos una fecha de inicio (esto dispara el listener configurado)
        Date hoy = new Date();
        jdcInicio.setDate(hoy);

        // Esperamos un breve momento para asegurar la actualización de los componentes (opcional)
        try { Thread.sleep(50); } catch (InterruptedException ignored) {}

        assertNotNull(jdcFin.getDate()); // Debe haberse calculado la fecha final
        assertFalse(jdcFin.isEnabled()); // El campo debe estar deshabilitado
    }

    @Test
    public void testActualizarSalarioSiListo_devuelveResultado() {
        UtilidadesFrmContrato util = new UtilidadesFrmContrato();
        ContratoNegocioCalculo mockCalculo = mock(ContratoNegocioCalculo.class);
        util.setNegocioContratoCalculo(mockCalculo);

        TipoContrato tipo = new TipoContrato(); tipo.setNombre("CONTRATO INDEFINIDO");
        Cargo cargo = new Cargo();
        Area area = new Area();
        Especialidad especialidad = new Especialidad();

        JComboBox<Object> cmbTipoContrato = new JComboBox<>(new Object[]{"DEFAULT", tipo});
        JComboBox<Object> cmbCargo = new JComboBox<>(new Object[]{"DEFAULT", cargo});
        JComboBox<Object> cmbArea = new JComboBox<>(new Object[]{"DEFAULT", area});
        JComboBox<Object> cmbEspecialidad = new JComboBox<>(new Object[]{"DEFAULT", especialidad});

        // Seleccionar index 1, porque el index 0 representa el "DEFAULT" no válido
        cmbTipoContrato.setSelectedIndex(1);
        cmbCargo.setSelectedIndex(1);
        cmbArea.setSelectedIndex(1);
        cmbEspecialidad.setSelectedIndex(1);

        Resultado esperado = new Resultado("1000.0", true, "OK");
        when(mockCalculo.actualizarSalarioSiListo(tipo, cargo, area, especialidad)).thenReturn(esperado);

        Resultado resultado = util.actualizarSalarioSiListo(cmbTipoContrato, cmbCargo, cmbArea, cmbEspecialidad);

        assertNotNull(resultado);
        assertTrue(resultado.estado());
    }

}

