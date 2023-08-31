package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public Cliente salvaCliente(@RequestBody ClientePayload body) throws NotFoundException {

		Cliente clienteCreato = cs.creaCliente(body);

		return clienteCreato;
	}
	
	//--------------------------------------------------------------------------- trova per id
	@GetMapping("/{id}")
	public Cliente findById(@PathVariable UUID id) throws NotFoundException {
		return cs.findById(id);
	}
	
	//--------------------------------------------------------------------------- elimina
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable UUID id) throws NotFoundException {
		cs.findByIdAndDelete(id);
	}

	//--------------------------------------------------------------------------- ordinamenti
	@GetMapping("")
	public Page<Cliente> findAll(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "ragioneSociale") String order
	) {
		return cs.findAll(page, order);
	}

	//--------------------------------------------------------------------------- filtro fattura annuale
	@GetMapping("/filter/fatturatoAnnuale")
	public Page<Cliente> filterFattuartoAnnuale(
            @RequestParam double fatturatoAnnuale,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return cs.filterFatturatoAnnuale(fatturatoAnnuale, page, pageSize);
    }

	//--------------------------------------------------------------------------- filtro data inserimento
	@GetMapping("/filter/dataInserimento")
	public Page<Cliente> filterDataInserimento(
			@RequestParam LocalDate dataInserimento,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
		return cs.filterDataInserimento(dataInserimento, page, pageSize);
	}

	//--------------------------------------------------------------------------- filtro ultimo inserimento
	@GetMapping("/filter/ultimoContatto")
	public Page<Cliente> filterUltimoInserimento(
			@RequestParam LocalDate ultimoContatto,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
		return cs.filterUltimoContatto(ultimoContatto, page, pageSize);
	}

	//--------------------------------------------------------------------------- filtro parte ragione sociale
	@GetMapping("/filter/ragioneSociale")
	public Page<Cliente> filterRagioneSociale(
			@RequestParam String parteRagioneSociale,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
		return cs.filterRagioneSociale(parteRagioneSociale, page, pageSize);
	}

}
