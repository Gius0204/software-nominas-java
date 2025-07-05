
package com.grupo01.softwarenominas.CapaEntidad;

import java.util.Date;
import java.util.Objects;

public class Cargo {
    private int idCargo;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    public Cargo() {}

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

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
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
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cargo other = (Cargo) obj;
        System.out.println("Comparando " + this.idCargo + " con " + other.idCargo);
        return idCargo == other.idCargo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCargo);
    }

}
