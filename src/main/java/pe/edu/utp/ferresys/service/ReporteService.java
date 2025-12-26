package pe.edu.utp.ferresys.service;

import java.util.List;
import pe.edu.utp.ferresys.dao.ReporteDAO;
import pe.edu.utp.ferresys.model.ReporteStock;

public class ReporteService {

	private final ReporteDAO reporteDAO = new ReporteDAO();

	public List<ReporteStock> reporteStockActual() {
		return reporteDAO.obtenerStockActual();
	}
}
