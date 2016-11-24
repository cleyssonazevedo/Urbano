package br.com.urbano.controller.cliente.apoio;

import java.util.List;

import br.com.urbano.modelo.cliente.Cliente;
import br.com.urbano.modelo.cliente.Fisico;
import br.com.urbano.modelo.cliente.Juridico;
import br.com.urbano.modelo.cliente.outros.DadosCadastrais;
import br.com.urbano.modelo.cliente.outros.Email;
import br.com.urbano.modelo.cliente.outros.Endereco;
import br.com.urbano.modelo.cliente.outros.Telefone;
import br.com.urbano.modelo.login.Login;

public class ApoioCliente {
	private TipoCliente tipo;
	
	// Campo padrão Cliente
	private long id;
	private Login login;

	// Campos padrão Fisico
	private String nome;
	private String cpf;
	private String rg;

	// Campos padrão Juridico
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String ie;
	private String im;

	// Campos padrão Dados Cadastrais
	private List<Endereco> enderecos = null;
	private List<Email> emails = null;
	private List<Telefone> telefones = null;
	
	/**
	 * Método para transformar esta classe em Cliente Fisico
	 * 
	 * @return
	 */
	public Fisico RetornaFisico() {
		Fisico fisico = new Fisico();

		fisico.setId(id);
		fisico.setLogin(login);
		fisico.setEnderecos(enderecos);
		fisico.setEmails(emails);
		fisico.setTelefones(telefones);
		
		
		fisico.setNome(nome);
		fisico.setCpf(cpf);
		fisico.setRg(rg);

		return fisico;
	}

	/**
	 * Método para transformar esta classe em Cliente Juridico
	 * 
	 * @return
	 */
	public Juridico RetornaJuridico() {
		Juridico juridico = new Juridico();

		juridico.setId(id);
		juridico.setLogin(login);
		juridico.setEnderecos(enderecos);
		juridico.setEmails(emails);
		juridico.setTelefones(telefones);
		
		juridico.setNomeFantasia(nomeFantasia);
		juridico.setRazaoSocial(razaoSocial);
		juridico.setCnpj(cnpj);
		juridico.setIe(ie);
		juridico.setIm(im);

		return juridico;
	}

	/**
	 * Método para transformar esta classe em Cliente
	 * 
	 * @return
	 */
	public Cliente RetornaCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setLogin(login);

		return cliente;
	}

	public DadosCadastrais RetornaDadosCadastrais() {
		return new DadosCadastrais(enderecos, emails, telefones);
	}

	
	
	/* Getters e Setters */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
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

	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

}
