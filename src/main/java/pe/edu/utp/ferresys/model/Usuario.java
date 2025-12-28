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
    private Rol rol;

    // =========================================================
    // CONSTRUCTOR VACIO (OBLIGATORIO)
    // =========================================================
    public Usuario() {
    }

    // =========================================================
    // CONSTRUCTOR COMPLETO
    // =========================================================
    public Usuario(int idUsuario, String username, String passwordHash, boolean estado, Rol rol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.passwordHash = passwordHash;
        this.estado = estado;
        this.rol = rol;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
