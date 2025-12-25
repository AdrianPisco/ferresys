package pe.edu.utp.ferresys.model;

/*
================================================================================
 PRODUCTO – FERRETERÍA PERUANA
 BASADO EN INVENTARIO REAL
================================================================================
*/
public class Producto {

	// IDENTIDAD INTERNA
	private int idProducto;

	// IDENTIDAD DE NEGOCIO
	private String codigo; // EJ: ABRAMANG02

	// DESCRIPCION
	private String descripcion;

	// CLASIFICACION
	private String categoria; // FAM.
	private String marca;
	private String unidadMedida; // UND

	// STOCK
	private int stockTotal;

	// PRECIOS DE COMPRA
	private Double precioCompraSoles;
	private Double precioCompraDolares;

	// PRECIO DE VENTA (SOLO SOLES)
	private double precioVentaSoles;

	// CONTROL
	private boolean estado;

	// ============================
	// GETTERS Y SETTERS
	// ============================

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public int getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
	}

	public Double getPrecioCompraSoles() {
		return precioCompraSoles;
	}

	public void setPrecioCompraSoles(Double precioCompraSoles) {
		this.precioCompraSoles = precioCompraSoles;
	}

	public Double getPrecioCompraDolares() {
		return precioCompraDolares;
	}

	public void setPrecioCompraDolares(Double precioCompraDolares) {
		this.precioCompraDolares = precioCompraDolares;
	}

	public double getPrecioVentaSoles() {
		return precioVentaSoles;
	}

	public void setPrecioVentaSoles(double precioVentaSoles) {
		this.precioVentaSoles = precioVentaSoles;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
