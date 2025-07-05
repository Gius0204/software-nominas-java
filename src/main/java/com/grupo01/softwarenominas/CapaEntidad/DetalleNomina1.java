/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo01.softwarenominas.CapaEntidad;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author UCV
 */
public class DetalleNomina1 {
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

    public void calcularIngresos(double sueldoBase, boolean tieneCargaFamiliar) {
        gratificacionLegal = sueldoBase * 0.09;
        asignacionFamiliar = tieneCargaFamiliar ? 102.50 : 0.0; // Supuesto: sueldo mínimo = 1025.00
        cts = 0.5 * ((gratificacionLegal / 6) + sueldoBase + asignacionFamiliar);
        totalIngresos = sueldoBase + gratificacionLegal + asignacionFamiliar + cts;
    }

    public void calcularDescuentos(double sueldoBase, boolean esExterno) {
        descuentoSeguroSalud = sueldoBase * 0.03;
        descuentoSeguroVida = sueldoBase * 0.01;
        descuentoSeguroAccidentes = sueldoBase * 0.01;
        descuentoAFP = sueldoBase * 0.10;
        descuentoRenta = sueldoBase * (esExterno ? 0.08 : 0.05); // Supuesto: 8% para externos, 5% internos
        totalDescuentos = descuentoSeguroSalud + descuentoSeguroVida + descuentoSeguroAccidentes + descuentoAFP + descuentoRenta;
    }

    public double calcularSueldoNeto(double sueldoBase) {
        return totalIngresos - totalDescuentos;
    }

    // Getters para todos los campos (omitidos por brevedad)

    public DetalleNomina1() {
    }

    public DetalleNomina1(double gratificacionLegal, double asignacionFamiliar, double cts, double descuentoSeguroSalud, double descuentoSeguroVida, double descuentoSeguroAccidentes, double descuentoAFP, double descuentoRenta, double totalIngresos, double totalDescuentos) {
        this.gratificacionLegal = gratificacionLegal;
        this.asignacionFamiliar = asignacionFamiliar;
        this.cts = cts;
        this.descuentoSeguroSalud = descuentoSeguroSalud;
        this.descuentoSeguroVida = descuentoSeguroVida;
        this.descuentoSeguroAccidentes = descuentoSeguroAccidentes;
        this.descuentoAFP = descuentoAFP;
        this.descuentoRenta = descuentoRenta;
        this.totalIngresos = totalIngresos;
        this.totalDescuentos = totalDescuentos;
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
    
    // Ingresos
    public double calcularAsignacionFamiliar(double sueldoBase, boolean tieneHijos) {
        if (sueldoBase < 0) throw new IllegalArgumentException("Sueldo base no puede ser negativo");
        return redondear(sueldoBase * 0.10 + (tieneHijos ? 102.50 : 0.0));
    }

    public double calcularGratificacionLegal(double sueldoBase, Date fecha) {
        if (fecha == null) throw new IllegalArgumentException("Fecha no puede ser nula");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int mes = calendar.get(Calendar.MONTH); // Enero = 0, Diciembre = 11

        // Junio = 5, Diciembre = 11
        return (mes == Calendar.JUNE || mes == Calendar.DECEMBER) ? redondear(sueldoBase) : 0.0;
    }

    public double calcularPagoHorasExtra(double sueldoBase, int horasExtra) {
        double valorHora = sueldoBase / 240; // Supongamos 240h/mes
        double total = 0.0;
        for (int i = 1; i <= horasExtra; i++) {
            if (i <= 2) {
                total += valorHora * 1.25;
            } else {
                total += valorHora * 1.35;
            }
        }
        return redondear(total);
    }

    public double calcularCTS(double sueldoBase, double asignacionFamiliar, double gratificacion) {
        return redondear(0.5 * ((gratificacion / 6) + sueldoBase + asignacionFamiliar));
    }

    public double calcularTotalIngresos(double sueldoBase, double asignacionFamiliar, double gratificacion, double pagoHorasExtra, double cts) {
        return redondear(sueldoBase + asignacionFamiliar + gratificacion + pagoHorasExtra + cts);
    }

    // Descuentos
    public double calcularSeguroSalud(double sueldoBase, String tipoSeguro) {
        if (tipoSeguro.equalsIgnoreCase("ESSALUD")) {
            return redondear(sueldoBase * 0.09);
        } else if (tipoSeguro.equalsIgnoreCase("EPS")) {
            return redondear(sueldoBase * 0.0675);
        }
        return 0.0;
    }

    public double calcularSeguroVida(double sueldoBase, boolean tieneSeguroVida) {
        return tieneSeguroVida ? redondear(sueldoBase * 0.01) : 0.0;
    }

    public double calcularSeguroAccidentes(double sueldoBase, boolean tieneSeguroAccidente) {
        return tieneSeguroAccidente ? redondear(sueldoBase * 0.015) : 0.0;
    }
    
    public double calcularRenta(double sueldoBase, boolean esExterno) {
        double rentaAnual = sueldoBase * 12;
        double uit = 5150;
        double tramos = rentaAnual / uit;
        double impuesto = 0.0;

        if (tramos <= 5) {
            impuesto = rentaAnual * (esExterno ? 0.08 : 0.05);
        }
        else if (tramos <= 20) impuesto = rentaAnual * 0.14;
        else if (tramos <= 35) impuesto = rentaAnual * 0.17;
        else if (tramos <= 45) impuesto = rentaAnual * 0.20;
        else impuesto = rentaAnual * 0.30;

        return redondear(impuesto / 12); // prorrateado mensual
    }

    public double calcularTotalDescuentos(double salud, double vida, double accidentes, double renta) {
        return redondear(salud + vida + accidentes + renta);
    }

    public double calcularSueldoNeto(double ingresos, double descuentos) {
        return redondear(ingresos - descuentos);
    }

    private double redondear(double valor) {
        return new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    

}
