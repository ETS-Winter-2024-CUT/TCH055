package exceptions;
/**
 * Exception déclenchée en cas d'erreur d'authentification d'un usager.
 * 
 * @author Anis Boubaker
 */
public class AccesRefuseException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut de l'exception
	 * 
	 * @param message Le message associé à l'exception.
	 */
	public AccesRefuseException(String message) {
		super(message);
	}
}
