/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.grupo01.softwarenominas.capanegocio.nominanegocio;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class NominaNegocioCalculoTest {

    @Test
    public void testCalcularSueldoPorHora() {
        double sueldoBase = 1025.0;
        int horasMensuales = 160;
        double esperado = 6.41;
        double resultado = NominaNegocioCalculo.calcularSueldoPorHora(sueldoBase, horasMensuales);
        assertEquals(esperado, resultado, 0.01);
    }

    @Test
    public void testCalcularHorasExtras() {
        assertEquals(5, NominaNegocioCalculo.calcularHorasExtras(165, 160));
        assertEquals(0, NominaNegocioCalculo.calcularHorasExtras(150, 160));
    }

    @Test
    public void testCalcularHorasNoCompletadas() {
        assertEquals(10, NominaNegocioCalculo.calcularHorasNoCompletadas(150, 160));
        assertEquals(0, NominaNegocioCalculo.calcularHorasNoCompletadas(170, 160));
    }

    @Test
    public void testCalcularPagoHorasExtras_5horas() {
        double pago = NominaNegocioCalculo.calcularPagoHorasExtras(1600, 5, 160);
        assertEquals(62.5, pago, 0.1);
    }

    @Test
    public void testCalcularPagoHorasExtras_8horas() {
        double pago = NominaNegocioCalculo.calcularPagoHorasExtras(1600, 8, 160);
        assertEquals(102.0, pago, 0.1);
    }

    @Test
    public void testCalcularGratificacionLegalJulio_Essalud() {
        Date inicio = getDate(2025, 1, 15);   // 15 Enero
        Date fin = getDate(2025, 7, 10);      // 10 Julio
        double result = NominaNegocioCalculo.calcularGratificacionLegal(1200, inicio, fin, "ESSALUD");
        assertTrue(result > 0);
    }

    @Test
    public void testCalcularGratificacionLegalNoJulioNiDiciembre() {
        Date inicio = getDate(2025, 1, 1);
        Date fin = getDate(2025, 4, 1); // Abril
        double result = NominaNegocioCalculo.calcularGratificacionLegal(1200, inicio, fin, "ESSALUD");
        assertEquals(0.0, result, 0.0);
    }

    @Test
    public void testCalcularAsignacionFamiliar() {
        assertEquals(102.5, NominaNegocioCalculo.calcularAsignacionFamiliar(true), 0.1);
        assertEquals(0.0, NominaNegocioCalculo.calcularAsignacionFamiliar(false), 0.0);
    }

    @Test
    public void testCalcularCTS() {
        double cts = NominaNegocioCalculo.calcularCTS(1200, 100, 600);
        assertEquals(700.0, cts, 0.1);
    }

    @Test
    public void testCalcularDescuentoHorasNoCompletadas() {
        double descuento = NominaNegocioCalculo.calcularDescuentoHorasNoCompletadas(1600, 10, 160);
        assertEquals(100.0, descuento, 0.1);
    }

    @Test
    public void testCalcularSeguroSalud() {
        assertEquals(30.0, NominaNegocioCalculo.calcularSeguroSalud(1000, "ESSALUD"), 0.1);
        assertEquals(50.0, NominaNegocioCalculo.calcularSeguroSalud(1000, "EPS"), 0.1);
        assertEquals(0.0, NominaNegocioCalculo.calcularSeguroSalud(1000, ""), 0.0);
    }

    @Test
    public void testCalcularSeguroVida() {
        assertEquals(15.0, NominaNegocioCalculo.calcularSeguroVida(1000, true), 0.1);
        assertEquals(0.0, NominaNegocioCalculo.calcularSeguroVida(1000, false), 0.0);
    }

    @Test
    public void testCalcularSeguroAccidentes() {
        assertEquals(12.0, NominaNegocioCalculo.calcularSeguroAccidentes(1000, true), 0.1);
        assertEquals(0.0, NominaNegocioCalculo.calcularSeguroAccidentes(1000, false), 0.0);
    }

    @Test
    public void testCalcularDescuentoAFP() {
        assertEquals(100.0, NominaNegocioCalculo.calcularDescuentoAFP(1000), 0.1);
    }

    @Test
    public void testCalcularRenta() {
        assertEquals(80.0, NominaNegocioCalculo.calcularRenta(1000, true), 0.1);
        assertEquals(50.0, NominaNegocioCalculo.calcularRenta(1000, false), 0.1);
    }

    @Test
    public void testCalcularTotalIngresos() {
        double total = NominaNegocioCalculo.calcularTotalIngresos(1000, 200, 300, 100, 150);
        assertEquals(1750.0, total, 0.1);
    }

    @Test
    public void testCalcularTotalDescuentos() {
        double total = NominaNegocioCalculo.calcularTotalDescuentos(50, 20, 15, 10, 100, 30);
        assertEquals(225.0, total, 0.1);
    }

    @Test
    public void testCalcularSueldoNeto() {
        assertEquals(900.0, NominaNegocioCalculo.calcularSueldoNeto(1200, 300), 0.1);
    }

    private Date getDate(int anio, int mes, int dia) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, anio);
        cal.set(Calendar.MONTH, mes - 1); // Enero = 0
        cal.set(Calendar.DAY_OF_MONTH, dia);
        return cal.getTime();
    }
}
