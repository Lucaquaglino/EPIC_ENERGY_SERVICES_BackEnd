package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import EPIC_ENERGY_SERVICES_BackEnd.exceptions.NotFoundException;

@Service
public class FatturaService {

	@Autowired
	private final FatturaRepository fatturaRepo;

	private FatturaService(FatturaRepository fatturaRepo) {
		super();
		this.fatturaRepo = fatturaRepo;
	}

	//To add Cliente, body.getCliente
	public Fattura create(Fattura body) {
		Fattura newFattura = new Fattura( body.getAnno(), body.getData(), body.getImporto(), body.getStatoFattura());
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
	
//	public List<Fattura> findByClienteId(UUID idCliente) throws NotFoundException {
//		List<Fattura> listaFatture = fatturaRepo.findByClienteId(idCliente);
//
//		if(listaFatture.isEmpty()) {
//			throw new NotFoundException("Nessuna fattura trovata per il cliente con ID:" + idCliente);
//		}
//		return listaFatture;
//	}
	
	public List<Fattura> findByStatoFattura(StatoFattura statoFattura) throws NotFoundException {
		List<Fattura> listaFatture = fatturaRepo.findByStatoFattura(statoFattura);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata per lo stato" + statoFattura);
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
	
	public List<Fattura> findByImportoBetween(BigDecimal minImporto, BigDecimal maxImporto) throws NotFoundException {
		List<Fattura> listaFatture = fatturaRepo.findByImportoBetween(minImporto, maxImporto);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata tra i range dei seguenti importi:" + minImporto + " - " + maxImporto);
		}

		return listaFatture;
	}

}
