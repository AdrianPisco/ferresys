package pe.edu.utp.ferresys.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
================================================================================
 MODELO VENTA
 RESPONSABILIDAD:
    - REPRESENTAR LA CABECERA DE UNA VENTA
    - CONTENER DATOS GENERALES (FECHA, USUARIO, TOTAL)
    - CONTENER DETALLES (LISTA DE ITEMS)
 NOTA:
    - USAMOS BIGDECIMAL PARA IMPORTES (EVITA ERRORES CON DECIMAL/NUMERIC EN SQL SERVER)
================================================================================
*/
public class Venta {

	// IDENTIDAD
	private int idVenta;

	// CONTROL
	private LocalDateTime fecha;

	// QUIEN REGISTRA LA VENTA (USUARIO DEL SISTEMA)
	private int idUsuario;

	// IMPORTE TOTAL DE LA VENTA
	private BigDecimal total;

	// ESTADO LOGICO (POR SI LUEGO ANULAMOS)
	private boolean estado;

	// DETALLE (ITEMS DE LA VENTA)
	private List<VentaDetalle> detalles;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================
	public Venta() {
		this.detalles = new ArrayList<>();
		this.total = BigDecimal.ZERO;
		this.estado = true;
	}

	// =========================================================
	// GETTERS / SETTERS
	// =========================================================
	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = (total == null) ? BigDecimal.ZERO : total;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<VentaDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<VentaDetalle> detalles) {
		this.detalles = (detalles == null) ? new ArrayList<>() : detalles;
	}

	// =========================================================
	// HELPERS DE NEGOCIO (SIMPLES)
	// =========================================================
	public void agregarDetalle(VentaDetalle d) {
		if (d == null) return;
		this.detalles.add(d);
	}

	// RECALCULA TOTAL SUMANDO SUBTOTALES
	public void recalcularTotal() {

		BigDecimal suma = BigDecimal.ZERO;

		for (VentaDetalle d : detalles) {
			if (d != null && d.getSubtotal() != null) {
				suma = suma.add(d.getSubtotal());
			}
		}

		this.total = suma;
	}
}
