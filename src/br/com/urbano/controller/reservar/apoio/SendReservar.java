package br.com.urbano.controller.reservar.apoio;

import java.text.SimpleDateFormat;

import br.com.urbano.modelo.reserva.Reservar;
import br.com.urbano.modelo.veiculo.Veiculo;

public class SendReservar {
	private long id;
	private Veiculo veiculo;
	private String dataRegistro;
	private String dataReserva;

	public SendReservar() {
		// TODO Auto-generated constructor stub
	}

	public SendReservar(Reservar reservar) {
		// TODO Auto-generated constructor stub
		SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		this.id = reservar.getId();
		this.veiculo = reservar.getVeiculo();
		this.dataRegistro = formatData.format(reservar.getDataRegistro());
		this.dataReserva = formatData.format(reservar.getDataReserva());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(String dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(String dataReserva) {
		this.dataReserva = dataReserva;
	}

}
