package br.com.urbano.controller.cliente.apoio.outros;

import java.util.List;

import br.com.urbano.modelo.cliente.outros.Email;

public class Emails {
	private List<Email> emails;

	public Emails() {
		// TODO Auto-generated constructor stub
	}

	public Emails(List<Email> emails) {
		// TODO Auto-generated constructor stub
		this.emails = emails;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

}
