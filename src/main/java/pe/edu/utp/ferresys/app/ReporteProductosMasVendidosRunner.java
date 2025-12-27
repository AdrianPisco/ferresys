package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.ReporteProductoVendido;
import pe.edu.utp.ferresys.service.ReporteService;

public class ReporteProductosMasVendidosRunner {

    public static void main(String[] args) {

        ReporteService service = new ReporteService();

        for (ReporteProductoVendido r : service.productosMasVendidos()) {
        	System.out.printf(
        		    "%s | %s | Cantidad: %d | Total S/: %.2f%n",
        		    r.getCodigo(),
        		    r.getDescripcion(),
        		    r.getCantidadVendida(),
        		    r.getTotalVendido()
            );
        }
    }
}
