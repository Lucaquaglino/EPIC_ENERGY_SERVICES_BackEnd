package EPIC_ENERGY_SERVICES_BackEnd.entities.comune;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comune")
public class ComuneController {
	@Autowired
	ComuneService cs;

	@GetMapping("")
	public Page<Comune> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String order) {
		return cs.findAll(page, order);
	}

	@GetMapping("/{id}")
	public Comune findById(@PathVariable int id) throws NotFoundException {
		return cs.findById(id);
	}

	@GetMapping("/nome")
	public Comune findByname(@RequestParam String nome) throws NotFoundException {
		return cs.findByNameIgnoreCase(nome);
	}
}
