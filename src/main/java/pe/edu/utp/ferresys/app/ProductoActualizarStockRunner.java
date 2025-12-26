package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.service.ProductoService;

public class ProductoActualizarStockRunner {

    public static void main(String[] args) {

        ProductoService service = new ProductoService();

        service.actualizarStock("TEST-005", 50, 1);

        System.out.println("Stock actualizado correctamente");
    }
}
