package com.grupo01.softwarenominas.CapaPresentacion;

import com.grupo01.softwarenominas.CapaEntidad.Area;
import com.grupo01.softwarenominas.CapaEntidad.Especialidad;
import com.grupo01.softwarenominas.CapaEntidad.Contrato;
import com.grupo01.softwarenominas.CapaEntidad.DetalleContrato;
import com.grupo01.softwarenominas.CapaEntidad.Cargo;
import com.grupo01.softwarenominas.CapaEntidad.TipoContrato;
import com.grupo01.softwarenominas.CapaEntidad.Trabajador;
import com.grupo01.softwarenominas.CapaNegocio.ContratoNegocio.ResultadoOperacion;
import com.grupo01.softwarenominas.CapaPersistencia.ContratoDAO;
import com.grupo01.softwarenominas.CapaPersistencia.TrabajadorDAO;
import com.grupo01.softwarenominas.CapaPresentacion.CapaPresentacionValidaciones.FiltroDescripcion;
import com.grupo01.softwarenominas.CapaPresentacion.CapaPresentacionValidaciones.FiltroNumerico;
import com.grupo01.softwarenominas.CapaPresentacion.CapaPresentacionValidaciones.FiltroSalario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

import com.grupo01.softwarenominas.CapaPresentacion.Utilities.Utilidades;
import com.grupo01.softwarenominas.CapaPresentacion.Utilities.ConstantesUIContrato;

import javax.swing.table.DefaultTableModel;

import javax.swing.text.AbstractDocument;

public class frmContrato extends javax.swing.JFrame {   

    private static final long serialVersionUID = 1L;
    transient Utilidades utilidades = new Utilidades();    
    transient ContratoDAO contratoDAO = new ContratoDAO();
    transient TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    transient private Trabajador trabajadorActual;
    transient private Contrato contratoActual;
    transient private DetalleContrato detalleContratoActual;
    
    private boolean modoEdicionContrato = false;    
    
    public frmContrato() {
        initComponents();
        inicializarFormulario();
        inicializarTrueOrFalseComponents();
        configurarListeners();
        configurarListenersCombobox();
        listenersFechas();
        rtnESSALUD.setSelected(true);      
        inicializarCamposValidados();       
        configurarFocusListeners();
    }

    private void inicializarFormulario() {
        setLocationRelativeTo(null);
        trabajadorDAO.cargarAreas(cmbArea);
        contratoDAO.cargarTiposContrato(cmbTipoContrato);
        contratoDAO.cargarCargos(cmbCargo);       
        inicializarTablaContrato(jtbTabla);       
        listarContratosTabla(jtbTabla, null, null, "", "");        
        jdcFechaInicial.addPropertyChangeListener("date", evt -> {
            Date fechaInicio = jdcFechaInicial.getDate();
            Date fechaFin = jdcFechaFinal.getDate();

            if (fechaInicio != null && fechaFin != null) {
                if (fechaFin.before(fechaInicio)) {
                    jdcFechaFinal.setDate(null);
                    lblMensajeBuscar.setText("Vuelva a escoger la Fecha Fin para esta Fecha Inicio elegida.");
                }
            }
        });
    }
     
    private void inicializarTrueOrFalseComponents(){
        txtSalario.setEditable(false);
        jdcFechaFin.setDate(null);
        jdcFechaFin.setEnabled(false);
        btnRegistrar.setEnabled(false);
        btnEditarHorasTrabajadas.setEnabled(false);       
        jhcHabilitarFechas.setSelected(true);
        jhcSeguroSalud.setSelected(true);
    }
    
    private void inicializarCamposValidados(){
        ((AbstractDocument) txtDNI.getDocument()).setDocumentFilter(new FiltroNumerico(9));
        ((AbstractDocument) txtHorasTotales.getDocument()).setDocumentFilter(new FiltroNumerico(3));
        ((AbstractDocument) jtxDescripcion.getDocument()).setDocumentFilter(new FiltroDescripcion());
        ((AbstractDocument) txtSalario.getDocument()).setDocumentFilter(new FiltroSalario());
        
        ((AbstractDocument) txtDocumentoBuscar.getDocument()).setDocumentFilter(new FiltroNumerico(9));
        ((AbstractDocument) txtNombresBuscar.getDocument()).setDocumentFilter(new FiltroDescripcion());
    }
    
