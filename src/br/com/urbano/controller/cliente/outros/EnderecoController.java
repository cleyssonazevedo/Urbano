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
import br.com.urbano.controller.cliente.apoio.ValidarApoio;
import br.com.urbano.controller.cliente.apoio.outros.Enderecos;
import br.com.urbano.exceptions.EmptyException;
import br.com.urbano.exceptions.UnauthorizedException;
import br.com.urbano.modelo.cliente.Cliente;
import br.com.urbano.modelo.cliente.outros.Endereco;

@RestController
public class EnderecoController {
	@Autowired
	private DAOOutros outrosDAO;

	@Autowired
	private DAOLogin loginDAO;

	@Autowired
	private DAOCliente clienteDAO;

	/**
	 * Exibindo Endereco
	 * 
	 * @param cookien
	 * @param request
	 * @return
	 */
	@RequestMapping(value = Constants.ENDERECO, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Enderecos> Exibir(@CookieValue(name = "token", required = true) String cookien,
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
			return ResponseEntity.status(HttpStatus.OK).body(new Enderecos(outrosDAO.Enderecos(cliente.getId())));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = Constants.ENDERECO_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Endereco> Exibir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request, @PathVariable(value = Constants.PATH_VARIABLE) long id_endereco) {
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
			return ResponseEntity.status(HttpStatus.OK).body(outrosDAO.Endereco(cliente.getId(), id_endereco));
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
	 * Inserindo Endereço
	 * 
	 * @param cookien
	 * @param request
	 * @param response
	 * @param enderecos
	 * @return
	 */
	@RequestMapping(value = Constants.ENDERECO, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Void> Inserir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request, HttpServletResponse response, @RequestBody Enderecos enderecos) {
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
			if (!new ValidarApoio().ValidarEnderecos(enderecos.getEnderecos()))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			else {
				outrosDAO.InserirEndereco(enderecos.getEnderecos(), cliente);

				response.sendRedirect(request.getContextPath() + Constants.ENDERECO);
				return ResponseEntity.status(HttpStatus.CREATED).body(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = Constants.ENDERECO_DELETE, method = RequestMethod.DELETE)
	private ResponseEntity<Void> Excluir(@CookieValue(name = "token", required = true) String cookien,
			HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = Constants.PATH_VARIABLE) long id_endereco) {
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
			if (outrosDAO.Enderecos(cliente.getId()).size() >= 1) {
				Endereco endereco = outrosDAO.Endereco(cliente.getId(), id_endereco);
				outrosDAO.ExcluirEndereco(endereco.getId());

				return ResponseEntity.status(HttpStatus.OK).body(null);
			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (EmptyException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
