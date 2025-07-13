
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

public class ContratoNegocioValidacion {
    
    public boolean validarDocumentoIdentidad (String texto){
        return texto.matches("^\\d{8,9}$");     
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
        return texto.length() <= 250 && texto.matches("[\\p{L}0-9\\s]*");    
    }
    
    public boolean validarSalario(String valor) {
        try {
            double salario = Double.parseDouble(valor);
            return salario >= 1025 && salario <= 999999;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean validarSalario (String tipo, String salarioTexto){
        if (tipo.equalsIgnoreCase("SERVICIO EXTERNO")) {
            return validarSalario(salarioTexto);
        }
        return true;
    }  
}