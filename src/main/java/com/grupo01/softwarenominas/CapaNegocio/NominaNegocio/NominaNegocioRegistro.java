
package com.grupo01.softwarenominas.capanegocio.nominanegocio;


import com.grupo01.softwarenominas.capaentidad.Nomina;
import com.grupo01.softwarenominas.capaentidad.ContratoPeriodo;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;
import com.grupo01.softwarenominas.capapersistencia.NominaDAO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.grupo01.softwarenominas.capaentidad.DetalleNomina;

@Getter
@Setter
@AllArgsConstructor
public class NominaNegocioRegistro {  
    private final NominaDAO dao;
    public NominaNegocioRegistro() {
        this.dao = new NominaDAO();
    }
    
    public Nomina procesarNominaCompleta(ContratoPeriodo cp, DetalleContrato detalleContrato, String metodoPago, String tipoSeguro) {
        Contrato contrato = cp.getContrato();
        PeriodoPago periodo = cp.getPeriodo();
        Nomina nomina = new Nomina();

        nomina.setContratoPeriodo(cp);
        nomina.setMetodoPago(metodoPago);
        nomina.setDescripcion("Generado automáticamente");
        DetalleNomina detalle = new DetalleNomina();

        double sueldoBase = contrato.getSalarioBase();
        boolean tieneAsignacionFamiliar = detalleContrato.isTieneAsignacionFamiliar();
        boolean esExterno = contrato.getTipoContrato().getNombre().equalsIgnoreCase("SERVICIO EXTERNO");

        int horasExtras = NominaNegocioCalculo.calcularHorasExtras(cp.getHorasTrabajadas(), contrato.getHorasTotales());
        int horasNoCompletadas = NominaNegocioCalculo.calcularHorasNoCompletadas(cp.getHorasTrabajadas(), contrato.getHorasTotales());

        double pagoHorasExtras = NominaNegocioCalculo.calcularPagoHorasExtras(sueldoBase, horasExtras, contrato.getHorasTotales());
        double gratificacion = NominaNegocioCalculo.calcularGratificacionLegal(sueldoBase, contrato.getFechaInicio(), periodo.getFechaFin(), tipoSeguro);
        double asignacion = NominaNegocioCalculo.calcularAsignacionFamiliar(tieneAsignacionFamiliar);
        double cts = NominaNegocioCalculo.calcularCTS(sueldoBase, asignacion, gratificacion);

        double dHorasNo = NominaNegocioCalculo.calcularDescuentoHorasNoCompletadas(sueldoBase, horasNoCompletadas, contrato.getHorasTotales());
        double dSalud = NominaNegocioCalculo.calcularSeguroSalud(sueldoBase, detalleContrato.getTipoSeguroSalud());
        double dVida = NominaNegocioCalculo.calcularSeguroVida(sueldoBase, detalleContrato.isTieneSeguroDeVida());
        double dAccidentes = NominaNegocioCalculo.calcularSeguroAccidentes(sueldoBase, detalleContrato.isTieneSeguroDeAccidentes());
        double afp = NominaNegocioCalculo.calcularDescuentoAFP(sueldoBase);
        double renta = NominaNegocioCalculo.calcularRenta(sueldoBase, esExterno);

        double totalIngresos = NominaNegocioCalculo.calcularTotalIngresos(sueldoBase, pagoHorasExtras, gratificacion, asignacion, cts);
        double totalDescuentos = NominaNegocioCalculo.calcularTotalDescuentos(dHorasNo, dSalud, dVida, dAccidentes, afp, renta);
        double sueldoNeto = NominaNegocioCalculo.calcularSueldoNeto(totalIngresos, totalDescuentos);

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

    public ResultadoOperacion insertarNominaCompleta(Nomina nomina) {
        return dao.insertarNominaCompleta(nomina);
    }

  }