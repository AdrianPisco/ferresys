package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.model.ReporteVentaDiaria;
import pe.edu.utp.ferresys.service.ReporteService;

public class ReporteVentasDiariasRunner {

	public static void main(String[] args) {

		ReporteService service = new ReporteService();

		for (ReporteVentaDiaria r : service.reporteVentasDiarias()) {
			System.out.println(
				r.getFecha() +
				" | Total: S/ " + r.getTotalVentas() +
				" | Ventas: " + r.getCantidadVentas()
			);
		}
	}
}
