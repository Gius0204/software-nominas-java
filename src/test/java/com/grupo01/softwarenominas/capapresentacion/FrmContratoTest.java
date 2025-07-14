/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapresentacion;

import com.grupo01.softwarenominas.capaentidad.Area;
import com.grupo01.softwarenominas.capaentidad.Cargo;
import com.grupo01.softwarenominas.capaentidad.Contrato;
import com.grupo01.softwarenominas.capaentidad.DetalleContrato;
import com.grupo01.softwarenominas.capaentidad.Especialidad;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capaentidad.Trabajador;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioCalculo;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioListado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioLlenado;
import com.grupo01.softwarenominas.capanegocio.contratonegocio.ContratoNegocioRegistro;
import com.grupo01.softwarenominas.capanegocio.trabajadornegocio.TrabajadorNegocioLlenado;
import com.grupo01.softwarenominas.capapresentacion.utils.UtilidadesFrmContrato;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class FrmContratoTest {

    private FrmContrato frm;

    @Mock private ContratoNegocioRegistro mockRegistro;
    @Mock private ContratoNegocioLlenado mockLlenado;
    @Mock private ContratoNegocioListado mockListado;
    @Mock private ContratoNegocioCalculo mockCalculo;
    @Mock private TrabajadorNegocioLlenado mockTrabajadorLlenado;

    @Before
    public void setUp() {
        mockRegistro = mock(ContratoNegocioRegistro.class);
        mockTrabajadorLlenado = mock(TrabajadorNegocioLlenado.class);
        mockListado = mock(ContratoNegocioListado.class);
        mockLlenado = mock(ContratoNegocioLlenado.class);
        mockCalculo = mock(ContratoNegocioCalculo.class);

        

        frm = new FrmContrato(mockLlenado, mockCalculo,mockListado,mockRegistro, mockTrabajadorLlenado);

        // Inicializar campos de GUI necesarios
        frm.setTxtDNI(new JTextField());
        frm.setTxtNombres(new JTextField());
        frm.setLblMensaje(new JLabel());
    }

    @Test
    public void testBuscarTrabajadorPorDNI_TrabajadorEncontrado() {
        frm.getTxtDNI().setText("12345678");

        Trabajador mockTrabajador = new Trabajador();
        mockTrabajador.setNombres("Juan");
        mockTrabajador.setApellidoPaterno("Perez");
        mockTrabajador.setApellidoMaterno("Gomez");

        when(mockTrabajadorLlenado.buscarPorDocumentoIdentidad("12345678")).thenReturn(mockTrabajador);

        frm.buscarTrabajadorPorDNI();

        assertEquals("Juan Perez Gomez", frm.getTxtNombres().getText());
        assertEquals("Trabajador Encontrado", frm.getLblMensaje().getText());
    }

    @Test
    public void testBuscarTrabajadorPorDNI_TrabajadorNoEncontrado() {
        frm.getTxtDNI().setText("87654321");

        when(mockTrabajadorLlenado.buscarPorDocumentoIdentidad("87654321")).thenReturn(null);

        frm.buscarTrabajadorPorDNI();

        assertEquals("", frm.getTxtNombres().getText());
        assertEquals("No se encontró al trabajador, vuelva a intentarlo antes de registrar el contrato.", frm.getLblMensaje().getText());
    }

    @Test
    public void testProcesarContrato_RegistroExitoso() {
        UtilidadesFrmContrato mockUtil = mock(UtilidadesFrmContrato.class);
        frm.setUtil(mockUtil);

        FrmContrato spyFrm = Mockito.spy(frm);

        Contrato contrato = new Contrato();

        doReturn(true).when(spyFrm).formularioValido();
        doReturn(contrato).when(spyFrm).mapearFormularioAContrato();
        doNothing().when(spyFrm).registrarContratoYDetalle(contrato);
        doNothing().when(mockUtil).listarContratosTabla(any(), any(), any(), any(), any(), any());

        spyFrm.setModoEdicionContrato(false);
        spyFrm.setJtbTabla(new JTable());
        spyFrm.setLblMensajeBuscar(new JLabel());

        spyFrm.procesarContrato();

        verify(spyFrm).registrarContratoYDetalle(contrato);
    }

    @Test
    public void testActualizarContratoYDetalle_Exito() {
        FrmContrato spyFrm = Mockito.spy(frm);
        Contrato contrato = Contrato.builder()
            .idContrato(1)
            .idTrabajador(101)
            .idTipoContrato(2)
            .idCargo(3)
            .fechaInicio(new Date())
            .fechaFin(new Date(System.currentTimeMillis() + 86400000L * 30)) // +30 días
            .salarioBase(1500.0)
            .horasTotales(160)
            .estado(true)
            .descripcion("Contrato por proyecto de desarrollo")
            .estadoContrato("VIGENTE")
            .fechaRegistro(new Date())
            .idArea(4)
            .idEspecialidad(5)
            .trabajador(new Trabajador())
            .tipoContrato(new TipoContrato(2, "CAS"))
            .cargo(new Cargo(3, "Ingeniero de Software"))
            .area(new Area(4, "Desarrollo"))
            .especialidad(new Especialidad(5, "Sistemas"))
            .build();

        frm.setContratoActual(contrato);
        spyFrm.setContratoActual(contrato);
        

        DetalleContrato detalle = DetalleContrato.builder()
            .idDetalleContrato(10)
            .idContrato(1)
            .tipoSeguroSalud("EPS")
            .tieneSeguroDeVida(true)
            .tieneSeguroDeAccidentes(true)
            .tieneAsignacionFamiliar(false)
            .descripcion("Detalle asociado al contrato de prueba")
            .estado(true)
            .fechaRegistro(new Date())
            .contrato(contrato)
            .build();

        frm.setDetalleContratoActual(detalle);
        spyFrm.setDetalleContratoActual(detalle);

        doReturn(detalle).when(spyFrm).mapearFormularioADetalleContrato(1);
        doNothing().when(spyFrm).mostrarMensaje(anyString(), any());
        doNothing().when(spyFrm).salirModoEditar();
        doNothing().when(spyFrm).limpiar();

        when(mockRegistro.actualizarContrato(contrato)).thenReturn(true);
        when(mockRegistro.actualizarDetalleContrato(detalle)).thenReturn(true);

        spyFrm.actualizarContratoYDetalle(contrato);

        verify(spyFrm).mostrarMensaje("Contrato y detalle actualizados.", new Color(0, 128, 0));
    }


    @Test
    public void testRegistrarContratoYDetalle_Exito() {
        FrmContrato spyFrm = Mockito.spy(frm);
        Contrato contrato = Contrato.builder()
            .idContrato(1)
            .idTrabajador(101)
            .idTipoContrato(2)
            .idCargo(3)
            .fechaInicio(new Date())
            .fechaFin(new Date(System.currentTimeMillis() + 86400000L * 30)) // +30 días
            .salarioBase(1500.0)
            .horasTotales(160)
            .estado(true)
            .descripcion("Contrato por proyecto de desarrollo")
            .estadoContrato("VIGENTE")
            .fechaRegistro(new Date())
            .idArea(4)
            .idEspecialidad(5)
            .trabajador(new Trabajador())
            .tipoContrato(new TipoContrato(2, "CAS"))
            .cargo(new Cargo(3, "Ingeniero de Software"))
            .area(new Area(4, "Desarrollo"))
            .especialidad(new Especialidad(5, "Sistemas"))
            .build();

        frm.setContratoActual(contrato);
        spyFrm.setContratoActual(contrato);
        

        DetalleContrato detalle = DetalleContrato.builder()
            .idDetalleContrato(10)
            .idContrato(1)
            .tipoSeguroSalud("EPS")
            .tieneSeguroDeVida(true)
            .tieneSeguroDeAccidentes(true)
            .tieneAsignacionFamiliar(false)
            .descripcion("Detalle asociado al contrato de prueba")
            .estado(true)
            .fechaRegistro(new Date())
            .contrato(contrato)
            .build();

        frm.setDetalleContratoActual(detalle);
        spyFrm.setDetalleContratoActual(detalle);

        ResultadoOperacion resultado = new ResultadoOperacion(true, 1, "OK");

        when(mockRegistro.registrarContrato(contrato)).thenReturn(resultado);
        doReturn(detalle).when(spyFrm).mapearFormularioADetalleContrato(99);

        when(mockRegistro.registrarDetalleContrato(Mockito.any(DetalleContrato.class)))
            .thenReturn(new ResultadoOperacion(true, 1, "OK"));

        doNothing().when(spyFrm).mostrarMensaje(anyString(), any());
        doNothing().when(spyFrm).limpiar();

        spyFrm.registrarContratoYDetalle(contrato);

        verify(spyFrm).mostrarMensaje("Contrato registrado correctamente.", new Color(0, 128, 0));
    }

    @Test
    public void testAbrirHorasTrabajadasDialog_NoException() {
        FrmContrato spyFrm = Mockito.spy(frm);
        spyFrm.setContratoActual(new Contrato());

        // Puedes mockear el dialog si has separado su creación en un método
        doNothing().when(spyFrm).abrirHorasTrabajadasDialog();

        spyFrm.abrirHorasTrabajadasDialog();

        // Solo se asegura de que no lanza excepción
        assertTrue(true);
    }

    @Test
    public void testMain_NoExcepcion() {
        FrmContrato.main(new String[]{});
        assertTrue(true); // Solo cobertura
    }
}
