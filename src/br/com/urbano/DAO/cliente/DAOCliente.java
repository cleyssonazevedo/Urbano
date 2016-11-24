package br.com.urbano.DAO.cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.urbano.modelo.cliente.Fisico;
import br.com.urbano.modelo.cliente.Juridico;

@Repository
public class DAOCliente {
	@PersistenceContext
	private EntityManager manager;

	private Fisico fisico;
	private Juridico juridico;

	/**
	 * Método para visualizar um cliente de acordo com o seu id de login
	 * 
	 * @param id_login
	 * @return
	 * @throws Exception
	 */
	public Object Exibir(long id_login) throws Exception {
		try {
			TypedQuery<Object> query = manager
					.createQuery("SELECT cliente FROM Cliente as cliente WHERE id_login = :id", Object.class);
			query.setParameter("id", id_login);
			Object result = query.getSingleResult();

			if (result.getClass() == Fisico.class) {
				Fisico fisico = (Fisico) result;
				fisico.setLogin(null);

				return fisico;
			} else if (result.getClass() == Juridico.class) {
				Juridico juridico = (Juridico) result;
				juridico.setLogin(null);

				return juridico;
			} else
				throw new Exception();

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	/**
	 * Validação de dados, para ver se não existe o registro na tabela de
	 * cliente
	 * 
	 * @param cliente
	 * @return se False = Dados não Clonados, se True = Dados Clonados!
	 */
	public boolean ValidarCliente(Object cliente) {
		return false;
	}

	/**
	 * Inserção de Cliente dentro do banco de dados
	 * 
	 * @param cliente Classes Fisico ou Juridico
	 * @throws Exception
	 */
	@Transactional
	public void Inserir(Object cliente) throws Exception {
		if (cliente.getClass() == Fisico.class) {
			this.fisico = (Fisico) cliente;
			try {
				fisico.setEnderecos(null);
				fisico.setTelefones(null);
				fisico.setEmails(null);

				manager.persist(fisico);
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			} finally {
				fisico = null;
			}

		} else if (cliente.getClass() == Juridico.class) {
			this.juridico = (Juridico) cliente;
			try {
				juridico.setEnderecos(null);
				juridico.setTelefones(null);
				juridico.setEmails(null);

				manager.persist(juridico);
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			} finally {
				juridico = null;
			}
		} else
			throw new Exception("Objeto Inválido!");
	}
}
