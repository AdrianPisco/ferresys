package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.service.ProductoService;

public class ProductoUpdateTestRunner {

    public static void main(String[] args) {

        ProductoService service = new ProductoService();

        Producto p = service.buscarPorCodigo("TEST-019");

        p.setDescripcion("Producto actualizado 19");
        p.setPrecioVentaSoles(20.50);

        service.actualizarProducto(p, 1);

        System.out.println("Producto actualizado OK");
    }
}
