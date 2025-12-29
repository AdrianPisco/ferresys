package pe.edu.utp.ferresys.service;

import java.sql.Connection;
import java.time.LocalDateTime;

import pe.edu.utp.ferresys.dao.AuditoriaDAO;
import pe.edu.utp.ferresys.dao.UsuarioDAO;
import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Auditoria;
import pe.edu.utp.ferresys.model.Rol;
import pe.edu.utp.ferresys.model.Usuario;
import pe.edu.utp.ferresys.service.base.ServiceTransaccional;
import pe.edu.utp.ferresys.util.PasswordUtils;
import pe.edu.utp.ferresys.model.Permiso;
import pe.edu.utp.ferresys.security.SecurityManager;
import pe.edu.utp.ferresys.session.UserSession;

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
public class UsuarioService extends ServiceTransaccional {

	// DEPENDENCIAS
	private final UsuarioDAO usuarioDAO;
	private final AuditoriaDAO auditoriaDAO = new AuditoriaDAO();

	// CONSTRUCTOR
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
			registrarAuditoria("LOGIN_FALLIDO");
			throw new BusinessException("Credenciales incorrectas");
		}

		// CASO 2: USUARIO INACTIVO
		if (!usuario.isEstado()) {
			registrarAuditoria("LOGIN_FALLIDO");
			throw new BusinessException("Usuario inactivo");
		}

		// CASO 3: PASSWORD INCORRECTO
		if (!PasswordUtils.checkPassword(passwordPlano, usuario.getPasswordHash())) {
			registrarAuditoria("LOGIN_FALLIDO");
			throw new BusinessException("Credenciales incorrectas");
		}

		// CASO 4: LOGIN EXITOSO
		UserSession.setUsuarioActual(usuario);

		registrarAuditoria("LOGIN_EXITOSO");

		return usuario;
	}

	// =========================================================
	// CREACION DE USUARIO
	// =========================================================
	public void createUser(String username, String passwordPlano, Rol rol) {

		SecurityManager.validar(Permiso.USUARIO_CREAR);
		validarUsuarioNoExiste(username);

		Connection conn = abrirTransaccion();

		try {
			String passwordHash = generarPasswordHash(passwordPlano);

			Usuario usuario = construirUsuarioNuevo(username, passwordHash, rol);

			usuarioDAO.create(usuario, conn);

			Auditoria auditoria = new Auditoria();
			auditoria.setUsuario(username);
			auditoria.setAccion("CREAR_USUARIO");
			auditoria.setFecha(LocalDateTime.now());

			auditoriaDAO.registrar(auditoria, conn);

			commit(conn);

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

	public Usuario getUsuarioPorUsername(String username) {

		SecurityManager.validar(Permiso.USUARIO_VER);

		Usuario usuario = usuarioDAO.findByUsername(username);

		if (usuario == null) {
			throw new BusinessException("Usuario no encontrado");
		}

		return usuario;
	}

	public void desactivarUsuario(String username) {

		SecurityManager.validar(Permiso.USUARIO_EDITAR);

		Connection conn = abrirTransaccion();

		try {
			Usuario usuario = usuarioDAO.findByUsername(username);

			if (usuario == null) {
				throw new BusinessException("Usuario no existe");
			}

			usuario.setEstado(false);
			usuarioDAO.updateEstado(usuario, conn);

			Auditoria auditoria = new Auditoria();
			auditoria.setUsuario(username);
			auditoria.setAccion("DESACTIVAR_USUARIO");
			auditoria.setFecha(LocalDateTime.now());

			auditoriaDAO.registrar(auditoria, conn);

			commit(conn);

		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			cerrar(conn);
		}
	}

	public void eliminarUsuario(String username) {

		SecurityManager.validar(Permiso.USUARIO_ELIMINAR);

		Connection conn = abrirTransaccion();

		try {
			usuarioDAO.deleteByUsername(username, conn);

			Auditoria auditoria = new Auditoria();
			auditoria.setUsuario(username);
			auditoria.setAccion("ELIMINAR_USUARIO");
			auditoria.setFecha(LocalDateTime.now());

			auditoriaDAO.registrar(auditoria, conn);

			commit(conn);

		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			cerrar(conn);
		}
	}

	// =========================================================
	// METODOS PRIVADOS
	// =========================================================
	private Usuario construirUsuarioNuevo(String username, String passwordHash, Rol rol) {

		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPasswordHash(passwordHash);
		usuario.setEstado(true);
		usuario.setRol(rol);

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

	private void registrarAuditoria(String accion) {

		Usuario usuarioSesion = UserSession.getUsuarioActual();

		Auditoria auditoria = new Auditoria();
		auditoria.setUsuario(usuarioSesion != null ? usuarioSesion.getUsername() : "SIN_SESION");
		auditoria.setAccion(accion);
		auditoria.setFecha(LocalDateTime.now());

		auditoriaDAO.registrar(auditoria);
	}

}
