package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
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
	
	@GetMapping("/orderByRagioneSociale")
	public ResponseEntity<List<Cliente>> getOrderByRagioneSociale(){
		
		List<Cliente> listaClienti = cs.orderByRagioneSociale();
		
		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/orderByFatturatoAnnuale")
	public ResponseEntity<List<Cliente>> getOrderByFatturatoAnnuale(){
		
		List<Cliente> listaClienti = cs.orderByFatturatoAnnuale();
		
		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/orderByFatturatoAnnuale")
	public ResponseEntity<List<Cliente>> getOrderByDataInserimento(){
		
		List<Cliente> listaClienti = cs.orderByDataInserimento();
		
		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/{fatturatoAnnuale}")
	public ResponseEntity<List<Cliente>> getFiltroFatturatoAnnuale(@PathVariable double fatturatoAnnuale){
		
		List<Cliente> listaClienti = cs.filterFatturatoAnnuale(fatturatoAnnuale);
		
		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@GetMapping("/{dataInserimento}")
	public ResponseEntity<List<Cliente>> getFiltroDataInserimento(@PathVariable LocalDate dataInserimento){
		
		List<Cliente> listaClienti = cs.filterDataInserimento(dataInserimento);
		
		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@GetMapping("/{dataUltimoContatto}")
	public ResponseEntity<List<Cliente>> getFiltroDataUltimoContatto(@PathVariable LocalDate dataUltimoContatto){
		
		List<Cliente> listaClienti = cs.filterDataUltimoContatto(dataUltimoContatto);
		
		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@GetMapping("/{parteRagioneSociale}")
	public ResponseEntity<List<Cliente>> getFiltroParteRagioneSociale(@PathVariable String parteRagioneSociale){
		
		List<Cliente> listaClienti = cs.filterParteRagioneSociale(parteRagioneSociale);
		
		if (!listaClienti.isEmpty()) {
			return new ResponseEntity<>(listaClienti, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}

}
