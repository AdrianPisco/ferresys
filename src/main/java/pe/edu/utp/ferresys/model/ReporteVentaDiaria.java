package pe.edu.utp.ferresys.model;

import java.time.LocalDate;

/*
================================================================================
 REPORTE VENTA DIARIA
 RESPONSABILIDAD:
    - REPRESENTAR EL RESUMEN DE VENTAS POR DIA
================================================================================
*/
public class ReporteVentaDiaria {

	private LocalDate fecha;
	private double totalVentas;
	private int cantidadVentas;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(double totalVentas) {
		this.totalVentas = totalVentas;
	}

	public int getCantidadVentas() {
		return cantidadVentas;
	}

	public void setCantidadVentas(int cantidadVentas) {
		this.cantidadVentas = cantidadVentas;
	}
}
