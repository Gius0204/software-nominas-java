/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo01.softwarenominas.CapaNegocio.ContratoNegocio;

/**
 *
 * @author Usuario
 */
public class ResultadoOperacion {
    private boolean exito;
    private int idGenerado;
    private String mensaje;

    public ResultadoOperacion(boolean exito, int idGenerado, String mensaje) {
        this.exito = exito;
        this.idGenerado = idGenerado;
        this.mensaje = mensaje;
    }

    public boolean isExito() {
        return exito;
    }

    public int getIdGenerado() {
        return idGenerado;
    }

    public String getMensaje() {
        return mensaje;
    }
}

