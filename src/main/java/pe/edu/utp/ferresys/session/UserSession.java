package pe.edu.utp.ferresys.session;

import pe.edu.utp.ferresys.model.Usuario;

/*
================================================================================
 USER SESSION
 RESPONSABILIDAD:
    - MANTENER EN MEMORIA AL USUARIO ACTUAL DEL SISTEMA
    - SERVIR COMO CONTEXTO PARA SEGURIDAD (ROLES Y PERMISOS)
    - NO CONTIENE UI
    - NO CONTIENE LOGICA DE NEGOCIO
================================================================================
*/
public final class UserSession {

	// =========================================================
	// USUARIO ACTUAL DEL SISTEMA
	// =========================================================
	private static Usuario usuarioActual;

	// =========================================================
	// CONSTRUCTOR PRIVADO
	// =========================================================
	private UserSession() {
		// EVITA INSTANCIACION
	}

	// =========================================================
	// SETEA EL USUARIO LOGUEADO
	// =========================================================
	public static void setUsuarioActual(Usuario usuario) {
		usuarioActual = usuario;
	}

	// =========================================================
	// OBTIENE EL USUARIO LOGUEADO
	// =========================================================
	public static Usuario getUsuarioActual() {
		return usuarioActual;
	}

	// =========================================================
	// CIERRA LA SESION ACTUAL
	// =========================================================
	public static void cerrarSesion() {
		usuarioActual = null;
	}

	// =========================================================
	// VERIFICA SI HAY SESION ACTIVA
	// =========================================================
	public static boolean haySesionActiva() {
		return usuarioActual != null;
	}
}
