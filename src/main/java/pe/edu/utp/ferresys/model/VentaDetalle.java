package pe.edu.utp.ferresys.model;

import java.math.BigDecimal;

/*
================================================================================
 MODELO VENTA DETALLE
 RESPONSABILIDAD:
    - REPRESENTAR UN ITEM DE LA VENTA
    - GUARDAR PRODUCTO, CANTIDAD, PRECIO UNITARIO Y SUBTOTAL
================================================================================
*/
public class VentaDetalle {

	// IDENTIDAD
	private int idDetalle;

	// RELACION
	private int idVenta;

	// PRODUCTO
	private int idProducto;
	private String codigoProducto; // OPCIONAL PERO UTIL PARA DEBUG / REPORTES

	// CANTIDAD
	private int cantidad;

	// PRECIOS
	private BigDecimal precioUnitario;
	private BigDecimal subtotal;

	// =========================================================
	// GETTERS / SETTERS
	// =========================================================
	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	// =========================================================
	// HELPERS DE NEGOCIO (SIMPLES)
	// =========================================================
	public void recalcularSubtotal() {

		if (precioUnitario == null) {
			subtotal = BigDecimal.ZERO;
			return;
		}

		if (cantidad <= 0) {
			subtotal = BigDecimal.ZERO;
			return;
		}

		subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
	}
}
