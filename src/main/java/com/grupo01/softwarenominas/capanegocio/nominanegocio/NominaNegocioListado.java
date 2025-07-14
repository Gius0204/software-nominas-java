
package com.grupo01.softwarenominas.capanegocio.nominanegocio;


import javax.swing.JTable;

import com.grupo01.softwarenominas.capapersistencia.NominaDAO;

public class NominaNegocioListado {
    private final NominaDAO nominaDAO = new NominaDAO();

    public int listarNominasPorPeriodo(JTable tabla, int idPeriodo) {
        return nominaDAO.listarNominasPorPeriodo(tabla, idPeriodo);
    }
    

    
}
