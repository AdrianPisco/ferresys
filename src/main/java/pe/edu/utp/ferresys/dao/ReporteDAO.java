package pe.edu.utp.ferresys.dao;

import java.sql.*;
import java.util.*;

import pe.edu.utp.ferresys.db.DatabaseConnection;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.ReporteStock;

public class ReporteDAO {

	public List<ReporteStock> obtenerStockActual() {

		List<ReporteStock> lista = new ArrayList<>();

		String sql = "SELECT codigo, descripcion, stock_total, precio_venta_soles, "
				+ "(stock_total * precio_venta_soles) AS valor_stock\n" + "FROM productos\n" + "WHERE estado = 1\n"
				+ "ORDER BY descripcion";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ReporteStock r = new ReporteStock();
				r.setCodigo(rs.getString("codigo"));
				r.setDescripcion(rs.getString("descripcion"));
				r.setStockTotal(rs.getInt("stock_total"));
				r.setPrecioVenta(rs.getDouble("precio_venta_soles"));
				r.setValorStock(rs.getDouble("valor_stock"));
				lista.add(r);
			}

		} catch (SQLException e) {
			throw new TechnicalException("Error generando reporte de stock", e);
		}

		return lista;
	}
}
