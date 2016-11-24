package br.com.urbano.modelo.veiculo;

import br.com.urbano.modelo.veiculo.ENUM.TipoCombustivel;

public class Carro {
	public Carro() {
		// TODO Auto-generated constructor stub
	}

	public Carro(Veiculo veiculo) {
		id = veiculo.getId();
		modelo = veiculo.getModelo();
		marca = veiculo.getMarca();
		ano = veiculo.getAno();
		kilometragem = veiculo.getKilometragem();
		cor = veiculo.getCor();
		combustivel = veiculo.getCombustivel();
		finalPlaca = veiculo.getFinalPlaca();
		preco = veiculo.getPreco();
		versao = veiculo.getVersao();
		cambio = veiculo.getCambio();
		detalhes = veiculo.getDetalhes();
		numPortas = veiculo.getNumPortas();
	}

	private long id;
	private String modelo;
	private String marca;
	private String ano;
	private String kilometragem;
	private String cor;
	private TipoCombustivel combustivel;
	private String finalPlaca;
	private double preco;
	private String numPortas;
	private String versao;
	private String cambio;
	private String detalhes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getKilometragem() {
		return kilometragem;
	}

	public void setKilometragem(String kilometragem) {
		this.kilometragem = kilometragem;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public TipoCombustivel getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(TipoCombustivel combustivel) {
		this.combustivel = combustivel;
	}

	public String getFinalPlaca() {
		return finalPlaca;
	}

	public void setFinalPlaca(String finalPlaca) {
		this.finalPlaca = finalPlaca;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getNumPortas() {
		return numPortas;
	}

	public void setNumPortas(String numPortas) {
		this.numPortas = numPortas;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getCambio() {
		return cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
}
