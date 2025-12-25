package pe.edu.utp.ferresys.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
================================================================================
 DATABASE CONNECTION
 RESPONSABILIDAD:
    - CARGAR CONFIGURACION DESDE application.properties
    - PROVEER CONEXIONES JDBC A SQL SERVER
    - NO CONTIENE LOGICA DE NEGOCIO
    - NO CONTIENE SQL
================================================================================
*/
public class DatabaseConnection {

	// =========================================================
	// ATRIBUTOS DE CONFIGURACION
	// =========================================================
	private static String url;
	private static String user;
	private static String password;

	// =========================================================
	// BLOQUE STATIC: SE EJECUTA UNA SOLA VEZ
	// =========================================================
	static {
		try {
			Properties props = new Properties();

			// CARGA application.properties DESDE resources
			InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("application.properties");

			if (input == null) {
				throw new RuntimeException("No se encontró application.properties en src/main/resources");
			}

			props.load(input);

			url = props.getProperty("db.url");
			user = props.getProperty("db.user");
			password = props.getProperty("db.password");

		} catch (Exception e) {
			throw new RuntimeException("Error cargando configuración de base de datos", e);
		}
	}

	// =========================================================
	// METODO PUBLICO PARA OBTENER CONEXION
	// =========================================================
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
