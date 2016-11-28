package br.com.urbano.controller.reservar;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.urbano.DAO.cliente.DAOCliente;
import br.com.urbano.DAO.login.DAOLogin;
import br.com.urbano.DAO.reservar.DAOReservar;
import br.com.urbano.DAO.veiculo.DAOVeiculo;
import br.com.urbano.controller.Constants;
import br.com.urbano.controller.reservar.apoio.SendReservar;
import br.com.urbano.controller.reservar.apoio.SendReservas;
import br.com.urbano.exceptions.ConflictException;
import br.com.urbano.exceptions.DateConverterException;
import br.com.urbano.exceptions.EmptyException;
import br.com.urbano.exceptions.UnauthorizedException;
import br.com.urbano.modelo.cliente.Cliente;
import br.com.urbano.modelo.reserva.Reservar;

@RestController
public class ReservarController {
	@Autowired
	private DAOReservar reservarDAO;

	@Autowired
	private DAOLogin loginDAO;

	@Autowired
	private DAOCliente clienteDAO;

	@Autowired
	private DAOVeiculo veiculoDAO;

	@RequestMapping(value = Constants.RESERVA, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<SendReservas> ListarTodos(
			@CookieValue(name = "token", required = true, defaultValue = "null") String cookien,
			HttpServletRequest request) {
		long id_login;
		// Operações com o cookie
		try {
			Cookie cookie = request.getCookies()[request.getCookies().length - 1];
			id_login = loginDAO.ChecarCookieCompleta(cookie);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		try {
			Cliente cliente = (Cliente) (clienteDAO.Exibir(id_login));
			return ResponseEntity.status(HttpStatus.OK).body(new SendReservas(reservarDAO.Listar(cliente.getId())));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = Constants.RESERVA_VARIABLE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Object> ListarUm(
			@CookieValue(name = "token", required = true, defaultValue = "null") String cookien,
			HttpServletRequest request, @PathVariable(value = Constants.PATH_VARIABLE) long id_reserva) {
		long id_login;
		// Operações com o cookie
		try {
			Cookie cookie = request.getCookies()[request.getCookies().length - 1];
			id_login = loginDAO.ChecarCookieCompleta(cookie);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		} catch (UnauthorizedException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		try {
			Cliente cliente = (Cliente) (clienteDAO.Exibir(id_login));
			return ResponseEntity.status(HttpStatus.OK)
					.body(new SendReservar(reservarDAO.Listar(cliente.getId(), id_reserva)));
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Inserindo reserva
	 * 
	 * @param strReservar
	 * @param cookien
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = Constants.RESERVA, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Void> Inserir(@RequestBody String strReservar,
			@CookieValue(value = "token", required = true, defaultValue = "null") String cookien,
			HttpServletRequest request, HttpServletResponse response) {

		long id_login;
		// Operações com o cookie
		try {
			Cookie cookie = request.getCookies()[request.getCookies().length - 1];
			id_login = loginDAO.ChecarCookieCompleta(cookie);
		} catch (NullPointerException e) {
			// TODO: Cookie não encontrado na máquina, mande relogar ou ativar
			// os cookies
			return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		} catch (UnauthorizedException e) {
			// TODO Tempo de login já encerrado
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		try {
			Reservar reservar = Reservar(strReservar, id_login);
			if (!reservarDAO.isReserved(reservar)) {
				reservarDAO.inserir(reservar);

				response.sendRedirect(request.getContextPath() + Constants.RESERVA);
				return ResponseEntity.status(HttpStatus.CREATED).body(null);
			} else
				// Caso queira reservar um carro já reservado
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (ConflictException e) {
			// TODO: Caso queira Inserir uma Data Menor que hoje
			return new ResponseEntity<>(HttpStatus.LOCKED);
		} catch (EmptyException e) {
			// TODO: Caso o ID do carro não esteja no banco
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO Erros de execução
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = Constants.RESERVA_VARIABLE, method = RequestMethod.DELETE)
	private ResponseEntity<Void> Excluir(@PathVariable long id,
			@CookieValue(value = "token", required = true, defaultValue = "null") String cookien,
			HttpServletRequest request) {
		long id_login;
		// Operações com o cookie
		try {
			Cookie cookie = request.getCookies()[request.getCookies().length - 1];
			id_login = loginDAO.ChecarCookieCompleta(cookie);
		} catch (NullPointerException e) {
			// TODO: Cookie não encontrado na máquina, mande relogar ou ativar
			// os cookies
			return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
		} catch (UnauthorizedException e) {
			// TODO Tempo de login já encerrado
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		try {
			Cliente cliente = (Cliente) clienteDAO.Exibir(id_login);
			reservarDAO.Excluir(id, cliente.getId());

			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (EmptyException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Método de apoio para inserir Reserva
	 * 
	 * @param jsonReservar
	 * @param id_login
	 * @return
	 * @throws DateConverterException
	 * @throws JSONException
	 * @throws EmptyException
	 * @throws Exception
	 */
	public Reservar Reservar(String jsonReservar, long id_login)
			throws DateConverterException, JSONException, EmptyException, Exception {
		SimpleDateFormat formatData = new SimpleDateFormat(Constants.DATETIME);
		Reservar reservar = new Reservar();
		JSONObject json = new JSONObject(jsonReservar);

		// Data atual é a data de registro
		reservar.setDataRegistro(Date.from(Instant.now()));

		// Checar se a data foi enviada no formato correto
		try {
			reservar.setDataReserva(formatData.parse(json.getString("dataReserva")));
		} catch (Exception e) {
			// TODO: handle exception
			throw new DateConverterException(e);
		}

		// Buscar Veiculo
		try {
			reservar.setVeiculo(veiculoDAO.Buscar(json.getLong("id_veiculo")));
		} catch (Exception e) {
			// TODO: handle exception
			throw new EmptyException("Veiculo não Existe!");
		}

		// Buscar Cliente
		try {
			reservar.setCliente((Cliente) clienteDAO.Exibir(id_login));
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

		return reservar;
	}
}
