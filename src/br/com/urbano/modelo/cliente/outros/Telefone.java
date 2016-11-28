package br.com.urbano.modelo.cliente.outros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import br.com.urbano.modelo.cliente.outros.ENUM.TiposTelefones;

@Entity
@Table(name = "Telefone")
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "INT UNSIGNED")
	private long id;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_telefone"), nullable = false)
	private Cliente cliente;
	@Column(columnDefinition = "CHAR(4) NOT NULL DEFAULT '0055'")
	private String ddi;
	@Column(columnDefinition = "CHAR(2) NOT NULL")
	private String ddd;
	@Column(columnDefinition = "VARCHAR(25) NOT NULL")
	private String numero;
	@Column(columnDefinition = "ENUM('FIXO', 'CELULAR', 'COMERCIAL', 'FAX') NOT NULL")
	@Enumerated(EnumType.STRING)
	private TiposTelefones tipoTelefone;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDdi() {
		return ddi;
	}

	public void setDdi(String ddi) {
		this.ddi = ddi;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TiposTelefones getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TiposTelefones tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}