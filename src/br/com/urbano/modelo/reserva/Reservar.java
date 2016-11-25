package br.com.urbano.modelo.reserva;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.urbano.modelo.cliente.Cliente;
import br.com.urbano.modelo.veiculo.Veiculo;

@Entity
@Table(name = "Reservar", uniqueConstraints = @UniqueConstraint(columnNames = { "id_veiculo",
"id_cliente" }, name = "ClienteAndVeiculo"))
public class Reservar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_veiculo", foreignKey = @ForeignKey(name = "fk_reserva_veiculo"), nullable = false)
	private Veiculo veiculo;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_reserva_cliente"), nullable = false)
	private Cliente cliente;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataRegistro;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataReserva;

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

}
