package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

	//--------------------------------------------------------------------------- filtro per cliente
	@GetMapping("/filter/ragioneSociale")
	public Page<Fattura> filterByRagioneSociale(
			@RequestParam String ragioneSociale,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
	) {
		return fs.filterByCliente(ragioneSociale, page, pageSize);
	}
	
	//--------------------------------------------------------------------------- filtro per stato fattura
	@GetMapping("/filter/statoFattura")
	public Page<Fattura> filterByStatoFattura(
			@RequestParam StatoFattura statoFattura,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int pageSize
	) {
		return fs.filterByStatoFattura(statoFattura, page, pageSize);
	}
	
	//--------------------------------------------------------------------------- filtro per data
	@GetMapping("/filter/data")
	public Page<Fattura> filterByData(
			@RequestParam LocalDate data,
		    @RequestParam(defaultValue = "0") int page,
		    @RequestParam(defaultValue = "10") int pageSize
	) {
		return fs.filterByData(data, page, pageSize);
	}
	
	//--------------------------------------------------------------------------- filtro per range importi
	@GetMapping("/filter/importRange")
	public Page<Fattura> filterByImportRange(
			@RequestParam BigDecimal minImporto,
			@RequestParam BigDecimal maxImporto,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize
	) {
		return fs.filterByImportRange(minImporto, maxImporto, page, pageSize);
	}
}
