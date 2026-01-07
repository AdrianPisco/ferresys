package pe.edu.utp.ferresys.ui.sidebar;

/*
=========================================================
 SIDEBAR LISTENER
 RESPONSABILIDAD:
    - ACTUAR COMO CONTRATO ENTRE SIDEBAR Y MAINFRAME
    - NOTIFICAR EVENTOS DE NAVEGACION
 REGLAS:
    - NO CONTIENE LOGICA
    - NO CONOCE UI CONCRETA
=========================================================
*/
public interface SidebarListener {

	void onMenuSelected(String viewKey);

}
