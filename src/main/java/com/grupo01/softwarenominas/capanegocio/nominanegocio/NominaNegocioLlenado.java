
package com.grupo01.softwarenominas.capanegocio.nominanegocio;

import javax.swing.JComboBox;

import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capapersistencia.NominaDAO;

public class NominaNegocioLlenado {
    private final NominaDAO nominaDAO = new NominaDAO();
    public void cargarPeriodosPago(JComboBox<PeriodoPago> comboBoxPeriodo) {
        nominaDAO.cargarPeriodosPago(comboBoxPeriodo);
    }

    public TipoContrato obtenerTipoContratoPorId(int idTipoContrato) {
        return nominaDAO.obtenerTipoContratoPorId(idTipoContrato);
    }
}
