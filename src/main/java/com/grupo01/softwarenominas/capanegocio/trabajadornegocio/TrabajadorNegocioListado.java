package com.grupo01.softwarenominas.capanegocio.trabajadornegocio;

import java.util.Date;

import javax.swing.JTable;

import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TrabajadorNegocioListado {
    private final TrabajadorDAO trabajadorDAO;

    public TrabajadorNegocioListado() {
      this.trabajadorDAO = new TrabajadorDAO();
    }

    public int listarTrabajadoresFiltrado(JTable tabla) {
        return trabajadorDAO.listarTrabajadoresFiltrado(tabla);
    }

    public int listarTrabajadoresFiltradoPorFecha(JTable tabla, Date fechaInicio, Date fechaFin) {
        return trabajadorDAO.listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin);
    }
}
