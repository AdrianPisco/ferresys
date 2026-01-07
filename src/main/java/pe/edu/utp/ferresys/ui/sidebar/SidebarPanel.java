package pe.edu.utp.ferresys.ui.sidebar;

import java.awt.*;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pe.edu.utp.ferresys.ui.UIFactory;

/*
================================================================================
 SIDEBAR PANEL – FERRESYS
 RESPONSABILIDAD:
    - MOSTRAR MENÚ LATERAL DEL SISTEMA
    - USAR SidebarButton
    - MANEJAR ESTADO ACTIVO DE BOTONES
    - EMITIR OPCIÓN SELECCIONADA (MenuOption)
    - NO NAVEGA
    - NO CONOCE MainFrame
================================================================================
*/
public class SidebarPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// CALLBACK PARA NOTIFICAR SELECCIÓN DE MENÚ
	private final Consumer<MenuOption> onMenuSelected;

	// CONTENEDOR DE BOTONES
	private JPanel menuPanel;

	// MAPA PARA CONTROLAR ESTADO ACTIVO
	private final Map<MenuOption, SidebarButton> botones = new EnumMap<>(MenuOption.class);

	// =========================================================
	// CONSTRUCTOR
	// =========================================================
	public SidebarPanel(Consumer<MenuOption> onMenuSelected) {
		this.onMenuSelected = onMenuSelected;

		configurarPanel();
		construirUI();
	}

	// =========================================================
	// CONFIGURACIÓN BASE DEL PANEL
	// =========================================================
	private void configurarPanel() {
		setLayout(new BorderLayout());
		setBackground(UIFactory.sidebarBackground());
		setPreferredSize(new Dimension(220, 0));
	}

	// =========================================================
	// CONSTRUCCIÓN GENERAL DE LA UI
	// =========================================================
	private void construirUI() {
		add(construirHeader(), BorderLayout.NORTH);
		add(construirMenu(), BorderLayout.CENTER);
		add(construirFooter(), BorderLayout.SOUTH);
	}

	// =========================================================
	// HEADER (TÍTULO DEL SISTEMA)
	// =========================================================
	private JComponent construirHeader() {
		JLabel lblTitulo = new JLabel("FERRESYS");
		lblTitulo.setFont(UIFactory.fontBold(18));
		lblTitulo.setForeground(UIFactory.sidebarTextPrimary());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(20, 10, 20, 10));
		panel.add(lblTitulo);

		return panel;
	}

	// =========================================================
	// MENÚ CENTRAL
	// =========================================================
	private JComponent construirMenu() {
		menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		menuPanel.setOpaque(false);
		menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		agregarBotonesMenu();

		JScrollPane scroll = new JScrollPane(menuPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(null);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);

		return scroll;
	}

	// =========================================================
	// FOOTER (CONFIGURACIÓN / LOGOUT)
	// =========================================================
	private JComponent construirFooter() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);
		panel.setBorder(new EmptyBorder(10, 10, 20, 10));

		panel.add(crearBoton(MenuOption.CONFIGURACION, "Configuración"));
		panel.add(Box.createVerticalStrut(8));
		panel.add(crearBoton(MenuOption.LOGOUT, "Cerrar sesión"));

		return panel;
	}

	// =========================================================
	// BOTONES DEL MENÚ
	// =========================================================
	private void agregarBotonesMenu() {
		menuPanel.add(crearBoton(MenuOption.DASHBOARD, "Dashboard"));
		menuPanel.add(crearBoton(MenuOption.VENTAS, "Ventas"));
		menuPanel.add(crearBoton(MenuOption.PRODUCTOS, "Productos"));
		menuPanel.add(crearBoton(MenuOption.USUARIOS, "Usuarios"));
		menuPanel.add(crearBoton(MenuOption.REPORTES, "Reportes"));

		// BOTÓN ACTIVO POR DEFECTO
		setActivo(MenuOption.DASHBOARD);
	}

	// =========================================================
	// FÁBRICA DE BOTONES
	// =========================================================
	private SidebarButton crearBoton(MenuOption option, String texto) {
		SidebarButton btn = new SidebarButton(texto);

		btn.setAlignmentX(Component.LEFT_ALIGNMENT);
		btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

		btn.addActionListener(e -> {
			setActivo(option);
			onMenuSelected.accept(option);
		});

		botones.put(option, btn);
		menuPanel.add(btn);

		return btn;
	}

	// =========================================================
	// ACTIVA UN BOTÓN Y DESACTIVA LOS DEMÁS
	// =========================================================
	private void setActivo(MenuOption option) {
		botones.forEach((op, btn) -> btn.setActivo(op == option));
	}
}
