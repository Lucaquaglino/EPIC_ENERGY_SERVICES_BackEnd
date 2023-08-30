package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

	@Autowired
	ClienteService cs;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvaCliente(@RequestBody ClientePayload body) {

		Cliente clienteCreato = cs.creaCliente(body);

		return clienteCreato;
	}

	//--------------------------------------------------------------------------- ordinamenti
	@GetMapping("")
	public Page<Cliente> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "ragioneSociale") String order
	) {
		return cs.findAll(page, order);
	}

	//--------------------------------------------------------------------------- ordinamenti
	@GetMapping("/filter/fatturatoAnnuale")
	public Page<Cliente> filterFattuartoAnnuale(
            @RequestParam double fatturatoAnnuale,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return cs.filterFatturatoAnnuale(fatturatoAnnuale, page, pageSize);
    }

	@GetMapping("/{dataInserimento}")
	public ResponseEntity<List<Cliente>> getFiltroDataInserimento(@PathVariable LocalDate dataInserimento) {

		List<Cliente> listaClienti = cs.filterDataInserimento(dataInserimento);

		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/{dataUltimoContatto}")
	public ResponseEntity<List<Cliente>> getFiltroDataUltimoContatto(@PathVariable LocalDate dataUltimoContatto) {

		List<Cliente> listaClienti = cs.filterDataUltimoContatto(dataUltimoContatto);

		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/{parteRagioneSociale}")
	public ResponseEntity<List<Cliente>> getFiltroParteRagioneSociale(@PathVariable String parteRagioneSociale) {

		List<Cliente> listaClienti = cs.filterParteRagioneSociale(parteRagioneSociale);

		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

}
