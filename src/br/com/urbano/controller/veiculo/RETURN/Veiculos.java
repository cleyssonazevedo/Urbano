package br.com.urbano.controller.veiculo.RETURN;

import java.util.List;

import br.com.urbano.modelo.veiculo.Carro;
import br.com.urbano.modelo.veiculo.Moto;

public class Veiculos {
	private List<Carro> carros;
	private List<Moto> motos;

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public List<Moto> getMotos() {
		return motos;
	}

	public void setMotos(List<Moto> motos) {
		this.motos = motos;
	}
}