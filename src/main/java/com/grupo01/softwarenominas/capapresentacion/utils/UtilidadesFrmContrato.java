package com.grupo01.softwarenominas.capapresentacion.utils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;

import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioCalculo.Resultado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioCalculo;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioListado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioLlenado;
import com.grupo01.softwarenominas.capapresentacion.FrmMenu;
import com.grupo01.softwarenominas.capapresentacion.validacionespresentacion.FiltroSalario;
import com.toedter.calendar.JDateChooser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UtilidadesFrmContrato {
  
    private transient ContratoNegocioListado negocioContratoListado;
    private transient ContratoNegocioLlenado negocioContratoLlenado;
    private transient ContratoNegocioCalculo negocioContratoCalculo;

    public UtilidadesFrmContrato() {
        this.negocioContratoListado = new ContratoNegocioListado();
        this.negocioContratoLlenado = new ContratoNegocioLlenado();
        this.negocioContratoCalculo = new ContratoNegocioCalculo();
    }

    public static void configurarComponentesIniciales(JRadioButton rtnESSALUD, JCheckBox jhcHabilitarFechas,
                                                     JCheckBox jhcSeguroSalud, JButton btnRegistrar,
                                                     JButton btnEditarHorasTrabajadas, JDateChooser jdcFechaFin) {
        rtnESSALUD.setSelected(true);
        jhcHabilitarFechas.setSelected(true);
        jhcSeguroSalud.setSelected(true);
        btnRegistrar.setEnabled(false);
        btnEditarHorasTrabajadas.setEnabled(false);
        jdcFechaFin.setDate(null);
        jdcFechaFin.setEnabled(false);
    }

    public static void aplicarFiltros(JTextField txtDNI, JTextField txtDocumentoBuscar,
                                      JTextField txtHorasTotales, JTextField txtNombres,
                                      JTextField txtNombresBuscar, JTextArea jtxDescripcion,
                                      JTextField txtSalario) {
        Utilidades.aplicarFiltroNumerico(9, txtDNI, txtDocumentoBuscar);
        Utilidades.aplicarFiltroNumerico(3, txtHorasTotales);
        Utilidades.aplicarFiltroTextoGeneral(txtNombres, txtNombresBuscar, jtxDescripcion);
        ((AbstractDocument) txtSalario.getDocument()).setDocumentFilter(new FiltroSalario());
    }

    public static void configurarFechaInicioYFin(JDateChooser jdcFechaInicial, JDateChooser jdcFechaFinal, JLabel lblMensajeBuscar) {
        jdcFechaInicial.addPropertyChangeListener("date", evt -> {
            Date fechaInicio = jdcFechaInicial.getDate();
            Date fechaFin = jdcFechaFinal.getDate();

            if (fechaInicio != null && fechaFin != null && fechaFin.before(fechaInicio)) {
                jdcFechaFinal.setDate(null);
                lblMensajeBuscar.setText("Vuelva a escoger la Fecha Fin para esta Fecha Inicio elegida.");
            }
        });
    }

    public static boolean validarHoras(String horasTexto, JLabel lblMensaje) {
        try {
            int horas = Integer.parseInt(horasTexto.trim());
            if (horas < 80 || horas > 200) {
                lblMensaje.setText("Las horas deben estar entre 80 y 200.");
                return false;
            }
            return true;
        } catch (NumberFormatException ex) {
            lblMensaje.setText("Ingrese un número válido para horas.");
            return false;
        }
    }

    public static String validarRangoSalario(String salarioTexto) {
        try {
            double salario = Double.parseDouble(salarioTexto);
            if (salario < 1025 || salario >= 1_000_000) {
                return "El salario debe ser mayor al mínimo de 1025 soles y tener hasta 6 cifras.";
            }
        } catch (NumberFormatException ex) {
            return "Ingrese un número válido para salario.";
        }
        return null;
    }

    public static String validarDescripcion(String descripcion) {
        descripcion = descripcion.trim();
        if (descripcion.length() > 250 || !descripcion.matches("[a-zA-Z0-9\\s]*")) {
            return "La descripción debe tener solo letras y números (máx. 250).";
        }
        return null;
    }

    public static boolean validarDescripcion(JLabel lblMensaje, JTextArea jtxDescripcion) {
        String error = validarDescripcion(jtxDescripcion.getText());
        if (error != null) lblMensaje.setText(error);
        return error == null;
    }

    public static boolean validarDNI(String dni, JLabel lblMensaje) {
        if (!dni.matches("^\\d{8,9}$")) {
            lblMensaje.setText("El DNI debe tener entre 8 y 9 dígitos numéricos.");
            return false;
        }
        return true;
    }

    
    public static boolean validarSalario(JComboBox<?> cmbTipoContrato, JComboBox<?> cmbCargo, JComboBox<?> cmbArea, JComboBox<?> cmbEspecialidad, JTextField txtSalario, JLabel lblMensaje) {
        String tipo = cmbTipoContrato.getSelectedItem().toString();
        String salarioTexto = txtSalario.getText().trim();

        if (tipo.equalsIgnoreCase(ConstantesUIContrato.TEXTO_SERVICIO_EXTERNO)) {
            String error = UtilidadesFrmContrato.validarRangoSalario(salarioTexto);
            if (error != null) {
                lblMensaje.setText(error);
                return false;
            }
        } else {
            TipoContrato tipoContrato = (TipoContrato) cmbTipoContrato.getSelectedItem();
            Cargo cargo = (Cargo) cmbCargo.getSelectedItem();
            Area area = (Area) cmbArea.getSelectedItem();
            Especialidad especialidad = (Especialidad) cmbEspecialidad.getSelectedItem();

            String errorCombos = UtilidadesFrmContrato.validarCombosMensaje(tipoContrato, cargo, area, especialidad);
            if (errorCombos != null) {
                lblMensaje.setText(errorCombos);
                return false;
            }

            String errorSalario = UtilidadesFrmContrato.validarRangoSalario(salarioTexto);
            if (errorSalario != null) {
                lblMensaje.setText(errorSalario);
                return false;
            }
        }
        return true;
    }

    public static void limpiarCampos(JTextComponent[] camposTexto, JComboBox<?>[] combos, JDateChooser[] fechas,
                                      JRadioButton[] radios, JCheckBox[] checks, JButton btnRegistrar) {
        for (JTextComponent campo : camposTexto) {
            campo.setText("");
        }
        for (JComboBox<?> combo : combos) {
            combo.setSelectedIndex(0);
        }
        for (JDateChooser fecha : fechas) {
            fecha.setDate(null);
        }
        for (JRadioButton radio : radios) {
            radio.setSelected(false);
        }
        for (JCheckBox check : checks) {
            check.setSelected(false);
        }
        btnRegistrar.setEnabled(false);
    }

    public static boolean validarCombos(JComboBox<?>... combos) {
        for (JComboBox<?> combo : combos) {
            if (combo.getSelectedIndex() == 0) return false;
        }
        return true;
    }

    public static boolean validarFechas(JDateChooser inicio, JDateChooser fin) {
        return inicio.getDate() != null && fin.getDate() != null;
    }

    public static void mostrarMensaje(JLabel lblMensaje, String mensaje, Color color) {
        lblMensaje.setText("<html><div style='text-align: center; width: 300px;'>" + mensaje + "</div></html>");
        lblMensaje.setForeground(color);
    }

    public static void resetUI(JLabel lblMensaje, JPanel jpanelContenedor, Color labelColor, Color panelColor) {
        lblMensaje.setForeground(labelColor);
        jpanelContenedor.setBackground(panelColor);
    }

    public static Contrato mapearFormularioAContrato(Trabajador trabajadorActual, JComboBox<TipoContrato> cmbTipoContrato,
                                                     JComboBox<Cargo> cmbCargo, JDateChooser jdcFechaInicio,
                                                     JDateChooser jdcFechaFin, JTextField txtSalario,
                                                     JTextField txtHorasTotales, JTextArea jtxDescripcion,
                                                     JComboBox<Area> cmbArea, JComboBox<Especialidad> cmbEspecialidad) {
        Contrato contrato = new Contrato();
        contrato.setIdTrabajador(trabajadorActual.getIdTrabajador());
        contrato.setIdTipoContrato(cmbTipoContrato.getItemAt(cmbTipoContrato.getSelectedIndex()).getIdTipoContrato());
        contrato.setIdCargo(cmbCargo.getItemAt(cmbCargo.getSelectedIndex()).getIdCargo());
        contrato.setFechaInicio(jdcFechaInicio.getDate());
        contrato.setFechaFin(jdcFechaFin.getDate());
        contrato.setSalarioBase(Double.parseDouble(txtSalario.getText()));
        contrato.setHorasTotales(Integer.parseInt(txtHorasTotales.getText()));
        contrato.setDescripcion(jtxDescripcion.getText());
        contrato.setIdArea(cmbArea.getItemAt(cmbArea.getSelectedIndex()).getIdArea());
        contrato.setIdEspecialidad(cmbEspecialidad.getItemAt(cmbEspecialidad.getSelectedIndex()).getIdEspecialidad());
        return contrato;
    }

    public static DetalleContrato mapearFormularioADetalleContrato(int idContrato, JCheckBox jhcSeguroSalud,
                                                                   JRadioButton rtnESSALUD, JRadioButton rtnEPS,
                                                                   JCheckBox jcbSeguroVida, JCheckBox jcbSeguroAccidentes,
                                                                   JCheckBox jcbAsignacion) {
        DetalleContrato detalle = new DetalleContrato();
        detalle.setIdContrato(idContrato);

        String tipoSeguro = "NINGUNO";
        if (jhcSeguroSalud.isSelected()) {
            if (rtnESSALUD.isSelected()) {
                tipoSeguro = "ESSALUD";
            } else if (rtnEPS.isSelected()) {
                tipoSeguro = "EPS";
            }
        }
        detalle.setTipoSeguroSalud(tipoSeguro);
        detalle.setTieneSeguroDeVida(jcbSeguroVida.isSelected());
        detalle.setTieneSeguroDeAccidentes(jcbSeguroAccidentes.isSelected());
        detalle.setTieneAsignacionFamiliar(jcbAsignacion.isSelected());
        return detalle;
    }

    public static String validarCombosMensaje(TipoContrato tipoContrato, Cargo cargo, Area area, Especialidad especialidad) {
        if (tipoContrato.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_TIPO_CONTRATO_DEFAULT)) {
            return "Seleccionar un tipo de contrato.";
        }
        if (cargo.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_CARGO_DEFAULT)) {
            return "Seleccionar un cargo.";
        }
        if (area.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_AREA_DEFAULT)) {
            return "Seleccionar un área.";
        }
        if (especialidad.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_ESPECIALIDAD_DEFAULT)) {
            return "Seleccionar una especialidad.";
        }
        return null;
    }

    public void listarContratosTabla(JTable tabla, Date fechaInicio, Date fechaFin, String documentoIdentidad, String nombres, JLabel lblMensajeBuscar){
        int resultados = negocioContratoListado.listarContratosFiltrado(tabla, fechaInicio, fechaFin, documentoIdentidad, nombres);
        
        Utilidades.ajustarTabla(tabla);

        lblMensajeBuscar.setText(
            switch (resultados) {
                case 0 -> "No se encontraron contratos en la base de datos.";
                case 1 -> "Se encontró 1 contrato.";
                default -> "Se encontraron " + resultados + " contratos.";
            }
        );

    }

    public static void configurarListenersDuracion(JRadioButton rtn3meses, JRadioButton rtn6meses, JRadioButton rtn1anio, JTextField txtDNI, Runnable calcularFechaFin, Runnable buscarTrabajadorPorDNI) {
        ActionListener duracionListener = e -> calcularFechaFin.run();
        rtn3meses.addActionListener(duracionListener);
        rtn6meses.addActionListener(duracionListener);
        rtn1anio.addActionListener(duracionListener);
        txtDNI.addActionListener(e -> buscarTrabajadorPorDNI.run());
    }

    public static void configurarListenersActivadores(JCheckBox jhcHabilitarFechas, JDateChooser jdcFechaInicial, JDateChooser jdcFechaFinal,
                                                      JCheckBox jhcSeguroSalud, JRadioButton rtnESSALUD, JRadioButton rtnEPS) {
        jhcHabilitarFechas.addActionListener(e -> {
            boolean habilitar = jhcHabilitarFechas.isSelected();
            jdcFechaInicial.setEnabled(habilitar);
            jdcFechaFinal.setEnabled(habilitar);

            if (!habilitar) {
                jdcFechaInicial.setDate(null);
                jdcFechaFinal.setDate(null);
            }
        });

        jhcSeguroSalud.addActionListener(e -> {
            boolean habilitar = jhcSeguroSalud.isSelected();
            rtnESSALUD.setEnabled(habilitar);
            rtnEPS.setEnabled(habilitar);
        });
    }

    public void configurarListenersCombobox(JComboBox<Area> cmbArea,
                                                   JComboBox<Especialidad> cmbEspecialidad,
                                                   JComboBox<TipoContrato> cmbTipoContrato,
                                                   JComboBox<Cargo> cmbCargo,
                                                   Runnable actualizarSalarioCallback) {
        cmbArea.addActionListener(e -> {
            Area area = (Area) cmbArea.getSelectedItem();
            if (area != null) {
                negocioContratoLlenado.cargarEspecialidadesPorArea(cmbEspecialidad, area.getIdArea());
            }
            actualizarSalarioCallback.run();
        });

        cmbTipoContrato.addActionListener(e -> actualizarSalarioCallback.run());
        cmbCargo.addActionListener(e -> actualizarSalarioCallback.run());
        cmbEspecialidad.addActionListener(e -> actualizarSalarioCallback.run());
    }

    public static void configurarListenersFechas(JDateChooser jdcFechaInicio, JDateChooser jdcFechaFin,
                                                 JRadioButton rtn3meses, JRadioButton rtn6meses, JRadioButton rtn1anio) {
        jdcFechaInicio.getJCalendar().setMinSelectableDate(new java.util.Date());

        jdcFechaInicio.getDateEditor().addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                if (jdcFechaInicio.getDate() == null || (!rtn3meses.isSelected() && !rtn6meses.isSelected() && !rtn1anio.isSelected())) {
                    jdcFechaFin.setDate(null);
                } else {
                    calcularFechaFin(jdcFechaInicio, jdcFechaFin, rtn3meses, rtn6meses, rtn1anio);
                }
                jdcFechaFin.setEnabled(false);
            }
        });
    }

    public static void configurarFocusListeners(JTextField txtDNI, JTextField txtHorasTotales, JTextArea jtxDescripcion, JTextField txtSalario,
                                                JComboBox<?> cmbTipoContrato, JComboBox<?> cmbCargo, JComboBox<?> cmbArea, JComboBox<?> cmbEspecialidad,
                                                JLabel lblMensaje, JPanel jpanelContenedor) {
        txtDNI.addFocusListener(crearFocusAdapter(() -> validarDNI(txtDNI.getText(), lblMensaje), lblMensaje, jpanelContenedor));
        txtHorasTotales.addFocusListener(crearFocusAdapter(() -> validarHoras(txtHorasTotales.getText(), lblMensaje), lblMensaje, jpanelContenedor));
        jtxDescripcion.addFocusListener(crearFocusAdapter(() -> validarDescripcion(lblMensaje, jtxDescripcion), lblMensaje, jpanelContenedor));
        txtSalario.addFocusListener(crearFocusAdapter(() -> validarSalario(cmbTipoContrato, cmbCargo, cmbArea, cmbEspecialidad, txtSalario, lblMensaje), lblMensaje, jpanelContenedor));
        cmbTipoContrato.addFocusListener(crearFocusAdapter(() -> validarComboTipoContrato(cmbTipoContrato, lblMensaje), lblMensaje, jpanelContenedor));
        cmbCargo.addFocusListener(crearFocusAdapter(() -> validarComboCargo(cmbCargo, lblMensaje), lblMensaje, jpanelContenedor));
        cmbArea.addFocusListener(crearFocusAdapter(() -> validarComboArea(cmbArea, lblMensaje), lblMensaje, jpanelContenedor));
        cmbEspecialidad.addFocusListener(crearFocusAdapter(() -> validarComboEspecialidad(cmbEspecialidad, lblMensaje), lblMensaje, jpanelContenedor));
    }

    public static FocusAdapter crearFocusAdapter(Runnable validador, JLabel lblMensaje, JPanel jpanelContenedor) {
        return new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                resetUI(lblMensaje, jpanelContenedor);
                validador.run();
            }
        };
    }

    private static void resetUI(JLabel lblMensaje, JPanel jpanelContenedor) {
        UtilidadesFrmContrato.resetUI(lblMensaje, jpanelContenedor,
                UIManager.getColor(ConstantesUIContrato.COLOR_LABEL_FOREGROUND),
                UIManager.getColor(ConstantesUIContrato.COLOR_PANEL_BACKGROUND));
    }

    private static void validarComboTipoContrato(JComboBox<?> cmbTipoContrato, JLabel lblMensaje) {
        TipoContrato tipoContrato = (TipoContrato) cmbTipoContrato.getSelectedItem();
        if (tipoContrato.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_TIPO_CONTRATO_DEFAULT)) {
            lblMensaje.setText("Seleccionar un tipo de contrato.");
        }
    }

    private static void validarComboCargo(JComboBox<?> cmbCargo, JLabel lblMensaje) {
        Cargo cargo = (Cargo) cmbCargo.getSelectedItem();
        if (cargo.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_CARGO_DEFAULT)) {
            lblMensaje.setText("Seleccionar un cargo.");
        }
    }

    private static void validarComboArea(JComboBox<?> cmbArea, JLabel lblMensaje) {
        Area area = (Area) cmbArea.getSelectedItem();
        if (area.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_AREA_DEFAULT)) {
            lblMensaje.setText("Seleccionar un área.");
        }
    }

    private static void validarComboEspecialidad(JComboBox<?> cmbEspecialidad, JLabel lblMensaje) {
        Especialidad especialidad = (Especialidad) cmbEspecialidad.getSelectedItem();
        if (especialidad.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_ESPECIALIDAD_DEFAULT)) {
            lblMensaje.setText("Seleccionar una especialidad.");
        }
    }

    public static void regresarMenu() {
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
    }

    public void buscarTabla(String documento, String nombres, JTable jtbTabla, JCheckBox jhcHabilitarFechas,
                             JDateChooser jdcFechaInicial, JDateChooser jdcFechaFinal, JLabel lblMensajeBuscar) {
        Date fechaInicio = null;
        Date fechaFin = null;

        if (jhcHabilitarFechas.isSelected()) {
            if (jdcFechaInicial.getDate() != null) {
                fechaInicio = jdcFechaInicial.getDate();
            }
            
            if (jdcFechaFinal.getDate() != null) {
                fechaFin = jdcFechaFinal.getDate();
            }
            
            if (fechaInicio != null && fechaFin != null && fechaFin.before(fechaInicio)) {
                jdcFechaFinal.setDate(null);
                lblMensajeBuscar.setText("Vuelva a escoger la Fecha Fin para esta Fecha Inicio elegida.");
                return;
            }
            
        }

        listarContratosTabla(jtbTabla, fechaInicio, fechaFin, documento, nombres, lblMensajeBuscar);
    }

    public static void calcularFechaFin(JDateChooser jdcFechaInicio, JDateChooser jdcFechaFin, 
                                    JRadioButton rtn3meses, JRadioButton rtn6meses, JRadioButton rtn1anio) {
        Date fechaInicio = jdcFechaInicio.getDate();
        if (fechaInicio == null) {
            jdcFechaFin.setDate(null);
            return;
        }

        int meses;
        if (rtn3meses.isSelected()) {
            meses = 3;
        } else if (rtn6meses.isSelected()) {
            meses = 6;
        } else if (rtn1anio.isSelected()) {
            meses = 12;
        } else {
            meses = 0;
        }

        if (meses > 0) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaInicio);
            cal.add(Calendar.MONTH, meses);
            jdcFechaFin.setDate(cal.getTime());
        } else {
            jdcFechaFin.setDate(null);
        }

        jdcFechaFin.setEnabled(false);
    }

    public Resultado actualizarSalarioSiListo(JComboBox<?> cmbTipoContrato, JComboBox<?> cmbCargo, JComboBox<?> cmbArea, JComboBox<?> cmbEspecialidad) {
        TipoContrato tipoContrato = (TipoContrato) cmbTipoContrato.getSelectedItem();
        Cargo cargo = (Cargo) cmbCargo.getSelectedItem();
        Area area = (Area) cmbArea.getSelectedItem();
        Especialidad especialidad = (Especialidad) cmbEspecialidad.getSelectedItem();

        if (cmbTipoContrato.getSelectedIndex() == 0 ||
            cmbCargo.getSelectedIndex() == 0 ||
            cmbArea.getSelectedIndex() == 0 ||
            cmbEspecialidad.getSelectedIndex() == 0) {
            return null;
        }

        Resultado resultado = negocioContratoCalculo.actualizarSalarioSiListo(tipoContrato, cargo, area, especialidad);

        return resultado;
      }

}

