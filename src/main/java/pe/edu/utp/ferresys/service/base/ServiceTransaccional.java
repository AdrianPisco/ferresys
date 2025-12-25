package pe.edu.utp.ferresys.service.base;

import java.sql.Connection;
import java.sql.SQLException;

import pe.edu.utp.ferresys.db.DatabaseConnection;
import pe.edu.utp.ferresys.exception.TechnicalException;

/*
================================================================================
 SERVICE TRANSACCIONAL BASE
 RESPONSABILIDAD:
    - PROVEER CONEXION
    - CONTROLAR COMMIT / ROLLBACK
    - EVITAR DUPLICAR LOGICA EN SERVICES
================================================================================
*/
public abstract class ServiceTransaccional {

	protected Connection abrirTransaccion() {
		try {
			Connection cn = DatabaseConnection.getConnection();
			cn.setAutoCommit(false);
			return cn;
		} catch (SQLException e) {
			throw new TechnicalException("ERROR AL ABRIR TRANSACCION", e);
		}
	}

	protected void commit(Connection cn) {
		try {
			cn.commit();
		} catch (SQLException e) {
			throw new TechnicalException("ERROR AL CONFIRMAR TRANSACCION", e);
		}
	}

	protected void rollback(Connection cn) {
		try {
			if (cn != null)
				cn.rollback();
		} catch (SQLException e) {
			throw new TechnicalException("ERROR AL HACER ROLLBACK", e);
		}
	}

	protected void cerrar(Connection cn) {
		try {
			if (cn != null)
				cn.close();
		} catch (SQLException e) {
			throw new TechnicalException("ERROR AL CERRAR CONEXION", e);
		}
	}
}
