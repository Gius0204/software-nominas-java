package com.grupo01.softwarenominas.capaentidad;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Nomina {
    private int idNomina;
    private ContratoPeriodo contratoPeriodo;
    private double sueldoNeto;
    private String metodoPago;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;
    @Builder.Default
    private DetalleNomina detalle = new DetalleNomina();
}

