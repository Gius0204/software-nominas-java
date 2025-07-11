
package com.grupo01.softwarenominas.capaentidad;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import lombok.Builder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idCargo")
@Builder
public class Cargo {
    private int idCargo;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    public Cargo(int idCargo, String nombre) {
        this.idCargo = idCargo;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
