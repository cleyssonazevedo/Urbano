package br.com.urbano.DAO.cliente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.urbano.exceptions.DataNotFoundException;
import br.com.urbano.modelo.cliente.Cliente;
import br.com.urbano.modelo.cliente.outros.Email;
import br.com.urbano.modelo.cliente.outros.Endereco;
import br.com.urbano.modelo.cliente.outros.Telefone;

/**
 * Inserir e Excluir dados do cliente como o Endereco, Email e Telefone
 * 
 * @author Cleysson Azevedo
 */
@Repository
public class DAOOutros {
	@PersistenceContext
	private EntityManager manager;

	/* Comandos de Endereço */

	@Transactional
	public void InserirEndereco(List<Endereco> enderecos, Cliente cliente) {
		try {
			if (enderecos != null)
				for (Endereco endereco : enderecos) {
					endereco.setCliente(cliente);
					manager.persist(endereco);
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Transactional
	public void ExcluirEndereco(long id) {
		manager.remove(manager.find(Endereco.class, id));
	}

	public List<Endereco> Enderecos(long id_cliente) {
		try {
			TypedQuery<Endereco> query = manager.createQuery("SELECT e FROM Endereco as e WHERE id_cliente = :id",
					Endereco.class);
			query.setParameter("id", id_cliente);

			List<Endereco> lista = query.getResultList();
			int cont = 0;
			for (Endereco endereco : lista) {
				endereco.setCliente(null);
				lista.set(cont, endereco);
				cont++;
			}

			return lista;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	/**
	 * 
	 * @param id_cliente
	 * @param id_endereco
	 * @return
	 * @throws DataNotFoundException
	 */
	public Endereco Endereco(long id_cliente, long id_endereco) throws DataNotFoundException {
		try {
			TypedQuery<Endereco> query = manager.createQuery(
					"SELECT e FROM Endereco e WHERE id_cliente = :id_cliente AND id = :id", Endereco.class);
			query.setParameter("id", id_endereco);
			query.setParameter("id_cliente", id_cliente);
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			throw new DataNotFoundException("Endereço não encontrado no seu cadastro!");
		}
	}

	/* Comandos para Email */

	@Transactional
	public void InserirEmail(List<Email> emails, Cliente cliente) throws Exception {
		try {
			if (emails != null)
				for (Email email : emails) {
					email.setCliente(cliente);
					manager.persist(email);
				}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Transactional
	public void ExcluirEmail(long id) {
		manager.remove(manager.find(Email.class, id));
	}

	public List<Email> Emails(long id_cliente) {
		try {
			TypedQuery<Email> query = manager.createQuery("SELECT e FROM Email as e WHERE id_cliente = :id",
					Email.class);
			query.setParameter("id", id_cliente);

			List<Email> lista = query.getResultList();
			int cont = 0;
			for (Email email : lista) {
				email.setCliente(null);
				lista.set(cont, email);
				cont++;
			}

			return lista;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public Email Email(long id_cliente, long id_email) throws DataNotFoundException {
		try {
			TypedQuery<Email> query = manager
					.createQuery("SELECT e FROM Email e WHERE id_cliente = :id_cliente AND id = :id", Email.class);
			query.setParameter("id", id_email);
			query.setParameter("id_cliente", id_cliente);
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			throw new DataNotFoundException("Email não encontrado no seu cadastro!");
		}
	}

	/* Comandos para Telefone */

	@Transactional
	public void InserirTelefone(List<Telefone> telefones, Cliente cliente) throws Exception {
		try {
			if (telefones != null)
				for (Telefone telefone : telefones) {
					telefone.setCliente(cliente);
					manager.persist(telefone);
				}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@Transactional
	public void ExcluirTelefone(long id) {
		manager.remove(manager.find(Telefone.class, id));
	}

	public List<Telefone> Telefones(long id_cliente) {
		try {
			TypedQuery<Telefone> query = manager.createQuery("SELECT e FROM Telefone as e WHERE id_cliente = :id",
					Telefone.class);
			query.setParameter("id", id_cliente);

			List<Telefone> lista = query.getResultList();
			int cont = 0;
			for (Telefone telefone : lista) {
				telefone.setCliente(null);
				lista.set(cont, telefone);
				cont++;
			}

			return lista;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public Telefone Telefone(long id_cliente, long id_telefone) throws DataNotFoundException{
		try {
			TypedQuery<Telefone> query = manager.createQuery(
					"SELECT e FROM Telefone e WHERE id_cliente = :id_cliente AND id = :id", Telefone.class);
			query.setParameter("id", id_telefone);
			query.setParameter("id_cliente", id_cliente);
			return query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			throw new DataNotFoundException("Telefone não encontrado com o seu cadastro!");
		}
	}
}
