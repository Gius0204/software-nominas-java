package com.grupo01.softwarenominas.CapaEntidad;

import java.util.Date;

public class Nomina1 {
    private Contrato contrato;
    private PeriodoPago periodo;
    private String metodoPago;
    private String descripcion;
    private double sueldoNeto;
    private DetalleNomina1 detalle;

    public Nomina1(Contrato contrato, PeriodoPago periodo, String metodoPago, String descripcion) {
        this.contrato = contrato;
        this.periodo = periodo;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;
        this.detalle = new DetalleNomina1();
    }
    
    public void procesarNomina(DetalleContrato detalleContrato, Contrato contrato, PeriodoPago periodoPago) {
        if (!contrato.isEstado()) {
            throw new IllegalStateException("No se puede procesar la nómina: el contrato está inactivo.");
        }

        double sueldoBase = contrato.getSalarioBase();
        boolean tieneCargaFamiliar = detalleContrato.isTieneAsignacionFamiliar();
        boolean esExterno = "SERVICIO EXTERNO".equalsIgnoreCase(contrato.getTipoContrato().getNombre());
        Date fechaPeriodo = periodoPago.getFechaInicio();

        detalle.setGratificacionLegal(detalle.calcularGratificacionLegal(sueldoBase, fechaPeriodo));
        detalle.setAsignacionFamiliar(detalle.calcularAsignacionFamiliar(sueldoBase, tieneCargaFamiliar));
        double pagoHorasExtras = (detalle.calcularPagoHorasExtra(sueldoBase, 4));
        detalle.setCts(detalle.calcularCTS(sueldoBase, detalle.getAsignacionFamiliar(), detalle.getGratificacionLegal()));

        detalle.setTotalIngresos(detalle.calcularTotalIngresos(sueldoBase, detalle.getAsignacionFamiliar(), detalle.getGratificacionLegal(), pagoHorasExtras, detalle.getCts()));// suma de todo lo anterior

        detalle.setDescuentoSeguroSalud(detalle.calcularSeguroSalud(sueldoBase, detalleContrato.getTipoSeguroSalud()));
        detalle.setDescuentoSeguroVida(detalle.calcularSeguroVida(sueldoBase, detalleContrato.isTieneSeguroDeVida()));
        detalle.setDescuentoSeguroAccidentes(detalle.calcularSeguroAccidentes(sueldoBase, detalleContrato.isTieneSeguroDeAccidentes()));
        detalle.setDescuentoRenta(detalle.calcularRenta(sueldoBase, esExterno));
        detalle.setTotalDescuentos(sueldoNeto);

        sueldoNeto = detalle.calcularSueldoNeto(detalle.getTotalIngresos(), detalle.getTotalDescuentos());
    }

    public void procesarNomina1(DetalleContrato detalleContrato, Contrato contrato) {
        double sueldoBase = contrato.getSalarioBase();
        boolean tieneCargaFamiliar = detalleContrato.isTieneAsignacionFamiliar();
        boolean esExterno = "SERVICIO EXTERNO".equalsIgnoreCase(contrato.getTipoContrato().getNombre());

        detalle.calcularIngresos(sueldoBase, tieneCargaFamiliar);
        detalle.calcularDescuentos(sueldoBase, esExterno);
        sueldoNeto = detalle.calcularSueldoNeto(sueldoBase);
    }

    public Nomina1() {
    }
    

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public PeriodoPago getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoPago periodo) {
        this.periodo = periodo;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(double sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }

    public DetalleNomina1 getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleNomina1 detalle) {
        this.detalle = detalle;
    }
}
