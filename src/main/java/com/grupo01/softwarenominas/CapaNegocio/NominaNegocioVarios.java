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
    
    public void listarTrabajadores(JTable tabla) {
        trabajadorDAO.listarTrabajadores(tabla);
    }

    public void listarTrabajadoresFiltrado(JTable tabla) {
        trabajadorDAO.listarTrabajadoresFiltrado(tabla);
    }

    public void listarTrabajadoresFiltradoPorFecha(JTable tabla, Date fechaInicio, Date fechaFin) {
        trabajadorDAO.listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin);
    }
    
    public void listarNominas(JTable tabla) {
        nominaDAO.listarNominas(tabla);
    }

    public DetalleNomina2 obtenerDetallePorId(int idNomina) {
        return nominaDAO.obtenerDetalleNominaPorId(idNomina);
    }

    public boolean registrarNominaConDetalle(Nomina2 nomina, DetalleNomina2 detalle) {
        try {
            String validacion = validarNomina(nomina, detalle);
            if (validacion != null) {
                throw new Exception(validacion);
            }

            double totalIngresos = detalle.getGratificacionLegal()
                    + detalle.getAsignacionFamiliar()
                    + detalle.getCts();

            double totalDescuentos = detalle.getDescuentoSeguroSalud()
                    + detalle.getDescuentoSeguroVida()
                    + detalle.getDescuentoSeguroAccidentes()
                    + detalle.getDescuentoAFP()
                    + detalle.getDescuentoRenta();

            double sueldoNeto = totalIngresos - totalDescuentos;

            nomina.setSueldoNeto(sueldoNeto);
            detalle.setTotalIngresos(totalIngresos);
            detalle.setTotalDescuentos(totalDescuentos);

            boolean exitoNomina = nominaDAO.registrarNomina(nomina);
            if (!exitoNomina) return false;

            return nominaDAO.registrarDetalleNomina(detalle);

        } catch (Exception e) {
            System.err.println("Error al registrar nómina con detalle: " + e.getMessage());
            return false;
        }
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