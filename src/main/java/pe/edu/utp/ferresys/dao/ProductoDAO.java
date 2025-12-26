package pe.edu.utp.ferresys.dao;

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
		p.setPrecioCompraSoles((Double) rs.getObject("precio_compra_soles"));
		p.setPrecioCompraDolares((Double) rs.getObject("precio_compra_dolares"));
		p.setPrecioVentaSoles(rs.getDouble("precio_venta_soles"));
		p.setEstado(rs.getBoolean("estado"));

		return p;
	}
	
	public List<Producto> listarTodos() {

	    List<Producto> productos = new ArrayList<>();

	    String sql = "SELECT id_producto, codigo, descripcion, categoria, marca, " +
	                 "unidad_medida, stock_total, precio_compra_soles, " +
	                 "precio_compra_dolares, precio_venta_soles, estado " +
	                 "FROM productos";

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
}
