package br.com.urbano.exceptions;

import java.io.Serializable;

public class SessionEndException extends Exception implements Serializable {
	private static final long serialVersionUID = 1107971945342605248L;

	public SessionEndException() {
		// TODO Auto-generated constructor stub
		super("Sessão já encerrada!");
	}
}
