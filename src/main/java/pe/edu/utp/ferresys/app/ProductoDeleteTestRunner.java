package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.service.ProductoService;

public class ProductoDeleteTestRunner {

    public static void main(String[] args) {

        ProductoService service = new ProductoService();

        int idProducto = 2; 
        int idUsuario = 1;  

        service.eliminarProducto(idProducto, idUsuario);

        System.out.println("Producto eliminado lógicamente");
    }
}