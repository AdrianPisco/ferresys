package pe.edu.utp.ferresys.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.edu.utp.ferresys.db.DatabaseConnection;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Producto;

/*
================================================================================
 PRODUCTO DAO
 DAO PASIVO
================================================================================
*/
public class ProductoDAO {

	private static final String SQL_INSERT = "INSERT INTO productos "
			+ "(codigo, descripcion, categoria, marca, unidad_medida, stock_total, "
			+ " precio_compra_soles, precio_compra_dolares, precio_venta_soles, estado) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_FIND_BY_CODIGO = "SELECT id_producto, codigo, descripcion, categoria, marca, unidad_medida, "
			+ "       stock_total, precio_compra_soles, precio_compra_dolares, " + "       precio_venta_soles, estado "
			+ "FROM productos WHERE codigo = ?";

	// =========================================================
	// CREAR PRODUCTO (TRANSACCIONAL)
	// =========================================================
	public void create(Producto p, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT, new String[] { "id_producto" })) {

			ps.setString(1, p.getCodigo());
			ps.setString(2, p.getDescripcion());
			ps.setString(3, p.getCategoria());
			ps.setString(4, p.getMarca());
			ps.setString(5, p.getUnidadMedida());
			ps.setInt(6, p.getStockTotal());

			ps.setObject(7, p.getPrecioCompraSoles());
			ps.setObject(8, p.getPrecioCompraDolares());

			ps.setDouble(9, p.getPrecioVentaSoles());
			ps.setBoolean(10, p.isEstado());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				p.setIdProducto(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new TechnicalException("Error al crear producto", e);
		}
	}

	// =========================================================
	// BUSCAR POR CODIGO (LECTURA)
	// =========================================================
	public Producto findByCodigo(String codigo) {
		try (Connection conn = pe.edu.utp.ferresys.db.DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_CODIGO)) {

			ps.setString(1, codigo);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return mapear(rs);
			}

			return null;
		} catch (SQLException e) {
			throw new TechnicalException("Error al buscar producto por código", e);
		}
	}

	// =========================================================
	// MAPEO
	// =========================================================
	private Producto mapear(ResultSet rs) throws SQLException {
		Producto p = new Producto();
		p.setIdProducto(rs.getInt("id_producto"));
		p.setCodigo(rs.getString("codigo"));
		p.setDescripcion(rs.getString("descripcion"));
		p.setCategoria(rs.getString("categoria"));
		p.setMarca(rs.getString("marca"));
		p.setUnidadMedida(rs.getString("unidad_medida"));
		p.setStockTotal(rs.getInt("stock_total"));
		BigDecimal compraSoles = rs.getBigDecimal("precio_compra_soles");
		BigDecimal compraDolares = rs.getBigDecimal("precio_compra_dolares");

		p.setPrecioCompraSoles(compraSoles != null ? compraSoles.doubleValue() : null);
		p.setPrecioCompraDolares(compraDolares != null ? compraDolares.doubleValue() : null);
		p.setPrecioVentaSoles(rs.getDouble("precio_venta_soles"));
		p.setEstado(rs.getBoolean("estado"));

		return p;
	}

	public List<Producto> listarTodos() {

		List<Producto> productos = new ArrayList<>();

		String sql = "SELECT id_producto, codigo, descripcion, categoria, marca, "
				+ "unidad_medida, stock_total, precio_compra_soles, "
				+ "precio_compra_dolares, precio_venta_soles, estado " + "FROM productos";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Producto p = new Producto();

				p.setIdProducto(rs.getInt("id_producto"));
				p.setCodigo(rs.getString("codigo"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setCategoria(rs.getString("categoria"));
				p.setMarca(rs.getString("marca"));
				p.setUnidadMedida(rs.getString("unidad_medida"));
				p.setStockTotal(rs.getInt("stock_total"));
				p.setPrecioCompraSoles(rs.getDouble("precio_compra_soles"));
				p.setPrecioCompraDolares(rs.getDouble("precio_compra_dolares"));
				p.setPrecioVentaSoles(rs.getDouble("precio_venta_soles"));
				p.setEstado(rs.getBoolean("estado"));

				productos.add(p);
			}

		} catch (SQLException e) {
			throw new TechnicalException("Error listando productos", e);
		}

		return productos;
	}

	public Producto buscarPorCodigo(String codigo) {

		String sql = "SELECT id_producto, codigo, descripcion, categoria, marca, "
				+ "unidad_medida, stock_total, precio_compra_soles, "
				+ "precio_compra_dolares, precio_venta_soles, estado " + "FROM productos WHERE codigo = ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, codigo);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					Producto p = new Producto();
					p.setIdProducto(rs.getInt("id_producto"));
					p.setCodigo(rs.getString("codigo"));
					p.setDescripcion(rs.getString("descripcion"));
					p.setCategoria(rs.getString("categoria"));
					p.setMarca(rs.getString("marca"));
					p.setUnidadMedida(rs.getString("unidad_medida"));
					p.setStockTotal(rs.getInt("stock_total"));
					p.setPrecioCompraSoles(rs.getDouble("precio_compra_soles"));
					p.setPrecioCompraDolares(rs.getDouble("precio_compra_dolares"));
					p.setPrecioVentaSoles(rs.getDouble("precio_venta_soles"));
					p.setEstado(rs.getBoolean("estado"));
					return p;
				}
			}

			return null;

		} catch (SQLException e) {
			throw new TechnicalException("Error buscando producto por código", e);
		}
	}

	public List<Producto> buscarPorDescripcion(String texto) {

		List<Producto> productos = new ArrayList<>();

		String sql = "SELECT id_producto, codigo, descripcion, categoria, marca, "
				+ "unidad_medida, stock_total, precio_compra_soles, "
				+ "precio_compra_dolares, precio_venta_soles, estado " + "FROM productos WHERE descripcion LIKE ?";

		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, "%" + texto + "%");

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Producto p = new Producto();

					p.setIdProducto(rs.getInt("id_producto"));
					p.setCodigo(rs.getString("codigo"));
					p.setDescripcion(rs.getString("descripcion"));
					p.setCategoria(rs.getString("categoria"));
					p.setMarca(rs.getString("marca"));
					p.setUnidadMedida(rs.getString("unidad_medida"));
					p.setStockTotal(rs.getInt("stock_total"));
					p.setPrecioCompraSoles(rs.getDouble("precio_compra_soles"));
					p.setPrecioCompraDolares(rs.getDouble("precio_compra_dolares"));
					p.setPrecioVentaSoles(rs.getDouble("precio_venta_soles"));
					p.setEstado(rs.getBoolean("estado"));

					productos.add(p);
				}
			}

		} catch (SQLException e) {
			throw new TechnicalException("Error buscando productos por descripción", e);
		}

		return productos;
	}

	public void actualizarStock(String codigo, int stockFinal, Connection conn) {

		String sql = "UPDATE productos SET stock_total = ? WHERE codigo = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, stockFinal);
			ps.setString(2, codigo);

			int filas = ps.executeUpdate();

			if (filas == 0) {
				throw new TechnicalException("No existe producto con código: " + codigo);
			}

		} catch (SQLException e) {
			throw new TechnicalException("Error actualizando stock", e);
		}
	}

	// BUSCAR POR ID
	public Producto findById(int idProducto) {

		String sql = "SELECT * FROM productos WHERE id_producto = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idProducto);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return mapear(rs);
			}

			return null;

		} catch (SQLException e) {
			throw new TechnicalException("Error buscando producto por ID", e);
		}
	}

	// UPDATE
	public void update(Producto p, Connection conn) {

		String sql = "UPDATE productos SET " + "    codigo = ?, " + "    descripcion = ?, " + "    categoria = ?, "
				+ "    marca = ?, " + "    unidad_medida = ?, " + "    stock_total = ?, "
				+ "    precio_compra_soles = ?, " + "    precio_compra_dolares = ?, " + "    precio_venta_soles = ? "
				+ "WHERE id_producto = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, p.getCodigo());
			stmt.setString(2, p.getDescripcion());
			stmt.setString(3, p.getCategoria());
			stmt.setString(4, p.getMarca());
			stmt.setString(5, p.getUnidadMedida());
			stmt.setInt(6, p.getStockTotal());
			stmt.setObject(7, p.getPrecioCompraSoles());
			stmt.setObject(8, p.getPrecioCompraDolares());
			stmt.setDouble(9, p.getPrecioVentaSoles());
			stmt.setInt(10, p.getIdProducto());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new TechnicalException("Error actualizando producto", e);
		}
	}

	// DELETE LOGICO
	public void deleteLogico(int idProducto, Connection conn) {

		String sql = "UPDATE productos SET estado = 0 WHERE id_producto = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, idProducto);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new TechnicalException("Error eliminando producto (lógico)", e);
		}
	}
}
