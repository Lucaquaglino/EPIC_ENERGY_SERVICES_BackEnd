package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/{sortParam}")
	public ResponseEntity<List<Cliente>> getClienti(@PathVariable String sort){
		
		List<Cliente> listaClienti = cs.find(sort);
		
		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	
}
