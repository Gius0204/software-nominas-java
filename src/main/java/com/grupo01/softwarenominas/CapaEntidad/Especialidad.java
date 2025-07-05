
package com.grupo01.softwarenominas.CapaEntidad;

import java.util.Date;
import java.util.Objects;

public class Especialidad {
    private int idEspecialidad;
    private int idArea;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    private Area area;

    public Especialidad() {
    }

    public Especialidad(int idEspecialidad, int idArea, String nombre, String descripcion, boolean estado, Date fechaRegistro, Area area) {
        this.idEspecialidad = idEspecialidad;
        this.idArea = idArea;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.area = area;
    }

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

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
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
        System.out.println("Comparando " + this.idEspecialidad + " con " + other.idEspecialidad);
        return idEspecialidad == other.idEspecialidad;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idEspecialidad);
    }

}