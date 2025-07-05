
package com.grupo01.softwarenominas.CapaNegocio.ContratoNegocio;
import com.grupo01.softwarenominas.CapaEntidad.Contrato;
import com.grupo01.softwarenominas.CapaEntidad.DetalleContrato;
import com.grupo01.softwarenominas.CapaPersistencia.ContratoDAO;
import com.grupo01.softwarenominas.CapaPersistencia.TrabajadorDAO;

public class ContratoNegocioRegistro {
    
    ContratoDAO contratoDAO = new ContratoDAO();
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
      
    public String RegistrarContrato(Contrato c){
        String mensaje = "";
        try{

            int idContratoGenerado = contratoDAO.registrarContrato2(c);
            
            if (idContratoGenerado != -1) {
                    mensaje = "Contrato registrado correctamente.";
            } else{
                mensaje = "Fallo al registrar el contrato.";
            }


        } catch(Exception e){
            mensaje = "Error al registrar contrato: " + e.getMessage();
        }
        
        return mensaje;
    }
    
    public String RegistrarDetalleContrato(DetalleContrato dc){
        String mensaje = "";
        try{
            boolean detalleregistrado = contratoDAO.registrarDetalleContrato2(dc);
            
            if (detalleregistrado) {
                mensaje = "Detalle del contrato registrado correctamente.";
            } else{
                mensaje = "Fallo al registrar el detalle del contrato.";
            }
        }catch(Exception e){
            mensaje = "Error al registrar el detalle del contrato: " + e.getMessage();
        }
        
        return mensaje;
    }
    
    public String ActualizarContrato(Contrato c){
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
    
    public String ActualizarDetalleContrato(DetalleContrato dc){
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
    
    public String EliminarDetalleContrato(DetalleContrato dc){
        String mensaje = "";
        try{
            boolean eliminadoDetalle = contratoDAO.eliminarDetalleContrato(dc.getIdDetalleContrato());
            
            if (eliminadoDetalle) {
                mensaje = "Detalle del contrato eliminado correctamente.";
            }else{
                mensaje = "Fallo al eliminar el detalle del contrato.";
            }
            
        }catch(Exception e){
            mensaje = "No hay contrato válido para eliminar: " + e.getMessage();
        }
        
        return mensaje;
    }
    
    public String EliminarContrato(Contrato c){
        String mensaje = "";
        try{
            boolean eliminadoContrato = contratoDAO.eliminarContrato(c.getIdContrato());
            
            if (eliminadoContrato) {
                mensaje = "Contrato eliminado correctamente.";
            }else{
                mensaje = "Fallo al eliminar el contrato.";
            }
        }catch(Exception e){
            mensaje = "No hay contrato válido para eliminar: " + e.getMessage();
        }
        
        return mensaje;
    }
}