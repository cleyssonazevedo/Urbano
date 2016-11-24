package br.com.urbano.modelo.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe para login, ainda não tem a criptografia de usuario e senha
 */
@Entity
@Table(name = "Login")
public class Login {
	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED")
	private long id;
	@Column(columnDefinition = "VARCHAR(255) BINARY UNIQUE NOT NULL")
	private String usuario;
	@Column(columnDefinition = "VARCHAR(255) BINARY NOT NULL")
	private String senha;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}