package br.com.urbano.controller.cliente.apoio.outros;

import java.util.List;

import br.com.urbano.modelo.cliente.outros.Telefone;

public class Telefones {
	private List<Telefone> telefones;

	public Telefones() {
		// TODO Auto-generated constructor stub
	}

	public Telefones(List<Telefone> telefones) {
		// TODO Auto-generated constructor stub
		this.telefones = telefones;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

}
