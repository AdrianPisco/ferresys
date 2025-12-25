package pe.edu.utp.ferresys.util;

import org.mindrot.jbcrypt.BCrypt;

/*
================================================================================
 PASSWORD UTILS
 RESPONSABILIDAD:
    - GENERAR HASH SEGURO DE CONTRASEÑAS
    - VERIFICAR CONTRASEÑAS CONTRA SU HASH
    - NO CONTIENE LOGICA DE NEGOCIO
================================================================================
*/
public final class PasswordUtils {

    // =========================================================
    // CONSTRUCTOR PRIVADO (CLASE UTILITARIA)
    // =========================================================
    private PasswordUtils() {
        // Evita instanciación
    }

    // =========================================================
    // GENERAR HASH
    // =========================================================
    public static String hashPassword(String passwordPlano) {
        return BCrypt.hashpw(passwordPlano, BCrypt.gensalt(12));
    }

    // =========================================================
    // VERIFICAR PASSWORD
    // =========================================================
    public static boolean checkPassword(String passwordPlano, String hashAlmacenado) {
        return BCrypt.checkpw(passwordPlano, hashAlmacenado);
    }
}
