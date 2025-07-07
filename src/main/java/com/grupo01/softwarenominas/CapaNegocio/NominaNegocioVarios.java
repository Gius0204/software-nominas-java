package com.grupo01.softwarenominas.CapaNegocio;

import com.grupo01.softwarenominas.CapaEntidad.Nomina2;
import com.grupo01.softwarenominas.CapaEntidad.DetalleNomina2;
import com.grupo01.softwarenominas.CapaPersistencia.TrabajadorDAO;
import com.grupo01.softwarenominas.CapaPersistencia.NominaDAO;
import java.util.Date;
import javax.swing.JTable;


public class NominaNegocioVarios {
    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    private final NominaDAO nominaDAO = new NominaDAO();

    public void listarTrabajadoresFiltrado(JTable tabla) {
        trabajadorDAO.listarTrabajadoresFiltrado(tabla);
    }

    public void listarTrabajadoresFiltradoPorFecha(JTable tabla, Date fechaInicio, Date fechaFin) {
        trabajadorDAO.listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin);
    }
    

    public String validarNomina(Nomina2 nomina, DetalleNomina2 detalle) {

        if (nomina.getIdContrato() <= 0) {
            return "Debe seleccionar un contrato válido.";
        }
        if (nomina.getIdPeriodo()
                <= 0) {
            return "Debe seleccionar un periodo de pago.";
        }
        if (detalle
                == null) {
            return "Debe ingresar el detalle de la nómina.";
        }

        if (detalle.getGratificacionLegal()
                < 0 || detalle.getCts() < 0) {
            return "Los valores no pueden ser negativos.";
        }

        return null;
    }
}