package EPIC_ENERGY_SERVICES_BackEnd.entities.comune;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;

@Service
public class ComuneService {

	@Autowired
	private ComuneRepository comuneRepo;

	public Comune create(String codiceProvincia, String codiceComune, String nomeComune, Provincia provincia) {

		Comune c = new Comune(codiceProvincia, codiceComune, nomeComune, provincia);

		return comuneRepo.save(c);
	}

	public List<Comune> find() {
		return comuneRepo.findAll();
	}

	public Page<Comune> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return comuneRepo.findAll(pagina);
	}

	public Comune findById(UUID id) throws NotFoundException {
		return comuneRepo.findById(id).orElseThrow(() -> new NotFoundException());
	}

	public Comune findByNameIgnoreCase(String nome) throws NotFoundException {
		String nomeCapitalized = nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
		Optional<Comune> optionalComune = comuneRepo.findByNomeComune(nomeCapitalized);
		return optionalComune.orElseThrow(() -> new NotFoundException());
	}

}
