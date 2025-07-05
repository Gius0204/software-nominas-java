
package com.grupo01.softwarenominas.CapaEntidad;

import java.util.Date;
import java.util.Objects;

public class Area {
    private int idArea;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    public Area() {
    }

    public Area(int idArea, String nombre, String descripcion, boolean estado, Date fechaRegistro) {
        this.idArea = idArea;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Area(int idArea, String nombre) {
        this.idArea = idArea;
        this.nombre = nombre;
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

    @Override
    public String toString(){
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Area other = (Area) obj;
        System.out.println("Comparando " + this.idArea + " con " + other.idArea);
        return idArea == other.idArea;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idArea);
    }

}