package pe.edu.utp.ferresys.model;

import java.math.BigDecimal;

/*
================================================================================
 REPORTE PRODUCTO VENDIDO
 RESPONSABILIDAD:
    - REPRESENTAR PRODUCTOS MAS VENDIDOS (LECTURA)
================================================================================
*/
public class ReporteProductoVendido {

	private String codigo;
	private String descripcion;
	private int cantidadVendida;
	private BigDecimal totalVendido;

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

	public int getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public BigDecimal getTotalVendido() {
		return totalVendido;
	}

	public void setTotalVendido(BigDecimal totalVendido) {
		this.totalVendido = totalVendido;
	}
}
