/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo01.softwarenominas.CapaNegocio.ContratoNegocio;

import com.grupo01.softwarenominas.CapaEntidad.Area;
import com.grupo01.softwarenominas.CapaEntidad.Cargo;
import com.grupo01.softwarenominas.CapaEntidad.Especialidad;
import com.grupo01.softwarenominas.CapaEntidad.TipoContrato;
import com.grupo01.softwarenominas.CapaPersistencia.ContratoDAO;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class ContratoNegocioCalculo {
    
    ContratoDAO contratoDAO = new ContratoDAO();
    
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
    //validaciones
    public Resultado actualizarSalarioSiListo(TipoContrato tipoContrato, Cargo cargo, Area area, Especialidad especialidad) {
        if (tipoContrato == null || cargo == null || area == null || especialidad == null) {
            return new Resultado("", false, ""); // Aún no están todos seleccionados
        }
        
        String tipoContratoNombre = tipoContrato.getNombre(); // e.g. "SERVICIO EXTERNO"
        boolean estado = false;
        String mensaje = "";

        if (!tipoContratoNombre.equalsIgnoreCase("SERVICIO EXTERNO")) {
            double salario = contratoDAO.obtenerSalarioBase(
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
}
