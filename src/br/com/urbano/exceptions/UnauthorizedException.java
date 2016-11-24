package br.com.urbano.exceptions;

/**
 * Exceção para tratar erro de login no sistema
 */
public class UnauthorizedException extends Exception {
	private static final long serialVersionUID = 5718758031398290794L;
	
	/**
	 * Envia a mensagem padrão do sistema e não informa a causa do erro
	 */
	public UnauthorizedException() {
		// TODO Auto-generated constructor stub
		super("Usuário ou senha incorretos!");
	}
	
	/**
	 * Cria uma mensagem própria para enviar e não informa a causa do erro
	 * @param message Mensagem que vai ser enviada
	 */
	public UnauthorizedException(String message){
		super(message);
	}
	
	/**
	 *  Envia a mensagem padrão do sistema e informa a causa do erro
	 * @param cause O que causou este erro
	 */
	public UnauthorizedException(Throwable cause) {
		// TODO Auto-generated constructor stub
		super("Usuário ou senha incorretos!", cause);
	}
	
	
	/**
	 * Cria uma mensagem própria para enviar e informa a causa do erro
	 * @param message Mensagem que vai ser enviada
	 * @param cause O que causou este erro
	 */
	public UnauthorizedException(String message, Throwable cause){
		super(message, cause);
	}
	
}
