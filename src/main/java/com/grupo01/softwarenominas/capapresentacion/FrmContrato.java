package com.grupo01.softwarenominas.capapresentacion;

import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioCalculo;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioListado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioLlenado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioRegistro;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioLlenado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioCalculo.Resultado;
import com.toedter.calendar.JDateChooser;

import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.annotation.processing.Generated;
import javax.swing.*;

import com.grupo01.softwarenominas.capapresentacion.utils.Utilidades;
import com.grupo01.softwarenominas.capapresentacion.utils.UtilidadesFrmContrato;
import com.grupo01.softwarenominas.capapresentacion.utils.ConstantesUIContrato;
import com.grupo01.softwarenominas.capapresentacion.utils.ConstantesUITablas;

import javax.swing.text.JTextComponent;

@Getter
@Setter
public class FrmContrato extends javax.swing.JFrame {   

    private transient ContratoNegocioLlenado negocioContratoLlenado;
    private transient ContratoNegocioCalculo negocioContratoCalculo;
    private transient ContratoNegocioListado negocioContratoListado;
    private transient ContratoNegocioRegistro negocioContratoRegistro;
    private transient TrabajadorNegocioLlenado negocioTrabajadorLlenado;

    UtilidadesFrmContrato util;


    private static final long serialVersionUID = 1L;
    private transient Trabajador trabajadorActual;
    private transient Contrato contratoActual;
    private transient DetalleContrato detalleContratoActual;
    
    private boolean modoEdicionContrato = false;    
    
    public FrmContrato() {
        this(new ContratoNegocioLlenado(), new ContratoNegocioCalculo(), new ContratoNegocioListado(),
            new ContratoNegocioRegistro(), new TrabajadorNegocioLlenado());
        
    }

    public FrmContrato(ContratoNegocioLlenado contratoLlenado,
                      ContratoNegocioCalculo contratoCalculo,
                      ContratoNegocioListado contratoListado,
                      ContratoNegocioRegistro contratoRegistro,
                      TrabajadorNegocioLlenado trabajadorLlenado) {

        this.negocioContratoLlenado = contratoLlenado;
        this.negocioContratoCalculo = contratoCalculo;
        this.negocioContratoListado = contratoListado;
        this.negocioContratoRegistro = contratoRegistro;
        this.negocioTrabajadorLlenado = trabajadorLlenado;

        this.util = new UtilidadesFrmContrato(negocioContratoListado, negocioContratoLlenado, negocioContratoCalculo);

        initComponents();
        inicializarFormulario();
    }

    private void inicializarFormulario() {
        setLocationRelativeTo(null);
        UtilidadesFrmContrato.configurarComponentesIniciales(rtnESSALUD, jhcHabilitarFechas, jhcSeguroSalud,
                btnRegistrar, btnEditarHorasTrabajadas, jdcFechaFin);

        negocioContratoLlenado.cargarAreas(cmbArea);
        negocioContratoLlenado.cargarTiposContrato(cmbTipoContrato);
        negocioContratoLlenado.cargarCargos(cmbCargo);

        Utilidades.configurarTabla(jtbTabla, ConstantesUITablas.COLUMNAS_CONTRATO);

        util.listarContratosTabla(jtbTabla, null, null, "", "", lblMensajeBuscar);

        UtilidadesFrmContrato.configurarFechaInicioYFin(jdcFechaInicial, jdcFechaFinal, lblMensajeBuscar);

        inicializarListenersTabla();

        UtilidadesFrmContrato.aplicarFiltros(txtDNI, txtDocumentoBuscar, txtHorasTotales,
                txtNombres, txtNombresBuscar, jtxDescripcion, txtSalario);


        UtilidadesFrmContrato.configurarListenersDuracion(
            rtn3meses, rtn6meses, rtn1anio, txtDNI,
            this::calcularFechaFin, this::buscarTrabajadorPorDNI
        );

        UtilidadesFrmContrato.configurarListenersActivadores(
            jhcHabilitarFechas, jdcFechaInicial, jdcFechaFinal,jhcSeguroSalud,
            rtnESSALUD, rtnEPS
        );

        util.configurarListenersCombobox(
            cmbArea, cmbEspecialidad, cmbTipoContrato, cmbCargo, 
            this::actualizarSalarioSiListo
        );

        UtilidadesFrmContrato.configurarListenersFechas(
            jdcFechaInicio, jdcFechaFin, rtn3meses, rtn6meses, rtn1anio
        );

        UtilidadesFrmContrato.configurarFocusListeners(
            txtDNI, txtHorasTotales, jtxDescripcion, txtSalario,
            cmbTipoContrato, cmbCargo, cmbArea, cmbEspecialidad,
            lblMensaje, jpanelContenedor
        );

        
    }
    public boolean formularioValido() {
        return UtilidadesFrmContrato.validarDNI(txtDNI.getText().trim(), lblMensaje) && UtilidadesFrmContrato.validarHoras(txtHorasTotales.getText(), lblMensaje) && UtilidadesFrmContrato.validarDescripcion(lblMensaje, jtxDescripcion) && UtilidadesFrmContrato.validarSalario(cmbTipoContrato, cmbCargo, cmbArea, cmbEspecialidad, txtSalario, lblMensaje)
                && UtilidadesFrmContrato.validarCombos(cmbTipoContrato, cmbCargo, cmbArea, cmbEspecialidad)
                && UtilidadesFrmContrato.validarFechas(jdcFechaInicio, jdcFechaFin);
    }

