/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo01.softwarenominas.CapaNegocio.ContratoNegocio;

/**
 *
 * @author Usuario
 */
public class ContratoNegocioValidacion {
    
    public boolean validarDocumentoIdentidad (String texto){
        boolean esValido = true;

        if (!texto.matches("^\\d{8,9}$")) esValido = false;
        
        return esValido;
        
    }
    
    public boolean validarHoras(String horasStr) {
        try {
            int horas = Integer.parseInt(horasStr);
            return horas >= 80 && horas <= 200;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean validarDescripcion (String texto){
        boolean esValido = true;

        if (texto.length() > 250 || !texto.matches("[a-zA-Z0-9\\s]*")) esValido = false;
        
        return esValido;
        
    }
    
    public boolean validarSalario(String valor) {
        try {
            double salario = Double.parseDouble(valor);
            return salario >= 1025 && salario <= 999999; // Mínimo Perú y máximo 6 cifras
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean validarSalario (String tipo, String salarioTexto){
        // Salario
        if (tipo.equalsIgnoreCase("SERVICIO EXTERNO")) {
            validarSalario(salarioTexto);
        }
        return true;
    }
    
    
    
}
