package pe.edu.utp.ferresys.security;

import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.model.Permiso;
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
	public static boolean tienePermiso(Permiso permiso) {

		validarSesionActiva();

		Usuario usuario = UserSession.getUsuarioActual();

		if (usuario.getRol() == null) {
			return false;
		}

		return RolPermisoConfig.permisosDe(usuario.getRol()).contains(permiso);
	}

	// =========================================================
	// VALIDACION: BLOQUEA SI NO TIENE PERMISO
	// =========================================================
	public static void validar(Permiso permiso) {

		validarSesionActiva();

		if (!tienePermiso(permiso)) {
			throw new BusinessException("NO TIENE PERMISOS PARA REALIZAR ESTA OPERACION");
		}
	}
}
