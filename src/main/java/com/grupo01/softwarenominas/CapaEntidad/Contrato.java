package com.grupo01.softwarenominas.capaentidad;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}