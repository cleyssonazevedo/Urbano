package br.com.urbano.DAO.login;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Cookie;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWTExpiredException;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;

import br.com.urbano.exceptions.ConflictException;
import br.com.urbano.exceptions.DataNotFoundException;
import br.com.urbano.exceptions.SessionEndException;
import br.com.urbano.exceptions.UnauthorizedException;
import br.com.urbano.exceptions.UserAlreadyExistsException;
import br.com.urbano.modelo.TokenJwt;
import br.com.urbano.modelo.login.Login;

@Repository
public class DAOLogin {
	public final String SECRET = "aplicacaoUrbanno";
	public final String ISSUER = "http://www.urbano.com.br";

	@PersistenceContext
	private EntityManager manager;
	private Login login;

	@Transactional
	public Login Inserir(Login login) throws Exception {
		try {
			manager.persist(login);
			return login;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Transactional
	public Login Alterar(Login login, int tipo) throws ConflictException, Exception {
		try {
			switch (tipo) {
			case 1:
				this.login = Logon(login.getId());
				if (!(this.login.getUsuario().equals(login.getUsuario()))) {
					manager.merge(login);
				} else
					throw new ConflictException("Já existe um usuário cadastrado com este nome!");

				break;
			case 2:
				this.login = Logon(login.getId());
				if (!(this.login.getUsuario().equals(login.getUsuario()))) {
					login.setSenha(this.login.getSenha());
					manager.merge(login);
				} else
					throw new ConflictException("Já existe um usuário cadastrado com este nome!");
				break;
			case 3:
				this.login = Logon(login.getId());
				login.setUsuario(this.login.getUsuario());
				manager.merge(login);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			this.login = null;
		}

		return null;
	}

	@Transactional
	public void Excluir(Login login) throws Exception {
		try {
			login = manager.find(Login.class, login.getId());
			manager.remove(login);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	/**
	 * Pesquisa o usuario no sistema de login
	 * 
	 * @param usuario
	 * @return
	 * @throws UserAlreadyExistsException
	 * @throws DataNotFoundException
	 * @throws Exception
	 */
	public Long PesquisarUsuario(String usuario) {
		try {
			TypedQuery<Login> query = manager.createQuery("SELECT l FROM Login l WHERE usuario = :usuario",
					Login.class);
			query.setParameter("usuario", usuario);

			return query.getSingleResult().getId();
		} catch (NoResultException e) {
			// TODO: handle exception
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Método para logar no sistema, pode entrar com o login ou com o id do
	 * login
	 * 
	 * @param login
	 * @return
	 */
	public Login Logon(Object login) throws UnauthorizedException {
		if (login.getClass() == Login.class) {
			try {
				this.login = (Login) login;
				TypedQuery<Login> query = manager
						.createQuery("SELECT l FROM Login l WHERE usuario = :usuario AND senha = :senha", Login.class);
				query.setParameter("usuario", this.login.getUsuario());
				query.setParameter("senha", this.login.getSenha());

				return query.getSingleResult();
			} catch (Exception e) {
				// TODO: handle exception
				throw new UnauthorizedException(e);
			} finally {
				this.login = null;
			}
		} else if (login.getClass() == Long.class) {
			try {
				Long id = (Long) login;
				TypedQuery<Login> query = manager.createQuery("SELECT l FROM Login l WHERE id = :id", Login.class);
				query.setParameter("id", id);

				return query.getSingleResult();
			} catch (Exception e) {
				// TODO: handle exception
				throw new UnauthorizedException(e);
			}
		} else
			return null;
	}

	/**
	 * 
	 * @param cookie
	 * @return
	 * @throws SessionEndException
	 * @throws Exception
	 */
	public long ChecarCookie(Cookie cookie) throws SessionEndException, Exception {
		try {
			JWTVerifier jwtVerifier = new JWTVerifier(SECRET);
			Map<String, Object> claims = jwtVerifier.verify(cookie.getValue());

			return Long.parseLong(claims.get("id_usuario").toString());
		} catch (JWTExpiredException e) {
			// TODO: handle exception
			throw new SessionEndException();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("Cookie Inválido!", e);
		}
	}

	/**
	 * Método para gerar cookie de login
	 * 
	 * @param login
	 * @return
	 */
	public Cookie GerarCookie(Login login) throws Exception {
		try {
			JWTSigner signer = new JWTSigner(SECRET);
			HashMap<String, Object> claims = new HashMap<>();
			long iat = System.currentTimeMillis() / 1000;
			long exp = iat + 50;

			// Informações no token
			claims.put("iss", ISSUER);
			claims.put("iat", iat);
			claims.put("exp", exp);
			claims.put("id_usuario", login.getId());
			String jwt = signer.sign(claims);

			// Junção dos dados e criação do token
			TokenJwt token = new TokenJwt(jwt);

			Cookie cookie = new Cookie("token", token.getToken());
			cookie.setComment("Login da concessionária Urbano");
			// cookie.setDomain("urbano");
			cookie.setSecure(false);
			cookie.setMaxAge(6000);

			return cookie;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Erro ao Gerar cookie!", e);
		}
	}

	/**
	 * Diferente do método checar cookie, este checa se o cookie existe,
	 * 
	 * @param cookie
	 * @return
	 * @throws NullPointerException
	 * @throws SessionEndException
	 * @throws Exception
	 */
	public long ChecarCookieCompleta(Cookie cookie) throws NullPointerException, UnauthorizedException {
		try {
			return ChecarCookie(cookie);
		} catch (NullPointerException e) {
			// TODO: handle exception
			throw new NullPointerException("Este site necessita de Cookies, habilite eles!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// Cookie não existe!
			throw new UnauthorizedException("Sessão já encerrada!, faça login novamente para acessar seus dados!");
		}
	}

}
