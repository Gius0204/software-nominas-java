package com.grupo01.softwarenominas.capanegocio.trabajadornegocio;

import java.util.Date;

import javax.swing.JTable;

import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;

public class TrabajadorNegocioListado {
  private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

    public int listarTrabajadoresFiltrado(JTable tabla) {
        return trabajadorDAO.listarTrabajadoresFiltrado(tabla);
    }

    public int listarTrabajadoresFiltradoPorFecha(JTable tabla, Date fechaInicio, Date fechaFin) {
        return trabajadorDAO.listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin);
    }
}
