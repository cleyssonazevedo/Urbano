package br.com.urbano.exceptions;

public class DataNotFoundException extends Exception {
	private static final long serialVersionUID = 5718758031398290794L;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
