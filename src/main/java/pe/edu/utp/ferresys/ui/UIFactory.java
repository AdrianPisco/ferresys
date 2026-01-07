package pe.edu.utp.ferresys.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/*
================================================================================
 UI FACTORY – FERRESYS
 RESPONSABILIDAD:
    - CENTRALIZAR COLORES, FUENTES Y ESTILOS UI
    - EVITAR DUPLICACIÓN VISUAL
    - SERVIR COMO BASE PARA LIGHT / DARK (FUTURO)
================================================================================
*/
public final class UIFactory {

	// EVITA INSTANCIACIÓN
	private UIFactory() {
	}

	// =========================================================
	// COLORES BASE DEL SISTEMA
	// =========================================================
	public static Color sidebarBackground() {
		return new Color(32, 34, 37);
	}

	public static Color sidebarButtonBackground() {
		return new Color(47, 49, 54);
	}

	public static Color sidebarButtonHover() {
		return new Color(64, 68, 75);
	}

	public static Color sidebarTextPrimary() {
		return Color.WHITE;
	}

	// =========================================================
	// FUENTES
	// =========================================================
	public static Font fontBold(int size) {
		return new Font("Segoe UI", Font.BOLD, size);
	}

	public static Font fontRegular(int size) {
		return new Font("Segoe UI", Font.PLAIN, size);
	}

	// =========================================================
	// ESTILOS DE COMPONENTES
	// =========================================================
	public static void estilizarSidebarButton(JButton button) {
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(true);
		button.setOpaque(true);

		button.setBackground(sidebarButtonBackground());
		button.setForeground(sidebarTextPrimary());
		button.setFont(fontRegular(14));

		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setBorder(new EmptyBorder(10, 16, 10, 10));
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
}
