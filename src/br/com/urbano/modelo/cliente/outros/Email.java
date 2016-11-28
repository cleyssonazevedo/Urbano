package br.com.urbano.modelo.cliente.outros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.urbano.modelo.cliente.Cliente;
import br.com.urbano.modelo.cliente.outros.ENUM.TipoEmail;

@Entity
@Table(name = "Email")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED")
	private long id;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_email"), nullable = false)
	private Cliente cliente;
	@Column(columnDefinition = "VARCHAR(255) NOT NULL")
	private String email;
	@Column(columnDefinition = "ENUM('PESSOAL', 'COMERCIAL', 'OUTROS') NOT NULL")
	@Enumerated(EnumType.STRING)
	private TipoEmail tipoEmail;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoEmail getTipoEmail() {
		return tipoEmail;
	}

	public void setTipoEmail(TipoEmail tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
