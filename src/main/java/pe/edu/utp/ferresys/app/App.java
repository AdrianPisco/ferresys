package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.service.UsuarioService;

public class App {

    public static void main(String[] args) {

        UsuarioService service = new UsuarioService();

        service.createUser("admin", "Admin123!", 1);

        System.out.println("Usuario creado correctamente");
    }
}