package br.com.urbano.exceptions;

import java.io.Serializable;

public class ConflictException extends Exception implements Serializable {
	private static final long serialVersionUID = -3095729621783577308L;

	public ConflictException() {
		// TODO Auto-generated constructor stub
		super();
	}

	public ConflictException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

	public ConflictException(String message, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}

	public ConflictException(Throwable cause) {
		// TODO Auto-generated constructor stub
		super(cause.getMessage(), cause);
	}
}
