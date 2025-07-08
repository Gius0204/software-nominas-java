
package com.grupo01.softwarenominas.capanegocio.contratonegocio;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;
import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;

public class ContratoNegocioRegistro {
    
    ContratoDAO contratoDAO = new ContratoDAO();
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
      
    
    
    public String actualizarContrato(Contrato c){
        String mensaje = "";
        try{
            boolean actualizado = contratoDAO.actualizarContrato(c);
            
            if (actualizado) {
                mensaje = "Contrato actualizado correctamente.";
            }else{
                mensaje = "Fallo al actualizar el contrato.";
            }
        }catch(Exception e){
            mensaje = "Error al actualizar contrato: " + e.getMessage();
        }
        
        return mensaje;
    }
    
    public String actualizarDetalleContrato(DetalleContrato dc){
        String mensaje = "";
        try{
            boolean detalleactualizado = contratoDAO.actualizarDetalleContrato(dc);
            
            if (detalleactualizado) {
                mensaje = "Detalle del contrato actualizado correctamente.";
            }else{
                mensaje = "Fallo al actualizar el detalle del contrato.";
            }
            
        }catch(Exception e){
            mensaje = "Error al actualizar detalle del contrato: " + e.getMessage();
        }
        
        return mensaje;
    }
    
    
}