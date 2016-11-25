package br.com.urbano.modelo.reserva;

import java.util.List;

public class Reservas {
	private List<Reservar> reservas;

	public Reservas() {
		// TODO Auto-generated constructor stub
	}
	
	public Reservas(List<Reservar> reservas) {
		// TODO Auto-generated constructor stub
		this.reservas = reservas;
	}
	
	public List<Reservar> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reservar> reservas) {
		this.reservas = reservas;
	}

}
