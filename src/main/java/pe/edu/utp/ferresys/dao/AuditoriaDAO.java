package pe.edu.utp.ferresys.dao;

import pe.edu.utp.ferresys.db.DatabaseConnection;
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

	private static final String SQL_INSERT = "INSERT INTO auditoria (id_usuario, accion, detalle, fecha) "
			+ "VALUES (?, ?, ?, ?)";

	public void registrar(Auditoria auditoria) {

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
			stmt.setObject(1, auditoria.getIdUsuario());
			stmt.setString(2, auditoria.getAccion());
			stmt.setString(3, auditoria.getDetalle());
			stmt.setTimestamp(4, Timestamp.valueOf(auditoria.getFecha()));

			stmt.executeUpdate();

		} catch (SQLException e) {
			// NO ROMPE EL FLUJO PRINCIPAL
			System.err.println("Error registrando auditoría: " + e.getMessage());
		}
	}

	public void registrar(Auditoria auditoria, Connection conn) {

		try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

			stmt.setObject(1, auditoria.getIdUsuario());
			stmt.setString(2, auditoria.getAccion());
			stmt.setString(3, auditoria.getDetalle());
			stmt.setTimestamp(4, Timestamp.valueOf(auditoria.getFecha()));

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Error registrando auditoría", e);
		}
	}
}
