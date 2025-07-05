
package com.grupo01.softwarenominas.CapaEntidad;

public class DetalleNomina2 {
    private int idDetalle;
    private int idNomina;
    private double gratificacionLegal;
    private double asignacionFamiliar;
    private double cts;
    private double descuentoSeguroSalud;
    private double descuentoSeguroVida;
    private double descuentoSeguroAccidentes;
    private double descuentoAFP;
    private double descuentoRenta;
    private double totalIngresos;
    private double totalDescuentos;
    private String fechaRegistro;

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(int idNomina) {
        this.idNomina = idNomina;
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}