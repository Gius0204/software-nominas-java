package com.grupo01.softwarenominas.capapresentacion.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;

import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capapresentacion.validacionespresentacion.FiltroTexto;
import com.toedter.calendar.JDateChooser;
import com.grupo01.softwarenominas.capapresentacion.validacionespresentacion.FiltroNumerico;

public class Utilidades {
    private Utilidades() {
        // Constructor privado para evitar instanciación
    }
    public static void ajustarTabla(JTable tabla){
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        int extraPadding = 15;

        for (int column = 0; column < tabla.getColumnCount(); column++) {
            TableColumn tableColumn = tabla.getColumnModel().getColumn(column);
            int preferredWidth = 50;
            int maxWidth = 500;

            for (int row = 0; row < tabla.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tabla.getCellRenderer(row, column);
                Component c = tabla.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + tabla.getIntercellSpacing().width + extraPadding;
                preferredWidth = Math.max(preferredWidth, width);
            }

            TableCellRenderer headerRenderer = tabla.getTableHeader().getDefaultRenderer();
            Component headerComp = headerRenderer.getTableCellRendererComponent(
                tabla, tableColumn.getHeaderValue(), false, false, -1, column);
            int headerWidth = headerComp.getPreferredSize().width + tabla.getIntercellSpacing().width + extraPadding;
            preferredWidth = Math.max(preferredWidth, headerWidth);

            preferredWidth = Math.min(preferredWidth, maxWidth);

            tableColumn.setPreferredWidth(preferredWidth);
        }

        int anchoTotal = 0;
        for (int col = 0; col < tabla.getColumnCount(); col++) {
            anchoTotal += tabla.getColumnModel().getColumn(col).getPreferredWidth();
        }
        
        tabla.setPreferredScrollableViewportSize(new Dimension(
            anchoTotal, tabla.getRowHeight() * tabla.getRowCount()
        ));
        
        tabla.setIntercellSpacing(new Dimension(5, 5));
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer())
            .setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public static int obtenerIdPeriodoSeleccionado(JComboBox<PeriodoPago> combo) {
        PeriodoPago periodo = (PeriodoPago) combo.getSelectedItem();
        return (periodo != null) ? periodo.getIdPeriodoPago() : -1;
    }

    public static String construirMensajeHtmlLista(List<String> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>Antes de procesar estos contratos debe pagar los períodos anteriores de los trabajadores, incluidos:<br>");
        for (String nombre : lista) {
            sb.append("• ").append(nombre).append("<br>");
        }
        sb.append("</html>");
        return sb.toString();
    }



    /**
     * Aplica filtro numérico con longitud máxima
     */
    public static void aplicarFiltroNumerico(int maxLen, JTextField... campos) {
        for (JTextField campo : campos) {
            ((AbstractDocument) campo.getDocument()).setDocumentFilter(new FiltroNumerico(maxLen));
        }
    }

    /**
     * Aplica filtro de texto básico (letras, números, espacios)
     */
    public static void aplicarFiltroTextoGeneral(JTextComponent... campos) {
        for (JTextComponent campo : campos) {
            ((AbstractDocument) campo.getDocument()).setDocumentFilter(new FiltroTexto());
        }
    }

    /**
     * Limpia el texto de los campos pasados
     */
    public static void limpiarCamposTexto(JTextComponent... campos) {
        for (JTextComponent campo : campos) {
            campo.setText("");
        }
    }

    /**
     * Establece el valor inicial en los comboBox
     */
    public static void resetearCombos(JComboBox<?>... combos) {
        for (JComboBox<?> combo : combos) {
            if (combo.getItemCount() > 0) {
                combo.setSelectedIndex(0);
            }
        }
    }

    /**
     * Crea y establece el modelo para una tabla con las columnas dadas
     */
    public static void configurarTabla(JTable tabla, String... columnas) {
        DefaultTableModel modelo = new DefaultTableModel();
        for (String col : columnas) {
            modelo.addColumn(col);
        }
        tabla.setModel(modelo);
        ajustarTabla(tabla);
    }

    /**
     * Aplica color de error en campos o etiquetas
     */
    public static void mostrarError(JLabel labelMensaje, JPanel contenedor, String mensaje) {
        labelMensaje.setText(mensaje);
        labelMensaje.setForeground(Color.RED);
        contenedor.setBackground(new Color(255, 228, 225)); // rosa suave de fondo
    }

    public static void mostrarExito(JLabel labelMensaje, JPanel contenedor, String mensaje) {
        labelMensaje.setText(mensaje);
        labelMensaje.setForeground(new Color(0, 128, 0));
        contenedor.setBackground(new Color(225, 255, 225)); // verde suave de fondo
    }

    public static FocusAdapter crearFocusAdapter(Runnable validador, JLabel lblMensaje) {
        return new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validador.run();
                lblMensaje.setForeground(Color.BLACK);
            }
        };
    }

    public static void mostrarMensaje(JLabel lbl, String mensaje, Color color) {
        lbl.setText(mensaje);
        lbl.setForeground(color);
    }

    public static void configurarBusquedaPorFechas(JCheckBox check, JDateChooser inicio, JDateChooser fin, Runnable accion) {
        check.addActionListener(e -> {
            if (check.isSelected()) {
                Date fIni = inicio.getDate();
                Date fFin = fin.getDate();
                if (fIni != null && fFin != null) {
                    accion.run();
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar ambas fechas.");
                    check.setSelected(false);
                }
            } else {
                accion.run();
            }
        });
    }

    public static void setHabilitado(boolean habilitar, JComponent... componentes) {
        for (JComponent c : componentes) {
            c.setEnabled(habilitar);
        }
    }

    public static void setSeleccionadoYHabilitado(boolean seleccionado, boolean habilitado, AbstractButton... botones) {
        for (AbstractButton b : botones) {
            b.setSelected(seleccionado);
            b.setEnabled(habilitado);
        }
    }

    public static void setSeleccionado(boolean seleccionado, AbstractButton... botones) {
        for (AbstractButton b : botones) {
            b.setSelected(seleccionado);
        }
    }

    public static String validarSalario(String salarioTexto) {
        try {
            double salario = Double.parseDouble(salarioTexto);
            if (salario < 1025 || salario >= 1_000_000) {
                return "El salario debe ser mayor al mínimo de 1025 soles y hasta 6 cifras.";
            }
        } catch (NumberFormatException ex) {
            return "Ingrese un número válido para salario.";
        }
        return null;
    }

    public static boolean validarTexto(String texto, int maxLongitud, String regex) {
        return texto.length() <= maxLongitud && texto.matches(regex);
    }

    public static boolean validarDNI(String dni) {
        return dni.matches("^\\d{8,9}$");
    }

    public static String mensajeCantidad(int cantidad, String singular, String plural) {
        return switch (cantidad) {
            case 0 -> "No se encontraron " + plural + " en la base de datos.";
            case 1 -> "Se encontró 1 " + singular + ".";
            default -> "Se encontraron " + cantidad + " " + plural + ".";
        };
    }

}
