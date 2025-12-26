package pe.edu.utp.ferresys.service;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import pe.edu.utp.ferresys.dao.AuditoriaDAO;
import pe.edu.utp.ferresys.dao.ProductoDAO;
import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Auditoria;
import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.service.base.ServiceTransaccional;

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

		validarNuevoProducto(p);

		Connection conn = abrirTransaccion();

		try {
			p.setEstado(true);

			productoDAO.create(p, conn);

			Auditoria aud = new Auditoria();
			aud.setIdUsuario(idUsuario);
			aud.setAccion("CREAR_PRODUCTO");
			aud.setDetalle("Producto creado: " + p.getCodigo());
			aud.setFecha(LocalDateTime.now());

			//auditoriaDAO.registrar(aud, conn);

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
	// VALIDACIONES DE NEGOCIO
	// =========================================================
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
	
	public List<Producto> listarProductos() {
	    return productoDAO.listarTodos();
	}
}
