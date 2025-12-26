package pe.edu.utp.ferresys.app;

import pe.edu.utp.ferresys.service.KardexService;

/*
================================================================================
 KARDEX TEST RUNNER
 RESPONSABILIDAD:
    - PROBAR MOVIMIENTOS DE STOCK
    - VALIDAR TRANSACCIONES
================================================================================
*/
public class KardexTestRunner {

	public static void main(String[] args) {

		KardexService kardexService = new KardexService();

		int idProducto = 2; // ASEGÚRATE QUE EXISTE
		String usuario = "admin";

		// =========================
		// ENTRADA DE STOCK
		// =========================
		kardexService.registrarMovimiento(idProducto, "ENTRADA", 10, "Ingreso inicial de stock", usuario);

		System.out.println("ENTRADA registrada correctamente");

		// =========================
		// SALIDA DE STOCK
		// =========================
		kardexService.registrarMovimiento(idProducto, "SALIDA", 5, "Venta de producto", usuario);

		System.out.println("SALIDA registrada correctamente");

		// =========================
		// SALIDA INVÁLIDA (PRUEBA)
		// =========================
		try {
			kardexService.registrarMovimiento(idProducto, "SALIDA", 1000, "Salida inválida", usuario);
		} catch (Exception e) {
			System.out.println("ERROR CONTROLADO: " + e.getMessage());
		}

		System.out.println("Pruebas de Kardex finalizadas");
	}
}
