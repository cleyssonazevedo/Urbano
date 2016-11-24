package br.com.urbano.modelo.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Juridico")
@OnDelete(action = OnDeleteAction.CASCADE)
@PrimaryKeyJoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_cliente_juridico"))
public class Juridico extends Cliente {
	@Column(columnDefinition = "VARCHAR(255) UNIQUE NOT NULL")
	private String nomeFantasia;
	@Column(columnDefinition = "VARCHAR(255) UNIQUE NOT NULL")
	private String razaoSocial;
	@Column(columnDefinition = "CHAR(15) UNIQUE NOT NULL")
	private String cnpj;
	@Column(columnDefinition = "CHAR(12)")
	private String ie;
	@Column(columnDefinition = "CHAR(8)")
	private String im;

	public Juridico() {
		// TODO Auto-generated constructor stub
		this.tipo = "Juridico";
	}
	
	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

}
