package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.service.ReporteService;

public class ReporteStockRunner {

	public static void main(String[] args) {

		ReporteService service = new ReporteService();

		service.reporteStockActual().forEach(r -> System.out.printf("%s | %s | Stock: %d | Valor: %.2f%n",
				r.getCodigo(), r.getDescripcion(), r.getStockTotal(), r.getValorStock()));
	}
}
