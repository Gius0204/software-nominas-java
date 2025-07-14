
package com.grupo01.softwarenominas.capanegocio.trabajadornegocio;

import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;

/**
 *
 * @author Usuario
 */
public class TrabajadorNegocioRegistro {
    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

    public boolean registrarTrabajador(Trabajador t) {
        return trabajadorDAO.registrarTrabajador(t);
    }

    public boolean actualizarTrabajador(Trabajador t) {
        return trabajadorDAO.actualizarTrabajador(t);
    }

    public boolean eliminarTrabajador(int id) {
        return trabajadorDAO.eliminarTrabajador(id);
    }
}
