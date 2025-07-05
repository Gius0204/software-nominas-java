
package com.grupo01.softwarenominas.CapaNegocio.NominaNegocio;
import java.util.Calendar;
import java.util.Date;

public class NominaNegocioCalculo {
    private static final double SUELDO_MINIMO_2025 = 1025.00;
    private static double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
    
    public static double calcularSueldoPorHora(double sueldoBase, int horasMensuales) {
        return sueldoBase / horasMensuales;
    }
    
    public static int calcularHorasExtras(int horasTrabajadas, int horasTotales) {
        return Math.max(0, horasTrabajadas - horasTotales);
    }

    public static int calcularHorasNoCompletadas(int horasTrabajadas, int horasTotales) {
        return Math.max(0, horasTotales - horasTrabajadas);
    }

    public static double calcularPagoHorasExtras(double sueldoBase, int horasExtras, int horasMensuales) {
        double pagoHora = calcularSueldoPorHora(sueldoBase, horasMensuales);

        if (horasExtras <= 6) {
            return redondear(horasExtras * pagoHora * 1.25); // 25% adicional
        } else {
            int primeras6 = 6;
            int restantes = horasExtras - 6;
            return redondear((primeras6 * pagoHora * 1.25) + (restantes * pagoHora * 1.35)); // 35% adicional
        }
    }
    
    public static double calcularGratificacionLegal(double sueldoBase, Date fechaInicioContrato, Date fechaFinPeriodo, String tipoSeguro) {
        Calendar finPeriodo = Calendar.getInstance();
        finPeriodo.setTime(fechaFinPeriodo);

        int mesFin = finPeriodo.get(Calendar.MONTH) + 1;

        if (mesFin != 7 && mesFin != 12) {
            return 0.0;
        }

        Calendar inicioContrato = Calendar.getInstance();
        inicioContrato.setTime(fechaInicioContrato);

        Calendar inicioSemestre = Calendar.getInstance();
        Calendar finSemestre = Calendar.getInstance();

        if (mesFin == 7) {
            inicioSemestre.set(finPeriodo.get(Calendar.YEAR), Calendar.JANUARY, 1);
            finSemestre.set(finPeriodo.get(Calendar.YEAR), Calendar.JUNE, 30);
        } else {
            inicioSemestre.set(finPeriodo.get(Calendar.YEAR), Calendar.JULY, 1);
            finSemestre.set(finPeriodo.get(Calendar.YEAR), Calendar.DECEMBER, 31);
        }
        if (inicioContrato.after(inicioSemestre)) {
            inicioSemestre = inicioContrato;
        }
        int meses = (finSemestre.get(Calendar.YEAR) - inicioSemestre.get(Calendar.YEAR)) * 12 +
                    (finSemestre.get(Calendar.MONTH) - inicioSemestre.get(Calendar.MONTH)) + 1;

        if (meses < 0) meses = 0;
        if (meses > 6) meses = 6;
        double gratificacion = (sueldoBase * meses) / 6.0;
        
        double porcentajeBonificacion = 0.0;
        
        if ("ESSALUD".equalsIgnoreCase(tipoSeguro)) {
            porcentajeBonificacion = 0.09;
        } else if ("EPS".equalsIgnoreCase(tipoSeguro)) {
            porcentajeBonificacion = 0.0675;
        }
        double bonificacionExtra = gratificacion * porcentajeBonificacion;

        return redondear(gratificacion + bonificacionExtra);
    }


    public static double calcularAsignacionFamiliar(boolean tieneAsignacionFamiliar) {
        return tieneAsignacionFamiliar ? SUELDO_MINIMO_2025 * 0.10 : 0.0;
    }

    public static double calcularCTS(double sueldoBase, double asignacionFamiliar, double gratificacionLegal) {
        return 0.5 * ((gratificacionLegal / 6.0) + sueldoBase + asignacionFamiliar);
    }

    public static double calcularDescuentoHorasNoCompletadas(double sueldoBase, int horasNoCompletadas, int horasMensuales) {
        double pagoHora = calcularSueldoPorHora(sueldoBase, horasMensuales);
        return redondear(pagoHora * horasNoCompletadas);
    }

    public static double calcularSeguroSalud(double sueldoBase, String tipoSeguro) {
        if ("ESSALUD".equalsIgnoreCase(tipoSeguro)) return redondear(sueldoBase * 0.03);
        else if ("EPS".equalsIgnoreCase(tipoSeguro)) return redondear(sueldoBase * 0.05);
        else return 0.0;
    }

    public static double calcularSeguroVida(double sueldoBase, boolean tiene) {
        return tiene ? redondear(sueldoBase * 0.015) : 0.0;
    }

    public static double calcularSeguroAccidentes(double sueldoBase, boolean tiene) {
        return tiene ? redondear(sueldoBase * 0.012) : 0.0;
    }

    public static double calcularDescuentoAFP(double sueldoBase) {
        return redondear(sueldoBase * 0.10);
    }

    public static double calcularRenta(double sueldoBase, boolean esExterno) {
        double tasa = esExterno ? 0.08 : 0.05;
        return redondear(sueldoBase * tasa);
    }

    public static double calcularTotalIngresos(double sueldoBase, double pagoHorasExtras, double gratificacion,
                                               double asignacion, double cts) {
        return redondear(sueldoBase + pagoHorasExtras + gratificacion + asignacion + cts);
    }

    public static double calcularTotalDescuentos(double dHorasNoCompletadas, double sSalud, double sVida,
                                                 double sAccidente, double afp, double renta) {
        return redondear(dHorasNoCompletadas + sSalud + sVida + sAccidente + afp + renta);
    }

    public static double calcularSueldoNeto(double ingresos, double descuentos) {
        return redondear(ingresos - descuentos);
    }
}