package EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/indirizzo")
public class IndirizzoController {
	@Autowired
	IndirizzoService is;

	@GetMapping("")
	public Page<Indirizzo> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String order) {
		return is.findAll(page, order);
	}

	@GetMapping("/{id}")
	public Indirizzo findById(@PathVariable String id) throws NotFoundException {
		return is.findById(id);
	}

	@PutMapping("/{id}")
	public Indirizzo updateById(@PathVariable String id, @RequestBody IndirizzoPayload payload)
			throws NotFoundException {
		return is.findByIdAndUpadate(id, payload);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable String id) throws NotFoundException {
		is.findByIdAndDelete(id);
	}

	@PostMapping("")
	public Indirizzo createIndirizzo(@RequestBody IndirizzoPayload payload) throws NotFoundException {
		return is.create(payload.getVia(), payload.getCivico(), payload.getLocalita(), payload.getCap(),
				payload.getNomeComune());
	}

}
