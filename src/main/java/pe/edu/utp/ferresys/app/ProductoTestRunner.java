package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.service.ProductoService;

public class ProductoTestRunner {

	public static void main(String[] args) {

		ProductoService productoService = new ProductoService();

		int idUsuario = 1; // ADMIN EXISTENTE

		for (int i = 1; i <= 20; i++) {

			Producto p = new Producto();

			p.setCodigo("TEST-" + String.format("%03d", i));
			p.setDescripcion("Producto de prueba ferretería " + i);
			p.setCategoria("Pruebas");
			p.setMarca("Marca Genérica");
			p.setUnidadMedida("UND");
			p.setStockTotal(10 + i);

			// PRECIO DE COMPRA (SOLES)
			p.setPrecioCompraSoles(5.0 + i);

			// PRECIO DE VENTA (SOLES)
			p.setPrecioVentaSoles(8.0 + i);

			p.setEstado(true);

			productoService.crearProducto(p, idUsuario);

			System.out.println("Producto insertado: " + p.getCodigo());
		}

		System.out.println("Carga de productos de prueba finalizada");
	}
}
