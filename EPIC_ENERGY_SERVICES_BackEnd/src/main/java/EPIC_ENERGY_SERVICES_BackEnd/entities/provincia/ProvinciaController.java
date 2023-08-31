package EPIC_ENERGY_SERVICES_BackEnd.entities.provincia;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provincia")
public class ProvinciaController {
	@Autowired
	ProvinciaService ps;

	@GetMapping("")
	public Page<Provincia> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "provincia") String order) {
		return ps.findAll(page, order);
	}

	@GetMapping("/{provincia}")
	public Provincia findById(@PathVariable String provincia) throws NotFoundException {
		return ps.findByName(provincia);
	}

	@GetMapping("/{id}")
	public Provincia findById(@PathVariable UUID id) throws NotFoundException {
		return ps.findById(id);
	}
//	@Autowired
//	ProvinciaService provinceService;

//	@GetMapping("")
//	public Page<Provincia> getProvince(@RequestParam(defaultValue = "0") int page,
//			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order) {
//		return provinceService.findAll(page, order);
//	}

}