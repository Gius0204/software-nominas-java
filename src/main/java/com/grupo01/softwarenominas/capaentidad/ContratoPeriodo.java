package com.grupo01.softwarenominas.capaentidad;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

import javax.annotation.processing.Generated;

@Getter
@Setter
@NoArgsConstructor(onConstructor_ = {@Generated("Constructor Vacio Contrato Periodo")})
@AllArgsConstructor(onConstructor_ = {@Generated("Constructor Completo Contrato Periodo")})
@Builder
public class ContratoPeriodo {
    private int idContratoPeriodo;
    private int idContrato;
    private int idPeriodo;
    private Contrato contrato;
    private PeriodoPago periodo;
    private int horasTrabajadas;
    private String estadoPago;
    private boolean estado;
    private Date fechaRegistro;

    @Generated("Constructor Primero Contrato Periodo")
    public ContratoPeriodo(int idContratoPeriodo, Contrato contrato, PeriodoPago periodo, int horasTrabajadas) {
        this.idContratoPeriodo = idContratoPeriodo;
        this.contrato = contrato;
        this.periodo = periodo;
        this.horasTrabajadas = horasTrabajadas;
    }

    @Generated("Constructor Segundo Contrato Periodo")
    public ContratoPeriodo(int idContratoPeriodo, Contrato contrato, PeriodoPago periodo, int horasTrabajadas, String estadoPago, boolean estado, Date fechaRegistro) {
        this.idContratoPeriodo = idContratoPeriodo;
        this.contrato = contrato;
        this.periodo = periodo;
        this.horasTrabajadas = horasTrabajadas;
        this.estadoPago = estadoPago;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }
}

