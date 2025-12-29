package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Usuario;
import pe.edu.utp.ferresys.service.ProductoService;
import pe.edu.utp.ferresys.service.UsuarioService;
import pe.edu.utp.ferresys.session.UserSession;

public class ProductoDeleteTestRunner {

    public static void main(String[] args) {

        // ==========================================
        // LOGIN Y SESION (ADMIN)
        // ==========================================
        UsuarioService usuarioService = new UsuarioService();
        Usuario admin = usuarioService.login("admin", "admin123");
        UserSession.setUsuarioActual(admin);

        // ==========================================
        // ELIMINAR PRODUCTO
        // ==========================================
        ProductoService service = new ProductoService();

        int idProducto = 2;

        service.eliminarProducto(idProducto);

        System.out.println("Producto eliminado lógicamente");

        // ==========================================
        // CIERRE DE SESION
        // ==========================================
        UserSession.cerrarSesion();
    }
}
