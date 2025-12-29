package pe.edu.utp.ferresys.service;

import java.util.List;

import pe.edu.utp.ferresys.dao.ReporteDAO;
import pe.edu.utp.ferresys.model.Permiso;
import pe.edu.utp.ferresys.model.ReporteProductoVendido;
import pe.edu.utp.ferresys.model.ReporteStock;
import pe.edu.utp.ferresys.model.ReporteVentaDiaria;
import pe.edu.utp.ferresys.security.SecurityManager;

/*
================================================================================
 REPORTE SERVICE
 RESPONSABILIDAD:
    - EXPONER REPORTES DEL SISTEMA
    - VALIDAR PERMISOS DE ACCESO
================================================================================
*/
public class ReporteService {

	private final ReporteDAO reporteDAO = new ReporteDAO();

	// =====================================================
	// REPORTE: STOCK ACTUAL
	// =====================================================
	public List<ReporteStock> reporteStockActual() {

		SecurityManager.validar(Permiso.REPORTE_VER);

		return reporteDAO.obtenerStockActual();
	}

	// =====================================================
	// REPORTE: PRODUCTOS MAS VENDIDOS
	// =====================================================
	public List<ReporteProductoVendido> productosMasVendidos() {

		SecurityManager.validar(Permiso.REPORTE_VER);

		return reporteDAO.obtenerProductosMasVendidos();
	}

	// =====================================================
	// REPORTE: VENTAS DIARIAS
	// =====================================================
	public List<ReporteVentaDiaria> reporteVentasDiarias() {

		SecurityManager.validar(Permiso.REPORTE_VER);

		return reporteDAO.obtenerVentasDiarias();
	}
}