    public void inicializarTablaContrato(JTable tabla){
            DefaultTableModel modelo = new DefaultTableModel();
            String[] columnasDeseadas = {
                "FechaInicio", "FechaFin", "HorasTotales", "DocumentoIdentidad", "Nombres", 
                "ApellidoPaterno", "ApellidoMaterno", "AreaNombre", "Especialidad",
                "TipoContratoNombre", "CargoNombre"
            };
            for (String col : columnasDeseadas) {
                modelo.addColumn(col);
            }
            tabla.setModel(modelo);          
            utilidades.ajustarTabla(tabla);
    }

    private void configurarListeners() {
        ActionListener duracionListener = e -> calcularFechaFin();
        rtn3meses.addActionListener(duracionListener);
        rtn6meses.addActionListener(duracionListener);
        rtn1anio.addActionListener(duracionListener);
        txtDNI.addActionListener(e -> buscarTrabajadorPorDNI());
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

    private void configurarListenersCombobox(){
        cmbTipoContrato.addActionListener(e -> actualizarSalarioSiListo());
        cmbCargo.addActionListener(e -> actualizarSalarioSiListo());
        cmbArea.addActionListener(e -> actualizarSalarioSiListo());
        cmbEspecialidad.addActionListener(e -> actualizarSalarioSiListo());
    }
    
    private void configurarFocusListeners(){
        txtDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                lblMensaje.setForeground(UIManager.getColor("Label.foreground"));
                jpanelContenedor.setBackground(UIManager.getColor(ConstantesUIContrato.COLOR_PANEL_BACKGROUND));
                    
                String dni = txtDNI.getText().trim();
                if (!dni.matches("^\\d{8,9}$")) {
                    lblMensaje.setText("El DNI debe tener entre 8 y 9 dígitos numéricos.");                  
                }
                validarFormularioCompleto();
            }
        });

        txtHorasTotales.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                lblMensaje.setForeground(UIManager.getColor("Label.foreground"));
                jpanelContenedor.setBackground(UIManager.getColor(ConstantesUIContrato.COLOR_PANEL_BACKGROUND));
                        
                String horasTexto = txtHorasTotales.getText().trim();
                try {
                    int horas = Integer.parseInt(horasTexto);
                    if (horas < 80 || horas > 200) {
                        lblMensaje.setText("Las horas deben estar entre 80 y 200.");
                        
                    }
                    validarFormularioCompleto();
                } catch (NumberFormatException ex) {
                    lblMensaje.setText("Ingrese un número válido para horas.");
                }
            }
        });

        jtxDescripcion.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                lblMensaje.setForeground(UIManager.getColor("Label.foreground"));
                jpanelContenedor.setBackground(UIManager.getColor(ConstantesUIContrato.COLOR_PANEL_BACKGROUND));
                    
                String descripcion = jtxDescripcion.getText().trim();
                if (descripcion.length() > 250 || !descripcion.matches("[a-zA-Z0-9\\s]*")) {
                    lblMensaje.setText("La descripción debe tener solo letras y números (máx. 250).");
                    
                }
                validarFormularioCompleto();
            }
        });

        txtSalario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                lblMensaje.setForeground(UIManager.getColor("Label.foreground"));
                jpanelContenedor.setBackground(UIManager.getColor(ConstantesUIContrato.COLOR_PANEL_BACKGROUND));
                    
                String tipo = cmbTipoContrato.getSelectedItem().toString();
                if (tipo.equalsIgnoreCase(ConstantesUIContrato.TEXTO_SERVICIO_EXTERNO)) {
                    String salarioTexto = txtSalario.getText().trim();
                    try {
                        double salario = Double.parseDouble(salarioTexto);
                        if (salario < 1025 || salario >= 1000000) {
                            lblMensaje.setText("El salario debe ser mayor al mínimo de 1025 soles y tener hasta 6 cifras.");
                            
                        }
                        validarFormularioCompleto();
                    } catch (NumberFormatException ex) {
                        lblMensaje.setText("Ingrese un número válido para salario.");
                    }
                } else {
                }
            }
        });
        
        cmbTipoContrato.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                lblMensaje.setForeground(UIManager.getColor("Label.foreground"));
                jpanelContenedor.setBackground(UIManager.getColor(ConstantesUIContrato.COLOR_PANEL_BACKGROUND));
                
                TipoContrato tipoContrato = (TipoContrato) cmbTipoContrato.getSelectedItem();
                if (tipoContrato.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_TIPO_CONTRATO_DEFAULT )){
                    lblMensaje.setText("Seleccionar un tipo de contrato.");
                }
                
                validarFormularioCompleto();
            }
        });
        cmbCargo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                lblMensaje.setForeground(UIManager.getColor("Label.foreground"));
                jpanelContenedor.setBackground(UIManager.getColor(ConstantesUIContrato.COLOR_PANEL_BACKGROUND));
                
                Cargo cargo = (Cargo) cmbCargo.getSelectedItem();
                if (cargo.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_CARGO_DEFAULT)){
                    lblMensaje.setText("Seleccionar un cargo.");
                }
                
                validarFormularioCompleto();
            }
        });
        cmbArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                lblMensaje.setForeground(UIManager.getColor("Label.foreground"));
                jpanelContenedor.setBackground(UIManager.getColor(ConstantesUIContrato.COLOR_PANEL_BACKGROUND));
                
                Area area = (Area) cmbArea.getSelectedItem();
                if (area.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_AREA_DEFAULT)){
                    lblMensaje.setText("Seleccionar un area.");
                }
                
                validarFormularioCompleto();
            }
        });
        cmbEspecialidad.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                lblMensaje.setForeground(UIManager.getColor("Label.foreground"));
                jpanelContenedor.setBackground(UIManager.getColor(ConstantesUIContrato.COLOR_PANEL_BACKGROUND));
                
                Especialidad especialidad = (Especialidad) cmbEspecialidad.getSelectedItem();
                
                if (especialidad.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_ESPECIALIDAD_DEFAULT)){
                    lblMensaje.setText("Seleccionar una especialidad.");
                }             
                validarFormularioCompleto();
            }
        });         
    }
    
    private void listenersFechas(){
        jdcFechaInicio.getJCalendar().setMinSelectableDate(new java.util.Date());

        jdcFechaInicio.getDateEditor().addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                if (jdcFechaInicio.getDate() == null || (!rtn3meses.isSelected() && !rtn6meses.isSelected() && !rtn1anio.isSelected())) {
                    jdcFechaFin.setDate(null);
                } else {
                    calcularFechaFin();
                }
                jdcFechaFin.setEnabled(false); 
            }
        });
    }
    
    private void validarFormularioCompleto() {
        boolean esValido = true;

        String dni = txtDNI.getText().trim();
        if (!dni.matches("^\\d{8,9}$")) esValido = false;

        try {
            int horas = Integer.parseInt(txtHorasTotales.getText().trim());
            if (horas < 80 || horas > 200) esValido = false;
        } catch (NumberFormatException e) {
            esValido = false;
        }

        String descripcion = jtxDescripcion.getText().trim();
        if (descripcion.length() > 250 || !descripcion.matches("[a-zA-Z0-9\\s]*")) esValido = false;

        String tipo = cmbTipoContrato.getSelectedItem().toString();
        String salarioTexto = txtSalario.getText().trim();

        if (tipo.equalsIgnoreCase(ConstantesUIContrato.TEXTO_SERVICIO_EXTERNO)) {
            try {
                double salario = Double.parseDouble(salarioTexto);
                if (salario < 1025 || salario >= 1000000) esValido = false;
            } catch (NumberFormatException e) {
                esValido = false;
            }
        }else{
            TipoContrato tipoContrato = (TipoContrato) cmbTipoContrato.getSelectedItem();
            Cargo cargo = (Cargo) cmbCargo.getSelectedItem();
            Area area = (Area) cmbArea.getSelectedItem();
            Especialidad especialidad = (Especialidad) cmbEspecialidad.getSelectedItem();
                
            if (tipoContrato.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_TIPO_CONTRATO_DEFAULT )){
                esValido = false;
            }
            if (cargo.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_CARGO_DEFAULT)){
                esValido = false;
            }
            if (area.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_AREA_DEFAULT)){
                esValido = false;
            }
            if (especialidad.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_ESPECIALIDAD_DEFAULT)){
                esValido = false;
            }       
            try {
                double salario = Double.parseDouble(salarioTexto);
                if (salario < 1025 || salario >= 1000000) esValido = false;
            } catch (NumberFormatException e) {
                esValido = false;
            }
        }

        Date fechaInicio = jdcFechaInicio.getDate();
        Date fechaFin = jdcFechaFin.getDate();

        if (fechaInicio == null || fechaFin == null) {
            esValido = false;
        }
        btnRegistrar.setEnabled(esValido);
    }
    
    private void calcularFechaFin() {
        Date fechaInicio = jdcFechaInicio.getDate();
        if (fechaInicio == null) {
            jdcFechaFin.setDate(null);
            return;
        }

        int meses = rtn3meses.isSelected() ? 3 : rtn6meses.isSelected() ? 6 : rtn1anio.isSelected() ? 12 : 0;

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

    private void buscarTrabajadorPorDNI() {
        String dni = txtDNI.getText().trim();
        if (dni.isEmpty()) return;

        trabajadorActual = trabajadorDAO.buscarPorDocumentoIdentidad(dni);

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
    
    private void limpiar(){
        txtDNI.setText("");
        txtNombres.setText("");
        cmbArea.setSelectedIndex(0);
        cmbCargo.setSelectedIndex(0);
        cmbTipoContrato.setSelectedIndex(0);
        txtHorasTotales.setText("");
        txtSalario.setText("");
        jdcFechaInicio.setDate(null);
        jdcFechaFin.setDate(null);
        rtn3meses.setSelected(false);
        rtn6meses.setSelected(false);
        rtn1anio.setSelected(false);
        jcbAsignacion.setSelected(false);
        jcbSeguroAccidentes.setSelected(false);
        jcbSeguroVida.setSelected(false);
        jtxDescripcion.setText("");     
        btnRegistrar.setEnabled(false);
    }
    
    private void salirModoEditar(){
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
        TipoContrato tipoContrato = (TipoContrato) cmbTipoContrato.getSelectedItem();
        Cargo cargo = (Cargo) cmbCargo.getSelectedItem();
        Area area = (Area) cmbArea.getSelectedItem();
        Especialidad especialidad = (Especialidad) cmbEspecialidad.getSelectedItem();

        if (tipoContrato == null || cargo == null || area == null || especialidad == null) {
            return;
        }
        
        if (cmbTipoContrato.getSelectedIndex() == 0 ||
            cmbCargo.getSelectedIndex() == 0 ||
            cmbArea.getSelectedIndex() == 0 ||
            cmbEspecialidad.getSelectedIndex() == 0) {
            return;
        }

        String tipoContratoNombre = tipoContrato.getNombre();

        if (!tipoContratoNombre.equalsIgnoreCase(ConstantesUIContrato.TEXTO_SERVICIO_EXTERNO)) {
            
            double salario = contratoDAO.obtenerSalarioBase(
                area.getIdArea(),
                especialidad.getIdEspecialidad(),
                cargo.getIdCargo(),
                tipoContrato.getIdTipoContrato()
            );

            if (salario != -1) {
                txtSalario.setText(String.valueOf(salario));
                txtSalario.setEditable(false);
                lblMensaje.setText("Se muestra el salario base del trabajador");
            } else {
                txtSalario.setText("");
                txtSalario.setEditable(false);
                lblMensaje.setText("No se encontró salario base para su elección.");
            }
        } else {
            txtSalario.setText("");
            txtSalario.setEditable(true);
            lblMensaje.setText("Ingrese el salario manualmente. No debe ser menor al sueldo mínimo.");
        }
    }

    public boolean validarSalario(String valor) {
        try {
            double salario = Double.parseDouble(valor);
            return salario >= 1025 && salario <= 999999;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean validarHoras(String horasStr) {
        try {
            int horas = Integer.parseInt(horasStr);
            return horas >= 80 && horas <= 200;
        } catch (NumberFormatException e) {
            return false;
        }
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
            
            trabajadorDAO.cargarAreas(cmbArea);
            
            trabajadorDAO.cargarEspecialidadesPorArea(cmbEspecialidad, contratoActual.getArea().getIdArea());
            
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
    
    public void listarContratosTabla(JTable tabla, Date fechaInicio, Date fechaFin, String documentoIdentidad, String nombres){
        int resultados = contratoDAO.listarContratosFiltrado(tabla, fechaInicio, fechaFin, documentoIdentidad, nombres);
        
        utilidades.ajustarTabla(tabla);

        if (resultados == 0) {
            lblMensajeBuscar.setText("No se encontraron contratos en la base de datos.");
        } else if (resultados == 1) {
            lblMensajeBuscar.setText("Se encontró " + resultados + " contrato.");
        } else {
            lblMensajeBuscar.setText("Se encontraron " + resultados + " contratos.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgDuracion = new javax.swing.ButtonGroup();
        bgTipoSeguroSalud = new javax.swing.ButtonGroup();
        PanelMov = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtNombres = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxDescripcion = new javax.swing.JTextArea();
        btnRegresar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cmbArea = new javax.swing.JComboBox<>();
        btnLimpiar = new javax.swing.JButton();
        cmbCargo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        cmbTipoContrato = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        txtHorasTotales = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        jdcFechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jcbAsignacion = new javax.swing.JCheckBox();
        jcbSeguroVida = new javax.swing.JCheckBox();
        jcbSeguroAccidentes = new javax.swing.JCheckBox();
        rtnEPS = new javax.swing.JRadioButton();
        rtnESSALUD = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jhcSeguroSalud = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        ModuloContrato = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jdcFechaFinal = new com.toedter.calendar.JDateChooser();
        jdcFechaInicial = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();
        txtDocumentoBuscar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNombresBuscar = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jhcHabilitarFechas = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblMensajeBuscar = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbEspecialidad = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        rtn6meses = new javax.swing.JRadioButton();
        rtn3meses = new javax.swing.JRadioButton();
        rtn1anio = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jpanelContenedor = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        btnEditarHorasTrabajadas = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtbTabla = new javax.swing.JTable();

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

        txtNombres.setEditable(false);
        txtNombres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 250, 30));

        txtDNI.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
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
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 620, 110, 100));

        jLabel14.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        jLabel14.setText("Trabajador");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 80, -1));

        cmbArea.setBackground(new java.awt.Color(254, 255, 255));
        cmbArea.setBorder(null);
        cmbArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAreaActionPerformed(evt);
            }
        });
        jPanel1.add(cmbArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 220, 30));

        btnLimpiar.setBackground(new java.awt.Color(255, 254, 255));
        btnLimpiar.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
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
        rtnESSALUD.setText(ConstantesUIContrato.TEXTO_ESSALUD);
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

        ModuloContrato.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 24)); // NOI18N
        ModuloContrato.setText("MODULO CONTRATO");
        jPanel1.add(ModuloContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

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
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 100, 40));

        txtDocumentoBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDocumentoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumentoBuscarActionPerformed(evt);
            }
        });
        jPanel3.add(txtDocumentoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 210, 30));

        jLabel16.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 12)); // NOI18N
        jLabel16.setText("Por Fechas :");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        txtNombresBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNombresBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresBuscarActionPerformed(evt);
            }
        });
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
        jhcHabilitarFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jhcHabilitarFechasActionPerformed(evt);
            }
        });
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
        btnEditarHorasTrabajadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarHorasTrabajadasActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditarHorasTrabajadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, 280, -1));

        btnRegistrar.setBackground(new java.awt.Color(255, 254, 255));
        btnRegistrar.setFont(new java.awt.Font(ConstantesUIContrato.FUENTE_SEGOE_UI, 1, 14)); // NOI18N
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/log-out.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setBorder(null);
        btnRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
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
        jtbTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbTablaMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jtbTabla);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 620, 390));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        frmMenu menu = new frmMenu();
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
        salirModoEditar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cmbAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAreaActionPerformed
        cmbArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Area areaSeleccionada = (Area) cmbArea.getSelectedItem();
                if (areaSeleccionada != null) {
                    int idArea = areaSeleccionada.getIdArea();
                    trabajadorDAO.cargarEspecialidadesPorArea(cmbEspecialidad, idArea);
                }
            }
        });
    }//GEN-LAST:event_cmbAreaActionPerformed

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void btnEditarHorasTrabajadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarHorasTrabajadasActionPerformed
        //int idContratoSeleccionado = contratoActual.getIdContrato(); // Tu método para extraer ID seleccionado
        frmDialogHorasTrabajadas dialog = new frmDialogHorasTrabajadas(this, true); // modal
        dialog.inicializarConContrato(contratoActual);
        dialog.setVisible(true);

    }//GEN-LAST:event_btnEditarHorasTrabajadasActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        
        if(modoEdicionContrato){
            try {
                if (trabajadorActual == null || contratoActual == null || detalleContratoActual == null) {
                    JOptionPane.showMessageDialog(this, "Faltan datos para actualizar el contrato.");
                    return;
                }

                TipoContrato tipoContrato = (TipoContrato) cmbTipoContrato.getSelectedItem();
                Cargo cargo = (Cargo) cmbCargo.getSelectedItem();
                Area area = (Area) cmbArea.getSelectedItem();
                Especialidad especialidad = (Especialidad) cmbEspecialidad.getSelectedItem();

                Date fechaInicio = jdcFechaInicio.getDate();
                Date fechaFin = jdcFechaFin.getDate();
                double salario = Double.parseDouble(txtSalario.getText());
                int horasTotales = Integer.parseInt(txtHorasTotales.getText());
                String descripcion = jtxDescripcion.getText();

                contratoActual.setIdTipoContrato(tipoContrato.getIdTipoContrato());
                contratoActual.setIdCargo(cargo.getIdCargo());
                contratoActual.setFechaInicio(fechaInicio);
                contratoActual.setFechaFin(fechaFin);
                contratoActual.setSalarioBase(salario);
                contratoActual.setHorasTotales(horasTotales);
                contratoActual.setDescripcion(descripcion);
                contratoActual.setIdArea(area.getIdArea());
                contratoActual.setIdEspecialidad(especialidad.getIdEspecialidad());

                boolean actualizado = contratoDAO.actualizarContrato(contratoActual);

                if (!actualizado) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar el contrato.");
                    return;
                }
                
                // Determinar tipo de seguro salud seleccionado
                String tipoSeguroSalud = "NINGUNO";


                if (jhcSeguroSalud.isSelected()) {
                    if (rtnESSALUD.isSelected()) {
                        tipoSeguroSalud = ConstantesUIContrato.TEXTO_ESSALUD;
                    } else if (rtnEPS.isSelected()) {
                        tipoSeguroSalud = "EPS";
                    } else {
                        JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de seguro de salud.");
                        return;
                    }
                }

                detalleContratoActual.setTipoSeguroSalud(tipoSeguroSalud);
                detalleContratoActual.setTieneSeguroDeVida(jcbSeguroVida.isSelected());
                detalleContratoActual.setTieneSeguroDeAccidentes(jcbSeguroAccidentes.isSelected());
                detalleContratoActual.setTieneAsignacionFamiliar(jcbAsignacion.isSelected());

                boolean detalleActualizado = contratoDAO.actualizarDetalleContrato(detalleContratoActual);

                if (detalleActualizado) {
                    
                    salirModoEditar();
                    JOptionPane.showMessageDialog(this, "Contrato y detalle actualizados correctamente.");
                    btnRegistrar.setEnabled(false);
                    
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(this, "Contrato actualizado, pero falló la actualización del detalle.");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar contrato: " + e.getMessage());
            }
                    
        } else{
            try {
                if (trabajadorActual == null) {
                    lblMensaje.setText("Debe ingresar un DNI válido primero.");
                    return;
                }

                TipoContrato tipoContrato = (TipoContrato) cmbTipoContrato.getSelectedItem();
                Cargo cargo = (Cargo) cmbCargo.getSelectedItem();

                Date fechaInicio = jdcFechaInicio.getDate();
                Date fechaFin = jdcFechaFin.getDate();

                if (fechaInicio == null || fechaFin == null) {
                    lblMensaje.setText("Seleccionar las fechas y/o duración.");
                    return;
                }

                double salario = Double.parseDouble(txtSalario.getText());
                int horasTotales = Integer.parseInt(txtHorasTotales.getText());
                String descripcion = jtxDescripcion.getText();

                Area area = (Area) cmbArea.getSelectedItem();
                Especialidad especialidad = (Especialidad) cmbEspecialidad.getSelectedItem();
                
                if (tipoContrato.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_TIPO_CONTRATO_DEFAULT )){
                    lblMensaje.setText("Seleccionar un tipo de contrato.");
                    return;
                }
                if (cargo.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_CARGO_DEFAULT)){
                    lblMensaje.setText("Seleccionar un cargo.");
                    return;
                }
                if (area.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_AREA_DEFAULT)){
                    lblMensaje.setText("Seleccionar un area.");
                    return;
                }
                if (especialidad.getNombre().equalsIgnoreCase(ConstantesUIContrato.TEXTO_ESPECIALIDAD_DEFAULT)){
                    lblMensaje.setText("Seleccionar una especialidad.");
                    return;
                }

                Contrato contrato = new Contrato();
                contrato.setIdTrabajador(trabajadorActual.getIdTrabajador());
                contrato.setIdTipoContrato(tipoContrato.getIdTipoContrato());
                contrato.setIdCargo(cargo.getIdCargo());
                contrato.setFechaInicio(fechaInicio);
                contrato.setFechaFin(fechaFin);
                contrato.setSalarioBase(salario);
                contrato.setHorasTotales(horasTotales);
                contrato.setDescripcion(descripcion);
                contrato.setIdArea(area.getIdArea());
                contrato.setIdEspecialidad(especialidad.getIdEspecialidad());

                ResultadoOperacion resultado = contratoDAO.registrarContrato(contrato);
                String mensajeHTML = "<html><div style='text-align: center; width: 300px;'>" + resultado.getMensaje() + "</div></html>";
                lblMensaje.setText(mensajeHTML);
                lblMensaje.setForeground(Color.RED);
                
                if (resultado.isExito()) {
                    String tipoSeguroSalud = "NINGUNO";
                    
                    
                    if (jhcSeguroSalud.isSelected()) {
                        if (rtnESSALUD.isSelected()) {
                            tipoSeguroSalud = ConstantesUIContrato.TEXTO_ESSALUD;
                        } else if (rtnEPS.isSelected()) {
                            tipoSeguroSalud = "EPS";
                        } else {
                            JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de seguro de salud.");
                            return;
                        }
                    }

                    DetalleContrato detalle = new DetalleContrato();
                    detalle.setIdContrato(resultado.getIdGenerado());
                    detalle.setTipoSeguroSalud(tipoSeguroSalud);
                    detalle.setTieneSeguroDeVida(jcbSeguroVida.isSelected());
                    detalle.setTieneSeguroDeAccidentes(jcbSeguroAccidentes.isSelected());
                    detalle.setTieneAsignacionFamiliar(jcbAsignacion.isSelected());
                    
                    ResultadoOperacion resultadoDetalle = contratoDAO.registrarDetalleContrato(detalle);
                    String mensaje2HTML = "<html><div style='text-align: center; width: 300px;'>" + resultado.getMensaje() + "</div></html>";
                    lblMensaje.setText(mensaje2HTML);
                    
                    if (resultadoDetalle.isExito()) {
                        
                        lblMensaje.setForeground(new Color(0,128,0)); // para error
                        
                        btnRegistrar.setEnabled(false);
                        
                        limpiar();
                    } else {
                    }

                } else {
                }

            } catch (Exception e) {
                
            }
        }
        
        
        
        listarContratosTabla(jtbTabla, null, null, "", "");
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txtDocumentoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumentoBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentoBuscarActionPerformed

    private void txtNombresBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresBuscarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
        String documento = txtDocumentoBuscar.getText().trim();
        String nombres = txtNombresBuscar.getText().trim();

        Date fechaInicio = null;
        Date fechaFin = null;

        if (jhcHabilitarFechas.isSelected()) {
            if (jdcFechaInicial.getDate() != null) {
                fechaInicio = jdcFechaInicial.getDate();
            }
            
            if (jdcFechaFinal.getDate() != null) {
                fechaFin = jdcFechaFinal.getDate();
            }
            
            if (fechaInicio != null && fechaFin != null) {
                if (fechaFin.before(fechaInicio)) {
                    jdcFechaFinal.setDate(null);
                    lblMensajeBuscar.setText("Vuelva a escoger la Fecha Fin para esta Fecha Inicio elegida.");
                    return;
                }
            }
            
        }

        listarContratosTabla(jtbTabla, fechaInicio, fechaFin, documento, nombres);
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jhcHabilitarFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jhcHabilitarFechasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jhcHabilitarFechasActionPerformed

    private void jtbTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbTablaMouseClicked
        // TODO add your handling code here:
        jtbTabla.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = jtbTabla.getSelectedRow();
            if (fila != -1) {
                String documentoIdentidad = jtbTabla.getValueAt(fila, 3).toString();
                lblMensaje.setText("Contrato Encontrado de Trabajador con Documento de Identidad: " + documentoIdentidad);
                
                Trabajador t = trabajadorDAO.buscarPorDocumentoIdentidad(documentoIdentidad);
                
                Contrato c = contratoDAO.obtenerContratoPorDocumentoIdentidad(documentoIdentidad);
                DetalleContrato dc = contratoDAO.obtenerDetalleContratoPorDocumentoIdentidad(documentoIdentidad);


                if (t != null && c!=null && dc!=null) {
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
    }//GEN-LAST:event_jtbTablaMouseClicked
  
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(frmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmContrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmContrato().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ModuloContrato;
    private javax.swing.JPanel PanelMov;
    private javax.swing.ButtonGroup bgDuracion;
    private javax.swing.ButtonGroup bgTipoSeguroSalud;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditarHorasTrabajadas;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<Area> cmbArea;
    private javax.swing.JComboBox<Cargo> cmbCargo;
    private javax.swing.JComboBox<Especialidad> cmbEspecialidad;
    private javax.swing.JComboBox<TipoContrato> cmbTipoContrato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JCheckBox jcbAsignacion;
    private javax.swing.JCheckBox jcbSeguroAccidentes;
    private javax.swing.JCheckBox jcbSeguroVida;
    private com.toedter.calendar.JDateChooser jdcFechaFin;
    private com.toedter.calendar.JDateChooser jdcFechaFinal;
    private com.toedter.calendar.JDateChooser jdcFechaInicial;
    private com.toedter.calendar.JDateChooser jdcFechaInicio;
    private javax.swing.JCheckBox jhcHabilitarFechas;
    private javax.swing.JCheckBox jhcSeguroSalud;
    private javax.swing.JPanel jpanelContenedor;
    private javax.swing.JTable jtbTabla;
    private javax.swing.JTextArea jtxDescripcion;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblMensajeBuscar;
    private javax.swing.JRadioButton rtn1anio;
    private javax.swing.JRadioButton rtn3meses;
    private javax.swing.JRadioButton rtn6meses;
    private javax.swing.JRadioButton rtnEPS;
    private javax.swing.JRadioButton rtnESSALUD;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDocumentoBuscar;
    private javax.swing.JTextField txtHorasTotales;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNombresBuscar;
    private javax.swing.JTextField txtSalario;
    // End of variables declaration//GEN-END:variables
}
