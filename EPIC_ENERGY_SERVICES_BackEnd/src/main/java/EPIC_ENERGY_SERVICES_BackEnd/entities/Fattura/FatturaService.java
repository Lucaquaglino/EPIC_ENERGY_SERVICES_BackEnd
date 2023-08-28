package EPIC_ENERGY_SERVICES_BackEnd.entities.Fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import EPIC_ENERGY_SERVICES_BackEnd.entities.Fattura.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FatturaService {

	@Autowired
	private final FatturaRepository fatturaRepo;

	private FatturaService(FatturaRepository fatturaRepo) {
		super();
		this.fatturaRepo = fatturaRepo;
	}

	public Fattura create(Fattura body) {
		Fattura newFattura = new Fattura(null, body.getAnno(), null, body.getImporto(), 0, body.getStatoFattura(), body.getCliente());
		return fatturaRepo.save(newFattura);
	}

	public List<Fattura> find() {
		return fatturaRepo.findAll();
	}

	public Page<Fattura> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return fatturaRepo.findAll(pageable);
	}

	public Fattura findById(UUID id) throws NotFoundException {
		return fatturaRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Fattura findByIdAndUpdate(UUID id, FatturaPayload body) throws NotFoundException {
		Fattura fatturaTrovata = this.findById(id);
		fatturaTrovata.setStatoFattura(body.getStatoFattura());
		fatturaTrovata.setImporto(body.getImporto());
		return fatturaRepo.save(fatturaTrovata);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Fattura fatturaTrovata = this.findById(id);
		fatturaRepo.delete(fatturaTrovata);
	}
	
	public List<Fattura> findByClienteId(UUID idCliente) throws NotFoundException {
		List<Fattura> listaFatture = fatturaRepo.findByClienteId(idCliente);

		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata per il cliente con ID:" + idCliente);
		}
		return listaFatture;
	}
	
	public List<Fattura> findByStato(StatoFattura stato) throws NotFoundException {
		List<Fattura> listaFatture = fatturaRepo.findByStato(stato);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata per lo stato" + stato);
		}
		return listaFatture;
	}
	
	public List<Fattura> findByData(LocalDate data) throws NotFoundException {
		List<Fattura> listaFatture = fatturaRepo.findByData(data);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata per la data" + data);
		}
		
		return listaFatture;
		
	}
	
	public List<Fattura> findByAnno(int anno) throws NotFoundException {
		List<Fattura> listaFatture = fatturaRepo.findByAnno(anno);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata per l'anno" + anno);
		}
		
		return listaFatture;
	}
	
	public List<Fattura> findByRangeImporti(BigDecimal primoImporto, BigDecimal secImporto) throws NotFoundException {
		List<Fattura> listaFatture = fatturaRepo.findByRangeImporti(primoImporto, secImporto);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata tra i range dei seguenti importi:" + primoImporto + " - " + secImporto);
		}

		return listaFatture;
	}

}
