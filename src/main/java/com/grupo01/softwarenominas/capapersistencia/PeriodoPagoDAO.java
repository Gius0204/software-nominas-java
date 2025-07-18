
package com.grupo01.softwarenominas.capapersistencia;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.grupo01.softwarenominas.capaconexion.CConexion;
import com.grupo01.softwarenominas.capaentidad.PeriodoPago;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PeriodoPagoDAO {
    private final CConexion conexion;
    public PeriodoPagoDAO() {
        this.conexion = new CConexion();
    }

    public PeriodoPago obtenerPeriodoPorId(int idPeriodo) {
        PeriodoPago p = null;
        try (
            Connection conn = this.conexion.establecerConexion();
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerPeriodoPagoPorId(?)}");
        ) {
            
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