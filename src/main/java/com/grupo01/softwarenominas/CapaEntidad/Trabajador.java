
package com.grupo01.softwarenominas.capaentidad;

import java.util.Date;

public class Trabajador {
    private int idTrabajador;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String documentoIdentidad;
    private String tipoDocumento;
    private String telefono;
    private String correo;
    private String sexo;
    private Date fechaNacimiento;
    private String direccion;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;

    public String getNombreCompleto() {
        return nombres + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    public Trabajador() {
    }

    private Trabajador(Builder builder) {
        this.idTrabajador = builder.idTrabajador;
        this.nombres = builder.nombres;
        this.apellidoPaterno = builder.apellidoPaterno;
        this.apellidoMaterno = builder.apellidoMaterno;
        this.documentoIdentidad = builder.documentoIdentidad;
        this.tipoDocumento = builder.tipoDocumento;
        this.telefono = builder.telefono;
        this.correo = builder.correo;
        this.sexo = builder.sexo;
        this.fechaNacimiento = builder.fechaNacimiento;
        this.direccion = builder.direccion;
        this.descripcion = builder.descripcion;
        this.estado = builder.estado;
        this.fechaRegistro = builder.fechaRegistro;
    }

    public static class Builder {
        private int idTrabajador;
        private String nombres;
        private String apellidoPaterno;
        private String apellidoMaterno;
        private String documentoIdentidad;
        private String tipoDocumento;
        private String telefono;
        private String correo;
        private String sexo;
        private Date fechaNacimiento;
        private String direccion;
        private String descripcion;
        private boolean estado;
        private Date fechaRegistro;

        public Builder idTrabajador(int idTrabajador) { this.idTrabajador = idTrabajador; return this; }
        public Builder nombres(String nombres) { this.nombres = nombres; return this; }
        public Builder apellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; return this; }
        public Builder apellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; return this; }
        public Builder documentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; return this; }
        public Builder tipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; return this; }
        public Builder telefono(String telefono) { this.telefono = telefono; return this; }
        public Builder correo(String correo) { this.correo = correo; return this; }
        public Builder sexo(String sexo) { this.sexo = sexo; return this; }
        public Builder fechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; return this; }
        public Builder direccion(String direccion) { this.direccion = direccion; return this; }
        public Builder descripcion(String descripcion) { this.descripcion = descripcion; return this; }
        public Builder estado(boolean estado) { this.estado = estado; return this; }
        public Builder fechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; return this; }

        public Trabajador build() {
            return new Trabajador(this);
        }
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

}
