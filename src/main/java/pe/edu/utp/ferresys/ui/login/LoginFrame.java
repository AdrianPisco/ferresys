package pe.edu.utp.ferresys.ui.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pe.edu.utp.ferresys.exception.BusinessException;
import pe.edu.utp.ferresys.service.UsuarioService;

/*
================================================================================
 LOGIN FRAME
 RESPONSABILIDAD:
    - PEDIR CREDENCIALES (USUARIO / PASSWORD)
    - INVOCAR UsuarioService.login(...)
    - MOSTRAR MENSAJES DE ERROR
    - CERRARSE SI EL LOGIN ES EXITOSO
    - NO DECIDE NAVEGACION (ESO ES BLOQUE 2)
================================================================================
*/
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	// =========================================================
	// CAMPOS UI
	// =========================================================
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JLabel lblEstado;
	private JButton btnIngresar;

	// =========================================================
	// DEPENDENCIA
	// =========================================================
	private final UsuarioService usuarioService = new UsuarioService();

	// =========================================================
	// CONSTRUCTOR
	// =========================================================
	public LoginFrame() {
		configurarVentana();
		construirUI();
		configurarEventos();
	}

	// =========================================================
	// CONFIGURACION BASICA
	// =========================================================
	private void configurarVentana() {
		setTitle("FerreSys - Login");
		setSize(420, 260);
		setMinimumSize(new Dimension(420, 260));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	// =========================================================
	// CONSTRUCCION DE UI
	// =========================================================
	private void construirUI() {

		JPanel root = new JPanel(new BorderLayout());
		root.setBorder(new EmptyBorder(16, 16, 16, 16));
		add(root, BorderLayout.CENTER);

		JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
		lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
		root.add(lblTitulo, BorderLayout.NORTH);

		JPanel form = new JPanel(new GridBagLayout());
		form.setBorder(new EmptyBorder(12, 0, 0, 0));
		root.add(form, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6, 0, 6, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx = 0;
		gbc.gridy = 0;
		form.add(new JLabel("Usuario"), gbc);

		txtUsuario = new JTextField();
		gbc.gridy = 1;
		form.add(txtUsuario, gbc);

		gbc.gridy = 2;
		form.add(new JLabel("Contraseña"), gbc);

		txtPassword = new JPasswordField();
		gbc.gridy = 3;
		form.add(txtPassword, gbc);

		lblEstado = new JLabel(" ");
		lblEstado.setFont(lblEstado.getFont().deriveFont(Font.PLAIN, 12f));
		gbc.gridy = 4;
		form.add(lblEstado, gbc);

		btnIngresar = new JButton("Ingresar");
		gbc.gridy = 5;
		form.add(btnIngresar, gbc);

		getRootPane().setDefaultButton(btnIngresar);
	}

	// =========================================================
	// EVENTOS
	// =========================================================
	private void configurarEventos() {
		btnIngresar.addActionListener(e -> intentarLogin());
		txtPassword.addActionListener(e -> intentarLogin());
	}

	// =========================================================
	// LOGICA DE LOGIN
	// =========================================================
	private void intentarLogin() {

		setEstado(" ");

		String username = txtUsuario.getText() != null ? txtUsuario.getText().trim() : "";
		String password = new String(txtPassword.getPassword());

		if (username.isEmpty()) {
			setEstado("Ingrese usuario");
			txtUsuario.requestFocus();
			return;
		}

		if (password.isEmpty()) {
			setEstado("Ingrese contraseña");
			txtPassword.requestFocus();
			return;
		}

		setCargando(true);

		try {
			usuarioService.login(username, password);

			JOptionPane.showMessageDialog(this, "Acceso concedido", "LOGIN EXITOSO", JOptionPane.INFORMATION_MESSAGE);

			// EL LOGIN CUMPLIO SU FUNCION
			dispose();

		} catch (BusinessException ex) {
			setEstado(ex.getMessage());

		} catch (Exception ex) {
			setEstado("Error inesperado al iniciar sesión");
			ex.printStackTrace();

		} finally {
			setCargando(false);
		}
	}

	// =========================================================
	// AYUDANTES UI
	// =========================================================
	private void setEstado(String msg) {
		lblEstado.setText(msg != null ? msg : " ");
	}

	private void setCargando(boolean cargando) {
		btnIngresar.setEnabled(!cargando);
		txtUsuario.setEnabled(!cargando);
		txtPassword.setEnabled(!cargando);
	}
}
