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
	private Integer idUsuario;
	private String accion;
	private String detalle;
	private LocalDateTime fecha;

	public Auditoria() {
	}

	public int getIdAuditoria() {
		return idAuditoria;
	}

	public void setIdAuditoria(int idAuditoria) {
		this.idAuditoria = idAuditoria;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
}
