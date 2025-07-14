
package com.grupo01.softwarenominas.capaentidad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
