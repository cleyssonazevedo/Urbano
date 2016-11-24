package br.com.urbano.exceptions;

/**
 * Exce��o para tratar erro de login no sistema
 */
public class UnauthorizedException extends Exception {
	private static final long serialVersionUID = 5718758031398290794L;
	
	/**
	 * Envia a mensagem padr�o do sistema e n�o informa a causa do erro
	 */
	public UnauthorizedException() {
		// TODO Auto-generated constructor stub
		super("Usu�rio ou senha incorretos!");
	}
	
	/**
	 * Cria uma mensagem pr�pria para enviar e n�o informa a causa do erro
	 * @param message Mensagem que vai ser enviada
	 */
	public UnauthorizedException(String message){
		super(message);
	}
	
	/**
	 *  Envia a mensagem padr�o do sistema e informa a causa do erro
	 * @param cause O que causou este erro
	 */
	public UnauthorizedException(Throwable cause) {
		// TODO Auto-generated constructor stub
		super("Usu�rio ou senha incorretos!", cause);
	}
	
	
	/**
	 * Cria uma mensagem pr�pria para enviar e informa a causa do erro
	 * @param message Mensagem que vai ser enviada
	 * @param cause O que causou este erro
	 */
	public UnauthorizedException(String message, Throwable cause){
		super(message, cause);
	}
	
}
