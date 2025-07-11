
package com.grupo01.softwarenominas.capaentidad;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
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
    
    

    public TipoContrato(int idTipoContrato, String nombre, String descripcion, boolean estado, Date fechaRegistro) {
        this.idTipoContrato = idTipoContrato;
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
        TipoContrato other = (TipoContrato) obj;
        return idTipoContrato == other.idTipoContrato;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idTipoContrato);
    }

}
