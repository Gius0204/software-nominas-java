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
public class ContratoPeriodo {
    private int idContratoPeriodo;
    private int idContrato;
    private int idPeriodo;
    private Contrato contrato;
    private PeriodoPago periodo;
    private int horasTrabajadas;
    private String estadoPago; // 'PENDIENTE', 'PAGADO', 'CANCELADO'
    private boolean estado;
    private java.util.Date fechaRegistro;

    // Getters y setters omitidos por brevedad

    public ContratoPeriodo() {
    }

    public ContratoPeriodo(int idContratoPeriodo, Contrato contrato, PeriodoPago periodo, int horasTrabajadas) {
        this.idContratoPeriodo = idContratoPeriodo;
        this.contrato = contrato;
        this.periodo = periodo;
        this.horasTrabajadas = horasTrabajadas;
    }

    public ContratoPeriodo(int idContratoPeriodo, Contrato contrato, PeriodoPago periodo, int horasTrabajadas, String estadoPago, boolean estado, Date fechaRegistro) {
        this.idContratoPeriodo = idContratoPeriodo;
        this.contrato = contrato;
        this.periodo = periodo;
        this.horasTrabajadas = horasTrabajadas;
        this.estadoPago = estadoPago;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    
    
    public int getIdContratoPeriodo() {
        return idContratoPeriodo;
    }

    public void setIdContratoPeriodo(int idContratoPeriodo) {
        this.idContratoPeriodo = idContratoPeriodo;
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

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
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
    
    
}

