
package com.grupo01.softwarenominas.capaentidad;

import java.util.Date;

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

    private DetalleContrato(Builder builder) {
        this.idDetalleContrato = builder.idDetalleContrato;
        this.idContrato = builder.idContrato;
        this.tipoSeguroSalud = builder.tipoSeguroSalud;
        this.tieneSeguroDeVida = builder.tieneSeguroDeVida;
        this.tieneSeguroDeAccidentes = builder.tieneSeguroDeAccidentes;
        this.tieneAsignacionFamiliar = builder.tieneAsignacionFamiliar;
        this.descripcion = builder.descripcion;
        this.estado = builder.estado;
        this.fechaRegistro = builder.fechaRegistro;
        this.contrato = builder.contrato;
    }

    public static class Builder {
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

        public Builder idDetalleContrato(int idDetalleContrato) { this.idDetalleContrato = idDetalleContrato; return this; }
        public Builder idContrato(int idContrato) { this.idContrato = idContrato; return this; }
        public Builder tipoSeguroSalud(String tipoSeguroSalud) { this.tipoSeguroSalud = tipoSeguroSalud; return this; }
        public Builder tieneSeguroDeVida(boolean tieneSeguroDeVida) { this.tieneSeguroDeVida = tieneSeguroDeVida; return this; }
        public Builder tieneSeguroDeAccidentes(boolean tieneSeguroDeAccidentes) { this.tieneSeguroDeAccidentes = tieneSeguroDeAccidentes; return this; }
        public Builder tieneAsignacionFamiliar(boolean tieneAsignacionFamiliar) { this.tieneAsignacionFamiliar = tieneAsignacionFamiliar; return this; }
        public Builder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public Builder estado(boolean estado) { this.estado = estado; return this; }
        public Builder fechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }
        public Builder contrato(Contrato contrato) { this.contrato = contrato; return this; }

        public DetalleContrato build() {
            return new DetalleContrato(this);
        }
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