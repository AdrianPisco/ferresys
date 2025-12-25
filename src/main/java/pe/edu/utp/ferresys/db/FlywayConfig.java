package pe.edu.utp.ferresys.db;

import org.flywaydb.core.Flyway;

/*
================================================================================
 FLYWAY CONFIG
 RESPONSABILIDAD:
    - EJECUTAR MIGRACIONES DE BD AL ARRANQUE
================================================================================
*/
public class FlywayConfig {

	public static void migrate() {

		Flyway flyway = Flyway.configure()
				.dataSource("jdbc:sqlserver://localhost:1433;databaseName=FerreSysDB;encrypt=false", "ferresys_user",
						"FerreSys123!")
				.locations("classpath:db/migration").load();

		flyway.migrate();
	}
}
