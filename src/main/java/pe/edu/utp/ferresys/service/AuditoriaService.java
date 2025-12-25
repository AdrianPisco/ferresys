package pe.edu.utp.ferresys.service;

import pe.edu.utp.ferresys.dao.AuditoriaDAO;
import pe.edu.utp.ferresys.model.Auditoria;

import java.time.LocalDateTime;

/*
================================================================================
 AUDITORIA SERVICE
 RESPONSABILIDAD:
    - CENTRALIZAR REGISTRO DE EVENTOS
================================================================================
*/
public class AuditoriaService {

	private final AuditoriaDAO auditoriaDAO = new AuditoriaDAO();

	public void registrarEvento(Integer idUsuario, String accion, String detalle) {

		Auditoria auditoria = new Auditoria();
		auditoria.setIdUsuario(idUsuario);
		auditoria.setAccion(accion);
		auditoria.setDetalle(detalle);
		auditoria.setFecha(LocalDateTime.now());

		auditoriaDAO.registrar(auditoria);
	}
}
