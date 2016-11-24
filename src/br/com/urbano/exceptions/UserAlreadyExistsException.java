package br.com.urbano.exceptions;


/**
 * Erro caso queira cadastrar um usu�rio que j� existe
 */
public class UserAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 6489764422614535767L;
	
	public UserAlreadyExistsException(){
		super("Usu�rio j� cadastrado!");
	}
	
	public UserAlreadyExistsException(String message){
		super(message);
	}
	
	public UserAlreadyExistsException(Throwable cause){
		super("Usu�rio j� cadastrado!", cause);
	}
	
	public UserAlreadyExistsException(String message, Throwable cause){
		super(message, cause);
	}
}
