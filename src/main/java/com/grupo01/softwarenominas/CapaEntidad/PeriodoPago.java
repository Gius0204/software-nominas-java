/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo01.softwarenominas.CapaEntidad;
import java.util.Date;

/**
 *
 * @author UCV
 */
public class PeriodoPago {
        private int idPeriodoPago;
    private Date fechaInicio;
    private Date fechaFin;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    public PeriodoPago() {}

    public PeriodoPago(int idPeriodoPago, Date fechaInicio, Date fechaFin, String nombre, String descripcion, boolean estado, Date fechaRegistro) {
        this.idPeriodoPago = idPeriodoPago;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters y Setters
    public int getIdPeriodoPago() {
        return idPeriodoPago;
    }

    public void setIdPeriodoPago(int idPeriodoPago) {
        this.idPeriodoPago = idPeriodoPago;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return nombre;
    }
}
