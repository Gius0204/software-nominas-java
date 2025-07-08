package com.grupo01.softwarenominas.capaentidad;

import java.util.Date;

public class DetalleNomina {
    private double pagoHorasExtras;
    private double gratificacionLegal;
    private double asignacionFamiliar;
    private double cts;

    private double descuentoHorasNoCompletadas;
    private double descuentoSeguroSalud;
    private double descuentoSeguroVida;
    private double descuentoSeguroAccidentes;
    private double descuentoAFP;
    private double descuentoRenta;

    private double totalIngresos;
    private double totalDescuentos;

    private java.util.Date fechaRegistro;

    public DetalleNomina() {
    }

    private DetalleNomina(Builder builder) {
        this.pagoHorasExtras = builder.pagoHorasExtras;
        this.gratificacionLegal = builder.gratificacionLegal;
        this.asignacionFamiliar = builder.asignacionFamiliar;
        this.cts = builder.cts;
        this.descuentoHorasNoCompletadas = builder.descuentoHorasNoCompletadas;
        this.descuentoSeguroSalud = builder.descuentoSeguroSalud;
        this.descuentoSeguroVida = builder.descuentoSeguroVida;
        this.descuentoSeguroAccidentes = builder.descuentoSeguroAccidentes;
        this.descuentoAFP = builder.descuentoAFP;
        this.descuentoRenta = builder.descuentoRenta;
        this.totalIngresos = builder.totalIngresos;
        this.totalDescuentos = builder.totalDescuentos;
        this.fechaRegistro = builder.fechaRegistro;
    }

    public static class Builder {
        private double pagoHorasExtras;
        private double gratificacionLegal;
        private double asignacionFamiliar;
        private double cts;

        private double descuentoHorasNoCompletadas;
        private double descuentoSeguroSalud;
        private double descuentoSeguroVida;
        private double descuentoSeguroAccidentes;
        private double descuentoAFP;
        private double descuentoRenta;

        private double totalIngresos;
        private double totalDescuentos;

        private java.util.Date fechaRegistro;

        public Builder pagoHorasExtras(double pagoHorasExtras) { this.pagoHorasExtras = pagoHorasExtras; return this; }
        public Builder gratificacionLegal(double gratificacionLegal) { this.gratificacionLegal = gratificacionLegal; return this; }
        public Builder asignacionFamiliar(double asignacionFamiliar) { this.asignacionFamiliar = asignacionFamiliar; return this; }
        public Builder cts(double cts) { this.cts = cts; return this; }
        public Builder descuentoHorasNoCompletadas(double descuentoHorasNoCompletadas) { this.descuentoHorasNoCompletadas = descuentoHorasNoCompletadas; return this; }
        public Builder descuentoSeguroSalud(double descuentoSeguroSalud) { this.descuentoSeguroSalud = descuentoSeguroSalud; return this; }
        public Builder descuentoSeguroVida(double descuentoSeguroVida) { this.descuentoSeguroVida = descuentoSeguroVida; return this; }
        public Builder descuentoSeguroAccidentes(double descuentoSeguroAccidentes) { this.descuentoSeguroAccidentes = descuentoSeguroAccidentes; return this; }
        public Builder descuentoAFP(double descuentoAFP) { this.descuentoAFP = descuentoAFP; return this; }
        public Builder descuentoRenta(double descuentoRenta) { this.descuentoRenta = descuentoRenta; return this; }
        public Builder totalIngresos(double totalIngresos) { this.totalIngresos = totalIngresos; return this; }
        public Builder totalDescuentos(double totalDescuentos) { this.totalDescuentos = totalDescuentos; return this; }
        public Builder fechaRegistro(java.util.Date fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }

        public DetalleNomina build() {
            return new DetalleNomina(this);
        }
    }

    public double getPagoHorasExtras() {
        return pagoHorasExtras;
    }

    public void setPagoHorasExtras(double pagoHorasExtras) {
        this.pagoHorasExtras = pagoHorasExtras;
    }

    public double getGratificacionLegal() {
        return gratificacionLegal;
    }

    public void setGratificacionLegal(double gratificacionLegal) {
        this.gratificacionLegal = gratificacionLegal;
    }

    public double getAsignacionFamiliar() {
        return asignacionFamiliar;
    }

    public void setAsignacionFamiliar(double asignacionFamiliar) {
        this.asignacionFamiliar = asignacionFamiliar;
    }

    public double getCts() {
        return cts;
    }

    public void setCts(double cts) {
        this.cts = cts;
    }

    public double getDescuentoHorasNoCompletadas() {
        return descuentoHorasNoCompletadas;
    }

    public void setDescuentoHorasNoCompletadas(double descuentoHorasNoCompletadas) {
        this.descuentoHorasNoCompletadas = descuentoHorasNoCompletadas;
    }

    public double getDescuentoSeguroSalud() {
        return descuentoSeguroSalud;
    }

    public void setDescuentoSeguroSalud(double descuentoSeguroSalud) {
        this.descuentoSeguroSalud = descuentoSeguroSalud;
    }

    public double getDescuentoSeguroVida() {
        return descuentoSeguroVida;
    }

    public void setDescuentoSeguroVida(double descuentoSeguroVida) {
        this.descuentoSeguroVida = descuentoSeguroVida;
    }

    public double getDescuentoSeguroAccidentes() {
        return descuentoSeguroAccidentes;
    }

    public void setDescuentoSeguroAccidentes(double descuentoSeguroAccidentes) {
        this.descuentoSeguroAccidentes = descuentoSeguroAccidentes;
    }

    public double getDescuentoAFP() {
        return descuentoAFP;
    }

    public void setDescuentoAFP(double descuentoAFP) {
        this.descuentoAFP = descuentoAFP;
    }

    public double getDescuentoRenta() {
        return descuentoRenta;
    }

    public void setDescuentoRenta(double descuentoRenta) {
        this.descuentoRenta = descuentoRenta;
    }

    public double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public double getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(double totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}

