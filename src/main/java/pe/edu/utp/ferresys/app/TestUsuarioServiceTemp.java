package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Usuario;
import pe.edu.utp.ferresys.service.UsuarioService;
import pe.edu.utp.ferresys.session.UserSession;

public class TestUsuarioServiceTemp {

    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();

        // LOGIN COMO ADMIN
        Usuario admin = usuarioService.login("admin_test", "ADMIN123");
        UserSession.setUsuarioActual(admin);

        // PROBAR DESACTIVAR
        usuarioService.desactivarUsuario("juan");

        // PROBAR ELIMINAR
        usuarioService.eliminarUsuario("juan");

        System.out.println("Pruebas ejecutadas correctamente");
    }
}