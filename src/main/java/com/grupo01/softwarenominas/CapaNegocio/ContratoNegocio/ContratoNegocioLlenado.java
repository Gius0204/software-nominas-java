/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo01.softwarenominas.CapaNegocio.ContratoNegocio;

import com.grupo01.softwarenominas.CapaEntidad.Area;
import com.grupo01.softwarenominas.CapaEntidad.Cargo;
import com.grupo01.softwarenominas.CapaEntidad.Especialidad;
import com.grupo01.softwarenominas.CapaEntidad.TipoContrato;
import com.grupo01.softwarenominas.CapaPersistencia.ContratoDAO;
import com.grupo01.softwarenominas.CapaPersistencia.TrabajadorDAO;
import javax.swing.JComboBox;

/**
 *
 * @author Usuario
 */
public class ContratoNegocioLlenado {
    ContratoDAO contratoDAO = new ContratoDAO();
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    
    public void cargarAreas(JComboBox<Area> comboBoxArea) {
        try{
            trabajadorDAO.cargarAreas(comboBoxArea);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void cargarEspecialidadesPorArea(JComboBox<Especialidad> comboBoxEspecialidad, int idArea) {
        try{
            trabajadorDAO.cargarEspecialidadesPorArea(comboBoxEspecialidad, idArea);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void cargarTiposContrato(JComboBox<TipoContrato> comboBox) {
        try{
            contratoDAO.cargarTiposContrato(comboBox);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void cargarCargos(JComboBox<Cargo> comboBox) {
        try{
            contratoDAO.cargarCargos(comboBox);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
