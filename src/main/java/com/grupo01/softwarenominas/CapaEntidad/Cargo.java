
package com.grupo01.softwarenominas.capaentidad;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
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

    public Cargo(int idCargo, String nombre, String descripcion, boolean estado, Date fechaRegistro) {
        this.idCargo = idCargo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cargo other = (Cargo) obj;
        return idCargo == other.idCargo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCargo);
    }

}
