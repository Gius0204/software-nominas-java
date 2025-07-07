
package com.grupo01.softwarenominas.CapaNegocio;

import com.grupo01.softwarenominas.CapaEntidad.Trabajador;
import com.grupo01.softwarenominas.CapaPersistencia.TrabajadorDAO;
import java.util.regex.Pattern;
import java.util.Calendar;
import javax.swing.JTable;
import java.util.Date;


public class TrabajadorNegocioVarios {
    
    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

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
                throw new Exception("El trabajador no puede ser nulo.");
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
    
    private static void validarNombres(String nombres) throws Exception {
        if (nombres == null || nombres.trim().isEmpty()) {
            throw new Exception("El campo Nombres es obligatorio.");
        }
        if (!nombres.matches("^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]+$")) {
            throw new Exception("El campo Nombres solo debe contener caracteres alfabéticos.");
        }
    }

    private static void validarApellido(String campo, String apellido) throws Exception {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new Exception("El campo " + campo + " es obligatorio.");
        }
        if (!apellido.matches("^[A-Za-zÁÉÍÓÚÑáéíóúñ\\s]+$")) {
            throw new Exception("El campo " + campo + " solo debe contener caracteres alfabéticos.");
        }
    }

    private static void validarTipoYDocumentoIdentidad(String tipoDoc, String doc) throws Exception {
        if (tipoDoc == null || tipoDoc.trim().isEmpty()) {
            throw new Exception("El tipo de documento es obligatorio.");
        }
        if (doc == null || doc.trim().isEmpty()) {
            throw new Exception("El documento de identidad es obligatorio.");
        }

        if (!"DNI".equalsIgnoreCase(tipoDoc) && !"CE".equalsIgnoreCase(tipoDoc)) {
            throw new Exception("El tipo de documento debe ser DNI o CE.");
        }

        if ("DNI".equalsIgnoreCase(tipoDoc)) {
            if (!doc.matches("\\d{8}")) {
                throw new Exception("El DNI debe tener 8 dígitos numéricos.");
            }
        }

        if ("CE".equalsIgnoreCase(tipoDoc)) {
            if (!doc.matches("\\d{9}")) {
                throw new Exception("El Carnet de Extranjería debe tener 9 dígitos numéricos.");
            }
        }
    }

    private static void validarFechaNacimiento(Date fecha) throws Exception {
        if (fecha == null) {
            throw new Exception("La fecha de nacimiento es obligatoria.");
        }

        Date hoy = new Date();
        if (fecha.after(hoy)) {
            throw new Exception("La fecha de nacimiento no puede ser futura.");
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        Date edadMinima = cal.getTime();

        if (fecha.after(edadMinima)) {
            throw new Exception("El trabajador debe ser mayor de 18 años.");
        }
    }

    private static void validarTelefono(String telefono) throws Exception {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new Exception("El teléfono es obligatorio.");
        }
        if (!telefono.matches("9\\d{8}")) {
            throw new Exception("Número no válido. Debe tener 9 dígitos y empezar con 9.");
        }
    }

    private static void validarCorreo(String correo) throws Exception {
        if (correo == null || correo.trim().isEmpty()) {
            throw new Exception("El correo electrónico es obligatorio.");
        }
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!Pattern.matches(regex, correo)) {
            throw new Exception("El correo electrónico no tiene un formato válido.");
        }
    }

    private static void validarSexo(String sexo) throws Exception {
        if (sexo == null || sexo.trim().isEmpty()) {
            throw new Exception("El campo sexo es obligatorio.");
        }
        if (!"M".equalsIgnoreCase(sexo) && !"F".equalsIgnoreCase(sexo)) {
            throw new Exception("Debe seleccionar sexo Masculino (M) o Femenino (F).");
        }
    }

    private static void validarDireccion(String direccion) throws Exception {
        if (direccion.length() > 150) {
            throw new Exception("La dirección no debe exceder los 150 caracteres.");
        }
    }

    private static void validarDescripcion(String descripcion) throws Exception {
        if (descripcion.length() > 200) {
            throw new Exception("La descripción no debe exceder los 200 caracteres.");
        }
    }
}