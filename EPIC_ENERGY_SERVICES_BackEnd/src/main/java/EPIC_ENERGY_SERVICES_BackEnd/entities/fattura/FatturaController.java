package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

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
	public Fattura salvaFattura(@RequestBody FatturaPayload body) throws Exception {

		Fattura fatturaCreata = fs.creaFattura(body);

		return fatturaCreata;

	}

	// ---------------------------------------------------------------------------
	// trova per id
	@GetMapping("/{id}")
	public Fattura findById(@PathVariable UUID id) throws NotFoundException {
		return fs.findById(id);
	}

	// ---------------------------------------------------------------------------
	// elimina
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable UUID id) throws NotFoundException {
		fs.findByIdAndDelete(id);
	}

	// ---------------------------------------------------------------------------
	// filtro per cliente
	@GetMapping("/filter/ragioneSociale")
	public Page<Fattura> filterByRagioneSociale(@RequestParam String ragioneSociale,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
		return fs.filterByCliente(ragioneSociale, page, pageSize);
	}

	// ---------------------------------------------------------------------------
	// filtro per stato fattura
	@GetMapping("/filter/statoFattura")
	public Page<Fattura> filterByStatoFattura(@RequestParam StatoFattura statoFattura,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
		return fs.filterByStatoFattura(statoFattura, page, pageSize);
	}

	// ---------------------------------------------------------------------------
	// filtro per data
	@GetMapping("/filter/data")
	public Page<Fattura> filterByData(@RequestParam LocalDate data, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize) {
		return fs.filterByData(data, page, pageSize);
	}

	// ---------------------------------------------------------------------------
	// filtro per range importi
	@GetMapping("/filter/importRange")
	public Page<Fattura> filterByImportRange(@RequestParam double minImporto, @RequestParam double maxImporto,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
		return fs.filterByImportRange(minImporto, maxImporto, page, pageSize);
	}
}
