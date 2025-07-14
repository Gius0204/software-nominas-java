/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion.utils;

import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class UtilidadesFrmContratoTest {
    
    private JLabel lblMensaje;
    private JPanel panel;

    @Before
    public void setUp() {
        lblMensaje = new JLabel();
        panel = new JPanel();
    }

    @Test
    public void testValidarHoras_Valid() {
        assertTrue(UtilidadesFrmContrato.validarHoras("100", lblMensaje));
    }

    @Test
    public void testValidarHoras_InvalidRange() {
        assertFalse(UtilidadesFrmContrato.validarHoras("50", lblMensaje));
        assertEquals("Las horas deben estar entre 80 y 200.", lblMensaje.getText());
    }

    @Test
    public void testValidarHoras_NotNumber() {
        assertFalse(UtilidadesFrmContrato.validarHoras("abc", lblMensaje));
        assertEquals("Ingrese un número válido para horas.", lblMensaje.getText());
    }

    @Test
    public void testValidarRangoSalario_Valid() {
        assertNull(UtilidadesFrmContrato.validarRangoSalario("1500"));
    }

    @Test
    public void testValidarRangoSalario_InvalidLow() {
        assertEquals("El salario debe ser mayor al mínimo de 1025 soles y tener hasta 6 cifras.",
                UtilidadesFrmContrato.validarRangoSalario("1000"));
    }

    @Test
    public void testValidarRangoSalario_InvalidHigh() {
        assertEquals("El salario debe ser mayor al mínimo de 1025 soles y tener hasta 6 cifras.",
                UtilidadesFrmContrato.validarRangoSalario("1000000"));
    }

    @Test
    public void testValidarDescripcion_Valid() {
        assertNull(UtilidadesFrmContrato.validarDescripcion("Contrato temporal 2024"));
    }

    @Test
    public void testValidarDescripcion_InvalidChars() {
        assertEquals("La descripción debe tener solo letras y números (máx. 250).",
                UtilidadesFrmContrato.validarDescripcion("Texto $$$# con símbolos"));
    }

    @Test
    public void testValidarDNI_Valid() {
        assertTrue(UtilidadesFrmContrato.validarDNI("12345678", lblMensaje));
    }

    @Test
    public void testValidarDNI_Invalid() {
        assertFalse(UtilidadesFrmContrato.validarDNI("abc", lblMensaje));
        assertEquals("El DNI debe tener entre 8 y 9 dígitos numéricos.", lblMensaje.getText());
    }

    @Test
    public void testMostrarMensaje() {
        UtilidadesFrmContrato.mostrarMensaje(lblMensaje, "Texto de prueba", Color.BLUE);
        assertEquals("<html><div style='text-align: center; width: 300px;'>Texto de prueba</div></html>", lblMensaje.getText());
        assertEquals(Color.BLUE, lblMensaje.getForeground());
    }

    @Test
    public void testResetUI() {
        Color labelColor = Color.BLACK;
        Color panelColor = Color.LIGHT_GRAY;
        UtilidadesFrmContrato.resetUI(lblMensaje, panel, labelColor, panelColor);
        assertEquals(labelColor, lblMensaje.getForeground());
        assertEquals(panelColor, panel.getBackground());
    }

    @Test
    public void testCalcularFechaFin_3Meses() {
        JDateChooser inicio = new JDateChooser();
        JDateChooser fin = new JDateChooser();
        JRadioButton r3 = new JRadioButton();
        JRadioButton r6 = new JRadioButton();
        JRadioButton r12 = new JRadioButton();

        Calendar cal = Calendar.getInstance();
        cal.set(2024, Calendar.JANUARY, 1);
        inicio.setDate(cal.getTime());
        r3.setSelected(true);

        UtilidadesFrmContrato.calcularFechaFin(inicio, fin, r3, r6, r12);

        cal.add(Calendar.MONTH, 3);
        assertEquals(cal.getTime(), fin.getDate());
    }

    @Test
    public void testLimpiarCampos() {
        JTextField txt = new JTextField("123");
        JComboBox<String> combo = new JComboBox<>(new String[]{"--", "opción"});
        combo.setSelectedIndex(1);
        JDateChooser date = new JDateChooser();
        date.setDate(new Date());
        JRadioButton radio = new JRadioButton();
        radio.setSelected(true);
        JCheckBox check = new JCheckBox();
        check.setSelected(true);
        JButton btn = new JButton();
        btn.setEnabled(true);

        UtilidadesFrmContrato.limpiarCampos(
                new JTextComponent[]{txt},
                new JComboBox[]{combo},
                new JDateChooser[]{date},
                new JRadioButton[]{radio},
                new JCheckBox[]{check},
                btn
        );

        assertEquals("", txt.getText());
        assertEquals(0, combo.getSelectedIndex());
        assertNull(date.getDate());
        assertFalse(radio.isSelected());
        assertFalse(check.isSelected());
        assertFalse(btn.isEnabled());
    }

    @Test
    public void testMapearFormularioAContrato() {
        Trabajador trabajador = new Trabajador();
        trabajador.setIdTrabajador(123);

        JComboBox<TipoContrato> tipoContratoBox = new JComboBox<>();
        JComboBox<Cargo> cargoBox = new JComboBox<>();
        JComboBox<Area> areaBox = new JComboBox<>();
        JComboBox<Especialidad> especialidadBox = new JComboBox<>();

        tipoContratoBox.addItem(new TipoContrato(1, "CAS"));
        cargoBox.addItem(new Cargo(1, "Ingeniero"));
        areaBox.addItem(new Area(1, "Producción"));
        especialidadBox.addItem(new Especialidad(1, "Industrial"));

        tipoContratoBox.setSelectedIndex(0);
        cargoBox.setSelectedIndex(0);
        areaBox.setSelectedIndex(0);
        especialidadBox.setSelectedIndex(0);

        JDateChooser jdcInicio = new JDateChooser();
        JDateChooser jdcFin = new JDateChooser();
        jdcInicio.setDate(new Date());
        jdcFin.setDate(new Date());

        JTextField txtSalario = new JTextField("1500.50");
        JTextField txtHoras = new JTextField("160");
        JTextArea jtxDescripcion = new JTextArea("Contrato temporal");

        Contrato contrato = UtilidadesFrmContrato.mapearFormularioAContrato(trabajador, tipoContratoBox, cargoBox, jdcInicio, jdcFin, txtSalario, txtHoras, jtxDescripcion, areaBox, especialidadBox);

        assertNotNull(contrato);
        assertEquals(123, contrato.getIdTrabajador());
        assertEquals(1500.50, contrato.getSalarioBase(), 0.01);
        assertEquals(160, contrato.getHorasTotales());
        assertEquals("Contrato temporal", contrato.getDescripcion());
    }

    @Test
    public void testMapearFormularioADetalleContrato() {
        JCheckBox jhcSeguroSalud = new JCheckBox();
        JRadioButton rtnESSALUD = new JRadioButton();
        JRadioButton rtnEPS = new JRadioButton();
        JCheckBox jcbSeguroVida = new JCheckBox();
        JCheckBox jcbAccidentes = new JCheckBox();
        JCheckBox jcbAsignacion = new JCheckBox();

        jhcSeguroSalud.setSelected(true);
        rtnESSALUD.setSelected(true);
        jcbSeguroVida.setSelected(true);
        jcbAccidentes.setSelected(true);
        jcbAsignacion.setSelected(false);

        DetalleContrato detalle = UtilidadesFrmContrato.mapearFormularioADetalleContrato(100, jhcSeguroSalud, rtnESSALUD, rtnEPS, jcbSeguroVida, jcbAccidentes, jcbAsignacion);

        assertNotNull(detalle);
        assertEquals(100, detalle.getIdContrato());
        assertEquals("ESSALUD", detalle.getTipoSeguroSalud());
        assertTrue(detalle.isTieneSeguroDeVida());
        assertTrue(detalle.isTieneSeguroDeAccidentes());
        assertFalse(detalle.isTieneAsignacionFamiliar());
    }

    @Test
    public void testConfigurarListenersDuracion() {
        JRadioButton btn3Meses = new JRadioButton();
        JRadioButton btn6Meses = new JRadioButton();
        JRadioButton btn1Anio = new JRadioButton();
        JTextField txtDNI = new JTextField();

        final boolean[] fechaCalculada = {false};
        final boolean[] trabajadorBuscado = {false};

        UtilidadesFrmContrato.configurarListenersDuracion(
            btn3Meses, btn6Meses, btn1Anio, txtDNI,
            () -> fechaCalculada[0] = true,
            () -> trabajadorBuscado[0] = true
        );

        btn3Meses.doClick();
        txtDNI.postActionEvent();

        assertTrue(fechaCalculada[0]);
        assertTrue(trabajadorBuscado[0]);
    }

    @Test
    public void testCrearFocusAdapterNoExcepcion() {
        JPanel paneltest = new JPanel();
        JLabel lbl = new JLabel();
        Runnable validator = () -> lbl.setText("OK");

        FocusAdapter adapter = UtilidadesFrmContrato.crearFocusAdapter(validator, lbl, paneltest);
        adapter.focusLost(null);

        assertEquals("OK", lbl.getText());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testCalcularFechaFin() {
        JDateChooser jdcInicio = new JDateChooser();
        JDateChooser jdcFin = new JDateChooser();

        JRadioButton btn3Meses = new JRadioButton();
        btn3Meses.setSelected(true);

        jdcInicio.setDate(new GregorianCalendar(2023, Calendar.JANUARY, 1).getTime());

        UtilidadesFrmContrato.calcularFechaFin(jdcInicio, jdcFin, btn3Meses, new JRadioButton(), new JRadioButton());

        Calendar expected = Calendar.getInstance();
        expected.setTime(jdcInicio.getDate());
        expected.add(Calendar.MONTH, 3);

        assertEquals(expected.get(Calendar.MONTH), jdcFin.getDate().getMonth());
    }

}
