package EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.Comune;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneService;

@Service
public class IndirizzoService {

	@Autowired
	private IndirizzoRepository indirizzoRepo;
	@Autowired
	private ComuneService comuneService;

	public Indirizzo create(String via, String civico, String localita, String cap, Comune comune)
			throws NotFoundException {
		Indirizzo i = new Indirizzo(via, civico, localita, cap, comune);
		return indirizzoRepo.save(i);
	}

	public List<Indirizzo> find() {
		return indirizzoRepo.findAll();
	}

	public Page<Indirizzo> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return indirizzoRepo.findAll(pagina);
	}

	public Indirizzo findById(UUID id) throws NotFoundException {
		return indirizzoRepo.findById(id).orElseThrow(() -> new NotFoundException());
	}

	public Indirizzo findByIdAndUpadate(UUID id, IndirizzoPayload body) throws NotFoundException {
		Indirizzo in = findById(id);
		in.setCap(body.getCap());
		in.setCivico(body.getCivico());
		in.setComune(comuneService.findByNameIgnoreCase(body.getNomeComune()));
		in.setLocalita(body.getLocalita());
		in.setVia(body.getVia());

		return indirizzoRepo.save(in);

	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Indirizzo in = findById(id);

		indirizzoRepo.delete(in);

	}

}
