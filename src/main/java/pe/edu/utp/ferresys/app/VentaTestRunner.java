package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Venta;
import pe.edu.utp.ferresys.model.VentaDetalle;
import pe.edu.utp.ferresys.service.VentaService;

public class VentaTestRunner {

	public static void main(String[] args) {

		VentaService ventaService = new VentaService();

		// =========================
		// CREAR VENTA
		// =========================
		Venta venta = new Venta();
		venta.setIdUsuario(1); // ADMIN
		venta.setEstado(true);

		// =========================
		// DETALLE 1
		// Producto ID 3 | Cantidad 11
		// =========================
		VentaDetalle d1 = new VentaDetalle();
		d1.setIdProducto(3);
		d1.setCantidad(11);

		// =========================
		// DETALLE 2
		// Producto ID 7 | Cantidad 50
		// =========================
		VentaDetalle d2 = new VentaDetalle();
		d2.setIdProducto(7);
		d2.setCantidad(50);

		// =========================
		// AGREGAR DETALLES
		// =========================
		venta.getDetalles().add(d1);
		venta.getDetalles().add(d2);

		// =========================
		// REGISTRAR VENTA
		// =========================
		ventaService.registrarVenta(venta);

		System.out.println("Venta de prueba registrada correctamente");
	}
}
