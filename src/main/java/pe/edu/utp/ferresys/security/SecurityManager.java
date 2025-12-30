package pe.edu.utp.ferresys.security;

import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.model.Usuario;
import pe.edu.utp.ferresys.session.UserSession;

public final class SecurityManager {

	private SecurityManager() {
	}

	// =========================================================
	// VALIDAR SESION ACTIVA
	// =========================================================
	public static void validarSesionActiva() {

		if (!UserSession.isLoggedIn()) {
			throw new BusinessException("ACCESO DENEGADO: NO EXISTE UNA SESION ACTIVA");
		}
	}

	// =========================================================
	// CONSULTA: ¿TIENE PERMISO?
	// =========================================================
	// =========================================================
	// CONSULTA: ¿TIENE PERMISO?
	// =========================================================
	public static boolean tienePermiso(Permiso permiso) {

		// 1. SI NO HAY SESION ACTIVA, NO HAY PERMISOS
		if (!UserSession.isLoggedIn()) {
			return false;
		}

		// 2. OBTENER USUARIO DE SESION
		Usuario usuario = UserSession.getUsuarioActual();

		if (usuario == null || usuario.getRol() == null) {
			return false;
		}

		// 3. VALIDAR PERMISO SEGUN ROL
		return RolPermisoConfig.permisosDe(usuario.getRol()).contains(permiso);
	}

	// =========================================================
	// VALIDACION: BLOQUEA SI NO TIENE PERMISO
	// =========================================================
	public static void validarPermiso(Permiso permiso) {

		validarSesionActiva();

		if (!tienePermiso(permiso)) {
			throw new BusinessException("ACCESO DENEGADO: FALTA PERMISO " + permiso);
		}
	}

	// =========================================================
	// LOGOUT: CIERRE OFICIAL DE SESION
	// =========================================================
	public static void logout() {

		if (!UserSession.isLoggedIn()) {
			return;
		}

		UserSession.reset();
	}
}
