package br.com.urbano.modelo.cliente.outros;

import java.util.List;

public class DadosCadastrais {
	private List<Endereco> enderecos;
	private List<Email> emails;
	private List<Telefone> telefones;

	public DadosCadastrais() {
		// TODO Auto-generated constructor stub
	}

	public DadosCadastrais(List<Endereco> enderecos, List<Email> emails, List<Telefone> telefones) {
		// TODO Auto-generated constructor stub
		this.enderecos = enderecos;
		this.emails = emails;
		this.telefones = telefones;
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
