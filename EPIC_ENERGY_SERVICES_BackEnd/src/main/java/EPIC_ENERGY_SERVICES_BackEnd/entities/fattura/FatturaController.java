package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

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
@RequestMapping("/fattura")
public class FatturaController {
	
	@Autowired
	private FatturaService fs;	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Fattura salvaFattura(@RequestBody FatturaPayload body) {
		
		Fattura fatturaCreata = fs.creaFattura(body);
		
		return fatturaCreata;
		
	}
	
	@GetMapping("/filterByCliente")
	public ResponseEntity<List<Fattura>> getFilterByCliente(@PathVariable Cliente cliente) {
		
			List<Fattura> listaFatture = fs.filterByCliente(cliente);
			
			if (listaFatture.isEmpty()) {
				return new ResponseEntity<>(listaFatture, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
