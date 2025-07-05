package com.grupo01.softwarenominas.CapaPresentacion;

import com.grupo01.softwarenominas.CapaEntidad.*;
import com.grupo01.softwarenominas.CapaNegocio.*;
import com.grupo01.softwarenominas.CapaPersistencia.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;


public class frmNomina extends javax.swing.JFrame {
    private final NominaNegocioVarios negocio = new NominaNegocioVarios();
    private final ContratoDAO contratoDAO = new ContratoDAO();
    private Nomina2 nominaActual;
    private DetalleNomina2 detalleActual;
    private Contrato contratoActual;

    public frmNomina() {
        initComponents();
        cmbMetodoPago.removeAllItems();
        cmbMetodoPago.addItem("EN EFECTIVO");
        cmbMetodoPago.addItem("TRANSFERENCIA BANCARIA");
        cmbMetodoPago.addItem("BILLETERA DIGITAL");
        cmbMetodoPago.setSelectedIndex(0);
        setLocationRelativeTo(null);
        negocio.listarTrabajadoresFiltrado(tableTrabajador);
    }
    
    private void limpiarFormulario() {
        txtDNI.setText("");
        txtSueldoBase.setText("");
        txtHorasTrabajadas.setText("");
        txtHoras.setText("");
        txtingresost.setText("");
        txtDescuentosT.setText("");
        txtMontoTotal.setText("");
        chkSeguroSalud.setSelected(false);
        chkSeguroVida.setSelected(false);
        chkSeguroAccidentes.setSelected(false);
        chkAsignacionFamiliar.setSelected(false);
        cmbMetodoPago.setSelectedIndex(0);
        FechaInicioPeriodo.setDate(null);
        FechaFinPeriodo.setDate(null);
        nominaActual = null;
        detalleActual = null;
        contratoActual = null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgDocumentoIdentidad = new javax.swing.ButtonGroup();
        bgSexo = new javax.swing.ButtonGroup();
        PanelMov = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ModuloNomina = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelDNI = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        jLabelSueldo = new javax.swing.JLabel();
        txtSueldoBase = new javax.swing.JTextField();
        jLabelfechainicio = new javax.swing.JLabel();
        FechaInicioPeriodo = new com.toedter.calendar.JDateChooser();
        jLabelfechafin = new javax.swing.JLabel();
        FechaFinPeriodo = new com.toedter.calendar.JDateChooser();
        jLabelhoras = new javax.swing.JLabel();
        txtHoras = new javax.swing.JTextField();
        jLabelhorastrabajadas = new javax.swing.JLabel();
        txtHorasTrabajadas = new javax.swing.JTextField();
        LabeltipoDescuento = new javax.swing.JLabel();
        PanelSeguro = new javax.swing.JPanel();
        chkSeguroSalud = new javax.swing.JCheckBox();
        chkSeguroVida = new javax.swing.JCheckBox();
        chkAsignacionFamiliar = new javax.swing.JCheckBox();
        chkSeguroAccidentes = new javax.swing.JCheckBox();
        LabelMetodoPago = new javax.swing.JLabel();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        btnProcesar = new javax.swing.JButton();
        jLabelIngresosTotales = new javax.swing.JLabel();
        txtingresost = new javax.swing.JTextField();
        jLabelMontoTotal = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabelDescuentosT = new javax.swing.JLabel();
        txtDescuentosT = new javax.swing.JTextField();
        btnRegistra = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jhcHabilitarFechas = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jdcFechaFinal = new com.toedter.calendar.JDateChooser();
        jdcFechaInicial = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTrabajador = new javax.swing.JTable();
        btnBucar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelMov.setOpaque(false);
        getContentPane().add(PanelMov, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(470, 520));
        jPanel1.setMinimumSize(new java.awt.Dimension(470, 520));
        jPanel1.setPreferredSize(new java.awt.Dimension(470, 520));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ModuloNomina.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ModuloNomina.setText("MODULO NOMINA");
        jPanel1.add(ModuloNomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 830, 10));

