package EPIC_ENERGY_SERVICES_BackEnd.entities.provincia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {
	@Autowired
	private ProvinciaRepository provinciaRepo;

	public Provincia create(String sigla, String provincia, String regione) {

		Provincia p = new Provincia(sigla, provincia, regione);

		return provinciaRepo.save(p);
	}

	public List<Provincia> find() {
		return provinciaRepo.findAll();
	}

	public Page<Provincia> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return provinciaRepo.findAll(pagina);
	}
//
//	public Provincia findById(String idProvincia) throws NotFoundException {
//		return provinciaRepo.findById(idProvincia).orElseThrow(() -> new NotFoundException());
//	}

	public Provincia findByName(String nomeProvincia) {
		return provinciaRepo.findByProvinciaIgnoreCase(nomeProvincia);
	}

}
