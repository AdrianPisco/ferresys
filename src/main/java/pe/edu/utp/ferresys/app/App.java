package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.db.FlywayConfig;

public class App {

	public static void main(String[] args) {

		// Punto de entrada principal de la aplicación FerreSys
		//UsuarioService service = new UsuarioService();

		//service.createUser("admin", "Admin123!", 1);

	//	System.out.println("Usuario creado correctamente");

		
		FlywayConfig.migrate();

        System.out.println("Aplicación FerreSys iniciada");
		
		
	}
}