package pe.edu.utp.ferresys.model;

import java.time.LocalDateTime;

/*
================================================================================
 MODELO AUDITORIA
 RESPONSABILIDAD:
    - REPRESENTAR UN EVENTO DE AUDITORIA DEL SISTEMA
================================================================================
*/
public class Auditoria {

    private int idAuditoria;
    private String accion;
    private String usuario;   // username o identificador textual
    private LocalDateTime fecha;

    // ============================
    // GETTERS Y SETTERS
    // ============================

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
