/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo01.softwarenominas.CapaEntidad;

import java.util.Date;

/**
 *
 * @author Usuario
 */
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

    // Getters y setters omitidos por brevedad

    public DetalleNomina() {
    }

    public DetalleNomina(double pagoHorasExtras, double gratificacionLegal, double asignacionFamiliar, double cts, double descuentoHorasNoCompletadas, double descuentoSeguroSalud, double descuentoSeguroVida, double descuentoSeguroAccidentes, double descuentoAFP, double descuentoRenta, double totalIngresos, double totalDescuentos) {
        this.pagoHorasExtras = pagoHorasExtras;
        this.gratificacionLegal = gratificacionLegal;
        this.asignacionFamiliar = asignacionFamiliar;
        this.cts = cts;
        this.descuentoHorasNoCompletadas = descuentoHorasNoCompletadas;
        this.descuentoSeguroSalud = descuentoSeguroSalud;
        this.descuentoSeguroVida = descuentoSeguroVida;
        this.descuentoSeguroAccidentes = descuentoSeguroAccidentes;
        this.descuentoAFP = descuentoAFP;
        this.descuentoRenta = descuentoRenta;
        this.totalIngresos = totalIngresos;
        this.totalDescuentos = totalDescuentos;
    }

    public DetalleNomina(double pagoHorasExtras, double gratificacionLegal, double asignacionFamiliar, double cts, double descuentoHorasNoCompletadas, double descuentoSeguroSalud, double descuentoSeguroVida, double descuentoSeguroAccidentes, double descuentoAFP, double descuentoRenta, double totalIngresos, double totalDescuentos, Date fechaRegistro) {
        this.pagoHorasExtras = pagoHorasExtras;
        this.gratificacionLegal = gratificacionLegal;
        this.asignacionFamiliar = asignacionFamiliar;
        this.cts = cts;
        this.descuentoHorasNoCompletadas = descuentoHorasNoCompletadas;
        this.descuentoSeguroSalud = descuentoSeguroSalud;
        this.descuentoSeguroVida = descuentoSeguroVida;
        this.descuentoSeguroAccidentes = descuentoSeguroAccidentes;
        this.descuentoAFP = descuentoAFP;
        this.descuentoRenta = descuentoRenta;
        this.totalIngresos = totalIngresos;
        this.totalDescuentos = totalDescuentos;
        this.fechaRegistro = fechaRegistro;
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

