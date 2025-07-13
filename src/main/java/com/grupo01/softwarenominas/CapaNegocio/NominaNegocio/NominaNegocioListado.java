
package com.grupo01.softwarenominas.capanegocio.nominanegocio;


import javax.swing.JTable;

import com.grupo01.softwarenominas.capapersistencia.NominaDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NominaNegocioListado {
    private final NominaDAO nominaDAO;
    
    public NominaNegocioListado() {
        this.nominaDAO = new NominaDAO();
    }

    public int listarNominasPorPeriodo(JTable tabla, int idPeriodo) {
        return nominaDAO.listarNominasPorPeriodo(tabla, idPeriodo);
    }
    

    
}
