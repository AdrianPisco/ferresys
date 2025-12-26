package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.service.ProductoService;

public class ProductoBuscarRunner {

    public static void main(String[] args) {

        ProductoService service = new ProductoService();

        Producto p = service.buscarPorCodigo("TEST-020");

        if (p != null) {
            System.out.println(
                p.getCodigo() + " | " +
                p.getDescripcion() + " | S/ " +
                p.getPrecioVentaSoles()
            );
        } else {
            System.out.println("Producto no encontrado");
        }
    }
}
