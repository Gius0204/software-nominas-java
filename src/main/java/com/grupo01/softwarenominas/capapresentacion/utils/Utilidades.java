package com.grupo01.softwarenominas.capapresentacion.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.grupo01.softwarenominas.capaentidad.PeriodoPago;

public class Utilidades {
    public void ajustarTabla(JTable tabla){
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
    
    public int obtenerIdPeriodoSeleccionado(JComboBox<PeriodoPago> combo) {
        PeriodoPago periodo = (PeriodoPago) combo.getSelectedItem();
        return (periodo != null) ? periodo.getIdPeriodoPago() : -1;
    }

    public String construirMensajeHtmlLista(List<String> lista) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>Antes de procesar estos contratos debe pagar los períodos anteriores de los trabajadores, incluidos:<br>");
        for (String nombre : lista) {
            sb.append("• ").append(nombre).append("<br>");
        }
        sb.append("</html>");
        return sb.toString();
    }


}
