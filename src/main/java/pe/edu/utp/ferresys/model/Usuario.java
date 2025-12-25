package pe.edu.utp.ferresys.model;

/*
================================================================================
 MODELO USUARIO (POJO)
 RESPONSABILIDAD:
    - REPRESENTAR UN USUARIO DEL SISTEMA
    - NO CONTIENE SQL
    - NO CONTIENE LOGICA DE NEGOCIO
    - NO CONTIENE UI
================================================================================
*/
public class Usuario {
	// =========================================================
	// ATRIBUTOS
	// =========================================================
	private int idUsuario;
	private String username;
	private String passwordHash;
	private boolean estado;
	private int idRole;

	// =========================================================
	// CONSTRUCTOR VACIO (OBLIGATORIO)
	// =========================================================
	public Usuario() {
	}

	// =========================================================
	// CONSTRUCTOR COMPLETO
	// =========================================================
	public Usuario(int idUsuario, String username, String passwordHash, boolean estado, int idRole) {
		this.idUsuario = idUsuario;
		this.username = username;
		this.passwordHash = passwordHash;
		this.estado = estado;
		this.idRole = idRole;
	}

	// =========================================================
	// GETTERS Y SETTERS
	// =========================================================
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
}