/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo01.softwarenominas.CapaNegocio.NominaNegocio;


import com.grupo01.softwarenominas.CapaEntidad.Nomina;
import com.grupo01.softwarenominas.CapaEntidad.ContratoPeriodo;
import com.grupo01.softwarenominas.CapaEntidad.DetalleContrato;
import com.grupo01.softwarenominas.CapaEntidad.Contrato;
import com.grupo01.softwarenominas.CapaEntidad.PeriodoPago;
import com.grupo01.softwarenominas.CapaEntidad.DetalleNomina;

/**
 *
 * @author Usuario
 */
public class NominaNegocioRegistro {
    
    NominaNegocioCalculo NominaProcesador = new NominaNegocioCalculo();
    //Registrar Nomina -> ElPago
    //Registrar DetalleNomina
    
    public Nomina procesarNominaCompleta(ContratoPeriodo cp, DetalleContrato detalleContrato, String metodoPago, String tipoSeguro) {
        Contrato contrato = cp.getContrato();
        PeriodoPago periodo = cp.getPeriodo();
        Nomina nomina = new Nomina();

        nomina.setContratoPeriodo(cp);
        nomina.setMetodoPago(metodoPago); // Cambiar según UI
        nomina.setDescripcion("Generado automáticamente");
        DetalleNomina detalle = new DetalleNomina();

        double sueldoBase = contrato.getSalarioBase();
        boolean tieneAsignacionFamiliar = detalleContrato.isTieneAsignacionFamiliar();
        boolean esExterno = contrato.getTipoContrato().getNombre().equalsIgnoreCase("SERVICIO EXTERNO");

        int horasExtras = NominaProcesador.calcularHorasExtras(cp.getHorasTrabajadas(), contrato.getHorasTotales());
        int horasNoCompletadas = NominaProcesador.calcularHorasNoCompletadas(cp.getHorasTrabajadas(), contrato.getHorasTotales());

        double pagoHorasExtras = NominaProcesador.calcularPagoHorasExtras(sueldoBase, horasExtras, contrato.getHorasTotales());
        double gratificacion = NominaProcesador.calcularGratificacionLegal(sueldoBase, contrato.getFechaInicio(), periodo.getFechaFin(), tipoSeguro);
        double asignacion = NominaProcesador.calcularAsignacionFamiliar(tieneAsignacionFamiliar);
        double cts = NominaProcesador.calcularCTS(sueldoBase, asignacion, gratificacion);

        double dHorasNo = NominaProcesador.calcularDescuentoHorasNoCompletadas(sueldoBase, horasNoCompletadas, contrato.getHorasTotales());
        double dSalud = NominaProcesador.calcularSeguroSalud(sueldoBase, detalleContrato.getTipoSeguroSalud());
        double dVida = NominaProcesador.calcularSeguroVida(sueldoBase, detalleContrato.isTieneSeguroDeVida());
        double dAccidentes = NominaProcesador.calcularSeguroAccidentes(sueldoBase, detalleContrato.isTieneSeguroDeAccidentes());
        double afp = NominaProcesador.calcularDescuentoAFP(sueldoBase);
        double renta = NominaProcesador.calcularRenta(sueldoBase, esExterno);

        double totalIngresos = NominaProcesador.calcularTotalIngresos(sueldoBase, pagoHorasExtras, gratificacion, asignacion, cts);
        double totalDescuentos = NominaProcesador.calcularTotalDescuentos(dHorasNo, dSalud, dVida, dAccidentes, afp, renta);
        double sueldoNeto = NominaProcesador.calcularSueldoNeto(totalIngresos, totalDescuentos);

        detalle.setPagoHorasExtras(pagoHorasExtras);
        detalle.setGratificacionLegal(gratificacion);
        detalle.setAsignacionFamiliar(asignacion);
        detalle.setCts(cts);
        detalle.setDescuentoHorasNoCompletadas(dHorasNo);
        detalle.setDescuentoSeguroSalud(dSalud);
        detalle.setDescuentoSeguroVida(dVida);
        detalle.setDescuentoSeguroAccidentes(dAccidentes);
        detalle.setDescuentoAFP(afp);
        detalle.setDescuentoRenta(renta);
        detalle.setTotalIngresos(totalIngresos);
        detalle.setTotalDescuentos(totalDescuentos);

        nomina.setDetalle(detalle);
        nomina.setSueldoNeto(sueldoNeto);

        return nomina;
    }
}
