
package com.grupo01.softwarenominas.capaentidad;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idTipoContrato")
@Builder
public class TipoContrato {
    private int idTipoContrato;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;
    
    public TipoContrato(int idTipoContrato, String nombre) {
        this.idTipoContrato = idTipoContrato;
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}
