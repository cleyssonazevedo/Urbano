package br.com.urbano.controller.cliente.apoio.outros;

import java.util.List;

import br.com.urbano.modelo.cliente.outros.Endereco;

public class Enderecos {
	private List<Endereco> enderecos;

	public Enderecos() {
		// TODO Auto-generated constructor stub
	}

	public Enderecos(List<Endereco> enderecos) {
		// TODO Auto-generated constructor stub
		this.enderecos = enderecos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
