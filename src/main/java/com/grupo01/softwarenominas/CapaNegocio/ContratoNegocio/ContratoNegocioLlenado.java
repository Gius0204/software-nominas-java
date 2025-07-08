
package com.grupo01.softwarenominas.CapaNegocio.ContratoNegocio;

import javax.swing.JComboBox;

import com.grupo01.softwarenominas.CapaEntidad.Area;
import com.grupo01.softwarenominas.CapaEntidad.Cargo;
import com.grupo01.softwarenominas.CapaEntidad.Especialidad;
import com.grupo01.softwarenominas.CapaEntidad.TipoContrato;
import com.grupo01.softwarenominas.CapaPersistencia.ContratoDAO;
import com.grupo01.softwarenominas.CapaPersistencia.TrabajadorDAO;

public class ContratoNegocioLlenado {
    ContratoDAO contratoDAO = new ContratoDAO();
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    
    public void cargarAreas(JComboBox<Area> comboBoxArea) {
        try{
            trabajadorDAO.cargarAreas(comboBoxArea);
        }
        catch (Exception ex) {
        }
    }
    
    public void cargarEspecialidadesPorArea(JComboBox<Especialidad> comboBoxEspecialidad, int idArea) {
        try{
            trabajadorDAO.cargarEspecialidadesPorArea(comboBoxEspecialidad, idArea);
        }
        catch (Exception ex) {
        }
    }
    
    public void cargarTiposContrato(JComboBox<TipoContrato> comboBox) {
        try{
            contratoDAO.cargarTiposContrato(comboBox);
        }
        catch (Exception ex) {
        }
    }
    
    public void cargarCargos(JComboBox<Cargo> comboBox) {
        try{
            contratoDAO.cargarCargos(comboBox);
        }
        catch (Exception ex) {
        }
    }
}