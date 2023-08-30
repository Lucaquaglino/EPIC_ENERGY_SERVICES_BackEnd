package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.Cliente;
import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.ClienteRepository;
import EPIC_ENERGY_SERVICES_BackEnd.exceptions.NotFoundException;

@Service
public class FatturaService {

	@Autowired
	private final FatturaRepository fr;
	
	@Autowired
	private final ClienteRepository cr;

	private FatturaService(FatturaRepository fr, ClienteRepository cr) {
		super();
		this.fr = fr;
		this.cr = cr;
	}

	public Fattura creaFattura(FatturaPayload body) {
		Cliente clienteId = cr.findById(body.getCliente().getId_cliente()).orElseThrow(() -> new IllegalArgumentException("Cliente non trovato"));
		
		Fattura newFattura = new Fattura( body.getAnno(), body.getData(), body.getImporto(), body.getStatoFattura(), clienteId);
		
		newFattura.setCliente(clienteId);
		return fr.save(newFattura);
	}

	public List<Fattura> find() {
		return fr.findAll();
	}
	
	public Page<Fattura> findAll(int page, String sort) {
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));
		return fr.findAll(pageable);
	}

	public Fattura findById(UUID id) throws NotFoundException {
		return fr.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Fattura findByIdAndUpdate(UUID id, FatturaPayload body) throws NotFoundException {
		Fattura fatturaTrovata = this.findById(id);
		fatturaTrovata.setStatoFattura(body.getStatoFattura());
		fatturaTrovata.setImporto(body.getImporto());
		return fr.save(fatturaTrovata);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Fattura fatturaTrovata = this.findById(id);
		fr.delete(fatturaTrovata);
	}
	
	public List<Fattura> findByClienteId(UUID idCliente) throws NotFoundException {
		List<Fattura> listaFatture = fr.findByClienteId(idCliente);

		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata per il cliente con ID:" + idCliente);
		}
		return listaFatture;
	}
	
	public List<Fattura> findByStatoFattura(StatoFattura statoFattura) throws NotFoundException {
		List<Fattura> listaFatture = fr.findByStatoFattura(statoFattura);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata per lo stato" + statoFattura);
		}
		return listaFatture;
	}
	
	public List<Fattura> findByData(LocalDate data) throws NotFoundException {
		List<Fattura> listaFatture = fr.findByData(data);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata per la data" + data);
		}
		
		return listaFatture;
		
	}
	
	public List<Fattura> findByAnno(int anno) throws NotFoundException {
		List<Fattura> listaFatture = fr.findByAnno(anno);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata per l'anno" + anno);
		}
		
		return listaFatture;
	}
	
	public List<Fattura> findByImportoBetween(BigDecimal minImporto, BigDecimal maxImporto) throws NotFoundException {
		List<Fattura> listaFatture = fr.findByImportoBetween(minImporto, maxImporto);
		
		if(listaFatture.isEmpty()) {
			throw new NotFoundException("Nessuna fattura trovata tra i range dei seguenti importi:" + minImporto + " - " + maxImporto);
		}

		return listaFatture;
	}
	
//	----------------------------------- FILTERS ----------------------------------
	
	public List<Fattura> filterByClienteId(UUID clienteId) {
		return fr.findAll().stream().filter(f -> f.getCliente().getId_cliente().equals(clienteId)).collect(Collectors.toList());
	}
	
	public List<Fattura> filterByStato(StatoFattura stato) {
		return fr.findAll().stream().filter(f -> f.getStatoFattura().equals(stato)).collect(Collectors.toList());
	}
	
	public List<Fattura> filterByData(LocalDate data) {
		return fr.findAll().stream().filter(f -> f.getData().equals(data)).collect(Collectors.toList());
	}
	
	public List<Fattura> filterByAnno(int anno) {
		return fr.findAll().stream().filter(f -> f.getAnno() == anno).collect(Collectors.toList());
	}
	
	public List<Fattura> filterByImportRange(BigDecimal minImporto, BigDecimal maxImporto) {
		return fr.findAll().stream().filter(f -> f.getImporto().compareTo(minImporto) >= 0 && f.getImporto().compareTo(maxImporto) <= 0).collect(Collectors.toList());
	}

}
