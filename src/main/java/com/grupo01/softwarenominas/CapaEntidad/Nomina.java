package com.grupo01.softwarenominas.CapaEntidad;

import java.util.Date;

public class Nomina {
    private int idNomina;
    private ContratoPeriodo contratoPeriodo;
    private double sueldoNeto;
    private String metodoPago;
    private String descripcion;
    private boolean estado;
    private java.util.Date fechaRegistro;
    private DetalleNomina detalle;

    public Nomina() {
        this.detalle = new DetalleNomina();
    }

    public Nomina(int idNomina, ContratoPeriodo contratoPeriodo) {
        this.idNomina = idNomina;
        this.contratoPeriodo = contratoPeriodo;
    }

    public Nomina(int idNomina, ContratoPeriodo contratoPeriodo, double sueldoNeto, String metodoPago, String descripcion, DetalleNomina detalle) {
        this.idNomina = idNomina;
        this.contratoPeriodo = contratoPeriodo;
        this.sueldoNeto = sueldoNeto;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;
        this.detalle = detalle;
    }

    public Nomina(int idNomina, ContratoPeriodo contratoPeriodo, double sueldoNeto, String metodoPago, String descripcion) {
        this.idNomina = idNomina;
        this.contratoPeriodo = contratoPeriodo;
        this.sueldoNeto = sueldoNeto;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;
    }

    public Nomina(int idNomina, ContratoPeriodo contratoPeriodo, double sueldoNeto, String metodoPago, String descripcion, boolean estado, Date fechaRegistro, DetalleNomina detalle) {
        this.idNomina = idNomina;
        this.contratoPeriodo = contratoPeriodo;
        this.sueldoNeto = sueldoNeto;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.detalle = detalle;
    }

    public int getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(int idNomina) {
        this.idNomina = idNomina;
    }

    public ContratoPeriodo getContratoPeriodo() {
        return contratoPeriodo;
    }

    public void setContratoPeriodo(ContratoPeriodo contratoPeriodo) {
        this.contratoPeriodo = contratoPeriodo;
    }

    public double getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(double sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public DetalleNomina getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleNomina detalle) {
        this.detalle = detalle;
    }
    
    
}

