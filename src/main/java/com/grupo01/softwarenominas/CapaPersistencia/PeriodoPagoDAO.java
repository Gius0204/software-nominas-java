
package com.grupo01.softwarenominas.CapaPersistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.grupo01.softwarenominas.CapaConexion.CConexion;
import com.grupo01.softwarenominas.CapaEntidad.PeriodoPago;

public class PeriodoPagoDAO {
    public PeriodoPago obtenerPeriodoPorId(int idPeriodo) {
    PeriodoPago p = null;
    try (Connection conn = new CConexion().establecerConexion()) {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PeriodoPago WHERE IdPeriodo = ?");
        stmt.setInt(1, idPeriodo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            p = new PeriodoPago();
            p.setIdPeriodoPago(rs.getInt("IdPeriodo"));
            p.setFechaInicio(rs.getDate("FechaInicio"));
            p.setFechaFin(rs.getDate("FechaFin"));
            p.setNombre(rs.getString("Nombre"));
            p.setDescripcion(rs.getString("Descripcion"));
            p.setEstado(rs.getBoolean("Estado"));
            p.setFechaRegistro(rs.getTimestamp("FechaRegistro"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener PeriodoPago: " + e.getMessage());
    }
    return p;
}
}