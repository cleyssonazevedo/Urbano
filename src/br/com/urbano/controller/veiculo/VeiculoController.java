package br.com.urbano.controller.veiculo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.urbano.DAO.veiculo.DAOVeiculo;
import br.com.urbano.controller.Constants;
import br.com.urbano.controller.veiculo.RETURN.Veiculos;
import br.com.urbano.controller.veiculo.apoio.Filtro;
import br.com.urbano.modelo.veiculo.Veiculo;

@RestController
public class VeiculoController {
	@Autowired
	private DAOVeiculo veiculoDAO;

	@RequestMapping(value = Constants.VEICULO, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Veiculos> Exibir(HttpServletRequest request, HttpServletResponse response) {
		try {
			Veiculos veiculos = veiculoDAO.Listar();

			if (veiculos.getCarros() == null && veiculos.getMotos() == null)
				throw new Exception();
			else
				return ResponseEntity.ok().body(veiculos);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = Constants.VEICULO, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Veiculos> ExibirComFiltro(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Filtro filtro) {
		try {
			Veiculos veiculos = veiculoDAO.Listar(filtro);
			if (veiculos.getCarros() != null && veiculos.getMotos() != null)
				return ResponseEntity.ok().body(veiculos);
			else
				throw new Exception("Nenhum veículo encontrado no sistema!");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = Constants.VEICULO_GET, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Veiculo> ExibirUm(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = Constants.PATH_VARIABLE) long id_veiculo) {
		try {
			return ResponseEntity.ok().body(veiculoDAO.Buscar(id_veiculo));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
