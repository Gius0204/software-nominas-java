
package com.grupo01.softwarenominas.CapaEntidad;

public class Nomina2 {
    private int idNomina;
    private int idContrato;
    private int idPeriodo;
    private double sueldoNeto;
    private String estadoPago;
    private String metodoPago;
    private String descripcion;
    private String fechaRegistro;
    private boolean estado;

    public Nomina2(int idNomina, int idContrato, int idPeriodo, double sueldoNeto, String estadoPago, String metodoPago, String descripcion, String fechaRegistro, boolean estado) {
        this.idNomina = idNomina;
        this.idContrato = idContrato;
        this.idPeriodo = idPeriodo;
        this.sueldoNeto = sueldoNeto;
        this.estadoPago = estadoPago;
        this.metodoPago = metodoPago;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }
    
    public Nomina2(){
    }

    public int getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(int idNomina) {
        this.idNomina = idNomina;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public double getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(double sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}