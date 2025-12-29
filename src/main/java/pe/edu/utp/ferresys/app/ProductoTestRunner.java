package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.model.Usuario;
import pe.edu.utp.ferresys.service.ProductoService;
import pe.edu.utp.ferresys.service.UsuarioService;
import pe.edu.utp.ferresys.session.UserSession;

public class ProductoTestRunner {

	public static void main(String[] args) {

		// ==========================================
		// LOGIN Y SESION (ADMIN)
		// ==========================================
		UsuarioService usuarioService = new UsuarioService();
		Usuario admin = usuarioService.login("admin", "admin123");
		UserSession.setUsuarioActual(admin);

		// ==========================================
		// CARGA DE PRODUCTOS
		// ==========================================
		ProductoService productoService = new ProductoService();

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

			productoService.crearProducto(p);

			System.out.println("Producto insertado: " + p.getCodigo());
		}

		System.out.println("Carga de productos de prueba finalizada");

		// ==========================================
		// CIERRE DE SESION
		// ==========================================
		UserSession.cerrarSesion();
	}
}