        jLabelDNI.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDNI.setText("Buscar por DNI");
        jPanel1.add(jLabelDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));
        jPanel1.add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 140, 30));

        jLabelSueldo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelSueldo.setText("Sueldo Base");
        jPanel1.add(jLabelSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        txtSueldoBase.setEnabled(false);
        jPanel1.add(txtSueldoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 140, 30));

        jLabelfechainicio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelfechainicio.setText("Fecha Inicio Periodo");
        jPanel1.add(jLabelfechainicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 140, -1));

        FechaInicioPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(FechaInicioPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 200, 40));

        jLabelfechafin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelfechafin.setText("Fecha Fin Periodo");
        jPanel1.add(jLabelfechafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 140, -1));

        FechaFinPeriodo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(FechaFinPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 200, 40));

        jLabelhoras.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelhoras.setText("Horas Contratadas");
        jPanel1.add(jLabelhoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));
        jPanel1.add(txtHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 140, 30));

        jLabelhorastrabajadas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelhorastrabajadas.setText("Horas Trabajadas");
        jPanel1.add(jLabelhorastrabajadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, -1, -1));
        jPanel1.add(txtHorasTrabajadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 140, 30));

        LabeltipoDescuento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabeltipoDescuento.setText("Tipo de Descuento");
        jPanel1.add(LabeltipoDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 150, -1));

        PanelSeguro.setBackground(new java.awt.Color(255, 255, 255));
        PanelSeguro.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Seguros"));
        PanelSeguro.setForeground(new java.awt.Color(255, 255, 255));
        PanelSeguro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkSeguroSalud.setBackground(new java.awt.Color(255, 255, 255));
        chkSeguroSalud.setText("Seguro de salud");
        PanelSeguro.add(chkSeguroSalud, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 150, -1));

        chkSeguroVida.setBackground(new java.awt.Color(255, 255, 255));
        chkSeguroVida.setText("Seguro de vida");
        PanelSeguro.add(chkSeguroVida, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 150, -1));

        chkAsignacionFamiliar.setBackground(new java.awt.Color(255, 255, 255));
        chkAsignacionFamiliar.setText("Deduccion de impuestos");
        PanelSeguro.add(chkAsignacionFamiliar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 190, -1));

        chkSeguroAccidentes.setBackground(new java.awt.Color(255, 255, 255));
        chkSeguroAccidentes.setText("Seguro de accidentes");
        PanelSeguro.add(chkSeguroAccidentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 150, -1));

        jPanel1.add(PanelSeguro, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 360, 110));

        LabelMetodoPago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LabelMetodoPago.setText("Metodo de Pago");
        jPanel1.add(LabelMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 130, -1));

        jPanel1.add(cmbMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, 140, -1));

        btnProcesar.setBackground(new java.awt.Color(204, 255, 204));
        btnProcesar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProcesar.setForeground(new java.awt.Color(255, 0, 0));
        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });
        jPanel1.add(btnProcesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 500, 200, 30));

        jLabelIngresosTotales.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelIngresosTotales.setText("Ingresos totales");
        jPanel1.add(jLabelIngresosTotales, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, -1, -1));
        jPanel1.add(txtingresost, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 140, 30));

        jLabelMontoTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelMontoTotal.setText("Monto Total a pagar");
        jPanel1.add(jLabelMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 540, -1, -1));
        jPanel1.add(txtMontoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 570, 140, 30));

        jLabel2.setText("S/.");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 570, 20, 30));

        jLabelDescuentosT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelDescuentosT.setText("Descuentos Totales");
        jPanel1.add(jLabelDescuentosT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, -1, -1));
        jPanel1.add(txtDescuentosT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 140, 40));

        btnRegistra.setBackground(new java.awt.Color(255, 254, 255));
        btnRegistra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/log-out.png"))); // NOI18N
        btnRegistra.setText("REGISTRAR");
        btnRegistra.setBorder(null);
        btnRegistra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistra.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistraActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistra, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 700, 110, 100));

        btnLimpiar.setBackground(new java.awt.Color(255, 254, 255));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setBorder(null);
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLimpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 700, 100, 100));

        btnRegresar.setBackground(new java.awt.Color(255, 254, 255));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/regresar.png"))); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.setBorder(null);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegresar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 700, 110, 100));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Busqueda por Fecha");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jhcHabilitarFechas.setBackground(new java.awt.Color(0, 0, 0));
        jhcHabilitarFechas.setForeground(new java.awt.Color(255, 255, 255));
        jhcHabilitarFechas.setText("Habilitar Fechas");
        jhcHabilitarFechas.setBorder(null);
        jPanel4.add(jhcHabilitarFechas, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 110, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 660, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Fecha Final");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, 30));

        jLabel13.setText("Fecha Inicial");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 30));

        jdcFechaFinal.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jdcFechaFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 210, 30));

        jdcFechaInicial.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jdcFechaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 210, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 660, 60));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setText("De");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        jComboBox1.setBorder(null);
        jPanel5.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 40, 20));

        jLabel15.setText("Página");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("20");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 30, 20));

        jLabel16.setText("Página");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel18.setText("Se encontraron");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("00");
        jPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 30, 20));

        jLabel20.setText("registros");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jLabel22.setText("de");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, -1, -1));

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("00");
        jPanel9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 30, 20));

        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(5, 40));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 750, 660, -1));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableTrabajador.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableTrabajador);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 660, 570));

        btnBucar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buscar.png"))); // NOI18N
        btnBucar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnBucar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBucarMouseClicked(evt);
            }
        });
        jPanel1.add(btnBucar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 30, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 820));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistraActionPerformed
        try {
            if (contratoActual == null) {
                JOptionPane.showMessageDialog(this, "Debe buscar un trabajador primero.");
                return;
            }

            Nomina2 nom = new Nomina2();
            DetalleNomina2 det = new DetalleNomina2();

            String metodoPago = cmbMetodoPago.getSelectedItem().toString();
            double sueldoNeto = Double.parseDouble(txtMontoTotal.getText());
            double ingresos = Double.parseDouble(txtingresost.getText());
            double descuentos = Double.parseDouble(txtDescuentosT.getText());

            nom.setIdContrato(contratoActual.getIdContrato());
            nom.setIdPeriodo(1); // Ajustar si hay tabla de periodos
            nom.setSueldoNeto(sueldoNeto);
            nom.setMetodoPago(metodoPago);
            nom.setEstadoPago("PENDIENTE");
            nom.setDescripcion("Generado automáticamente");

            double sueldoBase = Double.parseDouble(txtSueldoBase.getText());
            det.setGratificacionLegal(sueldoBase);
            det.setAsignacionFamiliar(chkAsignacionFamiliar.isSelected() ? (sueldoBase * 0.10 + 102.50) : 0.0);
            det.setCts(0);
            det.setDescuentoSeguroSalud(chkSeguroSalud.isSelected() ? sueldoBase * 0.09 : 0.0);
            det.setDescuentoSeguroVida(chkSeguroVida.isSelected() ? sueldoBase * 0.01 : 0.0);
            det.setDescuentoSeguroAccidentes(chkSeguroAccidentes.isSelected() ? sueldoBase * 0.015 : 0.0);
            det.setDescuentoAFP(Math.min(sueldoBase, 4950) * 0.10);
            det.setDescuentoRenta(0);
            det.setTotalIngresos(ingresos);
            det.setTotalDescuentos(descuentos);

            boolean ok = negocio.registrarNominaConDetalle(nom, det);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Nómina registrada correctamente.");
                limpiarFormulario();
                negocio.listarNominas(tableTrabajador);
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar nómina.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnRegistraActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        frmMenu menu = new frmMenu();
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBucarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBucarMouseClicked
        String dni = txtDNI.getText().trim();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un DNI para buscar.");
            return;
        }

        contratoActual = contratoDAO.obtenerContratoPorDocumentoIdentidad(dni);
        if (contratoActual == null) {
            JOptionPane.showMessageDialog(this, "Trabajador no encontrado o sin contrato activo.");
            return;
        }

        txtSueldoBase.setText(String.valueOf(contratoActual.getSalarioBase()));
        txtHoras.setText(String.valueOf(contratoActual.getHorasTotales()));
        //txtHorasTrabajadas.setText(String.valueOf(contratoActual.getHorasTrabajadas()));
    }//GEN-LAST:event_btnBucarMouseClicked

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        try {
            double sueldoBase = Double.parseDouble(txtSueldoBase.getText());
            int horasTotales = Integer.parseInt(txtHoras.getText());
            //int horasTrabajadas = Integer.parseInt(txtHorasTrabajadas.getText());

            boolean tieneAsignacion = chkAsignacionFamiliar.isSelected();
            boolean tieneVida = chkSeguroVida.isSelected();
            boolean tieneAcc = chkSeguroAccidentes.isSelected();
            boolean tieneSalud = chkSeguroSalud.isSelected();

            double asignacionFamiliar = tieneAsignacion ? (sueldoBase * 0.10 + 102.50) : 0.0;
            double gratificacion = 0.0;

            Date fecha = FechaInicioPeriodo.getDate();
            if (fecha != null) {
                int mes = fecha.getMonth() + 1;
                if (mes == 6 || mes == 12) {
                    gratificacion = sueldoBase;
                }
            }

            double valorHora = sueldoBase / horasTotales;
            int horasExtra = Math.max(0, 200 - horasTotales); //aqui debe ir las horasTrabajadas - horasTotales
            double pagoExtra = 0.0;
            if (horasExtra > 0) {
                int primeras2 = Math.min(2, horasExtra);
                int resto = Math.max(0, horasExtra - 2);
                pagoExtra = primeras2 * valorHora * 1.25 + resto * valorHora * 1.35;
            }

            double totalIngresos = sueldoBase + gratificacion + asignacionFamiliar + pagoExtra;
            double seguroSalud = tieneSalud ? sueldoBase * 0.09 : 0.0;
            double seguroVida = tieneVida ? sueldoBase * 0.01 : 0.0;
            double seguroAcc = tieneAcc ? sueldoBase * 0.015 : 0.0;
            double afp = Math.min(sueldoBase, 4950) * 0.10;
            double renta = 0.0;

            double totalDescuentos = seguroSalud + seguroVida + seguroAcc + afp + renta;
            double sueldoNeto = totalIngresos - totalDescuentos;

            DecimalFormat df = new DecimalFormat("#.00");
            txtingresost.setText(df.format(totalIngresos));
            txtDescuentosT.setText(df.format(totalDescuentos));
            txtMontoTotal.setText(df.format(sueldoNeto));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar la nómina: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnProcesarActionPerformed
      
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmNomina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser FechaFinPeriodo;
    private com.toedter.calendar.JDateChooser FechaInicioPeriodo;
    private javax.swing.JLabel LabelMetodoPago;
    private javax.swing.JLabel LabeltipoDescuento;
    private javax.swing.JLabel ModuloNomina;
    private javax.swing.JPanel PanelMov;
    private javax.swing.JPanel PanelSeguro;
    private javax.swing.ButtonGroup bgDocumentoIdentidad;
    private javax.swing.ButtonGroup bgSexo;
    private javax.swing.JLabel btnBucar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JButton btnRegistra;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JCheckBox chkAsignacionFamiliar;
    private javax.swing.JCheckBox chkSeguroAccidentes;
    private javax.swing.JCheckBox chkSeguroSalud;
    private javax.swing.JCheckBox chkSeguroVida;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabelDNI;
    private javax.swing.JLabel jLabelDescuentosT;
    private javax.swing.JLabel jLabelIngresosTotales;
    private javax.swing.JLabel jLabelMontoTotal;
    private javax.swing.JLabel jLabelSueldo;
    private javax.swing.JLabel jLabelfechafin;
    private javax.swing.JLabel jLabelfechainicio;
    private javax.swing.JLabel jLabelhoras;
    private javax.swing.JLabel jLabelhorastrabajadas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private com.toedter.calendar.JDateChooser jdcFechaFinal;
    private com.toedter.calendar.JDateChooser jdcFechaInicial;
    private javax.swing.JCheckBox jhcHabilitarFechas;
    private javax.swing.JTable tableTrabajador;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDescuentosT;
    private javax.swing.JTextField txtHoras;
    private javax.swing.JTextField txtHorasTrabajadas;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtSueldoBase;
    private javax.swing.JTextField txtingresost;
    // End of variables declaration//GEN-END:variables
}
