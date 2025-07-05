
package com.grupo01.softwarenominas.CapaEntidad;
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

    public String getEstadoContrato() {
        return estadoContrato;
    }

    public void setEstadoContrato(String estadoContrato) {
        this.estadoContrato = estadoContrato;
    }

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

    public Contrato(int idContrato, int idTrabajador, int idTipoContrato, int idCargo, Date fechaInicio, Date fechaFin, double salarioBase, int horasTotales, boolean estado, String descripcion, Date fechaRegistro, int idArea, int idEspecialidad, Trabajador trabajador, TipoContrato tipoContrato, Cargo cargo, Area area, Especialidad especialidad) {
        this.idContrato = idContrato;
        this.idTrabajador = idTrabajador;
        this.idTipoContrato = idTipoContrato;
        this.idCargo = idCargo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.salarioBase = salarioBase;
        this.horasTotales = horasTotales;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.idArea = idArea;
        this.idEspecialidad = idEspecialidad;
        this.trabajador = trabajador;
        this.tipoContrato = tipoContrato;
        this.cargo = cargo;
        this.area = area;
        this.especialidad = especialidad;
    }
    
    

    public Contrato(int idContrato, int idTrabajador, int idTipoContrato, int idCargo,
                    Date fechaInicio, Date fechaFin, double salarioBase, int horasTotales,
                    boolean estado, String descripcion,
                    Date fechaRegistro, Trabajador trabajador, TipoContrato tipoContrato, Cargo cargo) {
        this.idContrato = idContrato;
        this.idTrabajador = idTrabajador;
        this.idTipoContrato = idTipoContrato;
        this.idCargo = idCargo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.salarioBase = salarioBase;
        this.horasTotales = horasTotales;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.trabajador = trabajador;
        this.tipoContrato = tipoContrato;
        this.cargo = cargo;
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