    public void limpiar() {
        UtilidadesFrmContrato.limpiarCampos(
                new JTextComponent[]{txtDNI, txtNombres, txtHorasTotales, txtSalario, jtxDescripcion, txtDocumentoBuscar, txtNombresBuscar},
                new JComboBox[]{cmbArea, cmbCargo, cmbTipoContrato},
                new JDateChooser[]{jdcFechaInicio, jdcFechaFin},
                new JRadioButton[]{rtn3meses, rtn6meses, rtn1anio},
                new JCheckBox[]{jcbAsignacion, jcbSeguroAccidentes, jcbSeguroVida},
                btnRegistrar
        );
    }

    public Contrato mapearFormularioAContrato() {
        return UtilidadesFrmContrato.mapearFormularioAContrato(trabajadorActual, cmbTipoContrato, cmbCargo, jdcFechaInicio,
                jdcFechaFin, txtSalario, txtHorasTotales, jtxDescripcion, cmbArea, cmbEspecialidad);
    }

    public DetalleContrato mapearFormularioADetalleContrato(int idContrato) {
        return UtilidadesFrmContrato.mapearFormularioADetalleContrato(idContrato, jhcSeguroSalud, rtnESSALUD,
                rtnEPS, jcbSeguroVida, jcbSeguroAccidentes, jcbAsignacion);
    }

    public void mostrarMensaje(String mensaje, Color color) {
        UtilidadesFrmContrato.mostrarMensaje(lblMensaje, mensaje, color);
    }

