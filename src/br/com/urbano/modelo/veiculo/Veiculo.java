package br.com.urbano.modelo.veiculo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.urbano.modelo.veiculo.ENUM.TipoCombustivel;
import br.com.urbano.modelo.veiculo.ENUM.TipoVeiculo;

@Entity
public class Veiculo {
	public Veiculo() {
		// TODO Auto-generated constructor stub
	}

	public Veiculo(Object veiculo) {
		// TODO Auto-generated constructor stub
		if (veiculo.getClass() == Carro.class) {
			this.setTipo(TipoVeiculo.CARRO);
		} else if (veiculo.getClass() == Moto.class) {
			this.setTipo(TipoVeiculo.MOTO);
			this.numPortas = null;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition = "CHAR(17) NOT NULL UNIQUE")
	private String chassi;
	@Column(nullable = false)
	private String modelo;
	@Column(nullable = false)
	private String marca;
	@Column(nullable = false, length = 20)
	private String ano;
	@Column(nullable = false)
	private String kilometragem;
	@Column(nullable = false, length = 100)
	private String cor;
	@Column(columnDefinition = "ENUM('GASOLINA', 'ALCOOL', 'DIESEL', 'FLEX', 'ELETRICO') NOT NULL")
	@Enumerated(EnumType.STRING)
	private TipoCombustivel combustivel;

	@Column(columnDefinition = "CHAR(3) NOT NULL")
	private String finalPlaca;

	@Column(columnDefinition = "DECIMAL(11, 2) NOT NULL")
	private double preco;
	private String versao;

	@Column(columnDefinition = "ENUM ('CARRO', 'MOTO') NOT NULL")
	@Enumerated(EnumType.STRING)
	private TipoVeiculo tipo;

	@Column(columnDefinition = "CHAR(1)")
	private String numPortas;

	@Column(nullable = false)
	private String cambio;

	@Column(columnDefinition = "TEXT")
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

	public void setKilometragem(Long kilometragem) {
		this.kilometragem = kilometragem.toString();
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

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public TipoVeiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVeiculo tipo) {
		this.tipo = tipo;
	}

	public String getNumPortas() {
		return numPortas;
	}

	public void setNumPortas(String numPortas) {
		this.numPortas = numPortas;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public String getCambio() {
		return cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

}