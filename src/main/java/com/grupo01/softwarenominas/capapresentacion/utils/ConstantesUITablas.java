package com.grupo01.softwarenominas.capapresentacion.utils;

public class ConstantesUITablas {
    private ConstantesUITablas() {
        // Constructor privado para evitar instanciación
    }

    public static final String DOCUMENTO_IDENTIDAD = "DocumentoIdentidad";
    public static final String NOMBRES = "Nombres";

    public static final String[] COLUMNAS_CONTRATO = { "FechaInicio", "FechaFin", "HorasTotales", "HorasTrabajadas", "EstadoPago", DOCUMENTO_IDENTIDAD, NOMBRES, "ApellidoPaterno", "ApellidoMaterno", "AreaNombre", "Especialidad", "TipoContratoNombre", "CargoNombre" }; // NOSONAR

    public static final String[] COLUMNAS_NOMINA = { NOMBRES, DOCUMENTO_IDENTIDAD, "PeriodoPago", "HorasTotales", "HorasTrabajadas", "EstadoPago", "SalarioBase", "PagoHorasExtras", "GratificacionLegal", "AsignacionFamiliar", "CTS", "TotalIngresos", "DescuentoHorasNoCompletadas", "DescuentoSeguroSalud", "DescuentoSeguroVida", "DescuentoSeguroAccidentes", "DescuentoAFP", "DescuentoRenta", "TotalDescuentos", "SueldoNeto", "MetodoPago" }; // NOSONAR

    public static final String[] COLUMNAS_TRABAJADOR = { NOMBRES, "ApellidoPaterno", "ApellidoMaterno", "TipoDocumento", DOCUMENTO_IDENTIDAD, "Telefono", "CorreoElectronico" };// NOSONAR

}
