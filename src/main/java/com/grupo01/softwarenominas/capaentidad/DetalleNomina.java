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

    private Date fechaRegistro;
}

