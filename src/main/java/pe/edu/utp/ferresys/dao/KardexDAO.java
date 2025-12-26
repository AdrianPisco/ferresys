package pe.edu.utp.ferresys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Kardex;

/*
================================================================================
 KARDEX DAO
 RESPONSABILIDAD:
    - REGISTRAR MOVIMIENTOS DE INVENTARIO
    - NO CALCULA STOCK
    - NO CONTIENE LOGICA DE NEGOCIO
================================================================================
*/
public class KardexDAO {

	private static final String SQL_INSERT = "INSERT INTO kardex (" + "id_producto, tipo_movimiento, cantidad, "
			+ "stock_anterior, stock_nuevo, motivo, usuario, fecha" + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	// =========================================================
	// REGISTRAR MOVIMIENTO (TRANSACCIONAL)
	// =========================================================
	public void registrarMovimiento(Kardex k, Connection conn) {

		try (PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

			stmt.setInt(1, k.getIdProducto());
			stmt.setString(2, k.getTipoMovimiento());
			stmt.setInt(3, k.getCantidad());
			stmt.setInt(4, k.getStockAnterior());
			stmt.setInt(5, k.getStockNuevo());
			stmt.setString(6, k.getMotivo());
			stmt.setString(7, k.getUsuario());
			stmt.setTimestamp(8, Timestamp.valueOf(k.getFecha()));

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new TechnicalException("Error registrando movimiento de kardex", e);
		}
	}
}
