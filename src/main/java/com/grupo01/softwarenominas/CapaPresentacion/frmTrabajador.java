package com.grupo01.softwarenominas.capapresentacion;
import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capanegocio.TrabajadorNegocioVarios;
import com.grupo01.softwarenominas.capapresentacion.validacionespresentacion.FiltroDescripcion;
import com.grupo01.softwarenominas.capapresentacion.validacionespresentacion.FiltroNumerico;
import com.grupo01.softwarenominas.capapresentacion.utils.Utilidades;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;


import com.grupo01.softwarenominas.capapresentacion.utils.ConstantesUITrabajador;

public class FrmTrabajador extends javax.swing.JFrame {

    transient TrabajadorNegocioVarios negocio = new TrabajadorNegocioVarios();
    private transient Trabajador trabajadorActual;
    
    transient Utilidades utilidades = new Utilidades();
    
    private boolean modoEdicion = false;
    
    public FrmTrabajador() {
        initComponents();
        inicializarFormulario();
        configurarListeners();
    }

    private void inicializarFormulario() {
        setLocationRelativeTo(null);
        inicializarCamposValidados();
        inicializarTablaTrabajadores(tableTrabajador);
        listarTrabajadoresTabla(null, null);
    }
    
    private void inicializarCamposValidados(){
        ((AbstractDocument) txtNombres.getDocument()).setDocumentFilter(new FiltroDescripcion());
        ((AbstractDocument) txtApellidoPaterno.getDocument()).setDocumentFilter(new FiltroDescripcion());
        ((AbstractDocument) txtApellidoMaterno.getDocument()).setDocumentFilter(new FiltroDescripcion());
        ((AbstractDocument) txtDocumentoIdentidad.getDocument()).setDocumentFilter(new FiltroNumerico(9));
        
        ((AbstractDocument) txtTelefono.getDocument()).setDocumentFilter(new FiltroNumerico(9));
        ((AbstractDocument) txtDireccion.getDocument()).setDocumentFilter(new FiltroDescripcion());
        ((AbstractDocument) txtDescripcion.getDocument()).setDocumentFilter(new FiltroDescripcion());

    }
    
    public void inicializarTablaTrabajadores(JTable tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnasDeseadas = {
            "Nombres", "ApellidoPaterno", "ApellidoMaterno", 
            "TipoDocumento", "DocumentoIdentidad", "Telefono", "CorreoElectronico"
        };
        for (String col : columnasDeseadas) {
            modelo.addColumn(col);
        }
        tabla.setModel(modelo);

        utilidades.ajustarTabla(tabla);
    }
    
    public void listarTrabajadoresTabla(Date fechaInicio, Date fechaFin){
        int resultados = 0;
        
        if (fechaInicio != null && fechaFin != null) {
            resultados = negocio.listarTrabajadoresFiltradoPorFecha(tableTrabajador, fechaInicio, fechaFin);
        } else {
            resultados = negocio.listarTrabajadoresFiltrado(tableTrabajador);
        }
        
        utilidades.ajustarTabla(tableTrabajador);

        lblMensajeBuscar.setText(
            switch (resultados) {
                case 0 -> "No se encontraron trabajadores en la base de datos.";
                case 1 -> "Se encontró 1 trabajador.";
                default -> "Se encontraron " + resultados + " trabajadores.";
            }
        );
    }

