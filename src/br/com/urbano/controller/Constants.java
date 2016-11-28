package br.com.urbano.controller;

/**
 * Classe com todos as URL e constantes que serão utilizadas pelo Controller
 *
 */
public class Constants {
	/* URL */
	
	public static final String PATH_VARIABLE = "id";
	private static final String VARIABLE = "/{" + PATH_VARIABLE + "}";

	public static final String RESERVA = "/reserva";	
	public static final String RESERVA_VARIABLE = RESERVA + VARIABLE;

	public static final String VEICULO = "/veiculo";
	public static final String VEICULO_VARIABLE = VEICULO + VARIABLE;

	public static final String CLIENTE = "/cliente";
	public static final String LOGIN = "/login";

	public static final String EMAIL = CLIENTE + "/email";
	public static final String EMAIL_VARIABLE = EMAIL + VARIABLE;

	public static final String ENDERECO = CLIENTE + "/endereco";
	public static final String ENDERECO_VARIABLE = ENDERECO + VARIABLE;

	public static final String TELEFONE = CLIENTE + "/telefone";
	public static final String TELEFONE_VARIABLE = TELEFONE + VARIABLE;
	/* Fim URL */

	/* Constantes */
	public static final String DATETIME = "dd/MM/yyyy HH:mm";

	/* Fim Constantes */
}
