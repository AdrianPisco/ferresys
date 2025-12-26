package pe.edu.utp.ferresys.model;

public class ReporteStock {

	private String codigo;
	private String descripcion;
	private int stockTotal;
	private double precioVenta;
	private double valorStock;

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

	public int getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(int stockTotal) {
		this.stockTotal = stockTotal;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public double getValorStock() {
		return valorStock;
	}

	public void setValorStock(double valorStock) {
		this.valorStock = valorStock;
	}

}