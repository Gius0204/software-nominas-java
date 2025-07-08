package com.grupo01.softwarenominas.capanegocio;
import java.util.Date;

import javax.swing.JTable;

import com.grupo01.softwarenominas.capapersistencia.NominaDAO;
import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;


public class NominaNegocioVarios {
    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    private final NominaDAO nominaDAO = new NominaDAO();

    public void listarTrabajadoresFiltrado(JTable tabla) {
        trabajadorDAO.listarTrabajadoresFiltrado(tabla);
    }

    public void listarTrabajadoresFiltradoPorFecha(JTable tabla, Date fechaInicio, Date fechaFin) {
        trabajadorDAO.listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin);
    }
    

}