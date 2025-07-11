
package com.grupo01.softwarenominas.capanegocio.contratonegocio;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capapersistencia.ContratoDAO;
import com.grupo01.softwarenominas.capapersistencia.TrabajadorDAO;

public class ContratoNegocioLlenado {
    ContratoDAO contratoDAO = new ContratoDAO();
    TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
    
    public void cargarAreas(JComboBox<Area> comboBoxArea) {
        try{
            trabajadorDAO.cargarAreas(comboBoxArea);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar las areas: " + ex.getMessage(), ConstantesUIContratoNegocio.ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cargarEspecialidadesPorArea(JComboBox<Especialidad> comboBoxEspecialidad, int idArea) {
        try{
            trabajadorDAO.cargarEspecialidadesPorArea(comboBoxEspecialidad, idArea);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar las especialidades: " + ex.getMessage(), ConstantesUIContratoNegocio.ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cargarTiposContrato(JComboBox<TipoContrato> comboBox) {
        try{
            contratoDAO.cargarTiposContrato(comboBox);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar tipos de contrato: " + ex.getMessage(), ConstantesUIContratoNegocio.ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cargarCargos(JComboBox<Cargo> comboBox) {
        try{
            contratoDAO.cargarCargos(comboBox);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los cargos: " + ex.getMessage(), ConstantesUIContratoNegocio.ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }
}