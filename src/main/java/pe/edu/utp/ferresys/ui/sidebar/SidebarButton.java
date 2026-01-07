package pe.edu.utp.ferresys.ui.sidebar;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pe.edu.utp.ferresys.ui.UIFactory;

/*
================================================================================
 SIDEBAR BUTTON – FERRESYS
 RESPONSABILIDAD:
    - REPRESENTAR UN BOTÓN DEL SIDEBAR
    - MANEJAR ESTADO HOVER
    - MANEJAR ESTADO ACTIVO
    - NO NAVEGA
    - NO CONOCE MainFrame
================================================================================
*/
public class SidebarButton extends JButton {

    private static final long serialVersionUID = 1L;

    private boolean activo = false;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public SidebarButton(String texto) {
        super(texto);

        configurarBase();
        registrarEventosMouse();
    }

    // =========================================================
    // CONFIGURACIÓN BASE DEL BOTÓN
    // =========================================================
    private void configurarBase() {
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(true);
        setOpaque(true);

        setBackground(UIFactory.sidebarButtonBackground());
        setForeground(UIFactory.sidebarTextPrimary());
        setFont(UIFactory.fontRegular(14));

        setHorizontalAlignment(SwingConstants.LEFT);
        setBorder(new EmptyBorder(10, 16, 10, 10));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // =========================================================
    // EVENTOS DE MOUSE (HOVER)
    // =========================================================
    private void registrarEventosMouse() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!activo) {
                    setBackground(UIFactory.sidebarButtonHover());
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!activo) {
                    setBackground(UIFactory.sidebarButtonBackground());
                }
            }
        });
    }

    // =========================================================
    // ESTADO ACTIVO / INACTIVO
    // =========================================================
    public void setActivo(boolean activo) {
        this.activo = activo;

        if (activo) {
            setBackground(UIFactory.sidebarButtonHover());
        } else {
            setBackground(UIFactory.sidebarButtonBackground());
        }
    }
}
