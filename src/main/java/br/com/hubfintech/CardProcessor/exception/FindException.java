package br.com.hubfintech.CardProcessor.exception;

/**
 * Exception genérica para tratar os erros
 * que aconteceram no processo de busca
 * as entidades.
 * @author marcus.martins
 */
public class FindException extends Exception {
	private static final long serialVersionUID = 1L;

	public FindException(String message) {
        super(message);
    }
	
	public FindException(String message, Exception exception) {
        super(message, exception);
    }
}
