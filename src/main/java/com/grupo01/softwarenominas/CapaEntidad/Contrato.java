
package com.grupo01.softwarenominas.capaentidad;
import java.util.Date;

public class Contrato {
    private int idContrato;
    private int idTrabajador;
    private int idTipoContrato;
    private int idCargo;
    private Date fechaInicio;
    private Date fechaFin;
    private double salarioBase;
    private int horasTotales;
    private boolean estado;
    private String descripcion;
    
    private String estadoContrato;


    private Date fechaRegistro;

    private int idArea;
    private int idEspecialidad;
    
    private Trabajador trabajador;
    private TipoContrato tipoContrato;
    private Cargo cargo;
    
    private Area area;
    private Especialidad especialidad;

    public Contrato() {
    }

    // Constructor privado para Builder
    private Contrato(Builder builder) {
        this.idContrato = builder.idContrato;
        this.idTrabajador = builder.idTrabajador;
        this.idTipoContrato = builder.idTipoContrato;
        this.idCargo = builder.idCargo;
        this.fechaInicio = builder.fechaInicio;
        this.fechaFin = builder.fechaFin;
        this.salarioBase = builder.salarioBase;
        this.horasTotales = builder.horasTotales;
        this.estado = builder.estado;
        this.descripcion = builder.descripcion;
        this.estadoContrato = builder.estadoContrato;
        this.fechaRegistro = builder.fechaRegistro;
        this.idArea = builder.idArea;
        this.idEspecialidad = builder.idEspecialidad;
        this.trabajador = builder.trabajador;
        this.tipoContrato = builder.tipoContrato;
        this.cargo = builder.cargo;
        this.area = builder.area;
        this.especialidad = builder.especialidad;
    }

    public static class Builder {
        private int idContrato;
        private int idTrabajador;
        private int idTipoContrato;
        private int idCargo;
        private Date fechaInicio;
        private Date fechaFin;
        private double salarioBase;
        private int horasTotales;
        private boolean estado;
        private String descripcion;
        private String estadoContrato;
        private Date fechaRegistro;
        private int idArea;
        private int idEspecialidad;

        private Trabajador trabajador;
        private TipoContrato tipoContrato;
        private Cargo cargo;
        private Area area;
        private Especialidad especialidad;

        public Builder idContrato(int idContrato) { this.idContrato = idContrato; return this; }
        public Builder idTrabajador(int idTrabajador) { this.idTrabajador = idTrabajador; return this; }
        public Builder idTipoContrato(int idTipoContrato) { this.idTipoContrato = idTipoContrato; return this; }
        public Builder idCargo(int idCargo) { this.idCargo = idCargo; return this; }
        public Builder fechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; return this; }
        public Builder fechaFin(Date fechaFin) { this.fechaFin = fechaFin; return this; }
        public Builder salarioBase(double salarioBase) { this.salarioBase = salarioBase; return this; }
        public Builder horasTotales(int horasTotales) { this.horasTotales = horasTotales; return this; }
        public Builder estado(boolean estado) { this.estado = estado; return this; }
        public Builder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public Builder estadoContrato(String estadoContrato) { this.estadoContrato = estadoContrato; return this; }
        public Builder fechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }
        public Builder idArea(int idArea) { this.idArea = idArea; return this; }
        public Builder idEspecialidad(int idEspecialidad) { this.idEspecialidad = idEspecialidad; return this; }
        public Builder trabajador(Trabajador trabajador) { this.trabajador = trabajador; return this; }
        public Builder tipoContrato(TipoContrato tipoContrato) { this.tipoContrato = tipoContrato; return this; }
        public Builder cargo(Cargo cargo) { this.cargo = cargo; return this; }
        public Builder area(Area area) { this.area = area; return this; }
        public Builder especialidad(Especialidad especialidad) { this.especialidad = especialidad; return this; }

        public Contrato build() {
            return new Contrato(this);
        }
    }

    public String getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public int getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(int idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public int getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(int horasTotales) {
        this.horasTotales = horasTotales;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
    
    
}