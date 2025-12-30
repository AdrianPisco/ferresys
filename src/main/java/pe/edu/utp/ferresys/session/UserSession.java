package pe.edu.utp.ferresys.session;

import pe.edu.utp.ferresys.model.Usuario;

/*
================================================================================
 USER SESSION
 RESPONSABILIDAD:
    - MANTENER EN MEMORIA EL CONTEXTO DE SESION DEL USUARIO
    - SERVIR COMO FUENTE UNICA DE VERDAD PARA SEGURIDAD
    - NO CONTIENE UI
    - NO CONTIENE LOGICA DE NEGOCIO
    - NO VALIDA PERMISOS (ESO ES TAREA DEL SECURITY MANAGER)
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
	// RESETEA COMPLETAMENTE LA SESION
	// USO EXCLUSIVO DEL SECURITY MANAGER
	// =========================================================
	public static void reset() {
		usuarioActual = null;
	}

	// =========================================================
	// METODO LEGACY
	// SE MANTIENE PARA COMPATIBILIDAD
	// =========================================================
	@Deprecated
	public static void cerrarSesion() {
		reset();
	}

	// =========================================================
	// VERIFICA SI HAY SESION ACTIVA
	// =========================================================
	public static boolean isLoggedIn() {
		return usuarioActual != null;
	}
}