    @Generated("FormDesigner")
    private void inicializarListenersTabla() {
        jtbTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = jtbTabla.getSelectedRow();
                if (fila != -1) {
                    String documentoIdentidad = jtbTabla.getValueAt(fila, 3).toString();
                    lblMensaje.setText("Contrato encontrado: DNI " + documentoIdentidad);

                    Trabajador t = negocioTrabajadorLlenado.buscarPorDocumentoIdentidad(documentoIdentidad);
                    Contrato c = negocioContratoLlenado.obtenerContratoPorDocumentoIdentidad(documentoIdentidad);
                    DetalleContrato dc = negocioContratoLlenado.obtenerDetalleContratoPorDocumentoIdentidad(documentoIdentidad);

                    if (t != null && c != null && dc != null) {
                        trabajadorActual = t;
                        contratoActual = c;
                        detalleContratoActual = dc;

                        modoEdicionContrato = true;
                        btnRegistrar.setText("EDITAR");
                        btnLimpiar.setText("NUEVO");

                        rtn3meses.setEnabled(false);
                        rtn6meses.setEnabled(false);
                        rtn1anio.setEnabled(false);
                        jdcFechaInicio.setEnabled(false);

                        btnEditarHorasTrabajadas.setEnabled(true);

                        cargarFormularioConContrato();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró información del contrato.");
                    }
                }
            }
        });
    }

    private void calcularFechaFin() {
        UtilidadesFrmContrato.calcularFechaFin(jdcFechaInicio, jdcFechaFin, rtn3meses, rtn6meses, rtn1anio);
    }

    public void buscarTrabajadorPorDNI() {
        String dni = txtDNI.getText().trim();
        if (dni.isEmpty()) return;

        trabajadorActual = negocioTrabajadorLlenado.buscarPorDocumentoIdentidad(dni);

        if (trabajadorActual != null) {
            txtNombres.setText(trabajadorActual.getNombres() + " " +
                               trabajadorActual.getApellidoPaterno() + " " +
                               trabajadorActual.getApellidoMaterno());

            lblMensaje.setText("Trabajador Encontrado");

        } else {
            trabajadorActual = null;
            txtNombres.setText("");
            lblMensaje.setText("No se encontró al trabajador, vuelva a intentarlo antes de registrar el contrato.");
        }
    }
    
    public void salirModoEditar(){
        modoEdicionContrato = false;
        trabajadorActual = null;
        contratoActual = null;
        detalleContratoActual = null;
        btnRegistrar.setText("REGISTRAR");
        btnLimpiar.setText("LIMPIAR");
        btnRegresar.setText("CERRAR");       
        rtn3meses.setEnabled(true);
        rtn6meses.setEnabled(true);
        rtn1anio.setEnabled(true);     
        jdcFechaInicio.setEnabled(true);       
    }
    
    private void actualizarSalarioSiListo() {

        Resultado resultado = util.actualizarSalarioSiListo(
            cmbTipoContrato, cmbCargo, cmbArea, cmbEspecialidad
        );

        if (resultado == null) {
            return;
        }

        txtSalario.setText(resultado.salario());
        txtSalario.setEditable(resultado.estado());
        lblMensaje.setText(resultado.mensaje());
        
    }

    private void cargarFormularioConContrato() {
        if (trabajadorActual != null) {
            txtDNI.setText(trabajadorActual.getDocumentoIdentidad());
            txtNombres.setText(trabajadorActual.getNombres() + " " +
                               trabajadorActual.getApellidoPaterno() + " " +
                               trabajadorActual.getApellidoMaterno());
        }

        if (contratoActual != null) {
            jdcFechaInicio.setDate(contratoActual.getFechaInicio());
            jdcFechaFin.setDate(contratoActual.getFechaFin());
            txtSalario.setText(String.valueOf(contratoActual.getSalarioBase()));
            txtHorasTotales.setText(String.valueOf(contratoActual.getHorasTotales()));
            jtxDescripcion.setText(contratoActual.getDescripcion());
            
            negocioContratoLlenado.cargarAreas(cmbArea);
            
            negocioContratoLlenado.cargarEspecialidadesPorArea(cmbEspecialidad, contratoActual.getArea().getIdArea());
            
            cmbArea.setSelectedItem(contratoActual.getArea());
            cmbEspecialidad.setSelectedItem(contratoActual.getEspecialidad());
            
            cmbTipoContrato.setSelectedItem(contratoActual.getTipoContrato());
            cmbCargo.setSelectedItem(contratoActual.getCargo());
            
            long diff = contratoActual.getFechaFin().getTime() - contratoActual.getFechaInicio().getTime();
            int meses = (int) (diff / (1000L * 60 * 60 * 24 * 30)); 
            
            if (meses <= 3) {
                rtn3meses.setSelected(true);
            } else if (meses <= 6) {
                rtn6meses.setSelected(true);
            } else {
                rtn1anio.setSelected(true);
            }
            
            String tipo = cmbTipoContrato.getSelectedItem().toString();
            if (tipo.equalsIgnoreCase(ConstantesUIContrato.TEXTO_SERVICIO_EXTERNO)) {
                txtSalario.setText(String.valueOf(contratoActual.getSalarioBase()));
            }
        }

        if (detalleContratoActual != null) {
            if (ConstantesUIContrato.TEXTO_ESSALUD.equalsIgnoreCase(detalleContratoActual.getTipoSeguroSalud())) {
                jhcSeguroSalud.setSelected(true);
                rtnESSALUD.setSelected(true);
                rtnESSALUD.setEnabled(true);
                rtnEPS.setEnabled(true);
            } else if ("EPS".equalsIgnoreCase(detalleContratoActual.getTipoSeguroSalud())) {
                jhcSeguroSalud.setSelected(true);
                rtnEPS.setSelected(true);
                rtnESSALUD.setEnabled(true);
                rtnEPS.setEnabled(true);
            } else{
                jhcSeguroSalud.setSelected(false);
                rtnESSALUD.setSelected(false);
                rtnEPS.setSelected(false);
                rtnESSALUD.setEnabled(false);
                rtnEPS.setEnabled(false);
            }
            
            jcbSeguroVida.setSelected(detalleContratoActual.isTieneSeguroDeVida());
            jcbSeguroAccidentes.setSelected(detalleContratoActual.isTieneSeguroDeAccidentes());
            jcbAsignacion.setSelected(detalleContratoActual.isTieneAsignacionFamiliar());
        }
    }
    
    public void procesarContrato() {
        if (!formularioValido()) {
            mostrarMensaje("Formulario incompleto o inválido.", Color.RED);
            return;
        }

        Contrato contrato = mapearFormularioAContrato();

        if (modoEdicionContrato) {
            actualizarContratoYDetalle(contrato);
        } else {
            registrarContratoYDetalle(contrato);
        }

        util.listarContratosTabla(jtbTabla, null, null, "", "", lblMensajeBuscar);
    }

    public void actualizarContratoYDetalle(Contrato contrato) {
        contrato.setIdContrato(contratoActual.getIdContrato());

        if (negocioContratoRegistro.actualizarContrato(contrato)) {
            DetalleContrato detalle = mapearFormularioADetalleContrato(contrato.getIdContrato());

            if (negocioContratoRegistro.actualizarDetalleContrato(detalle)) {
                mostrarMensaje("Contrato y detalle actualizados.", new Color(0, 128, 0));
                salirModoEditar();
                limpiar();
            } else {
                mostrarMensaje("Error al actualizar detalle.", Color.RED);
            }
        } else {
            mostrarMensaje("Error al actualizar contrato.", Color.RED);
        }
    }

    public void registrarContratoYDetalle(Contrato contrato) {
        ResultadoOperacion resultado = negocioContratoRegistro.registrarContrato(contrato);

        if (resultado.isExito()) {
            DetalleContrato detalle = mapearFormularioADetalleContrato(resultado.getIdGenerado());

            if (negocioContratoRegistro.registrarDetalleContrato(detalle).isExito()) {
                mostrarMensaje("Contrato registrado correctamente.", new Color(0, 128, 0));
                limpiar();
            } else {
                mostrarMensaje("Contrato registrado, detalle falló.", Color.RED);
            }
        } else {
            mostrarMensaje(resultado.getMensaje(), Color.RED);
        }
    }


    public void abrirHorasTrabajadasDialog(){
        FrmDialogHorasTrabajadas dialog = new FrmDialogHorasTrabajadas(this, true); // modal
        dialog.inicializarConContrato(contratoActual);
        dialog.setVisible(true);
    }

    private void volverAlMenu() {
        UtilidadesFrmContrato.regresarMenu();
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    @Generated("FormDesigner")
    private void initComponents() {
        javax.swing.ButtonGroup bgDuracion = new javax.swing.ButtonGroup();
        javax.swing.ButtonGroup bgTipoSeguroSalud = new javax.swing.ButtonGroup();
        javax.swing.JPanel panelMov = new javax.swing.JPanel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        txtNombres = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jtxDescripcion = new javax.swing.JTextArea();
        btnRegresar = new javax.swing.JButton();
        javax.swing.JLabel jLabel14 = new javax.swing.JLabel();
        cmbArea = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        cmbCargo = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel26 = new javax.swing.JLabel();
        cmbTipoContrato = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel27 = new javax.swing.JLabel();
        txtHorasTotales = new javax.swing.JTextField();
        javax.swing.JLabel jLabel28 = new javax.swing.JLabel();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel29 = new javax.swing.JLabel();
        jdcFechaInicio = new com.toedter.calendar.JDateChooser();
        javax.swing.JLabel jLabel30 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        jcbAsignacion = new javax.swing.JCheckBox();
        jcbSeguroVida = new javax.swing.JCheckBox();
        jcbSeguroAccidentes = new javax.swing.JCheckBox();
        rtnEPS = new javax.swing.JRadioButton();
        rtnESSALUD = new javax.swing.JRadioButton();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        jhcSeguroSalud = new javax.swing.JCheckBox();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel moduloContrato = new javax.swing.JLabel();
        javax.swing.JSeparator jSeparator2 = new javax.swing.JSeparator();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel25 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        jdcFechaFinal = new com.toedter.calendar.JDateChooser();
        jdcFechaInicial = new com.toedter.calendar.JDateChooser();
        javax.swing.JButton btnBuscar = new javax.swing.JButton();
        txtDocumentoBuscar = new javax.swing.JTextField();
        javax.swing.JLabel jLabel16 = new javax.swing.JLabel();
        txtNombresBuscar = new javax.swing.JTextField();
        javax.swing.JLabel jLabel19 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel21 = new javax.swing.JLabel();
        jhcHabilitarFechas = new javax.swing.JCheckBox();
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel12 = new javax.swing.JPanel();
        lblMensajeBuscar = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        cmbEspecialidad = new javax.swing.JComboBox<>();
        javax.swing.JPanel jPanel10 = new javax.swing.JPanel();
        rtn6meses = new javax.swing.JRadioButton();
        rtn3meses = new javax.swing.JRadioButton();
        rtn1anio = new javax.swing.JRadioButton();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        jpanelContenedor = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        btnEditarHorasTrabajadas = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane5 = new javax.swing.JScrollPane();
        jtbTabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMov.setOpaque(false);
        getContentPane().add(panelMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(470, 520));
        jPanel1.setMinimumSize(new java.awt.Dimension(470, 520));
        jPanel1.setPreferredSize(new java.awt.Dimension(470, 520));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombres.setEditable(false);
        txtNombres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 250, 30));

        txtDNI.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 210, 30));

        jLabel5.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel5.setText("Area");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel6.setText("Duracion");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, -1, -1));

        txtSalario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 70, 30));

        jtxDescripcion.setColumns(20);
        jtxDescripcion.setRows(5);
        jtxDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jtxDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 430, 280, 110));

        btnRegresar.setBackground(new java.awt.Color(255, 254, 255));
        btnRegresar.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saliir.png"))); // NOI18N
        btnRegresar.setText("CERRAR");
        btnRegresar.setBorder(null);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegresar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegresar.addActionListener(this::btnRegresarActionPerformed);
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 620, 110, 100));

        jLabel14.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel14.setText("Trabajador");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 80, -1));

        cmbArea.setBackground(new java.awt.Color(254, 255, 255));
        cmbArea.setBorder(null);
        jPanel1.add(cmbArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));

        btnLimpiar.setBackground(new java.awt.Color(255, 254, 255));
        btnLimpiar.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setBorder(null);
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLimpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 620, 100, 100));

        cmbCargo.setBackground(new java.awt.Color(255, 254, 255));
        cmbCargo.setBorder(null);
        jPanel1.add(cmbCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 220, 30));

        jLabel11.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel11.setText("Cargo");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, -1));

        jLabel26.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel26.setText("Tipo de Contrato");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        cmbTipoContrato.setBackground(new java.awt.Color(255, 254, 255));
        cmbTipoContrato.setBorder(null);
        jPanel1.add(cmbTipoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 220, 30));

        jLabel27.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel27.setText("# Horas");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        txtHorasTotales.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtHorasTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 80, 30));

        jLabel28.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel28.setText("Fecha Inicio");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, -1, -1));

        jdcFechaFin.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jdcFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 170, 30));

        jLabel29.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel29.setText("Fecha Fin");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, -1, -1));

        jdcFechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jdcFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 170, 30));

        jLabel30.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel30.setText("Salario");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcbAsignacion.setBackground(new java.awt.Color(255, 255, 255));
        jcbAsignacion.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jcbAsignacion.setText("Asignación Familiar");
        jPanel7.add(jcbAsignacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, -1));

        jcbSeguroVida.setBackground(new java.awt.Color(255, 255, 255));
        jcbSeguroVida.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jcbSeguroVida.setText("Seguro de Vida");
        jPanel7.add(jcbSeguroVida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, -1));

        jcbSeguroAccidentes.setBackground(new java.awt.Color(255, 255, 255));
        jcbSeguroAccidentes.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jcbSeguroAccidentes.setText("Seguro Accidentes");
        jPanel7.add(jcbSeguroAccidentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 160, -1));

        rtnEPS.setBackground(new java.awt.Color(255, 255, 255));
        bgTipoSeguroSalud.add(rtnEPS);
        rtnEPS.setText("EPS");
        jPanel7.add(rtnEPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        rtnESSALUD.setBackground(new java.awt.Color(255, 255, 255));
        bgTipoSeguroSalud.add(rtnESSALUD);
        rtnESSALUD.setText("ESSALUD");
        jPanel7.add(rtnESSALUD, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel7.setText("Tipo Seguro Salud");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jhcSeguroSalud.setBackground(new java.awt.Color(255, 255, 255));
        jhcSeguroSalud.setBorder(null);
        jPanel7.add(jhcSeguroSalud, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 20, 20));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 180, 170));

        jLabel4.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel4.setText("Documento Identidad (DNI/CE)");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        moduloContrato.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 24)); // NOI18N
        moduloContrato.setText("MODULO CONTRATO");
        jPanel1.add(moduloContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 830, 10));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Busqueda de Contratos");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 620, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Fecha Final");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, 30));

        jLabel13.setText("Fecha Inicial");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, 30));

        jdcFechaFinal.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jdcFechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 150, 30));

        jdcFechaInicial.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jdcFechaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 150, 30));

        btnBuscar.setBackground(new java.awt.Color(255, 254, 255));
        btnBuscar.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.setHideActionText(true);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);
        jPanel3.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 100, 40));

        txtDocumentoBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(txtDocumentoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 210, 30));

        jLabel16.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 12)); // NOI18N
        jLabel16.setText("Por Fechas :");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        txtNombresBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(txtNombresBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 210, 30));

        jLabel19.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 12)); // NOI18N
        jLabel19.setText("Por Nombres del Trabajador :");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel21.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 12)); // NOI18N
        jLabel21.setText("Por Documento :");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jhcHabilitarFechas.setBackground(new java.awt.Color(255, 255, 255));
        jhcHabilitarFechas.setText("Habilitar Fechas");
        jhcHabilitarFechas.setBorder(null);
        jPanel3.add(jhcHabilitarFechas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 110, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 620, 130));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));

        lblMensajeBuscar.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 3, 12)); // NOI18N
        lblMensajeBuscar.setForeground(new java.awt.Color(255, 255, 255));
        lblMensajeBuscar.setText("Mensaje: ");
        jPanel12.add(lblMensajeBuscar);

        jPanel5.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 640, 620, 40));

        jLabel8.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel8.setText("Especialidad");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, -1, -1));

        cmbEspecialidad.setBackground(new java.awt.Color(254, 255, 255));
        cmbEspecialidad.setBorder(null);
        jPanel1.add(cmbEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 220, 30));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rtn6meses.setBackground(new java.awt.Color(255, 255, 255));
        bgDuracion.add(rtn6meses);
        rtn6meses.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 0, 14)); // NOI18N
        rtn6meses.setText("6 meses");
        jPanel10.add(rtn6meses, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, -1));

        rtn3meses.setBackground(new java.awt.Color(255, 255, 255));
        bgDuracion.add(rtn3meses);
        rtn3meses.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 0, 14)); // NOI18N
        rtn3meses.setText("3 meses");
        jPanel10.add(rtn3meses, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, -1));

        rtn1anio.setBackground(new java.awt.Color(255, 255, 255));
        bgDuracion.add(rtn1anio);
        rtn1anio.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 0, 14)); // NOI18N
        rtn1anio.setText("1 año");
        jPanel10.add(rtn1anio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 80, -1));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 100, 110));

        jLabel9.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel9.setText("Descripcion");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, -1));

        jLabel10.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel10.setText("Detalle");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        lblMensaje.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 3, 12)); // NOI18N
        lblMensaje.setText("Mensaje: ");
        jpanelContenedor.add(lblMensaje);

        jPanel1.add(jpanelContenedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 470, 40));

        btnEditarHorasTrabajadas.setText("Editar Horas Trabajadas");
        btnEditarHorasTrabajadas.addActionListener(this::btnEditarHorasTrabajadasActionPerformed);
        jPanel1.add(btnEditarHorasTrabajadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, 280, -1));

        btnRegistrar.setBackground(new java.awt.Color(255, 254, 255));
        btnRegistrar.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/log-out.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setBorder(null);
        btnRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 620, 110, 100));

        jScrollPane5.setAutoscrolls(true);

        jtbTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jtbTabla);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 620, 390));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    @Generated("FormDesigner")
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//NOSONAR//GEN-FIRST:event_btnRegresarActionPerformed
        volverAlMenu();
    }//GEN-LAST:event_btnRegresarActionPerformed
    @Generated("FormDesigner")
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//NOSONAR//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
        salirModoEditar();
    }//GEN-LAST:event_btnLimpiarActionPerformed
    @Generated("FormDesigner")
    private void btnEditarHorasTrabajadasActionPerformed(java.awt.event.ActionEvent evt) {//NOSONAR//GEN-FIRST:event_btnEditarHorasTrabajadasActionPerformed
        abrirHorasTrabajadasDialog();

    }//GEN-LAST:event_btnEditarHorasTrabajadasActionPerformed
    @Generated("FormDesigner")
    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//NOSONAR//GEN-FIRST:event_btnRegistrarActionPerformed
        procesarContrato();
    }//GEN-LAST:event_btnRegistrarActionPerformed
    @Generated("FormDesigner")
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//NOSONAR//GEN-FIRST:event_btnBuscarActionPerformed
        
        util.buscarTabla(txtDocumentoBuscar.getText().trim(), txtNombresBuscar.getText().trim(), jtbTabla, jhcHabilitarFechas, jdcFechaInicial, jdcFechaFinal, lblMensajeBuscar);

    }//GEN-LAST:event_btnBuscarActionPerformed
  
    /**
     * @param args the command line arguments
     */
    @Generated("FormDesigner")
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmContrato().setVisible(true));
    }

    // Variables declaration - do not modify //NOSONAR //GEN-BEGIN:variables
        private javax.swing.JTextField txtNombres;
        private javax.swing.JTextField txtDNI;
        private javax.swing.JTextField txtSalario;
        private javax.swing.JTextArea jtxDescripcion;
        private javax.swing.JButton btnRegresar;
        private javax.swing.JComboBox<Area> cmbArea;
        private javax.swing.JButton btnLimpiar;
        private javax.swing.JComboBox<Cargo> cmbCargo;
        private javax.swing.JComboBox<TipoContrato> cmbTipoContrato;
        private javax.swing.JTextField txtHorasTotales;
        private com.toedter.calendar.JDateChooser jdcFechaFin;
        private com.toedter.calendar.JDateChooser jdcFechaInicio;
        private javax.swing.JCheckBox jcbAsignacion;
        private javax.swing.JCheckBox jcbSeguroVida;
        private javax.swing.JCheckBox jcbSeguroAccidentes;
        private javax.swing.JRadioButton rtnEPS;
        private javax.swing.JRadioButton rtnESSALUD;
        private javax.swing.JCheckBox jhcSeguroSalud;
        private com.toedter.calendar.JDateChooser jdcFechaFinal;
        private com.toedter.calendar.JDateChooser jdcFechaInicial;
        private javax.swing.JTextField txtDocumentoBuscar;
        private javax.swing.JTextField txtNombresBuscar;
        private javax.swing.JCheckBox jhcHabilitarFechas;
        private javax.swing.JLabel lblMensajeBuscar;
        private javax.swing.JComboBox<Especialidad> cmbEspecialidad;
        private javax.swing.JRadioButton rtn6meses;
        private javax.swing.JRadioButton rtn3meses;
        private javax.swing.JRadioButton rtn1anio;
        private javax.swing.JPanel jpanelContenedor;
        private javax.swing.JLabel lblMensaje;
        private javax.swing.JButton btnEditarHorasTrabajadas;
        private javax.swing.JButton btnRegistrar;
        private javax.swing.JTable jtbTabla;
    // End of variables declaration//GEN-END:variables
}
