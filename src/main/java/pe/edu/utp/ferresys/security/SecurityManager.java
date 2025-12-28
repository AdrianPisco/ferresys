package pe.edu.utp.ferresys.security;

import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.model.Permiso;
import pe.edu.utp.ferresys.model.Usuario;
import pe.edu.utp.ferresys.session.UserSession;

/*
================================================================================
 SECURITY MANAGER
 RESPONSABILIDAD:
    - VALIDAR PERMISOS DEL USUARIO EN SESION
    - CENTRALIZAR EL CONTROL DE ACCESO
    - EVITAR LOGICA DE SEGURIDAD DISTRIBUIDA
================================================================================
*/
public final class SecurityManager {

    // =========================================================
    // CONSTRUCTOR PRIVADO
    // =========================================================
    private SecurityManager() {
        // EVITA INSTANCIACION
    }

    // =========================================================
    // CONSULTA: ¿TIENE PERMISO?
    // =========================================================
    public static boolean tienePermiso(Permiso permiso) {

        Usuario usuario = UserSession.getUsuarioActual();

        if (usuario == null) {
            return false;
        }

        if (usuario.getRol() == null) {
            return false;
        }

        return RolPermisoConfig
                .permisosDe(usuario.getRol())
                .contains(permiso);
    }

    // =========================================================
    // VALIDACION: BLOQUEA SI NO TIENE PERMISO
    // =========================================================
    public static void validar(Permiso permiso) {

        if (!tienePermiso(permiso)) {
            throw new BusinessException(
                "NO TIENE PERMISOS PARA REALIZAR ESTA OPERACION"
            );
        }
    }
}
