package pe.edu.utp.ferresys.security;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pe.edu.utp.ferresys.model.Permiso;
import pe.edu.utp.ferresys.model.Rol;

/*
================================================================================
 CONFIGURACION ROL -> PERMISOS
 RESPONSABILIDAD:
    - DEFINIR QUE PERMISOS TIENE CADA ROL
    - CENTRALIZAR LAS REGLAS DE SEGURIDAD
    - FACIL DE MODIFICAR Y AUDITAR
================================================================================
*/
public final class RolPermisoConfig {

	// =========================================================
	// MAPA CENTRAL DE PERMISOS POR ROL
	// =========================================================
	private static final Map<Rol, Set<Permiso>> PERMISOS_POR_ROL = new HashMap<Rol, Set<Permiso>>();

	// =========================================================
	// BLOQUE DE CONFIGURACION
	// =========================================================
	static {

		// =====================================================
		// ROL: ADMIN
		// TIENE ACCESO TOTAL AL SISTEMA
		// =====================================================
		PERMISOS_POR_ROL.put(Rol.ADMIN, EnumSet.allOf(Permiso.class));

		// =====================================================
		// ROL: VENDEDOR
		// SOLO OPERACIONES DE VENTA Y CONSULTA
		// =====================================================
		PERMISOS_POR_ROL.put(Rol.VENDEDOR,
				EnumSet.of(Permiso.PRODUCTO_VER, Permiso.VENTA_CREAR, Permiso.VENTA_VER, Permiso.REPORTE_VER));

		// =====================================================
		// ROL: ALMACENERO
		// GESTION DE PRODUCTOS Y STOCK
		// =====================================================
		PERMISOS_POR_ROL.put(Rol.ALMACENERO,
				EnumSet.of(Permiso.PRODUCTO_VER, Permiso.PRODUCTO_CREAR, Permiso.PRODUCTO_EDITAR));
	}

	// =========================================================
	// OBTIENE LOS PERMISOS DE UN ROL
	// =========================================================
	public static Set<Permiso> permisosDe(Rol rol) {

		Set<Permiso> permisos = PERMISOS_POR_ROL.get(rol);

		if (permisos == null) {
			return EnumSet.noneOf(Permiso.class);
		}

		return permisos;
	}

	// =========================================================
	// CONSTRUCTOR PRIVADO
	// =========================================================
	private RolPermisoConfig() {
		// EVITA INSTANCIACION
	}
}
