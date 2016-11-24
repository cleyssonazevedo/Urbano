package br.com.urbano.modelo;

/**
 * Respons�vel por enviar mensagens de erro para o usu�rio
 * 
 * @author Cleysson Azevedo
 */
public class Message {
	private String message;
	
	public Message(String message) {
		// TODO Auto-generated constructor stub
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}