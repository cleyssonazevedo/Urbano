package br.com.urbano.controller.reservar.apoio;

import java.util.ArrayList;
import java.util.List;

import br.com.urbano.modelo.reserva.Reservar;
import br.com.urbano.modelo.reserva.Reservas;

public class SendReservas {
	private List<SendReservar> reservas;

	public SendReservas() {
		// TODO Auto-generated constructor stub
	}
	
	public SendReservas(Reservas reservas) {
		// TODO Auto-generated constructor stub
		List<SendReservar> items = new ArrayList<SendReservar>();
		for (Reservar reservar : reservas.getReservas()) {
			items.add(new SendReservar(reservar));
		}
		
		this.reservas = items;
	}
	
	public List<SendReservar> getReservas() {
		return reservas;
	}

	public void setReservas(List<SendReservar> reservas) {
		this.reservas = reservas;
	}
}