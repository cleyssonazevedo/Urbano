package br.com.urbano.exceptions;


/**
 * Erro caso queira cadastrar um usuário que já existe
 */
public class UserAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 6489764422614535767L;
	
	public UserAlreadyExistsException(){
		super("Usuário já cadastrado!");
	}
	
	public UserAlreadyExistsException(String message){
		super(message);
	}
	
	public UserAlreadyExistsException(Throwable cause){
		super("Usuário já cadastrado!", cause);
	}
	
	public UserAlreadyExistsException(String message, Throwable cause){
		super(message, cause);
	}
}
