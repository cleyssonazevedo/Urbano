package br.com.urbano.modelo.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Fisico")
@OnDelete(action = OnDeleteAction.CASCADE)
@PrimaryKeyJoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_cliente_fisico"))
public class Fisico extends Cliente {
	@Column(columnDefinition = "VARCHAR(255) NOT NULL")
	private String nome;
	@Column(columnDefinition = "CHAR(11) UNIQUE NOT NULL")
	private String cpf;
	@Column(columnDefinition = "VARCHAR(20)")
	private String rg;

	public Fisico() {
		// TODO Auto-generated constructor stub
		this.tipo = "Fisico";
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

}
