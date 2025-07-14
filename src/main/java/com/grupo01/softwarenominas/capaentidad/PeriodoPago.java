package com.grupo01.softwarenominas.capaentidad;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoPago {
    private int idPeriodoPago;
    private Date fechaInicio;
    private Date fechaFin;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    @Override
    public String toString() {
        return nombre;
    }
}
