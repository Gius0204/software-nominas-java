/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.nominanegocio;

import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capapersistencia.NominaDAO;

import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class NominaNegocioLlenadoTest {
    
    @Test
    public void testCargarPeriodosPago() {
        // Arrange
        @SuppressWarnings("unchecked")
        JComboBox<PeriodoPago> comboBoxMock = mock(JComboBox.class);
        NominaDAO daoMock = mock(NominaDAO.class);
        NominaNegocioLlenado negocio = new NominaNegocioLlenado(daoMock);

        // Act
        negocio.cargarPeriodosPago(comboBoxMock);

        // Assert
        verify(daoMock).cargarPeriodosPago(comboBoxMock);
    }

    @Test
    public void testObtenerTipoContratoPorId() {
        // Arrange
        int id = 10;
        TipoContrato contratoEsperado = new TipoContrato(id, "Indeterminado");
        NominaDAO daoMock = mock(NominaDAO.class);
        when(daoMock.obtenerTipoContratoPorId(id)).thenReturn(contratoEsperado);

        NominaNegocioLlenado negocio = new NominaNegocioLlenado(daoMock);

        // Act
        TipoContrato resultado = negocio.obtenerTipoContratoPorId(id);

        // Assert
        assertEquals(contratoEsperado, resultado);
        verify(daoMock).obtenerTipoContratoPorId(id);
    }
}
