package pe.edu.utp.ferresys.dao;

import java.sql.*;
import java.util.*;

import pe.edu.utp.ferresys.db.DatabaseConnection;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.ReporteProductoVendido;
import pe.edu.utp.ferresys.model.ReporteStock;
import pe.edu.utp.ferresys.model.ReporteVentaDiaria;

public class ReporteDAO {

	// =====================================================
	// REPORTE DE STOCK ACTUAL
	// =====================================================
	public List<ReporteStock> obtenerStockActual() {

		List<ReporteStock> lista = new ArrayList<>();

		String sql = "SELECT codigo, descripcion, stock_total, precio_venta_soles, "
				+ "       (stock_total * precio_venta_soles) AS valor_stock " + "FROM productos " + "WHERE estado = 1 "
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

	// =====================================================
	// REPORTE: PRODUCTOS MÁS VENDIDOS
	// =====================================================
	public List<ReporteProductoVendido> obtenerProductosMasVendidos() {

		List<ReporteProductoVendido> lista = new ArrayList<>();

		String sql = "SELECT p.codigo, p.descripcion, " + "       SUM(d.cantidad) AS cantidad_vendida, "
				+ "       SUM(d.subtotal) AS total_vendido " + "FROM venta_detalle d "
				+ "JOIN productos p ON p.id_producto = d.id_producto " + "JOIN ventas v ON v.id_venta = d.id_venta "
				+ "WHERE v.estado = 1 " + "GROUP BY p.codigo, p.descripcion " + "ORDER BY cantidad_vendida DESC";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ReporteProductoVendido r = new ReporteProductoVendido();
				r.setCodigo(rs.getString("codigo"));
				r.setDescripcion(rs.getString("descripcion"));
				r.setCantidadVendida(rs.getInt("cantidad_vendida"));
				r.setTotalVendido(rs.getBigDecimal("total_vendido"));
				lista.add(r);
			}

		} catch (SQLException e) {
			throw new TechnicalException("Error generando reporte de productos más vendidos", e);
		}

		return lista;
	}

	public List<ReporteVentaDiaria> obtenerVentasDiarias() {

		List<ReporteVentaDiaria> lista = new ArrayList<>();

		String sql = "SELECT " + "  CAST(fecha AS DATE) AS fecha, " + "  SUM(total) AS total_ventas, "
				+ "  COUNT(*) AS cantidad_ventas " + "FROM ventas " + "WHERE estado = 1 "
				+ "GROUP BY CAST(fecha AS DATE) " + "ORDER BY fecha DESC";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				ReporteVentaDiaria r = new ReporteVentaDiaria();
				r.setFecha(rs.getDate("fecha").toLocalDate());
				r.setTotalVentas(rs.getDouble("total_ventas"));
				r.setCantidadVentas(rs.getInt("cantidad_ventas"));
				lista.add(r);
			}

		} catch (SQLException e) {
			throw new TechnicalException("Error generando reporte de ventas diarias", e);
		}

		return lista;
	}

}
