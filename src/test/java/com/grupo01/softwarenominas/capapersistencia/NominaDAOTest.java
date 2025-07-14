/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capapersistencia;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.ContratoPeriodo;
import com.grupo01.softwarenominas.capaentidad.DetalleNomina;
import com.grupo01.softwarenominas.capaentidad.Nomina;
import com.grupo01.softwarenominas.capaentidad.PeriodoPago;
import com.grupo01.softwarenominas.capaentidad.TipoContrato;
import com.grupo01.softwarenominas.capanegocio.ResultadoOperacion;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class NominaDAOTest {
    
    private CConexion conexionMock;
    private Connection connectionMock;
    private CallableStatement stmtMock;
    private ResultSet rsMock;
    private NominaDAO nominaDAO;

    @Before
    public void setUp(){
        conexionMock = mock(CConexion.class);
        connectionMock = mock(Connection.class);
        stmtMock = mock(CallableStatement.class);
        rsMock = mock(ResultSet.class);

        nominaDAO = new NominaDAO(conexionMock);
    }

    @Test
    public void testCargarPeriodosPago_agregaElementosAlComboBox() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ObtenerPeriodosPago()}"))
                .thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getInt("IdPeriodoPago")).thenReturn(1);
        when(rsMock.getDate("FechaInicio")).thenReturn(new java.sql.Date(new Date().getTime()));
        when(rsMock.getDate("FechaFin")).thenReturn(new java.sql.Date(new Date().getTime()));
        when(rsMock.getString("Nombre")).thenReturn("Periodo 1");
        when(rsMock.getString("Descripcion")).thenReturn("Desc 1");
        when(rsMock.getBoolean("Estado")).thenReturn(true);
        when(rsMock.getDate("FechaRegistro")).thenReturn(new java.sql.Date(new Date().getTime()));

        JComboBox<PeriodoPago> combo = new JComboBox<>();
        nominaDAO.cargarPeriodosPago(combo);

        assertEquals(2, combo.getItemCount());
        assertEquals("-- Periodo de Pago --", combo.getItemAt(0).getNombre());
        assertEquals("Periodo 1", combo.getItemAt(1).getNombre());
    }

    @Test
    public void testExistePeriodoAnteriorPendientePorContrato_devuelveTrue() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        doNothing().when(stmtMock).setInt(anyInt(), anyInt());
        doNothing().when(stmtMock).registerOutParameter(3, Types.BIT);
        when(stmtMock.getBoolean(3)).thenReturn(true);

        boolean resultado = nominaDAO.existePeriodoAnteriorPendientePorContrato(1, 2);
        assertTrue(resultado);
    }

    @Test
    public void testExistePeriodoAnteriorPendiente_devuelveFalse() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        doNothing().when(stmtMock).setInt(anyInt(), anyInt());
        doNothing().when(stmtMock).registerOutParameter(2,Types.BIT);
        when(stmtMock.getBoolean(2)).thenReturn(false);

        boolean resultado = nominaDAO.existePeriodoAnteriorPendiente(3);
        assertFalse(resultado);
    }

    @Test
    public void testObtenerTipoContratoPorId_retornaTipoContrato() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt("IdTipoContrato")).thenReturn(5);
        when(rsMock.getString("Nombre")).thenReturn("Temporal");
        when(rsMock.getString("Descripcion")).thenReturn("Temporal 6 meses");
        when(rsMock.getBoolean("Estado")).thenReturn(true);
        when(rsMock.getTimestamp("FechaRegistro")).thenReturn(new Timestamp(System.currentTimeMillis()));

        TipoContrato tipoContrato = nominaDAO.obtenerTipoContratoPorId(5);
        assertNotNull(tipoContrato);
        assertEquals("Temporal", tipoContrato.getNombre());
    }

    @Test
    public void testInsertarNominaCompleta_retornaResultadoExitoso() throws Exception {
        Nomina nomina = mock(Nomina.class);
        DetalleNomina detalle = mock(DetalleNomina.class);
        ContratoPeriodo contratoPeriodo = mock(ContratoPeriodo.class);

        when(nomina.getContratoPeriodo()).thenReturn(contratoPeriodo);
        when(contratoPeriodo.getIdContratoPeriodo()).thenReturn(10);

        when(nomina.getSueldoNeto()).thenReturn(1500.0);
        when(nomina.getMetodoPago()).thenReturn("Transferencia");
        when(nomina.getDescripcion()).thenReturn("Nómina Junio");

        when(nomina.getDetalle()).thenReturn(detalle);
        when(detalle.getPagoHorasExtras()).thenReturn(50.0);
        when(detalle.getGratificacionLegal()).thenReturn(100.0);
        when(detalle.getAsignacionFamiliar()).thenReturn(80.0);
        when(detalle.getCts()).thenReturn(120.0);

        when(detalle.getDescuentoHorasNoCompletadas()).thenReturn(10.0);
        when(detalle.getDescuentoSeguroSalud()).thenReturn(5.0);
        when(detalle.getDescuentoSeguroVida()).thenReturn(8.0);
        when(detalle.getDescuentoSeguroAccidentes()).thenReturn(7.0);
        when(detalle.getDescuentoAFP()).thenReturn(20.0);
        when(detalle.getDescuentoRenta()).thenReturn(15.0);

        when(detalle.getTotalIngresos()).thenReturn(1750.0);
        when(detalle.getTotalDescuentos()).thenReturn(65.0);

        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall(anyString())).thenReturn(stmtMock);

        doNothing().when(stmtMock).setInt(anyInt(), anyInt());
        doNothing().when(stmtMock).setDouble(anyInt(), anyDouble());
        doNothing().when(stmtMock).setString(anyInt(), anyString());
        doNothing().when(stmtMock).registerOutParameter(17,Types.INTEGER);
        when(stmtMock.getInt(17)).thenReturn(999);

        ResultadoOperacion resultado = nominaDAO.insertarNominaCompleta(nomina);
        assertTrue(resultado.isExito());
        assertEquals(999, resultado.getIdGenerado());
    }

    @Test
    public void testListarNominasPorPeriodo_agregaFilasATabla() throws Exception {
        when(conexionMock.establecerConexion()).thenReturn(connectionMock);
        when(connectionMock.prepareCall("{call sp_ListarNominasPorPeriodo(?)}"))
            .thenReturn(stmtMock);
        when(stmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true, true, false);
        when(rsMock.getObject(anyString())).thenReturn("valor");

        JTable tablaMock = mock(JTable.class);
        DefaultTableModel modeloMock = mock(DefaultTableModel.class);
        when(tablaMock.getModel()).thenReturn(modeloMock);
        doNothing().when(modeloMock).addColumn(anyString());
        doNothing().when(modeloMock).addRow(any(Object[].class));

        int total = nominaDAO.listarNominasPorPeriodo(tablaMock, 1);
        assertEquals(2, total);
    }

}
