
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ContratoNegocioCalculo {
    
    private final ContratoDAO contratoDAO;

    public ContratoNegocioCalculo() {
        this.contratoDAO = new ContratoDAO();
    }
    
    public Date calcularFechaFin(Date fechaInicio, int meses) {
        if (fechaInicio == null) {
            return null;
        }
        
        if (meses > 0) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaInicio);
            cal.add(Calendar.MONTH, meses);
            return cal.getTime();
        } else {
            return null;
        }
    } 
    
    public record Resultado(String salario, boolean estado, String mensaje) {}
    public Resultado actualizarSalarioSiListo(TipoContrato tipoContrato, Cargo cargo, Area area, Especialidad especialidad) {
        if (tipoContrato == null || cargo == null || area == null || especialidad == null) {
            return new Resultado("", false, "");
        }
        
        String tipoContratoNombre = tipoContrato.getNombre();
        boolean estado = false;
        String mensaje = "";

        if (!tipoContratoNombre.equalsIgnoreCase("SERVICIO EXTERNO")) {
            double salario = obtenerSalarioBase(
                area.getIdArea(),
                especialidad.getIdEspecialidad(),
                cargo.getIdCargo(),
                tipoContrato.getIdTipoContrato()
            );

            if (salario != -1) {
                estado = false;
                mensaje = "Se muestra el salario base del trabajador";
                return new Resultado(String.valueOf(salario), estado, mensaje);
            } else {
                estado=false;
                mensaje = "No se encontró salario base para su elección.";
                return new Resultado("", estado, mensaje);
            }
        } else {
            estado = true;
            mensaje = "Ingrese el salario manualmente. No debe ser menor al sueldo mínimo.";
            return new Resultado("", estado, mensaje);
        }
    }

  public double obtenerSalarioBase(int idArea, int idEspecialidad, int idCargo, int idTipoContrato) {
        return contratoDAO.obtenerSalarioBase(
                idArea,
                idEspecialidad,
                idCargo,
                idTipoContrato
            );
      }
  }