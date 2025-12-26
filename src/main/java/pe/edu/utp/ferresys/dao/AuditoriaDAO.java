package pe.edu.utp.ferresys.dao;

import pe.edu.utp.ferresys.db.DatabaseConnection;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Auditoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/*
================================================================================
 AUDITORIA DAO
 RESPONSABILIDAD:
    - REGISTRAR EVENTOS DE AUDITORIA EN BD
================================================================================
*/
public class AuditoriaDAO {

    // SQL alineado con la BD REAL
    private static final String SQL_INSERT =
        "INSERT INTO auditoria (accion, usuario, fecha) VALUES (?, ?, ?)";

    // ============================
    // REGISTRO SIMPLE
    // ============================
    public void registrar(Auditoria auditoria) {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, auditoria.getAccion());
            stmt.setString(2, auditoria.getUsuario());
            stmt.setTimestamp(3, Timestamp.valueOf(auditoria.getFecha()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new TechnicalException("Error registrando auditoría", e);
        }
    }

    // ============================
    // REGISTRO TRANSACCIONAL
    // ============================
    public void registrar(Auditoria auditoria, Connection conn) {

        try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, auditoria.getAccion());
            stmt.setString(2, auditoria.getUsuario());
            stmt.setTimestamp(3, Timestamp.valueOf(auditoria.getFecha()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new TechnicalException("Error registrando auditoría", e);
        }
    }
}
