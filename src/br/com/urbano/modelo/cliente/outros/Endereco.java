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
import br.com.urbano.modelo.cliente.outros.ENUM.Estados;
import br.com.urbano.modelo.cliente.outros.ENUM.TipoEndereco;

@Entity
@Table(name = "Endereco")
/**
 * Tabela de Enderecos do cliente
 */
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition= "INT UNSIGNED")
	private long id;
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_enderecos"), nullable = false)
	public Cliente cliente;
	@Column(columnDefinition = "VARCHAR(255) NOT NULL")
	private String logradouro;
	@Column(columnDefinition = "VARCHAR(15) NOT NULL")
	private String numero;
	@Column(columnDefinition = "VARCHAR(255) NOT NULL")
	private String bairro;
	@Column(columnDefinition = "VARCHAR(255) NOT NULL")
	private String cidade;
	@Column(columnDefinition = "ENUM('AC', 'AL', 'AM', 'AP', 'BA', 'CE', 'DF', 'ES', "
			+ "'GO', 'MA', 'MG', 'MS', 'MT', 'PA', 'PB', 'PE', 'PI', 'PR', 'RJ', 'RN', 'RO', "
			+ "'RR', 'RS', 'SC', 'SE', 'SP', 'TO') NOT NULL")
	@Enumerated(EnumType.STRING)
	private Estados estado;
	@Column(columnDefinition = "VARCHAR(100) NOT NULL")
	private String pais;
	@Column(columnDefinition = "CHAR(8) NOT NULL")
	private String cep;
	@Column(columnDefinition = "ENUM('RESIDENCIAL', 'COMERCIAL', 'OUTRO') NOT NULL")
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;
	@Column(columnDefinition = "VARCHAR(255)")
	private String complemento;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}