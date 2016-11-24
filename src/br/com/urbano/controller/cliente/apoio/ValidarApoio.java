package br.com.urbano.controller.cliente.apoio;

import java.util.List;

import br.com.urbano.modelo.cliente.Cliente;
import br.com.urbano.modelo.cliente.Fisico;
import br.com.urbano.modelo.cliente.Juridico;
import br.com.urbano.modelo.cliente.outros.Email;
import br.com.urbano.modelo.cliente.outros.Endereco;
import br.com.urbano.modelo.cliente.outros.Telefone;
import br.com.urbano.modelo.login.Login;

public class ValidarApoio {
	public boolean ValidarFisico(Fisico fisico) {
		if (fisico.getNome() == null || (fisico.getNome().trim()).isEmpty() || fisico.getNome().length() > 255)
			return false;

		if (fisico.getCpf() == null || (fisico.getCpf().trim()).isEmpty() || fisico.getCpf().length() != 11)
			return false;

		if (fisico.getRg() != null)
			if (fisico.getRg().length() > 20)
				return false;

		return true;
	}

	public boolean ValidarJuridico(Juridico juridico) {
		if (juridico.getNomeFantasia() == null || (juridico.getNomeFantasia().trim()).isEmpty()
				|| juridico.getNomeFantasia().length() > 255)
			return false;

		if (juridico.getRazaoSocial() == null || (juridico.getRazaoSocial().trim()).isEmpty()
				|| juridico.getRazaoSocial().length() > 255)
			return false;

		if (juridico.getCnpj() == null || (juridico.getCnpj().trim()).isEmpty() || juridico.getCnpj().length() != 15)
			return false;

		if (juridico.getIe() != null)
			if (juridico.getIe().length() != 12)
				return false;

		if (juridico.getIm() != null)
			if (juridico.getIm().length() != 8)
				return false;

		return true;
	}

	public boolean ValidarEnderecos(List<Endereco> enderecos) {
		if (enderecos == null)
			return false;
		else if (enderecos.size() >= 1)
			for (Endereco endereco : enderecos) {
				if (endereco.getLogradouro() == null || (endereco.getLogradouro().trim()).isEmpty()
						|| endereco.getLogradouro().length() > 255)
					return false;

				if (endereco.getNumero() == null || (endereco.getNumero().trim()).isEmpty()
						|| endereco.getNumero().length() > 15)
					return false;

				if (endereco.getBairro() == null || (endereco.getBairro().trim()).isEmpty()
						|| endereco.getBairro().length() > 255)
					return false;

				if (endereco.getCidade() == null || (endereco.getCidade().trim()).isEmpty()
						|| endereco.getCidade().length() > 255)
					return false;

				if (endereco.getEstado() == null)
					return false;

				if (endereco.getPais() == null || (endereco.getPais().trim()).isEmpty()
						|| endereco.getPais().length() > 100)
					return false;

				if (endereco.getCep() == null || (endereco.getCep().trim()).isEmpty()
						|| endereco.getCep().length() != 8)
					return false;

				if (endereco.getTipoEndereco() == null)
					return false;

				if (endereco.getComplemento() != null)
					if (endereco.getComplemento().length() > 255)
						return false;
			}

		return true;
	}

	public boolean ValidarLogin(Login login) {
		if (login == null)
			return false;
		else {
			if (login.getUsuario() == null || (login.getUsuario().trim()).isEmpty()
					|| login.getUsuario().length() > 255)
				return false;
			if (login.getSenha() == null || (login.getSenha().trim()).isEmpty() || login.getSenha().length() > 255)
				return false;

			return true;
		}
	}

	public boolean ValidarEmails(List<Email> emails) {
		if (emails != null)
			for (Email email : emails) {
				if (email.getEmail() == null || (email.getEmail().trim()).isEmpty() || email.getEmail().length() > 255)
					return false;

				if (email.getTipoEmail() == null)
					return false;
			}

		return true;
	}

	public boolean ValidarTelefones(List<Telefone> telefones) {
		if (telefones != null)
			for (Telefone telefone : telefones) {
				if (telefone.getDdi() == null || (telefone.getDdi().trim()).isEmpty()
						|| telefone.getDdi().length() != 4)
					return false;
				if (telefone.getDdd() == null || (telefone.getDdd().trim()).isEmpty()
						|| telefone.getDdd().length() != 2)
					return false;

				if (telefone.getNumero() == null || (telefone.getNumero().trim()).isEmpty()
						|| telefone.getNumero().length() > 25)
					return false;

				if (telefone.getTipoTelefone() == null)
					return false;
			}

		return true;
	}

	/**
	 * Método para validar dados do cliente
	 * 
	 * @param clienteNaoValidado
	 * @return
	 */
	public boolean Validar(Object clienteNaoValidado) {
		Cliente cliente = (Cliente) clienteNaoValidado;

		boolean valCliente = false;
		if (clienteNaoValidado.getClass() == Fisico.class) {
			Fisico fisico = (Fisico) clienteNaoValidado;
			valCliente = ValidarFisico(fisico);
		} else if (clienteNaoValidado.getClass() == Juridico.class) {
			Juridico juridico = (Juridico) clienteNaoValidado;
			valCliente = ValidarJuridico(juridico);
		}

		boolean valLogin = ValidarLogin(cliente.getLogin());
		boolean valEndereco = ValidarEnderecos(cliente.getEnderecos());
		boolean valTelefone = ValidarTelefones(cliente.getTelefones());
		boolean valEmail = ValidarEmails(cliente.getEmails());

		if (valCliente && valLogin && valEndereco && valTelefone && valEmail)
			return true;
		else
			return false;
	}
}
