
package com.grupo01.softwarenominas.CapaEntidad;

import java.util.Date;

/**
 *
 * @author Usuario
 */

public class DetalleContrato {
    private int idDetalleContrato;
    private int idContrato;
    private String tipoSeguroSalud;
    private boolean tieneSeguroDeVida;
    private boolean tieneSeguroDeAccidentes;
    private boolean tieneAsignacionFamiliar;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    private Contrato contrato;

    public DetalleContrato() {
    }

    public DetalleContrato(int idDetalleContrato, int idContrato, String tipoSeguroSalud, boolean tieneSeguroDeVida, boolean tieneSeguroDeAccidentes, boolean tieneAsignacionFamiliar, String descripcion, boolean estado, Date fechaRegistro, Contrato contrato) {
        this.idDetalleContrato = idDetalleContrato;
        this.idContrato = idContrato;
        this.tipoSeguroSalud = tipoSeguroSalud;
        this.tieneSeguroDeVida = tieneSeguroDeVida;
        this.tieneSeguroDeAccidentes = tieneSeguroDeAccidentes;
        this.tieneAsignacionFamiliar = tieneAsignacionFamiliar;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.contrato = contrato;
    }

    public int getIdDetalleContrato() {
        return idDetalleContrato;
    }

    public void setIdDetalleContrato(int idDetalleContrato) {
        this.idDetalleContrato = idDetalleContrato;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getTipoSeguroSalud() {
        return tipoSeguroSalud;
    }

    public void setTipoSeguroSalud(String tipoSeguroSalud) {
        this.tipoSeguroSalud = tipoSeguroSalud;
    }

    public boolean isTieneSeguroDeVida() {
        return tieneSeguroDeVida;
    }

    public void setTieneSeguroDeVida(boolean tieneSeguroDeVida) {
        this.tieneSeguroDeVida = tieneSeguroDeVida;
    }

    public boolean isTieneSeguroDeAccidentes() {
        return tieneSeguroDeAccidentes;
    }

    public void setTieneSeguroDeAccidentes(boolean tieneSeguroDeAccidentes) {
        this.tieneSeguroDeAccidentes = tieneSeguroDeAccidentes;
    }

    public boolean isTieneAsignacionFamiliar() {
        return tieneAsignacionFamiliar;
    }

    public void setTieneAsignacionFamiliar(boolean tieneAsignacionFamiliar) {
        this.tieneAsignacionFamiliar = tieneAsignacionFamiliar;
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

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
}