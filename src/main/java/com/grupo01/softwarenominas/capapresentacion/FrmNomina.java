package com.grupo01.softwarenominas.capapresentacion;

import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;
import com.grupo01.softwarenominas.capapersistencia.NominaDAO;
import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;
import com.grupo01.softwarenominas.capapersistencia.ContratoPeriodoDAO;
import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capaentidad.ContratoPeriodo;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capaentidad.Nomina;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ResultadoOperacion;
import com.grupo01.softwarenominas.capanegocio.nominanegocio.NominaNegocioRegistro;
import com.grupo01.softwarenominas.capapresentacion.utils.Utilidades;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class FrmNomina extends javax.swing.JFrame {
    private final transient ContratoDAO contratoDAO = new ContratoDAO();
    
    transient TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    
    private transient NominaDAO nominas = new NominaDAO();

    transient Utilidades utilidades = new Utilidades();


    public FrmNomina() {
        initComponents();
        
        inicializarFormulario();
    }
    
    private int obtenerIdPeriodoSeleccionado1() {
        PeriodoPago periodo = (PeriodoPago) cmbPeriodoPago1.getSelectedItem();
        return (periodo != null) ? periodo.getIdPeriodoPago() : -1;
    }
    
    private int obtenerIdPeriodoSeleccionado2() {
        PeriodoPago periodo = (PeriodoPago) cmbPeriodoPago2.getSelectedItem();
        return (periodo != null) ? periodo.getIdPeriodoPago() : -1;
    }
    
    private void inicializarFormulario() {
        setLocationRelativeTo(null);
        
        inicializarTablaContrato(tableContrato);
        inicializarTablaNominas(tableNominas);
        
        nominas.cargarPeriodosPago(cmbPeriodoPago1);
        nominas.cargarPeriodosPago(cmbPeriodoPago2);
        cmbMetodoPago.removeAllItems();
        cmbMetodoPago.addItem("EN EFECTIVO");
        cmbMetodoPago.addItem("TRANSFERENCIA BANCARIA");
        cmbMetodoPago.addItem("BILLETERA DIGITAL");
        cmbMetodoPago.setSelectedIndex(0);
        
        tableContrato.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tableContrato.getSelectionModel().addListSelectionListener(e -> {
            int seleccionados = tableContrato.getSelectedRowCount();
            lblSeleccionados.setText("Seleccionados: " + seleccionados + " contratos");
        });
        
        cmbPeriodoPago2.addActionListener(e -> listarContratosTabla(tableContrato));
        
        cmbPeriodoPago1.addActionListener(e -> listarNominasTabla(tableNominas));

    }
    
    public void listarContratosTabla(JTable tabla){
        int idPeriodo = obtenerIdPeriodoSeleccionado2();
        if (idPeriodo > 0) {
            int resultados = contratoDAO.listarContratosPorPeriodo(tabla, idPeriodo);

            utilidades.ajustarTabla(tabla);

            lblContratos.setText(
                switch (resultados) {
                    case 0 -> "No se encontraron contratos en la base de datos.";
                    case 1 -> "Se encontró 1 contrato.";
                    default -> "Se encontraron " + resultados + " contratos.";
                }
            );
        } else {
            inicializarTablaContrato(tableContrato);
        }
        
    }
    
    public void inicializarTablaContrato(JTable tabla){
            DefaultTableModel modelo = new DefaultTableModel();
            String[] columnasDeseadas = {
                "FechaInicio", "FechaFin", "HorasTotales", "HorasTrabajadas", "EstadoPago", "DocumentoIdentidad", "Nombres",
                "ApellidoPaterno", "ApellidoMaterno", "AreaNombre", "Especialidad",
                "TipoContratoNombre", "CargoNombre"
            };
            for (String col : columnasDeseadas) {
                modelo.addColumn(col);
            }
            tabla.setModel(modelo);
            
            utilidades.ajustarTabla(tabla);

            lblContratos.setText("Seleccione un Periodo antes, por favor");
    }
    
    public void listarNominasTabla(JTable tabla){
        int idPeriodo = obtenerIdPeriodoSeleccionado1();
        if (idPeriodo > 0) {
            int resultados = nominas.listarNominasPorPeriodo(tabla, idPeriodo);

            utilidades.ajustarTabla(tabla);

            lblNominas.setText(
                switch (resultados) {
                    case 0 -> "No se encontraron nóminas en la base de datos.";
                    case 1 -> "Se encontró 1 nómina.";
                    default -> "Se encontraron " + resultados + " nóminas.";
                }
            );

        } else {
            inicializarTablaNominas(tableNominas);
        }
    }
    
    public void inicializarTablaNominas(JTable tabla){
        DefaultTableModel modelo = new DefaultTableModel();

        String[] columnasDeseadas = {
            "Nombres", "DocumentoIdentidad", "PeriodoPago", "HorasTotales", "HorasTrabajadas", "EstadoPago",
            "SalarioBase", "PagoHorasExtras", "GratificacionLegal","AsignacionFamiliar", "CTS", "TotalIngresos", 
            "DescuentoHorasNoCompletadas", "DescuentoSeguroSalud", "DescuentoSeguroVida","DescuentoSeguroAccidentes", "DescuentoAFP", "DescuentoRenta",
            "TotalDescuentos", "SueldoNeto", "MetodoPago"
        };


        for (String col : columnasDeseadas) {
            modelo.addColumn(col);
        }

        tabla.setModel(modelo);

        utilidades.ajustarTabla(tabla);

        lblNominas.setText("Seleccione un Periodo antes, por favor");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

      javax.swing.JLabel labelMetodoPago ;
      javax.swing.JLabel labelMetodoPago1 ;
      javax.swing.JLabel labelMetodoPago2 ;
      javax.swing.JLabel moduloNomina ;
      javax.swing.JLabel jLabel25 ;
      javax.swing.JLabel jLabel26 ;


      javax.swing.JPanel panelMov ;
      javax.swing.JPanel jPanel1 ;
      javax.swing.JPanel jPanel3 ;
      javax.swing.JPanel jPanel4 ;
      javax.swing.JPanel jPanel5 ;
      javax.swing.JPanel jPanel7 ;
      javax.swing.JPanel jPanel10 ;
      javax.swing.JPanel jPanel11 ;
      javax.swing.JPanel jPanel15 ;

      javax.swing.JScrollPane jScrollPane2;
      javax.swing.JScrollPane jScrollPane3;

      javax.swing.JSeparator jSeparator1;
      javax.swing.JSeparator jSeparator2;

      javax.swing.JButton btnProcesar ;
      javax.swing.JButton btnRegresar ;

        panelMov = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        moduloNomina = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        labelMetodoPago = new javax.swing.JLabel();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        btnProcesar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        labelMetodoPago1 = new javax.swing.JLabel();
        cmbPeriodoPago2 = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lblSeleccionados = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lblContratos = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableContrato = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        lblNominas = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableNominas = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        labelMetodoPago2 = new javax.swing.JLabel();
        cmbPeriodoPago1 = new javax.swing.JComboBox<>();
        lblProcesados = new javax.swing.JLabel();

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

        moduloNomina.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        moduloNomina.setText("MODULO NOMINA");
        jPanel1.add(moduloNomina, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 830, 10));

        labelMetodoPago.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelMetodoPago.setText("Metodo de Pago");
        jPanel1.add(labelMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 660, 130, -1));

        jPanel1.add(cmbMetodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 660, 140, -1));

        btnProcesar.setBackground(new java.awt.Color(204, 255, 204));
        btnProcesar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProcesar.setForeground(new java.awt.Color(255, 0, 0));
        btnProcesar.setText("Procesar");
        btnProcesar.addActionListener(evt -> btnProcesarActionPerformed(evt));
        jPanel1.add(btnProcesar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 660, 200, 30));

        btnRegresar.setBackground(new java.awt.Color(255, 254, 255));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/regresar.png"))); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.setBorder(null);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegresar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegresar.addActionListener(evt -> btnRegresarActionPerformed(evt));
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 700, 110, 100));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("CONTRATOS A PROCESAR");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 530, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMetodoPago1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelMetodoPago1.setText("Periodo de PAGO");
        jPanel3.add(labelMetodoPago1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, -1));

        jPanel3.add(cmbPeriodoPago2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 240, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 530, 60));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(0, 153, 153));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(5, 40));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, 40));

        lblSeleccionados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeleccionados.setText("Seleccionados: 0");
        jPanel5.add(lblSeleccionados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 20));

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblContratos.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblContratos.setForeground(new java.awt.Color(255, 255, 255));
        lblContratos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContratos.setText("Mensaje: ");
        jPanel15.add(lblContratos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 330, 30));

        jPanel5.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 350, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 530, -1));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableContrato.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tableContrato);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 530, 410));

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNominas.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblNominas.setForeground(new java.awt.Color(255, 255, 255));
        lblNominas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNominas.setText("Mensaje: ");
        jPanel11.add(lblNominas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 510, 30));

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 590, 530, 40));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("NOMINAS PROCESADAS");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 530, 40));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableNominas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableNominas);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 530, 410));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMetodoPago2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelMetodoPago2.setText("Periodo de PAGO");
        jPanel10.add(labelMetodoPago2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, -1));

        jPanel10.add(cmbPeriodoPago1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 240, 30));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 530, 60));

        lblProcesados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblProcesados, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 650, 530, 160));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 820));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//NOSONAR //GEN-FIRST:event_btnRegresarActionPerformed
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//NOSONAR //GEN-FIRST:event_btnProcesarActionPerformed
        try {
            int[] filasSeleccionadas = tableContrato.getSelectedRows();
            if (filasSeleccionadas.length == 0) {
                lblProcesados.setText("Seleccione al menos un contrato.");
                return;
            }

            PeriodoPago p = (PeriodoPago) cmbPeriodoPago2.getSelectedItem();
            String metodoPago = cmbMetodoPago.getSelectedItem().toString();

            NominaDAO dao = new NominaDAO();
            ContratoPeriodoDAO contratoPeriodoDAO = new ContratoPeriodoDAO();
            
    
            ArrayList<String> trabajadoresConDeuda = new ArrayList<>();

            int registrosExitosos = 0;

            boolean existeGlobalPendiente = dao.existePeriodoAnteriorPendiente(p.getIdPeriodoPago());

            if (existeGlobalPendiente) {
                for (int fila : filasSeleccionadas) {
                    String documentoIdentidad = tableContrato.getValueAt(fila, 5).toString();

                    Trabajador t = trabajadorDAO.buscarPorDocumentoIdentidad(documentoIdentidad);
                    Contrato c = contratoDAO.obtenerContratoPorDocumentoIdentidad(documentoIdentidad);

                    if (t != null && c != null) {
                        boolean tienePendiente = dao.existePeriodoAnteriorPendientePorContrato(
                            c.getIdContrato(), p.getIdPeriodoPago()
                        );

                        if (tienePendiente) {
                            trabajadoresConDeuda.add(t.getNombreCompleto());
                        }
                    }
                }

                if (!trabajadoresConDeuda.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("<html>Antes de procesar estos contratos debe pagar los períodos anteriores de los trabajadores, incluidos:<br>");
                    for (String nombre : trabajadoresConDeuda) {
                        sb.append("• ").append(nombre).append("<br>");
                    }
                    sb.append("</html>");
                    lblProcesados.setText(sb.toString());
                } else {
                    lblProcesados.setText("Error: Hay períodos anteriores sin pagar. Verifique todos los contratos.");
                }
                return;
            }

            for (int fila : filasSeleccionadas) {
                String documentoIdentidad = tableContrato.getValueAt(fila, 5).toString();

                Trabajador t = trabajadorDAO.buscarPorDocumentoIdentidad(documentoIdentidad);
                Contrato c = contratoDAO.obtenerContratoPorDocumentoIdentidad(documentoIdentidad);
                DetalleContrato dc = contratoDAO.obtenerDetalleContratoPorDocumentoIdentidad(documentoIdentidad);
                TipoContrato tc = nominas.obtenerTipoContratoPorId(c.getIdTipoContrato());
                c.setTipoContrato(tc);

                if (t != null && c != null && dc != null) {
                    ContratoPeriodo cp = contratoPeriodoDAO.obtenerContratoPeriodo(
                        c.getIdContrato(), p.getIdPeriodoPago()
                    );

                    if (cp == null) {
                        JOptionPane.showMessageDialog(
                            null, 
                            "No se encontró un ContratoPeriodo válido para el trabajador " + t.getNombreCompleto()
                        );
                        continue;
                    }

                    cp.setContrato(c);
                    cp.setPeriodo(p);
                    

                    Nomina nomina = new NominaNegocioRegistro().procesarNominaCompleta(cp, dc, metodoPago,dc.getTipoSeguroSalud());

                    ResultadoOperacion resultado = dao.insertarNominaCompleta(nomina);

                    if (!resultado.isExito()) {
                        lblProcesados.setText("Error: " + resultado.getMensaje());
                    } else {
                        registrosExitosos++;
                    }
                }
            }

            if (registrosExitosos > 0) {
                lblProcesados.setText("Se registraron " + registrosExitosos + " nóminas exitosamente.");
                listarContratosTabla(tableContrato);
                listarNominasTabla(tableNominas);
            } else {
                lblProcesados.setText("No se registró ninguna nómina.");
            }

        } catch (Exception ex) {
            lblProcesados.setText("Error al procesar la nómina: " + ex.getMessage());
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmNomina().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JComboBox<PeriodoPago> cmbPeriodoPago1;
    private javax.swing.JComboBox<PeriodoPago> cmbPeriodoPago2;
    
    private javax.swing.JLabel lblContratos;
    private javax.swing.JLabel lblNominas;
    private javax.swing.JLabel lblProcesados;
    private javax.swing.JLabel lblSeleccionados;
    private javax.swing.JTable tableContrato;
    private javax.swing.JTable tableNominas;
    // End of variables declaration//GEN-END:variables
}
