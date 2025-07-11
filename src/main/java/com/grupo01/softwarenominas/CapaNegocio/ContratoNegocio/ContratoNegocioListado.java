
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

import javax.swing.JTable;
import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;
import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;

public class ContratoNegocioListado {
    private final ContratoDAO contratoDAO = new ContratoDAO();
    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    
    
    public record Resultado(Trabajador trabajador, String texto) {}
    
    public Resultado buscarTrabajadorPorDocumentoIdentidad(String documentoIdentidad) {
        if (documentoIdentidad.isEmpty()) return new Resultado(null, "");
        
        Trabajador t = trabajadorDAO.buscarPorDocumentoIdentidad(documentoIdentidad);
        String texto = "";

        if (t != null) {
            texto = "Trabajador Encontrado";

        } else {
            texto = "No se encontró al trabajador, vuelva a intentarlo antes de registrar el contrato.";
        }
        
        return new Resultado(t,texto);
    }
    
    
    public Trabajador buscarPorDocumentoIdentidad(String documentoIdentidad) {
        try{
            return trabajadorDAO.buscarPorDocumentoIdentidad(documentoIdentidad);
        }
        catch (Exception ex) {
            return null;
        }
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