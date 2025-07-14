
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
public class DetalleContrato {
    private int idDetalleContrato;
    private int idContrato;
    private String tipoSeguroSalud;
    private boolean tieneSeguroDeVida;
    private boolean tieneSeguroDeAccidentes;
    private boolean tieneAsignacionFamiliar;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    private Contrato contrato;
}