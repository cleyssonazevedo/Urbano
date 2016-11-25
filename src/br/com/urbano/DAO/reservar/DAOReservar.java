package br.com.urbano.DAO.reservar;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.urbano.exceptions.ConflictException;
import br.com.urbano.exceptions.EmptyException;
import br.com.urbano.modelo.reserva.Reservar;
import br.com.urbano.modelo.reserva.Reservas;

@Repository
public class DAOReservar {
	@PersistenceContext
	private EntityManager manager;

	/**
	 * Inserir reserva do cliente
	 * 
	 * @param reservar
	 * @return
	 */
	@Transactional
	public Reservar inserir(Reservar reservar) throws ConflictException, Exception {
		try {
			if (reservar.getDataRegistro().before(reservar.getDataReserva())) {
				manager.persist(reservar);
				return reservar;
			} else
				throw new ConflictException("Não pode inserir com data de reserva menor que a data de registro");
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	/**
	 * Excluir Reserva do cliente
	 * 
	 * @param reservar
	 * @throws EmptyException
	 * @throws Exception
	 */
	@Transactional
	public void Excluir(Reservar reservar) throws EmptyException, Exception {
		try {
			TypedQuery<Reservar> query = manager.createQuery(
					"SELECT r FROM Reservar r WHERE ID = :id AND id_cliente = :id_cliente", Reservar.class);
			query.setParameter("id", reservar.getId());
			query.setParameter("id_cliente", reservar.getCliente().getId());

			reservar = query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			throw new EmptyException("Reserva não encontrada!", e);
		}

		try {
			manager.remove(reservar);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Erro na hora de excluir", e);
		}

	}

	/**
	 * Listar todos as reservas de um cliente
	 * 
	 * @param id_cliente
	 * @return
	 * @throws EmptyException
	 */
	public Reservas Listar(long id_cliente) throws EmptyException {
		try {
			TypedQuery<Reservar> query = manager.createQuery("SELECT r FROM Reservar r WHERE id_cliente = :id_cliente",
					Reservar.class);
			query.setParameter("id_cliente", id_cliente);

			List<Reservar> reservas = query.getResultList();
			int cont = 0;
			for (Reservar reservar : reservas) {
				reservar.setCliente(null);

				reservas.set(cont, reservar);
				cont++;
			}

			return new Reservas(reservas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new EmptyException();
		}
	}

	/**
	 * Listar somente uma reserva do cliente
	 * 
	 * @param id_cliente
	 * @param id_reserva
	 * @return
	 * @throws EmptyException
	 */
	public Reservar Listar(long id_cliente, long id_reserva) throws EmptyException {
		try {
			TypedQuery<Reservar> query = manager.createQuery(
					"SELECT r FROM Reservar r WHERE id_cliente = :id_cliente AND id = :id", Reservar.class);
			query.setParameter("id_cliente", id_cliente);
			query.setParameter("id", id_reserva);

			return query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			throw new EmptyException();
		}
	}

	/**
	 * Procura se o veiculo foi reservado
	 * 
	 * @param reserva
	 * @return
	 * @throws EmptyException
	 */
	public boolean isReserved(Reservar reserva) {
		try {
			TypedQuery<Reservar> query = manager.createQuery("SELECT r FROM Reservar r WHERE id_cliente = :id_cliente",
					Reservar.class);
			query.setParameter("id_cliente", reserva.getCliente().getId());

			for (Reservar reservar : query.getResultList()) {
				if (reservar.getVeiculo().getId() == reserva.getVeiculo().getId())
					return true;
			}

			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