    private void configurarListeners() {
        jhcHabilitarFechas.addActionListener(e -> {
            if (jhcHabilitarFechas.isSelected()) {
                Date fechaInicio = jdcFechaInicial.getDate();
                Date fechaFin = jdcFechaFinal.getDate();

                if (fechaInicio != null && fechaFin != null) {
                    listarTrabajadoresTabla(fechaInicio, fechaFin);
                } else {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar ambas fechas.");
                    jhcHabilitarFechas.setSelected(false);
                }
            } else {
                listarTrabajadoresTabla(null, null);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        javax.swing.ButtonGroup bgDocumentoIdentidad;
        javax.swing.ButtonGroup bgSexo;
        javax.swing.JPanel panelMov;
        javax.swing.JPanel jPanel1 ;
        javax.swing.JPanel jPanel3;
        javax.swing.JPanel jPanel4 ;
        javax.swing.JPanel jPanel10 ;
        javax.swing.JPanel jPanel11 ;
        javax.swing.JPanel jPanel12 ;

        javax.swing.JLabel moduloContrato;
        javax.swing.JLabel jLabel1 ;
        javax.swing.JLabel jLabel4 ;
        javax.swing.JLabel jLabel5 ;
        javax.swing.JLabel jLabel6 ;
        javax.swing.JLabel jLabel9 ;
        javax.swing.JLabel jLabel13 ;
        javax.swing.JLabel jLabel14 ;
        javax.swing.JLabel jLabel19 ;
        javax.swing.JLabel jLabel24 ;
        javax.swing.JLabel jLabel25;
        javax.swing.JLabel jLabel26 ;
        javax.swing.JLabel jLabel27 ;
        javax.swing.JLabel jLabel28 ;
        javax.swing.JLabel jLabel29 ;

        javax.swing.JScrollPane jScrollPane1;
        javax.swing.JScrollPane jScrollPane2;
        javax.swing.JSeparator jSeparator2;
        
        bgDocumentoIdentidad = new javax.swing.ButtonGroup();
        bgSexo = new javax.swing.ButtonGroup();
        panelMov = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtNombres = new javax.swing.JTextField();
        txtDocumentoIdentidad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnRegistra = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        dcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        moduloContrato = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jhcHabilitarFechas = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jdcFechaFinal = new com.toedter.calendar.JDateChooser();
        jdcFechaInicial = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTrabajador = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        rbCE = new javax.swing.JRadioButton();
        rbDNI = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtApellidoMaterno = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtApellidoPaterno = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        rbFemenino = new javax.swing.JRadioButton();
        rbMasculino = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        lblMensajeBuscar = new javax.swing.JLabel();

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

        txtNombres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 280, 30));

        txtDocumentoIdentidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtDocumentoIdentidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 150, 30));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 460, 120));

        btnRegistra.setBackground(new java.awt.Color(255, 254, 255));
        btnRegistra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/log-out.png"))); // NOI18N
        btnRegistra.setText("REGISTRAR");
        btnRegistra.setBorder(null);
        btnRegistra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistra.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistra.addActionListener(evt -> btnRegistraActionPerformed(evt));
        jPanel1.add(btnRegistra, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 590, 110, 100));

        btnRegresar.setBackground(new java.awt.Color(255, 254, 255));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saliir.png"))); // NOI18N
        btnRegresar.setText("CERRAR");
        btnRegresar.setBorder(null);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegresar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegresar.addActionListener(evt -> btnRegresarActionPerformed(evt));
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 110, 100));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Nombres");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        btnLimpiar.setBackground(new java.awt.Color(255, 254, 255));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/borrar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setBorder(null);
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLimpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLimpiar.addActionListener(evt -> btnLimpiarActionPerformed(evt));
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, 100, 100));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Fecha Nacimiento");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, -1, -1));

        dcFechaNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(dcFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 200, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Tipo Documento");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        moduloContrato.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        moduloContrato.setText("MODULO TRABAJADOR");
        jPanel1.add(moduloContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 830, 10));

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

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 620, 40));

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

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 620, 70));

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
        tableTrabajador.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTrabajadorMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableTrabajador);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 620, 430));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rbCE.setBackground(new java.awt.Color(255, 255, 255));
        bgDocumentoIdentidad.add(rbCE);
        rbCE.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbCE.setText("CE");
        jPanel10.add(rbCE, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 50, -1));

        rbDNI.setBackground(new java.awt.Color(255, 255, 255));
        bgDocumentoIdentidad.add(rbDNI);
        rbDNI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbDNI.setText("DNI");
        jPanel10.add(rbDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 130, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Descripcion");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("N° Documento");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Apellido Paterno");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        txtApellidoMaterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 130, 30));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Correo Electronico");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        txtCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 270, 30));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Apellido Materno");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));

        txtApellidoPaterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 130, 30));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Teléfono");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, -1, -1));

        txtTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 150, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Sexo");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rbFemenino.setBackground(new java.awt.Color(255, 255, 255));
        bgSexo.add(rbFemenino);
        rbFemenino.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbFemenino.setText("Femenino");
        jPanel11.add(rbFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, -1));

        rbMasculino.setBackground(new java.awt.Color(255, 255, 255));
        bgSexo.add(rbMasculino);
        rbMasculino.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rbMasculino.setText("Masculino");
        jPanel11.add(rbMasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 210, 40));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Dirección");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        txtDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 450, 30));

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMensajeBuscar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblMensajeBuscar.setForeground(new java.awt.Color(255, 255, 255));
        lblMensajeBuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel12.add(lblMensajeBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 600, 40));

        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 640, 620, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void limpiar(){
        txtDocumentoIdentidad.setText("");
        txtNombres.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtDocumentoIdentidad.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        dcFechaNacimiento.setDate(null);
        rbDNI.setSelected(false);
        rbCE.setSelected(false);
        rbMasculino.setSelected(false);
        rbFemenino.setSelected(false);
        txtDescripcion.setText("");
        
        rbDNI.setSelected(true);
        rbMasculino.setSelected(true);
    }

    private void btnRegistraActionPerformed(java.awt.event.ActionEvent evt) { //NOSONAR
        if (modoEdicion) {
            if(trabajadorActual!=null){
                try {
                    trabajadorActual.setNombres(txtNombres.getText());
                    trabajadorActual.setApellidoPaterno(txtApellidoPaterno.getText());
                    trabajadorActual.setApellidoMaterno(txtApellidoMaterno.getText());
                    trabajadorActual.setDocumentoIdentidad(txtDocumentoIdentidad.getText());
                    trabajadorActual.setTipoDocumento(rbDNI.isSelected() ? "DNI" : "CE");
                    trabajadorActual.setTelefono(txtTelefono.getText());
                    trabajadorActual.setCorreo(txtCorreo.getText());
                    trabajadorActual.setSexo(rbMasculino.isSelected() ? "M" : "F");

                    Date fecha = dcFechaNacimiento.getDate();
                    if (fecha != null) {
                        trabajadorActual.setFechaNacimiento(fecha);
                    }

                    trabajadorActual.setDireccion(txtDireccion.getText());
                    trabajadorActual.setDescripcion(txtDescripcion.getText());
                    
                    String mensajeValidacion = negocio.validarTrabajador(trabajadorActual);
                    if (mensajeValidacion != null) {
                        JOptionPane.showMessageDialog(this, mensajeValidacion);
                        return;
                    }

                    if (negocio.actualizarTrabajador(trabajadorActual)) {
                        btnRegresar.setText(ConstantesUITrabajador.BOTON_CERRAR);
                        btnRegistra.setText(ConstantesUITrabajador.BOTON_REGISTRAR);
                        btnLimpiar.setText(ConstantesUITrabajador.BOTON_LIMPIAR);
                        modoEdicion=false;
                        trabajadorActual=null;
                        JOptionPane.showMessageDialog(this, "Trabajador actualizado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar trabajador.");
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar trabajador: " + e.getMessage());
                }
            }
        } else {
            try {
                Trabajador t = new Trabajador();
                t.setNombres(txtNombres.getText());
                t.setApellidoPaterno(txtApellidoPaterno.getText());
                t.setApellidoMaterno(txtApellidoMaterno.getText());
                t.setDocumentoIdentidad(txtDocumentoIdentidad.getText());
                t.setTipoDocumento(rbDNI.isSelected() ? "DNI" : "CE");
                t.setTelefono(txtTelefono.getText());
                t.setCorreo(txtCorreo.getText());
                t.setSexo(rbMasculino.isSelected() ? "M" : "F");

                Date fecha = dcFechaNacimiento.getDate();
                if (fecha != null) {
                    t.setFechaNacimiento(fecha);
                }

                t.setDireccion(txtDireccion.getText());
                t.setDescripcion(txtDescripcion.getText());
                
                String mensajeValidacion = negocio.validarTrabajador(t);
                if (mensajeValidacion != null) {
                    JOptionPane.showMessageDialog(this, mensajeValidacion);
                    return;
                }

                if (negocio.registrarTrabajador(t)) {
                    JOptionPane.showMessageDialog(this, "Trabajador registrado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar trabajador.");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al registrar Trabajador: " + e.getMessage());
            }
        }

        limpiar();
        listarTrabajadoresTabla(null, null);
    }
    
    private void cargarFormularioConTrabajador(Trabajador t){
        txtNombres.setText(t.getNombres());
        txtApellidoPaterno.setText(t.getApellidoPaterno());
        txtApellidoMaterno.setText(t.getApellidoMaterno());
        txtDocumentoIdentidad.setText(t.getDocumentoIdentidad());
        txtTelefono.setText(t.getTelefono());
        txtCorreo.setText(t.getCorreo());

        if (t.getTipoDocumento().equalsIgnoreCase("DNI")) {
            rbDNI.setSelected(true);
        } else if (t.getTipoDocumento().equalsIgnoreCase("CE")) {
            rbCE.setSelected(true);
        }

        dcFechaNacimiento.setDate(t.getFechaNacimiento());
        
        txtDireccion.setText(t.getTelefono());
        txtDescripcion.setText(t.getDescripcion());
        
        if (t.getSexo().equalsIgnoreCase("M")) {
            rbMasculino.setSelected(true);
        } else if (t.getTipoDocumento().equalsIgnoreCase("F")) {
            rbFemenino.setSelected(true);
        }
    }

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//NOSONAR //GEN-FIRST:event_btnRegresarActionPerformed
        if (modoEdicion) {
            if (trabajadorActual != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar al trabajador?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {

                    if (negocio.eliminarTrabajador(trabajadorActual.getIdTrabajador())) {
                        JOptionPane.showMessageDialog(this, "Trabajador eliminado correctamente.");
                        limpiar();
                        listarTrabajadoresTabla(null, null);
                        modoEdicion = false;
                        trabajadorActual = null;
                        btnRegistra.setText(ConstantesUITrabajador.BOTON_REGISTRAR);
                        btnLimpiar.setText(ConstantesUITrabajador.BOTON_LIMPIAR);
                        btnRegresar.setText(ConstantesUITrabajador.BOTON_CERRAR);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al eliminar trabajador.");
                    }
                }
            }
        } else {
            FrmMenu menu = new FrmMenu();
            menu.setVisible(true);
            this.setVisible(false);
        }        
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//NOSONAR //GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
        modoEdicion = false;
        trabajadorActual = null;
        btnRegistra.setText(ConstantesUITrabajador.BOTON_REGISTRAR);
        btnLimpiar.setText(ConstantesUITrabajador.BOTON_LIMPIAR);
        btnRegresar.setText(ConstantesUITrabajador.BOTON_CERRAR);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void tableTrabajadorMouseClicked(java.awt.event.MouseEvent evt) {//NOSONAR //GEN-FIRST:event_tableTrabajadorMouseClicked
        tableTrabajador.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = tableTrabajador.getSelectedRow();
            if (fila != -1) {
                String documentoIdentidad = tableTrabajador.getValueAt(fila, 4).toString();
                
                Trabajador t = negocio.buscarPorDocumentoIdentidad(documentoIdentidad);

                if (t != null) {
                    cargarFormularioConTrabajador(t);
                    trabajadorActual = t;
                    btnRegistra.setText("EDITAR");
                    btnLimpiar.setText("NUEVO");
                    modoEdicion = true;
                    
                    btnRegresar.setText("ELIMINAR");
                }
            }
        }
    });
    }//GEN-LAST:event_tableTrabajadorMouseClicked
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmTrabajador().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistra;
    private javax.swing.JButton btnRegresar;
    private com.toedter.calendar.JDateChooser dcFechaNacimiento;
    private com.toedter.calendar.JDateChooser jdcFechaFinal;
    private com.toedter.calendar.JDateChooser jdcFechaInicial;
    private javax.swing.JCheckBox jhcHabilitarFechas;
    private javax.swing.JLabel lblMensajeBuscar;
    private javax.swing.JRadioButton rbCE;
    private javax.swing.JRadioButton rbDNI;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JTable tableTrabajador;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDocumentoIdentidad;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
