package pe.edu.utp.ferresys.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import pe.edu.utp.ferresys.dao.AuditoriaDAO;
import pe.edu.utp.ferresys.dao.UsuarioDAO;
import pe.edu.utp.ferresys.db.DatabaseConnection;
import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Auditoria;
import pe.edu.utp.ferresys.model.Usuario;
import pe.edu.utp.ferresys.util.PasswordUtils;

/*
================================================================================
 USUARIO SERVICE
 RESPONSABILIDAD:
    - CONTIENE LA LOGICA DE NEGOCIO DE USUARIOS
    - COORDINA DAO + REGLAS
    - NO CONTIENE SQL
    - NO CONTIENE UI
================================================================================
*/
public class UsuarioService {

	// =========================================================
	// DEPENDENCIA
	// =========================================================
	private final UsuarioDAO usuarioDAO;
	private final AuditoriaService auditoriaService = new AuditoriaService();
	private final AuditoriaDAO auditoriaDAO = new AuditoriaDAO();

	// =========================================================
	// CONSTRUCTOR
	// =========================================================
	public UsuarioService() {
		this.usuarioDAO = new UsuarioDAO();
	}

	// =========================================================
	// LOGIN DE USUARIO
	// =========================================================
	public Usuario login(String username, String passwordPlano) {

		Usuario usuario = usuarioDAO.findByUsername(username);

		// CASO 1: USUARIO NO EXISTE
		if (usuario == null) {
			auditoriaService.registrarEvento(null, "LOGIN_FALLIDO", "Intento con username inexistente: " + username);
			throw new BusinessException("Credenciales incorrectas");
		}

		// CASO 2: USUARIO INACTIVO
		if (!usuario.isEstado()) {
			auditoriaService.registrarEvento(usuario.getIdUsuario(), "LOGIN_FALLIDO", "Usuario inactivo");
			throw new BusinessException("Usuario inactivo");
		}

		// CASO 3: PASSWORD INCORRECTO
		if (!PasswordUtils.checkPassword(passwordPlano, usuario.getPasswordHash())) {
			auditoriaService.registrarEvento(usuario.getIdUsuario(), "LOGIN_FALLIDO", "Password incorrecto");
			throw new BusinessException("Credenciales incorrectas");
		}

		// CASO 4: LOGIN EXITOSO
		auditoriaService.registrarEvento(usuario.getIdUsuario(), "LOGIN_EXITOSO", "Login correcto");

		return usuario;
	}

	public void createUser(String username, String passwordPlano, int idRole) {

		validarUsuarioNoExiste(username);

		Connection conn = null;

		try {
			conn = DatabaseConnection.getConnection();
			conn.setAutoCommit(false);

			String passwordHash = generarPasswordHash(passwordPlano);
			Usuario usuario = construirUsuarioNuevo(username, passwordHash, idRole);

			usuarioDAO.create(usuario, conn);

			Auditoria auditoria = new Auditoria();
			auditoria.setIdUsuario(usuario.getIdUsuario());
			auditoria.setAccion("CREAR_USUARIO");
			auditoria.setDetalle("Usuario creado correctamente");
			auditoria.setFecha(LocalDateTime.now());

			auditoriaDAO.registrar(auditoria, conn);

			conn.commit();

		} catch (BusinessException e) {
			rollback(conn);
			throw e;

		} catch (Exception e) {
			rollback(conn);
			throw new TechnicalException("Error al crear usuario", e);

		} finally {
			cerrar(conn);
		}
	}

	private Usuario construirUsuarioNuevo(String username, String passwordHash, int idRole) {
		Usuario usuario = new Usuario();

		usuario.setUsername(username);
		usuario.setPasswordHash(passwordHash);
		usuario.setEstado(true);
		usuario.setIdRole(idRole);

		return usuario;
	}

	private void validarUsuarioNoExiste(String username) {
		if (usuarioDAO.findByUsername(username) != null) {
			throw new BusinessException("El usuario ya existe");
		}
	}

	private String generarPasswordHash(String passwordPlano) {
		return PasswordUtils.hashPassword(passwordPlano);
	}

	private void rollback(Connection conn) {
		try {
			if (conn != null)
				conn.rollback();
		} catch (SQLException ignored) {
		}
	}

	private void cerrar(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException ignored) {
		}
	}

}
