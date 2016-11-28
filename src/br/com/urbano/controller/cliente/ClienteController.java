package br.com.urbano.controller.cliente;

import java.net.URI;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.urbano.DAO.cliente.DAOCliente;
import br.com.urbano.DAO.cliente.DAOOutros;
import br.com.urbano.DAO.login.DAOLogin;
import br.com.urbano.controller.Constants;
import br.com.urbano.controller.cliente.apoio.ApoioCliente;
import br.com.urbano.controller.cliente.apoio.TipoCliente;
import br.com.urbano.controller.cliente.apoio.ValidarApoio;
import br.com.urbano.exceptions.ConflictException;
import br.com.urbano.exceptions.UnauthorizedException;
import br.com.urbano.exceptions.UserAlreadyExistsException;
import br.com.urbano.modelo.cliente.Fisico;
import br.com.urbano.modelo.cliente.Juridico;

@RestController
public class ClienteController {
	@Autowired
	private DAOCliente clienteDAO;

	@Autowired
	private DAOLogin loginDAO;

	@Autowired
	private DAOOutros outrosDAO;

	private Fisico fisico;
	private Juridico juridico;

	@RequestMapping(value = Constants.CLIENTE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Object> Exibir(HttpServletRequest request, HttpServletResponse response,
			@CookieValue(name = "token", required = true, defaultValue = "null") String cookien) {
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

		// Operações de requisição dos dados do cliente
		try {
			Object cliente = clienteDAO.Exibir(id_login);
			if (cliente.getClass() == Fisico.class) {
				fisico = (Fisico) cliente;

				fisico.setEnderecos(outrosDAO.Enderecos(fisico.getId()));
				fisico.setEmails(outrosDAO.Emails(fisico.getId()));
				fisico.setTelefones(outrosDAO.Telefones(fisico.getId()));

				cliente = fisico;
			} else {
				juridico = (Juridico) cliente;

				juridico.setEnderecos(outrosDAO.Enderecos(juridico.getId()));
				juridico.setEmails(outrosDAO.Emails(juridico.getId()));
				juridico.setTelefones(outrosDAO.Telefones(juridico.getId()));

				cliente = juridico;
			}

			return ResponseEntity.status(HttpStatus.OK).body(cliente);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			fisico = null;
			juridico = null;
		}
	}

	@RequestMapping(value = Constants.CLIENTE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Void> Inserir(@RequestBody ApoioCliente cliente,
			@CookieValue(name = "token", required = false) String cookien, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (cliente.getTipo().equals(TipoCliente.FISICO)) {
				fisico = cliente.RetornaFisico();
				// Validação dos dados para ver se não estão em branco!

				if (new ValidarApoio().Validar(fisico)) {
					// Inserção e Checagem final de login
					try {
						if ((loginDAO.PesquisarUsuario(fisico.getLogin().getUsuario())) == null) {
							if (clienteDAO.ValidarCliente(fisico) == false) {
								fisico.setLogin(loginDAO.Inserir(fisico.getLogin()));
								clienteDAO.Inserir(fisico);

								outrosDAO.InserirEndereco(cliente.getEnderecos(), fisico);
								outrosDAO.InserirTelefone(cliente.getTelefones(), fisico);
								outrosDAO.InserirEmail(cliente.getEmails(), fisico);

								URI location = new URI(Constants.CLIENTE + fisico.getId());

								Cookie cookie = loginDAO.GerarCookie(fisico.getLogin());
								response.addCookie(cookie);
								response.sendRedirect(request.getContextPath() + Constants.CLIENTE);
								return ResponseEntity.created(location).body(null);
							} else
								throw new UserAlreadyExistsException();
						} else
							return new ResponseEntity<>(HttpStatus.CONFLICT);
					} catch (UserAlreadyExistsException e) {
						// TODO: handle exception
						return new ResponseEntity<>(HttpStatus.CONFLICT);
					} catch (ConflictException e) {
						// TODO: handle exception
						return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			} else if (cliente.getTipo().equals(TipoCliente.JURIDICO)) {
				juridico = cliente.RetornaJuridico();
				// Validação dos dados para ver se não estão em branco!

				if (new ValidarApoio().Validar(juridico)) {
					// Validação para ver se dados não dão conflito
					try {
						if (loginDAO.PesquisarUsuario(juridico.getLogin().getUsuario()) != null) {
							if (clienteDAO.ValidarCliente(juridico) == false) {
								fisico.setLogin(loginDAO.Inserir(juridico.getLogin()));
								clienteDAO.Inserir(juridico);

								outrosDAO.InserirEndereco(cliente.getEnderecos(), juridico);
								outrosDAO.InserirTelefone(cliente.getTelefones(), juridico);
								outrosDAO.InserirEmail(cliente.getEmails(), juridico);

								URI location = new URI(Constants.CLIENTE + juridico.getId());

								Cookie cookie = loginDAO.GerarCookie(juridico.getLogin());
								response.addCookie(cookie);
								response.sendRedirect(request.getContextPath() + Constants.CLIENTE);
								return ResponseEntity.created(location).body(null);
							} else
								throw new UserAlreadyExistsException();
						} else
							return new ResponseEntity<>(HttpStatus.CONFLICT);
					} catch (UserAlreadyExistsException e) {
						// TODO: handle exception
						return new ResponseEntity<>(HttpStatus.CONFLICT);
					} catch (ConflictException e) {
						// TODO: handle exception
						return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else {
					return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
				}
			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}