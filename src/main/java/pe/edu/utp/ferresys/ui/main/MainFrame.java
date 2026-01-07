package pe.edu.utp.ferresys.ui.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
================================================================================
 MAIN FRAME – FERRESYS
 RESPONSABILIDAD:
    - VENTANA PRINCIPAL DEL SISTEMA
    - CONTENEDOR RAIZ DE TODA LA UI
    - MANEJA ESTRUCTURA Y NAVEGACIÓN (NO LOGICA)
================================================================================
*/
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	// =========================================================
	// CONSTANTES DE NAVEGACIÓN
	// =========================================================
	public static final String PANEL_DASHBOARD = "dashboard";
	public static final String PANEL_VACIO = "vacio";

	// =========================================================
	// ATRIBUTOS DE ESTRUCTURA
	// =========================================================
	private CardLayout cardLayout;
	private JPanel contentPanel;

	// =========================================================
	// CONSTRUCTOR
	// =========================================================
	public MainFrame() {

		setTitle("FerreSys - Sistema de Gestión");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		inicializarComponentes();
	}

	// =========================================================
	// METODO PRINCIPAL DE INICIALIZACION
	// =========================================================
	private void inicializarComponentes() {
		cardLayout = new CardLayout();
		contentPanel = new JPanel(cardLayout);

		// Por ahora solo paneles vacíos.
		contentPanel.add(crearPanelVacio("DASHBOARD (pendiente)"), PANEL_DASHBOARD);
		contentPanel.add(crearPanelVacio("SIN CONTENIDO"), PANEL_VACIO);

		add(contentPanel, BorderLayout.CENTER);

		mostrarPanel(PANEL_DASHBOARD);
	}

	// =========================================================
	// METODO PARA CREAR PANELES TEMPORALES
	// =========================================================
	private JPanel crearPanelVacio(String texto) {
		JPanel panel = new JPanel();
		panel.add(new javax.swing.JLabel(texto));

		return panel;
	}

	// =========================================================
	// METODO PUBLICO DE NAVEGACION
	// =========================================================
	public void mostrarPanel(String panelId) {
		cardLayout.show(contentPanel, panelId);
	}

	// =========================================================
	// METODO MAIN SOLO PARA PRUEBAS VISUALES
	// =========================================================
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MainFrame().setVisible(true);
		});
	}
}
