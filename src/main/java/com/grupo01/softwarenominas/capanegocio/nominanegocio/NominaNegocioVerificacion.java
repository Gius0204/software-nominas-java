
package com.grupo01.softwarenominas.capanegocio.nominanegocio;

import java.sql.SQLException;

import com.grupo01.softwarenominas.capapersistencia.NominaDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NominaNegocioVerificacion {

    private final NominaDAO nominaDAO;
    public NominaNegocioVerificacion() {
        this.nominaDAO = new NominaDAO();
    }
    
    public boolean existePeriodoAnteriorPendiente(int idPeriodoPago) {
        try {
            return nominaDAO.existePeriodoAnteriorPendiente(idPeriodoPago);
        } catch (SQLException e) {
            return false;
        }
    }
    public boolean existePeriodoAnteriorPendientePorContrato(int idContrato, int idPeriodoActual) {
        try {
            return nominaDAO.existePeriodoAnteriorPendientePorContrato(idContrato, idPeriodoActual);
        } catch (SQLException e) {
            return false;
        }
    }
}
