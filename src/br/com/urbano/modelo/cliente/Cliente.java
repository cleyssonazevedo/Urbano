package br.com.urbano.modelo.cliente;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.urbano.modelo.cliente.outros.Email;
import br.com.urbano.modelo.cliente.outros.Endereco;
import br.com.urbano.modelo.cliente.outros.Telefone;
import br.com.urbano.modelo.login.Login;

@Entity
@Table(name = "Cliente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED")
	private long id;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_login", foreignKey = @ForeignKey(name = "fk_login"), columnDefinition = "INT UNSIGNED NOT NULL UNIQUE")
	private Login login = null;
	
	@Column(columnDefinition = "ENUM('Fisico', 'Juridico') NOT NULL")
	protected String tipo;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(targetEntity = Endereco.class, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = null;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(targetEntity = Email.class, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Email> emails = null;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(targetEntity = Telefone.class, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Telefone> telefones = null;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

}
