/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion;

import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioListado;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioLlenado;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioRegistro;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioValidacion;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Usuario
 */
public class FrmTrabajadorTest {
    
    @Mock private TrabajadorNegocioListado negocioListado;
    @Mock private TrabajadorNegocioRegistro negocioRegistro;
    @Mock private TrabajadorNegocioValidacion negocioValidacion;
    @Mock private TrabajadorNegocioLlenado negocioLlenado;

    private FrmTrabajador frm;

    @SuppressWarnings("deprecation")
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        frm = new FrmTrabajador(negocioListado, negocioRegistro, negocioValidacion, negocioLlenado);
    }

    @Test
    public void testConstruirTrabajadorDesdeFormulario() {
        frm.getTxtNombres().setText("Carlos");
        frm.getTxtApellidoPaterno().setText("Perez");
        frm.getTxtApellidoMaterno().setText("Lopez");
        frm.getTxtDocumentoIdentidad().setText("12345678");
        frm.getTxtTelefono().setText("999999999");
        frm.getTxtCorreo().setText("correo@correo.com");
        frm.getTxtDireccion().setText("Calle falsa 123");
        frm.getTxtDescripcion().setText("Test");
        frm.getDcFechaNacimiento().setDate(new Date());
        frm.getRbDNI().setSelected(true);
        frm.getRbMasculino().setSelected(true);

        Trabajador t = frm.construirTrabajadorDesdeFormulario();

        assertEquals("Carlos", t.getNombres());
        assertEquals("Perez", t.getApellidoPaterno());
        assertEquals("Lopez", t.getApellidoMaterno());
        assertEquals("12345678", t.getDocumentoIdentidad());
        assertEquals("999999999", t.getTelefono());
        assertEquals("correo@correo.com", t.getCorreo());
        assertEquals("Calle falsa 123", t.getDireccion());
        assertEquals("Test", t.getDescripcion());
        assertEquals("DNI", t.getTipoDocumento());
        assertEquals("Masculino", t.getSexo());
        assertNotNull(t.getFechaNacimiento());
    }

    @Test
    public void testRegistrar_ModoRegistro_Exito() {
        FrmTrabajador spyFrm = Mockito.spy(frm);

        doNothing().when(spyFrm).mostrarJOptionPane(anyString());

        when(negocioValidacion.validarTrabajador(any())).thenReturn(null);
        when(negocioRegistro.registrarTrabajador(any())).thenReturn(true);

        spyFrm.getTxtNombres().setText("Ana");
        spyFrm.getTxtApellidoPaterno().setText("Torres");
        spyFrm.getTxtApellidoMaterno().setText("Mendez");
        spyFrm.getTxtDocumentoIdentidad().setText("87654321");
        spyFrm.getTxtTelefono().setText("955555555");
        spyFrm.getDcFechaNacimiento().setDate(new Date());
        spyFrm.getRbDNI().setSelected(true);
        spyFrm.getRbFemenino().setSelected(true);

        spyFrm.registrar();

        verify(negocioRegistro).registrarTrabajador(any(Trabajador.class));
        verify(spyFrm).mostrarJOptionPane(anyString());
    }


    @Test
    public void testListarTrabajadoresTabla_SinFechas() {
        when(negocioListado.listarTrabajadoresFiltrado(any())).thenReturn(2);

        frm.listarTrabajadoresTabla(null, null);

        verify(negocioListado, atLeastOnce()).listarTrabajadoresFiltrado(any());
        assertEquals("Se encontraron 2 trabajadores.", frm.getLblMensajeBuscar().getText());
    }

    @Test
    public void testLimpiarCampos() {
        frm.getTxtNombres().setText("Valor");
        frm.getTxtApellidoPaterno().setText("Apellido");
        frm.getTxtTelefono().setText("123");

        frm.limpiarCampos();

        assertEquals("", frm.getTxtNombres().getText());
        assertEquals("", frm.getTxtApellidoPaterno().getText());
        assertEquals("", frm.getTxtTelefono().getText());
        assertNull(frm.getDcFechaNacimiento().getDate());
    }

    @Test
    public void testRegresar_CerrarFormulario() {
        frm.setModoEdicion(false);
        frm.regresar();

        assertFalse(frm.isVisible()); // El formulario debería estar oculto
    }

    @Test
    public void testCargarFormularioConTrabajador() {
        Trabajador mockTrabajador = new Trabajador();
        mockTrabajador.setNombres("Juan");
        mockTrabajador.setApellidoPaterno("Perez");
        mockTrabajador.setApellidoMaterno("Lopez");
        mockTrabajador.setDocumentoIdentidad("98765432");
        mockTrabajador.setTelefono("123456789");
        mockTrabajador.setCorreo("correo@correo.com");
        mockTrabajador.setDireccion("Mi casa");
        mockTrabajador.setDescripcion("Test");
        mockTrabajador.setFechaNacimiento(new Date());
        mockTrabajador.setTipoDocumento("DNI");
        mockTrabajador.setSexo("Masculino");

        frm.setTrabajadorActual(mockTrabajador);
        frm.cargarFormularioConTrabajador();

        assertEquals("Juan", frm.getTxtNombres().getText());
        assertEquals("Perez", frm.getTxtApellidoPaterno().getText());
        assertEquals("Lopez", frm.getTxtApellidoMaterno().getText());
        assertEquals("98765432", frm.getTxtDocumentoIdentidad().getText());
        assertEquals("123456789", frm.getTxtTelefono().getText());
        assertEquals("correo@correo.com", frm.getTxtCorreo().getText());
    }
}
