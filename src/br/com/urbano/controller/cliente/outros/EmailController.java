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
import br.com.urbano.controller.cliente.apoio.outros.Emails;
import br.com.urbano.exceptions.EmptyException;
import br.com.urbano.exceptions.UnauthorizedException;
import br.com.urbano.modelo.cliente.Cliente;
import br.com.urbano.modelo.cliente.outros.Email;

@RestController
public class EmailController {
	@Autowired
	private DAOOutros outrosDAO;

	@Autowired
	private DAOLogin loginDAO;

	@Autowired
	private DAOCliente clienteDAO;

	/**
	 * Exibindo Emails
	 * 
	 * @param cookien
	 * @param request
	 * @return
	 */
	@RequestMapping(value = Constants.EMAIL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Object> Exibir(@CookieValue(name = "token", required = true) String cookien,
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
			Cliente cliente = (Cliente) clienteDAO.Exibir(id_login);
			return ResponseEntity.status(HttpStatus.OK).body(new Emails(outrosDAO.Emails(cliente.getId())));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = Constants.EMAIL_VARIABLE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Email> Exibir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request, @PathVariable(value = Constants.PATH_VARIABLE) long id_email) {
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
			Cliente cliente = (Cliente) clienteDAO.Exibir(id_login);
			return ResponseEntity.status(HttpStatus.OK).body(outrosDAO.Email(cliente.getId(), id_email));
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
	 * Inserindo Emails
	 * 
	 * @param cookien
	 * @param request
	 * @param response
	 * @param enderecos
	 * @return
	 */
	@RequestMapping(value = Constants.EMAIL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Object> Inserir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request, HttpServletResponse response, @RequestBody Emails emails) {
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
			Cliente cliente = (Cliente) clienteDAO.Exibir(id_login);
			outrosDAO.InserirEmail(emails.getEmails(), cliente);

			response.sendRedirect(request.getContextPath() + Constants.EMAIL);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = Constants.EMAIL_VARIABLE, method = RequestMethod.DELETE)
	private ResponseEntity<Object> Excluir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = Constants.PATH_VARIABLE) long id_email) {
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
			Cliente cliente = (Cliente) clienteDAO.Exibir(id_login);
			// Isto serve para ver se o ID é do endereco é do mesmo cliente
			Email email = outrosDAO.Email(cliente.getId(), id_email);
			outrosDAO.ExcluirEmail(email.getId());

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
