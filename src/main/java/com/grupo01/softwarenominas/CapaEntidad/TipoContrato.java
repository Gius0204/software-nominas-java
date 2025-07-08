
package com.grupo01.softwarenominas.capaentidad;

import java.util.Date;
import java.util.Objects;

public class TipoContrato {
    private int idTipoContrato;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    public TipoContrato() {}

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

    public int getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(int idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
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
        TipoContrato other = (TipoContrato) obj;
        return idTipoContrato == other.idTipoContrato;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(idTipoContrato);
    }

}
