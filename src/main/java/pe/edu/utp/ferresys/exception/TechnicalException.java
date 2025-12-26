package pe.edu.utp.ferresys.exception;

public class TechnicalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// MENSAJE SIMPLE
	public TechnicalException(String mensaje) {
		super(mensaje);
	}

	// MENSAJE + CAUSA
	public TechnicalException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}