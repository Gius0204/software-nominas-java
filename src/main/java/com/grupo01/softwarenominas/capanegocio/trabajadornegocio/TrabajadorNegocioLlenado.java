
package com.grupo01.softwarenominas.capanegocio.trabajadornegocio;

import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Usuario
 */

@Getter
@Setter
@AllArgsConstructor
public class TrabajadorNegocioLlenado {
    private final TrabajadorDAO trabajadorDAO;
    public TrabajadorNegocioLlenado() {
        this.trabajadorDAO = new TrabajadorDAO();
    }

    public Trabajador buscarPorDNI(String dni) {
        return trabajadorDAO.buscarPorDNI(dni);
    }

    public Trabajador buscarPorDocumentoIdentidad(String documentoIdentidad) {
        return trabajadorDAO.buscarPorDocumentoIdentidad(documentoIdentidad);
    }
}
