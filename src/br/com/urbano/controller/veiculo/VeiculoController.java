package br.com.urbano.controller.veiculo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.urbano.DAO.veiculo.DAOVeiculo;
import br.com.urbano.controller.veiculo.RETURN.Veiculos;
import br.com.urbano.controller.veiculo.apoio.Filtro;

@RestController
public class VeiculoController {
	private static final String URL = "/veiculo";
	@Autowired
	private DAOVeiculo veiculoDAO;

	@RequestMapping(value = URL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Veiculos> Exibir(HttpServletRequest request, HttpServletResponse response) {
		try {
			Veiculos veiculos = veiculoDAO.Listar();

			if (veiculos.getCarros() == null && veiculos.getMotos() == null)
				throw new Exception();
			else
				return ResponseEntity.ok().body(veiculos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}

	@RequestMapping(value = URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Object> ExibirComFiltro(HttpServletRequest request,
			HttpServletResponse response, @RequestBody Filtro filtro) {
		try {
			return ResponseEntity.ok().body(veiculoDAO.Listar(filtro));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
