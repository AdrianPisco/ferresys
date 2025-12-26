package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.Producto;
import pe.edu.utp.ferresys.service.ProductoService;

public class ProductoListRunner {

    public static void main(String[] args) {

        ProductoService service = new ProductoService();

        for (Producto p : service.listarProductos()) {
            System.out.println(
                p.getCodigo() + " | " +
                p.getDescripcion() + " | S/ " +
                p.getPrecioVentaSoles()
            );
        }
    }
}