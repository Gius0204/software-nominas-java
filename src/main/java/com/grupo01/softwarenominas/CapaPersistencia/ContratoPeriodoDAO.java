
package com.grupo01.softwarenominas.CapaPersistencia;
import com.grupo01.softwarenominas.CapaConexion.CConexion;

import com.grupo01.softwarenominas.CapaEntidad.ContratoPeriodo;
import com.grupo01.softwarenominas.CapaEntidad.Contrato;
import com.grupo01.softwarenominas.CapaEntidad.PeriodoPago;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Usuario
 */
public class ContratoPeriodoDAO {

    public ContratoPeriodo obtenerContratoPeriodo(int idContrato, int idPeriodo) {
        ContratoPeriodo cp = null;

        try (Connection conn = new CConexion().establecerConexion()) {
            CallableStatement stmt = conn.prepareCall("{call sp_ObtenerContratoPeriodo(?, ?)}");
            stmt.setInt(1, idContrato);
            stmt.setInt(2, idPeriodo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cp = new ContratoPeriodo();
                cp.setIdContratoPeriodo(rs.getInt("IdContratoPeriodo"));
                cp.setHorasTrabajadas(rs.getInt("HorasTrabajadas"));
                cp.setEstadoPago(rs.getString("EstadoPago"));
                cp.setEstado(rs.getBoolean("Estado"));
                cp.setFechaRegistro(rs.getTimestamp("FechaRegistro"));

                cp.setContrato(new Contrato());
                cp.getContrato().setIdContrato(idContrato);

                cp.setPeriodo(new PeriodoPago());
                cp.getPeriodo().setIdPeriodoPago(idPeriodo);

                ContratoDAO contratoDAO = new ContratoDAO();
                PeriodoPagoDAO periodoDAO = new PeriodoPagoDAO();

                Contrato contrato = contratoDAO.obtenerContratoPorId(idContrato);
                PeriodoPago periodo = periodoDAO.obtenerPeriodoPorId(idPeriodo);
                
                if (contrato == null) {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el contrato relacionado.");
                    return null;
                }
                
                if (periodo == null) {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener el periodo relacionado.");
                    return null;
                }

                cp.setContrato(contrato);
                cp.setPeriodo(periodo);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ContratoPeriodo: " + e.getMessage());
        }

        return cp;
    }
}