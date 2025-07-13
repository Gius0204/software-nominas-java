
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

import javax.swing.JTable;
import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContratoNegocioListado {
    private final ContratoDAO contratoDAO;
    public ContratoNegocioListado() {
        this.contratoDAO = new ContratoDAO();
    }

    public int listarContratosPorPeriodo(JTable tabla, int idPeriodo) {
        return contratoDAO.listarContratosPorPeriodo(tabla, idPeriodo);
    }

    public int listarContratosFiltrado(JTable tabla, java.util.Date fechaInicio, java.util.Date fechaFin, String documentoIdentidad, String nombres) {
        return contratoDAO.listarContratosFiltrado(tabla, fechaInicio, fechaFin, documentoIdentidad, nombres);
    }

    public void listarContratoPeriodosPorContrato(JTable jtbTablaHorasTrabajadas, int idContrato) {
        contratoDAO.listarContratoPeriodosPorContrato(jtbTablaHorasTrabajadas, idContrato);
    }
}