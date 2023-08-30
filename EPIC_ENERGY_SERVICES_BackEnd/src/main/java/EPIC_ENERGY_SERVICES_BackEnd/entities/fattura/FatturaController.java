package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
@RequestMapping("/fattura")
public class FatturaController {

	@Autowired
	private FatturaService fs;
	
	@GetMapping("")
	public Page<Fattura> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String order) {
		return fs.findAll(page, order);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Fattura salvaFattura(@RequestBody FatturaPayload body) {

		Fattura fatturaCreata = fs.creaFattura(body);

		return fatturaCreata;

	}

	@GetMapping("/filterByCliente/{idCliente}")
	public ResponseEntity<List<Fattura>> getFilterByClienteId(@PathVariable UUID idCliente) {

		List<Fattura> listaFatture = fs.filterByClienteId(idCliente);

		if (listaFatture.isEmpty()) {
			return new ResponseEntity<>(listaFatture, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/filterByStato/{statoCliente}")
	public ResponseEntity<List<Fattura>> getFilerByStatoFattura(@PathVariable StatoFattura statoFattura) {
		List<Fattura> listaFatture = fs.filterByStato(statoFattura);
		
		if(listaFatture.isEmpty()) {
			return new ResponseEntity<>(listaFatture, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/filterByData/{data}")
	public ResponseEntity<List<Fattura>> getFilterData(@PathVariable LocalDate data) {
		List<Fattura> listaFatture = fs.filterByData(data);
		
		if(listaFatture.isEmpty()) {
			return new ResponseEntity<>(listaFatture, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/filterByAnno/{anno}")
	public ResponseEntity<List<Fattura>> getFilterAnno(@PathVariable int anno) {
		List<Fattura> listaFatture = fs.filterByAnno(anno);
		
		if(listaFatture.isEmpty()) {
			return new ResponseEntity<>(listaFatture, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
