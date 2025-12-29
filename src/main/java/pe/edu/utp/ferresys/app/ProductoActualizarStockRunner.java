package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Usuario;
import pe.edu.utp.ferresys.service.ProductoService;
import pe.edu.utp.ferresys.service.UsuarioService;
import pe.edu.utp.ferresys.session.UserSession;

public class ProductoActualizarStockRunner {

    public static void main(String[] args) {

        // ==========================================
        // LOGIN Y SESION (ADMIN)
        // ==========================================
        UsuarioService usuarioService = new UsuarioService();
        Usuario admin = usuarioService.login("admin", "admin123");
        UserSession.setUsuarioActual(admin);

        // ==========================================
        // ACTUALIZAR STOCK
        // ==========================================
        ProductoService service = new ProductoService();

        service.actualizarStock("TEST-005", 50);

        System.out.println("Stock actualizado correctamente");

        // ==========================================
        // CIERRE DE SESION
        // ==========================================
        UserSession.cerrarSesion();
    }
}
