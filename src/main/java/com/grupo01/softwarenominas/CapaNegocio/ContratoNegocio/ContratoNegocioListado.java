
package com.grupo01.softwarenominas.CapaNegocio.ContratoNegocio;

import com.grupo01.softwarenominas.CapaEntidad.Contrato;
import com.grupo01.softwarenominas.CapaEntidad.DetalleContrato;
import com.grupo01.softwarenominas.CapaEntidad.Trabajador;
import com.grupo01.softwarenominas.CapaPersistencia.ContratoDAO;
import com.grupo01.softwarenominas.CapaPersistencia.TrabajadorDAO;

public class ContratoNegocioListado {
    ContratoDAO contratoDAO = new ContratoDAO();
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
     

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
    
    public Contrato obtenerContratoPorDocumentoIdentidad(String documentoIdentidad) {
        try{
            return contratoDAO.obtenerContratoPorDocumentoIdentidad(documentoIdentidad);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public DetalleContrato obtenerDetalleContratoPorDocumentoIdentidad(String documentoIdentidad) {
        try{
            return contratoDAO.obtenerDetalleContratoPorDocumentoIdentidad(documentoIdentidad);
        }
        catch (Exception ex) {
            return null;
        }
    }  
}