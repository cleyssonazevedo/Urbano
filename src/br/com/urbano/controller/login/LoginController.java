package br.com.urbano.controller.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.urbano.DAO.login.DAOLogin;
import br.com.urbano.controller.Constants;
import br.com.urbano.exceptions.ConflictException;
import br.com.urbano.exceptions.UnauthorizedException;
import br.com.urbano.modelo.login.Login;

@RestController
public class LoginController {
	@Autowired
	private DAOLogin loginDAO;

	@ModelAttribute
	@RequestMapping(value = Constants.LOGIN, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Void> Logon(@RequestBody Login login,
			@CookieValue(name = "token", required = false) String cookien, HttpServletRequest request,
			HttpServletResponse response) {

		// Checar Login válido
		try {
			login = loginDAO.Logon(login);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		// Apagar cookies antigos, caso tenha
		try {
			for (Cookie cookie : request.getCookies()) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Gerar Token e Cookie e Enviar o redirecionamento
		try {
			Cookie cookie = loginDAO.GerarCookie(login);

			response.addCookie(cookie);
			response.sendRedirect(request.getContextPath() + Constants.CLIENTE);
		} catch (Exception e) {
			// TODO: Erro ao gerar o cookie
			return new ResponseEntity<>(HttpStatus.PRECONDITION_REQUIRED);
		}

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@RequestMapping(value = Constants.LOGIN, method = RequestMethod.OPTIONS)
	private ResponseEntity<Void> Logout(HttpServletResponse response, HttpServletRequest request) {
		try {
			for (Cookie cookie : request.getCookies()) {
				cookie.setMaxAge(0);

				response.addCookie(cookie);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok().body(null);
	}

	@RequestMapping(value = Constants.LOGIN, method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Void> ExcluirConta(@RequestBody Login login, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			login = loginDAO.Logon(login);
		} catch (UnauthorizedException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		try {
			loginDAO.Excluir(login);
			// Limpando cookies na máquina
			try {
				for (Cookie cookie : request.getCookies()) {
					cookie.setMaxAge(0);

					response.addCookie(cookie);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = Constants.LOGIN, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Void> Alterar(@RequestBody Login login,
			@CookieValue(value = "token", required = true, defaultValue = "null") String cookien,
			HttpServletResponse response, HttpServletRequest request) {

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

		// Validar e Alterar
		try {
			if (login != null) {
				if (login.getUsuario() != null && !(login.getUsuario().trim()).isEmpty() && login.getSenha() != null
						&& !(login.getSenha().trim()).isEmpty()) {
					// Altera Usuário e senha
					login.setId(id_login);
					loginDAO.Alterar(login, 1);
				} else if (login.getUsuario() != null && !(login.getUsuario().trim()).isEmpty()) {
					// Alterar Usuário
					login.setId(id_login);
					loginDAO.Alterar(login, 2);
				} else if (login.getSenha() != null && !(login.getSenha().trim()).isEmpty()) {
					// Altera Senha
					login.setId(id_login);
					loginDAO.Alterar(login, 3);
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (ConflictException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}