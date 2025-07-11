
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
public class Especialidad {
    private int idEspecialidad;
    private int idArea;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    private Area area;

    public Especialidad(int idEspecialidad, String nombre) {
        this.idEspecialidad = idEspecialidad;
        this.nombre = nombre;
    }

    public Especialidad(int idEspecialidad, int idArea, String nombre) {
        this.idEspecialidad = idEspecialidad;
        this.idArea = idArea;
        this.nombre = nombre;
    }        
    
    public Especialidad(int idEspecialidad, String nombre, String descripcion, boolean estado, Date fechaRegistro) {
        this.idEspecialidad = idEspecialidad;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Especialidad other = (Especialidad) obj;
        return idEspecialidad == other.idEspecialidad;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idEspecialidad);
    }

}