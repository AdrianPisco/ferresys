package pe.edu.utp.ferresys.dao;

import pe.edu.utp.ferresys.db.DatabaseConnection;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.mapper.RolMapper;
import pe.edu.utp.ferresys.model.Rol;
import pe.edu.utp.ferresys.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
================================================================================
 USUARIO DAO
 RESPONSABILIDAD:
    - EJECUTAR SQL SOBRE LA TABLA usuarios
    - MAPEAR RESULTADOS A OBJETOS Usuario
    - TRADUCIR id_role (BD) ↔ Rol (DOMINIO)
================================================================================
*/
public class UsuarioDAO {

	// =========================================================
	// SQL CONSTANTES
	// =========================================================
	private static final String SQL_FIND_BY_USERNAME = "SELECT id_usuario, username, password_hash, estado, id_role "
			+ "FROM usuarios WHERE username = ?";

	private static final String SQL_INSERT = "INSERT INTO usuarios (username, password_hash, estado, id_role) "
			+ "VALUES (?, ?, ?, ?)";

	private static final String SQL_UPDATE_ESTADO = "UPDATE usuarios SET estado = ? WHERE username = ?";

	private static final String SQL_DELETE_BY_USERNAME = "DELETE FROM usuarios WHERE username = ?";

	// =========================================================
	// BUSCAR USUARIO POR USERNAME
	// =========================================================
	public Usuario findByUsername(String username) {

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQL_FIND_BY_USERNAME)) {

			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				int idRole = rs.getInt("id_role");
				Rol rol = RolMapper.fromId(idRole);

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("id_usuario"));
				usuario.setUsername(rs.getString("username"));
				usuario.setPasswordHash(rs.getString("password_hash"));
				usuario.setEstado(rs.getBoolean("estado"));
				usuario.setRol(rol);

				return usuario;
			}

			return null;

		} catch (SQLException e) {
			throw new TechnicalException("Error al buscar usuario por username", e);
		}
	}

	// =========================================================
	// CREAR USUARIO
	// =========================================================
	public void create(Usuario usuario, Connection conn) {

		try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERT, new String[] { "id_usuario" })) {

			stmt.setString(1, usuario.getUsername());
			stmt.setString(2, usuario.getPasswordHash());
			stmt.setBoolean(3, usuario.isEstado());
			stmt.setInt(4, RolMapper.toId(usuario.getRol()));

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				usuario.setIdUsuario(rs.getInt(1));
			}

		} catch (SQLException e) {
			throw new TechnicalException("Error al crear usuario", e);
		}
	}

	// =========================================================
	// ACTUALIZAR ESTADO DE USUARIO (ACTIVO / INACTIVO)
	// =========================================================
	public void updateEstado(Usuario usuario, Connection conn) {

		try (PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE_ESTADO)) {

			stmt.setBoolean(1, usuario.isEstado());
			stmt.setString(2, usuario.getUsername());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new TechnicalException("Error al actualizar estado del usuario", e);
		}
	}

	// =========================================================
	// ELIMINAR USUARIO POR USERNAME
	// =========================================================
	public void deleteByUsername(String username, Connection conn) {

		try (PreparedStatement stmt = conn.prepareStatement(SQL_DELETE_BY_USERNAME)) {

			stmt.setString(1, username);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new TechnicalException("Error al eliminar usuario", e);
		}
	}
}
