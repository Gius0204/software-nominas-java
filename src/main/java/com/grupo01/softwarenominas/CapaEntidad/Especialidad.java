
package com.grupo01.softwarenominas.capaentidad;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Builder;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idEspecialidad")
@ToString(of = "nombre")
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
    
}