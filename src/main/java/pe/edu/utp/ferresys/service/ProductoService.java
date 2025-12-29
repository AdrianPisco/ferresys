package pe.edu.utp.ferresys.service;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import pe.edu.utp.ferresys.dao.AuditoriaDAO;
import pe.edu.utp.ferresys.dao.ProductoDAO;
import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Auditoria;
import pe.edu.utp.ferresys.model.Permiso;
import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.service.base.ServiceTransaccional;
import pe.edu.utp.ferresys.security.SecurityManager;

/*
================================================================================
 PRODUCTO SERVICE
 SERVICE TRANSACCIONAL – FERRETERÍA
================================================================================
*/
public class ProductoService extends ServiceTransaccional {

	private final ProductoDAO productoDAO = new ProductoDAO();
	private final AuditoriaDAO auditoriaDAO = new AuditoriaDAO();

	// =========================================================
	// CREAR PRODUCTO
	// =========================================================
	public void crearProducto(Producto p, int idUsuario) {

		SecurityManager.validar(Permiso.PRODUCTO_CREAR);
		validarNuevoProducto(p);

		Connection conn = abrirTransaccion();

		try {
			p.setEstado(true);

			productoDAO.create(p, conn);

			Auditoria aud = new Auditoria();
			aud.setAccion("CREAR_PRODUCTO");
			aud.setFecha(LocalDateTime.now());

			auditoriaDAO.registrar(aud, conn);

			commit(conn);

		} catch (BusinessException e) {
			rollback(conn);
			throw e;

		} catch (Exception e) {
			rollback(conn);
			throw new TechnicalException("Error al crear producto", e);

		} finally {
			cerrar(conn);
		}
	}

	// =========================================================
	// LISTAR PRODUCTOS
	// =========================================================
	public List<Producto> listarProductos() {
		SecurityManager.validar(Permiso.PRODUCTO_VER);
		return productoDAO.listarTodos();
	}

	// =========================================================
	// BUSCAR POR CODIGO
	// =========================================================
	public Producto buscarPorCodigo(String codigo) {
		SecurityManager.validar(Permiso.PRODUCTO_VER);
		return productoDAO.buscarPorCodigo(codigo);
	}

	// =========================================================
	// BUSCAR POR DESCRIPCION
	// =========================================================
	public List<Producto> buscarPorDescripcion(String texto) {
		SecurityManager.validar(Permiso.PRODUCTO_VER);
		return productoDAO.buscarPorDescripcion(texto);
	}

	// =========================================================
	// ACTUALIZAR STOCK
	// =========================================================
	public void actualizarStock(String codigo, int nuevoStock, int idUsuario) {

		SecurityManager.validar(Permiso.PRODUCTO_EDITAR);

		if (nuevoStock < 0) {
			throw new BusinessException("El stock no puede ser negativo");
		}

		Connection conn = abrirTransaccion();

		try {
			productoDAO.actualizarStock(codigo, nuevoStock, conn);

			commit(conn);

		} catch (BusinessException e) {
			rollback(conn);
			throw e;

		} catch (Exception e) {
			rollback(conn);
			throw new TechnicalException("Error actualizando stock", e);

		} finally {
			cerrar(conn);
		}
	}

	// =========================================================
	// ACTUALIZAR PRODUCTO
	// =========================================================
	public void actualizarProducto(Producto p, int idUsuario) {

		SecurityManager.validar(Permiso.PRODUCTO_EDITAR);

		validarActualizacionProducto(p);

		Connection conn = abrirTransaccion();

		try {
			productoDAO.update(p, conn);

			Auditoria aud = new Auditoria();
			aud.setAccion("ACTUALIZAR_PRODUCTO");
			aud.setUsuario("admin"); // USUARIO TECNICO TEMPORAL
			aud.setFecha(LocalDateTime.now());

			auditoriaDAO.registrar(aud, conn);

			commit(conn);

		} catch (Exception e) {
			rollback(conn);
			throw new TechnicalException("Error actualizando producto", e);

		} finally {
			cerrar(conn);
		}
	}

	// =========================================================
	// ELIMINAR PRODUCTO
	// =========================================================
	public void eliminarProducto(int idProducto, int idUsuario) {

		SecurityManager.validar(Permiso.PRODUCTO_ELIMINAR);

		Producto existente = productoDAO.findById(idProducto);

		if (existente == null) {
			throw new BusinessException("El producto no existe");
		}

		if (!existente.isEstado()) {
			throw new BusinessException("El producto ya está eliminado");
		}

		Connection conn = abrirTransaccion();

		try {
			productoDAO.deleteLogico(idProducto, conn);

			Auditoria aud = new Auditoria();
			aud.setAccion("ELIMINAR_PRODUCTO");
			aud.setUsuario("admin"); // USUARIO TECNICO
			aud.setFecha(LocalDateTime.now());

			auditoriaDAO.registrar(aud, conn);

			commit(conn);

		} catch (Exception e) {
			rollback(conn);
			throw new TechnicalException("Error eliminando producto", e);
		} finally {
			cerrar(conn);
		}
	}

	// =========================================================
	// METODO PRIVADOS
	// =========================================================

	// VALIDAR ACTUALIZACION DEL PRODUCTO
	private void validarActualizacionProducto(Producto p) {

		if (p.getIdProducto() <= 0) {
			throw new BusinessException("ID de producto inválido");
		}

		if (p.getCodigo() == null || p.getCodigo().trim().isEmpty()) {
			throw new BusinessException("El código del producto es obligatorio");
		}

		Producto existente = productoDAO.findByCodigo(p.getCodigo());

		if (existente != null && existente.getIdProducto() != p.getIdProducto()) {
			throw new BusinessException("Ya existe otro producto con ese código");
		}

		if (p.getPrecioVentaSoles() <= 0) {
			throw new BusinessException("El precio de venta debe ser mayor a cero");
		}

		if (p.getPrecioCompraSoles() == null && p.getPrecioCompraDolares() == null) {
			throw new BusinessException("Debe existir al menos un precio de compra");
		}
	}

	// VALIDAR NUEVO PRODUCTO
	private void validarNuevoProducto(Producto p) {
		if (p.getCodigo() == null || p.getCodigo().trim().isEmpty()) {
			throw new BusinessException("El código del producto es obligatorio");
		}

		if (productoDAO.findByCodigo(p.getCodigo()) != null) {
			throw new BusinessException("Ya existe un producto con ese código");
		}

		if (p.getPrecioVentaSoles() <= 0) {
			throw new BusinessException("El precio de venta debe ser mayor a cero");
		}

		if (p.getPrecioCompraSoles() == null && p.getPrecioCompraDolares() == null) {
			throw new BusinessException("Debe existir al menos un precio de compra");
		}
	}
}
