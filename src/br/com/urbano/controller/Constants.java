package br.com.urbano.controller;

/**
 * Classe com todos as URL e constantes que serão utilizadas pelo Controller
 *
 */
public class Constants {
	/* URL */
	public static final String RESERVA = "/reserva";

	public static final String PATH_VARIABLE = "id";
	private static final String VARIABLE = "/{" + PATH_VARIABLE + "}";

	public static final String RESERVA_GET = RESERVA + VARIABLE;

	public static final String VEICULO = "/veiculo";
	public static final String VEICULO_GET = VEICULO + VARIABLE;

	public static final String CLIENTE = "/cliente";
	public static final String LOGIN = "/login";

	public static final String EMAIL = CLIENTE + "/email";
	public static final String EMAIL_GET = EMAIL + VARIABLE;
	public static final String EMAIL_DELETE = EMAIL + VARIABLE;

	public static final String ENDERECO = CLIENTE + "/endereco";
	public static final String ENDERECO_GET = ENDERECO + VARIABLE;
	public static final String ENDERECO_DELETE = ENDERECO + VARIABLE;

	public static final String TELEFONE = CLIENTE + "/telefone";
	public static final String TELEFONE_GET = TELEFONE + VARIABLE;
	public static final String TELEFONE_DELETE = TELEFONE + VARIABLE;
	/* Fim URL */

	/* Constantes */
	public static final String DATETIME = "dd/MM/yyyy HH:mm:ss";

	/* Fim Constantes */
}
