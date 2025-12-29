package pe.edu.utp.ferresys.dao;

import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Venta;
import pe.edu.utp.ferresys.model.VentaDetalle;

import java.sql.*;
import java.time.LocalDateTime;

/*
================================================================================
 VENTA DAO
 RESPONSABILIDAD:
    - PERSISTIR CABECERA Y DETALLE DE VENTAS
    - NO CONTIENE LOGICA DE NEGOCIO
    - USA CONNECTION EXTERNA (TRANSACCIONAL)
================================================================================
*/
public class VentaDAO {

	private static final String SQL_INSERT_VENTA = "INSERT INTO ventas (fecha, id_usuario, total, estado) VALUES (?, ?, ?, ?)";

	private static final String SQL_INSERT_DETALLE = "INSERT INTO venta_detalle (id_venta, id_producto, cantidad, precio_unitario, subtotal) "
			+ "VALUES (?, ?, ?, ?, ?)";

	// =========================================================
	// INSERTAR CABECERA DE VENTA (DEVUELVE ID)
	// =========================================================
	public int insertarVenta(Venta venta, Connection conn) {

		try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT_VENTA, Statement.RETURN_GENERATED_KEYS)) {

			ps.setTimestamp(1, Timestamp.valueOf(venta.getFecha() == null ? LocalDateTime.now() : venta.getFecha()));
			ps.setInt(2, venta.getIdUsuario());
			ps.setBigDecimal(3, venta.getTotal());
			ps.setBoolean(4, venta.isEstado());

			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}

			throw new TechnicalException("No se pudo obtener el ID de la venta");

		} catch (SQLException e) {
			throw new TechnicalException("Error insertando venta", e);
		}
	}

	// =========================================================
	// INSERTAR DETALLE DE VENTA
	// =========================================================
	public void insertarDetalle(VentaDetalle d, int idVenta, Connection conn) {

		try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT_DETALLE)) {

			ps.setInt(1, idVenta);
			ps.setInt(2, d.getIdProducto());
			ps.setInt(3, d.getCantidad());
			ps.setBigDecimal(4, d.getPrecioUnitario());
			ps.setBigDecimal(5, d.getSubtotal());

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new TechnicalException("Error insertando detalle de venta", e);
		}
	}
}
