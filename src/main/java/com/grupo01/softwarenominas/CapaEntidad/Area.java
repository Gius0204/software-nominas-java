
package com.grupo01.softwarenominas.capaentidad;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Area {
    private int idArea;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    public Area(int idArea, String nombre) {
        this.idArea = idArea;
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Area other = (Area) obj;
        return idArea == other.idArea;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idArea);
    }

}