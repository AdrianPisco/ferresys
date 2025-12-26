package pe.edu.utp.ferresys.service;

import pe.edu.utp.ferresys.dao.ProductoDAO;
import pe.edu.utp.ferresys.dao.VentaDAO;
import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.exception.TechnicalException;
import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.model.Venta;
import pe.edu.utp.ferresys.model.VentaDetalle;
import pe.edu.utp.ferresys.service.base.ServiceTransaccional;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDateTime;

/*
================================================================================
 VENTA SERVICE
 RESPONSABILIDAD:
    - ORQUESTAR UNA VENTA COMPLETA
    - VALIDAR STOCK
    - REGISTRAR KARDEX AUTOMATICO
    - CONTROL TRANSACCIONAL TOTAL
================================================================================
*/
public class VentaService extends ServiceTransaccional {

	private final VentaDAO ventaDAO = new VentaDAO();
	private final ProductoDAO productoDAO = new ProductoDAO();
	private final KardexService kardexService = new KardexService();

	// =========================================================
	// REGISTRAR VENTA
	// =========================================================
	public void registrarVenta(Venta venta) {

		if (venta == null || venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
			throw new BusinessException("La venta no tiene detalles");
		}

		Connection conn = abrirTransaccion();

		try {
			// FECHA DE LA VENTA
			venta.setFecha(LocalDateTime.now());
			venta.setEstado(true);

			// =========================
			// VALIDAR DETALLES
			// =========================
			for (VentaDetalle d : venta.getDetalles()) {

				if (d.getCantidad() <= 0) {
					throw new BusinessException("Cantidad inválida");
				}

				Producto p = productoDAO.findById(d.getIdProducto());

				if (p == null || !p.isEstado()) {
					throw new BusinessException("Producto no existe o está inactivo");
				}

				if (p.getStockTotal() < d.getCantidad()) {
					throw new BusinessException("Stock insuficiente para producto " + p.getCodigo());
				}

				// PRECIO DE VENTA VIENE DEL PRODUCTO
				d.setPrecioUnitario(BigDecimal.valueOf(p.getPrecioVentaSoles()));

				// SUBTOTAL = PRECIO * CANTIDAD
				d.recalcularSubtotal();
			}

			// =========================
			// TOTAL DE LA VENTA
			// =========================
			venta.recalcularTotal();

			// =========================
			// INSERTAR CABECERA
			// =========================
			int idVenta = ventaDAO.insertarVenta(venta, conn);

			// =========================
			// INSERTAR DETALLES + KARDEX
			// =========================
			for (VentaDetalle d : venta.getDetalles()) {

				// DETALLE
				ventaDAO.insertarDetalle(d, idVenta, conn);

				// KARDEX (ESTE MÉTODO YA ACTUALIZA STOCK)
				kardexService.registrarMovimiento(d.getIdProducto(), "SALIDA", d.getCantidad(), "Venta #" + idVenta,
						"sistema", conn);
			}

			// =========================
			// COMMIT FINAL
			// =========================
			commit(conn);

		} catch (BusinessException e) {
			rollback(conn);
			throw e;

		} catch (Exception e) {
			rollback(conn);
			throw new TechnicalException("Error registrando venta", e);

		} finally {
			cerrar(conn);
		}
	}
}
