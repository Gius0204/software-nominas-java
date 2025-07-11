
package com.grupo01.softwarenominas.capanegocio;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTable;

import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;


public class TrabajadorNegocioVarios {
    
    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    private static final Pattern CORREO_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

    public int listarTrabajadoresFiltrado(JTable tabla) {
        return trabajadorDAO.listarTrabajadoresFiltrado(tabla);
    }

    public int listarTrabajadoresFiltradoPorFecha(JTable tabla, Date fechaInicio, Date fechaFin) {
        return trabajadorDAO.listarTrabajadoresFiltradoPorFecha(tabla, fechaInicio, fechaFin);
    }

    public Trabajador buscarPorDNI(String dni) {
        return trabajadorDAO.buscarPorDNI(dni);
    }

    public Trabajador buscarPorDocumentoIdentidad(String documentoIdentidad) {
        return trabajadorDAO.buscarPorDocumentoIdentidad(documentoIdentidad);
    }

    public boolean actualizarTrabajador(Trabajador t) {
        return trabajadorDAO.actualizarTrabajador(t);
    }

    public boolean registrarTrabajador(Trabajador t) {
        return trabajadorDAO.registrarTrabajador(t);
    }

    public boolean eliminarTrabajador(int id) {
        return trabajadorDAO.eliminarTrabajador(id);
    }
    
    public String validarTrabajador(Trabajador t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException("El trabajador no puede ser nulo.");
            }
            
            String mensajeObligatorios = verificarCamposObligatorios(t);
            if (mensajeObligatorios != null) {
                return mensajeObligatorios;
            }
            validarNombres(t.getNombres());
            validarApellido("Apellido Paterno", t.getApellidoPaterno());
            validarApellido("Apellido Materno", t.getApellidoMaterno());
            validarTipoYDocumentoIdentidad(t.getTipoDocumento(), t.getDocumentoIdentidad());
            validarCorreo(t.getCorreo());
            validarTelefono(t.getTelefono());
            validarSexo(t.getSexo());
            validarFechaNacimiento(t.getFechaNacimiento());

            if (t.getDireccion() != null && !t.getDireccion().trim().isEmpty()) {
                validarDireccion(t.getDireccion());
            }

            if (t.getDescripcion() != null && !t.getDescripcion().trim().isEmpty()) {
                validarDescripcion(t.getDescripcion());
            }

            Trabajador existente = trabajadorDAO.buscarPorDocumentoIdentidad(t.getDocumentoIdentidad());
            if (existente != null && (t.getIdTrabajador()== 0 || existente.getIdTrabajador()!= t.getIdTrabajador())) {
                return "Este documento de identidad ya está registrado.";
            }

            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public String verificarCamposObligatorios(Trabajador t) {
        if (t == null) {
            return "El trabajador no puede ser nulo.";
        }

        if (t.getNombres() == null || t.getNombres().trim().isEmpty()
                || t.getApellidoPaterno() == null || t.getApellidoPaterno().trim().isEmpty()
                || t.getApellidoMaterno() == null || t.getApellidoMaterno().trim().isEmpty()
                || t.getTipoDocumento() == null || t.getTipoDocumento().trim().isEmpty()
                || t.getDocumentoIdentidad() == null || t.getDocumentoIdentidad().trim().isEmpty()
                || t.getCorreo() == null || t.getCorreo().trim().isEmpty()
                || t.getTelefono() == null || t.getTelefono().trim().isEmpty()
                || t.getFechaNacimiento() == null) {

            return "Debe llenar todos los campos obligatorios: Nombres, Apellido Paterno, Apellido Materno, Tipo de documento, Documento de identidad, Correo electrónico, Teléfono y Fecha de nacimiento.";
        }

        return null;
    }
    
    private static void validarNombres(String nombres){
        if (nombres == null || nombres.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo Nombres es obligatorio.");
        }
        if (!nombres.matches("^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]+$")) {
            throw new IllegalArgumentException("El campo Nombres solo debe contener caracteres alfabéticos.");
        }
    }

    private static void validarApellido(String campo, String apellido){
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio.");
        }
        if (!apellido.matches("^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]+$")) {
            throw new IllegalArgumentException("El campo " + campo + " solo debe contener caracteres alfabéticos.");
        }
    }

    private static void validarTipoYDocumentoIdentidad(String tipoDoc, String doc){
        if (tipoDoc == null || tipoDoc.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento es obligatorio.");
        }
        if (doc == null || doc.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento de identidad es obligatorio.");
        }

        if (!"DNI".equalsIgnoreCase(tipoDoc) && !"CE".equalsIgnoreCase(tipoDoc)) {
            throw new IllegalArgumentException("El tipo de documento debe ser DNI o CE.");
        }

        if ("DNI".equalsIgnoreCase(tipoDoc) && !doc.matches("\\d{8}")) {
            throw new IllegalArgumentException("El DNI debe tener 8 dígitos numéricos.");
        }

        if ("CE".equalsIgnoreCase(tipoDoc) && !doc.matches("\\d{9}")) {
            throw new IllegalArgumentException("El Carnet de Extranjería debe tener 9 dígitos numéricos.");
        }
    }

    private static void validarFechaNacimiento(Date fecha){
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }

        Date hoy = new Date();
        if (fecha.after(hoy)) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura.");
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        Date edadMinima = cal.getTime();

        if (fecha.after(edadMinima)) {
            throw new IllegalArgumentException("El trabajador debe ser mayor de 18 años.");
        }
    }

    private static void validarTelefono(String telefono){
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio.");
        }
        if (!telefono.matches("9\\d{8}")) {
            throw new IllegalArgumentException("Número no válido. Debe tener 9 dígitos y empezar con 9.");
        }
    }

    private static void validarCorreo(String correo){
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio.");
        }

        Matcher matcher = CORREO_PATTERN.matcher(correo);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
        }
    }

    private static void validarSexo(String sexo){
        if (sexo == null || sexo.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo sexo es obligatorio.");
        }
        if (!"M".equalsIgnoreCase(sexo) && !"F".equalsIgnoreCase(sexo)) {
            throw new IllegalArgumentException("Debe seleccionar sexo Masculino (M) o Femenino (F).");
        }
    }

    private static void validarDireccion(String direccion){
        if (direccion.length() > 150) {
            throw new IllegalArgumentException("La dirección no debe exceder los 150 caracteres.");
        }
    }

    private static void validarDescripcion(String descripcion){
        if (descripcion.length() > 200) {
            throw new IllegalArgumentException("La descripción no debe exceder los 200 caracteres.");
        }
    }
}