package br.com.urbano.controller.cliente.outros;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import br.com.urbano.DAO.cliente.DAOOutros;
import br.com.urbano.DAO.login.DAOLogin;
import br.com.urbano.controller.Constants;
import br.com.urbano.controller.cliente.apoio.outros.Telefones;
import br.com.urbano.exceptions.EmptyException;
import br.com.urbano.exceptions.UnauthorizedException;
import br.com.urbano.modelo.cliente.Cliente;
import br.com.urbano.modelo.cliente.outros.Telefone;

@RestController
public class TelefoneController {
	@Autowired
	private DAOOutros outrosDAO;

	@Autowired
	private DAOLogin loginDAO;

	@Autowired
	private DAOCliente clienteDAO;

	/**
	 * Exibindo Telefones
	 * 
	 * @param cookien
	 * @param request
	 * @return
	 */
	@RequestMapping(value = Constants.TELEFONE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Telefones> Exibir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request) {
		long id_login;
		// Opera��es com o cookie
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
			Cliente cliente = (Cliente) clienteDAO.Exibir(id_login);
			return ResponseEntity.status(HttpStatus.OK).body(new Telefones(outrosDAO.Telefones(cliente.getId())));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = Constants.TELEFONE_VARIABLE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Telefone> Exibir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request, @PathVariable(value = Constants.PATH_VARIABLE) long id_telefone) {
		long id_login;
		// Opera��es com o cookie
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
			Cliente cliente = (Cliente) clienteDAO.Exibir(id_login);
			return ResponseEntity.status(HttpStatus.OK).body(outrosDAO.Telefone(cliente.getId(), id_telefone));
		} catch (EmptyException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Inserindo Telefones
	 * 
	 * @param cookien
	 * @param request
	 * @param response
	 * @param enderecos
	 * @return
	 */
	@RequestMapping(value = Constants.TELEFONE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Void> Inserir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request, HttpServletResponse response, @RequestBody Telefones telefones) {
		long id_login;
		// Opera��es com o cookie
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
			Cliente cliente = (Cliente) clienteDAO.Exibir(id_login);
			outrosDAO.InserirTelefone(telefones.getTelefones(), cliente);

			response.sendRedirect(request.getContextPath() + Constants.TELEFONE);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = Constants.TELEFONE_VARIABLE, method = RequestMethod.DELETE)
	private ResponseEntity<Void> Excluir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = Constants.PATH_VARIABLE) long id_telefone) {
		long id_login;
		// Opera��es com o cookie
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
			Cliente cliente = (Cliente) clienteDAO.Exibir(id_login);
			// Isto serve para ver se o ID � do endereco � do mesmo cliente
			Telefone telefone = outrosDAO.Telefone(cliente.getId(), id_telefone);
			outrosDAO.ExcluirTelefone(telefone.getId());

			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (EmptyException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
