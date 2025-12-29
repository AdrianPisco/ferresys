package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.model.Usuario;
import pe.edu.utp.ferresys.service.ProductoService;
import pe.edu.utp.ferresys.service.UsuarioService;
import pe.edu.utp.ferresys.session.UserSession;

public class ProductoUpdateTestRunner {

    public static void main(String[] args) {

        // ==========================================
        // LOGIN Y SESION
        // ==========================================
        UsuarioService usuarioService = new UsuarioService();
        Usuario admin = usuarioService.login("admin", "admin123");
        UserSession.setUsuarioActual(admin);

        // ==========================================
        // LOGICA DE PRUEBA
        // ==========================================
        ProductoService service = new ProductoService();

        Producto p = service.buscarPorCodigo("TEST-019");

        p.setDescripcion("Producto actualizado 19");
        p.setPrecioVentaSoles(20.50);

        service.actualizarProducto(p);

        System.out.println("Producto actualizado OK");

        // ==========================================
        // CIERRE DE SESION (OPCIONAL)
        // ==========================================
        UserSession.cerrarSesion();
    }
}
