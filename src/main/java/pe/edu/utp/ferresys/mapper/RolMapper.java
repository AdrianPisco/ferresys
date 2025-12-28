package pe.edu.utp.ferresys.mapper;

import pe.edu.utp.ferresys.model.Rol;

/*
=========================================================
 ROL MAPPER
 RESPONSABILIDAD:
    - CONVERTIR ENTRE id_role (BD) Y Rol (DOMINIO)
    - AISLAR EL MODELO DE LA BASE DE DATOS
=========================================================
*/
public final class RolMapper {

    // =========================================================
    // CONSTRUCTOR PRIVADO
    // =========================================================
    private RolMapper() {
        // EVITA INSTANCIACION
    }

    // =========================================================
    // BD -> DOMINIO
    // =========================================================
    public static Rol fromId(int idRole) {

        if (idRole == 1) {
            return Rol.ADMIN;
        }

        if (idRole == 2) {
            return Rol.VENDEDOR;
        }

        if (idRole == 3) {
            return Rol.ALMACENERO;
        }

        throw new IllegalArgumentException(
            "ID DE ROL DESCONOCIDO: " + idRole
        );
    }

    // =========================================================
    // DOMINIO -> BD
    // =========================================================
    public static int toId(Rol rol) {

        if (rol == Rol.ADMIN) {
            return 1;
        }

        if (rol == Rol.VENDEDOR) {
            return 2;
        }

        if (rol == Rol.ALMACENERO) {
            return 3;
        }

        throw new IllegalArgumentException(
            "ROL NO SOPORTADO: " + rol
        );
    }
}
