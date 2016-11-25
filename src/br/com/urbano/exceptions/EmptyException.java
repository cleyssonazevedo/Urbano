package br.com.urbano.exceptions;

public class EmptyException extends Exception {
	private static final long serialVersionUID = 5718758031398290794L;

	public EmptyException() {
		super();
	}

	public EmptyException(String message) {
		super(message);
	}

	public EmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyException(Throwable cause) {
		super(cause);
	}

}
