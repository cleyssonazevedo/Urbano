package br.com.urbano.DAO.veiculo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.urbano.controller.veiculo.ENUM.TiposFiltro;
import br.com.urbano.controller.veiculo.RETURN.Veiculos;
import br.com.urbano.controller.veiculo.apoio.Filtro;
import br.com.urbano.modelo.veiculo.Carro;
import br.com.urbano.modelo.veiculo.Moto;
import br.com.urbano.modelo.veiculo.Veiculo;
import br.com.urbano.modelo.veiculo.ENUM.TipoVeiculo;

@Repository
public class DAOVeiculo {
	@PersistenceContext
	private EntityManager manager;

	public Veiculos Listar() {
		Veiculos veiculos = new Veiculos();
		try {
			TypedQuery<Veiculo> query = manager.createQuery("SELECT v FROM Veiculo v WHERE tipo = :tipo GROUP BY ID",
					Veiculo.class);
			query.setParameter("tipo", TipoVeiculo.CARRO);
			List<Veiculo> lista = query.getResultList();
			List<Carro> carros = new ArrayList<Carro>();
			for (Veiculo veiculo : lista) {
				veiculo.setChassi(null);
				carros.add(new Carro(veiculo));
			}

			if (!carros.isEmpty())
				veiculos.setCarros(carros);
			else
				veiculos.setCarros(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			veiculos.setCarros(null);
		}

		try {
			TypedQuery<Veiculo> query = manager.createQuery("SELECT v FROM Veiculo v WHERE tipo = :tipo  GROUP BY ID",
					Veiculo.class);
			query.setParameter("tipo", TipoVeiculo.MOTO);
			List<Veiculo> lista = query.getResultList();
			List<Moto> motos = new ArrayList<Moto>();

			for (Veiculo veiculo : lista) {
				veiculo.setChassi(null);
				motos.add(new Moto(veiculo));
			}

			if (!motos.isEmpty())
				veiculos.setMotos(motos);
			else
				veiculos.setMotos(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			veiculos.setMotos(null);
		}

		return veiculos;
	}

	public Veiculos Listar(Filtro filtro) throws Exception {

		Veiculos veiculos = new Veiculos();
		try {
			TypedQuery<Veiculo> query = manager.createQuery(
					"SELECT v FROM Veiculo v WHERE tipo = :tipo GROUP BY " + filtro.getTipo().toString() + ",ID ",
					Veiculo.class);
			query.setParameter("tipo", TipoVeiculo.CARRO);
			List<Veiculo> lista = query.getResultList();
			List<Carro> carros = new ArrayList<Carro>();
			for (Veiculo veiculo : lista) {
				veiculo.setChassi(null);
				carros.add(new Carro(veiculo));
			}

			if (!carros.isEmpty())
				veiculos.setCarros(carros);
			else
				veiculos.setCarros(null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			veiculos.setCarros(null);
		}

		if (filtro.getTipo() != TiposFiltro.NUMPORTAS) {
			try {
				TypedQuery<Veiculo> query = manager.createQuery(
						"SELECT v FROM Veiculo v WHERE tipo = :tipo  GROUP BY " + filtro.getTipo().toString() + ",ID",
						Veiculo.class);
				query.setParameter("tipo", TipoVeiculo.MOTO);
				List<Veiculo> lista = query.getResultList();
				List<Moto> motos = new ArrayList<Moto>();

				for (Veiculo veiculo : lista) {
					veiculo.setChassi(null);
					motos.add(new Moto(veiculo));
				}

				if (!motos.isEmpty())
					veiculos.setMotos(motos);
				else
					veiculos.setMotos(null);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				veiculos.setMotos(null);
			}
		} else
			veiculos.setMotos(null);

		return veiculos;
	}
}
