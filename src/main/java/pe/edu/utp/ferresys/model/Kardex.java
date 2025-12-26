package pe.edu.utp.ferresys.model;

import java.time.LocalDateTime;

/*
================================================================================
 MODELO KARDEX
 RESPONSABILIDAD:
    - REPRESENTAR UN MOVIMIENTO DE STOCK DE UN PRODUCTO
    - REGISTRAR HISTORIAL DE INVENTARIO
================================================================================
*/
public class Kardex {

	// IDENTIDAD
	private int idKardex;
	private int idProducto;

	// MOVIMIENTO
	private String tipoMovimiento; // ENTRADA | SALIDA | AJUSTE
	private int cantidad;

	// CONTROL DE STOCK
	private int stockAnterior;
	private int stockNuevo;

	// CONTEXTO
	private String motivo;
	private String usuario;
	private LocalDateTime fecha;

	// ============================
	// GETTERS Y SETTERS
	// ============================

	public int getIdKardex() {
		return idKardex;
	}

	public void setIdKardex(int idKardex) {
		this.idKardex = idKardex;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getStockAnterior() {
		return stockAnterior;
	}

	public void setStockAnterior(int stockAnterior) {
		this.stockAnterior = stockAnterior;
	}

	public int getStockNuevo() {
		return stockNuevo;
	}

	public void setStockNuevo(int stockNuevo) {
		this.stockNuevo = stockNuevo;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
}
