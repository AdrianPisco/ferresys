package pe.edu.utp.ferresys.app;

import javax.swing.SwingUtilities;

import pe.edu.utp.ferresys.db.FlywayConfig;
import pe.edu.utp.ferresys.ui.login.LoginFrame;

public class App {

	public static void main(String[] args) {

		// 1. MIGRACION DE BASE DE DATOS
		FlywayConfig.migrate();

		System.out.println("Base de datos verificada/migrada");

		// 2. LANZAR INTERFAZ GRAFICA (LOGIN)
		SwingUtilities.invokeLater(() -> {
			new LoginFrame().setVisible(true);
		});
	}
}
