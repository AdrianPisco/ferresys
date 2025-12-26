package pe.edu.utp.ferresys.service;

import java.sql.Connection;
import java.time.LocalDateTime;

import pe.edu.utp.ferresys.dao.KardexDAO;
import pe.edu.utp.ferresys.dao.ProductoDAO;
import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Kardex;
import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.service.base.ServiceTransaccional;

/*
================================================================================
 KARDEX SERVICE
 RESPONSABILIDAD:
    - CONTROLAR MOVIMIENTOS DE STOCK
    - ACTUALIZAR PRODUCTO
    - REGISTRAR KARDEX
    - MANEJAR TRANSACCIONES
================================================================================
*/
public class KardexService extends ServiceTransaccional {

	private final ProductoDAO productoDAO = new ProductoDAO();
	private final KardexDAO kardexDAO = new KardexDAO();

	// =========================================================
	// REGISTRAR MOVIMIENTO DE STOCK
	// =========================================================
	public void registrarMovimiento(int idProducto, String tipoMovimiento, // ENTRADA | SALIDA
			int cantidad, String motivo, String usuario) {

		if (cantidad <= 0) {
			throw new BusinessException("La cantidad debe ser mayor a cero");
		}

		Connection conn = abrirTransaccion();

		try {
			Producto producto = productoDAO.findById(idProducto);

			if (producto == null) {
				throw new BusinessException("Producto no existe");
			}

			int stockAnterior = producto.getStockTotal();
			int stockNuevo;

			if ("ENTRADA".equalsIgnoreCase(tipoMovimiento)) {
				stockNuevo = stockAnterior + cantidad;
			} else if ("SALIDA".equalsIgnoreCase(tipoMovimiento)) {
				stockNuevo = stockAnterior - cantidad;

				if (stockNuevo < 0) {
					throw new BusinessException("Stock insuficiente");
				}
			} else {
				throw new BusinessException("Tipo de movimiento inválido");
			}

			// 1. ACTUALIZAR STOCK DEL PRODUCTO
			productoDAO.actualizarStock(producto.getCodigo(), stockNuevo, conn);

			// 2. REGISTRAR KARDEX
			Kardex k = new Kardex();
			k.setIdProducto(idProducto);
			k.setTipoMovimiento(tipoMovimiento.toUpperCase());
			k.setCantidad(cantidad);
			k.setStockAnterior(stockAnterior);
			k.setStockNuevo(stockNuevo);
			k.setMotivo(motivo);
			k.setUsuario(usuario);
			k.setFecha(LocalDateTime.now());

			kardexDAO.registrarMovimiento(k, conn);

			commit(conn);

		} catch (BusinessException e) {
			rollback(conn);
			throw e;

		} catch (Exception e) {
			rollback(conn);
			throw new TechnicalException("Error registrando movimiento de stock", e);

		} finally {
			cerrar(conn);
		}
	}
}
