package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
	FatturaRepository fr;
	
	@Autowired
	ClienteRepository cr;

	//--------------------------------------------------------------------------- creazione cliente
	public Fattura creaFattura(FatturaPayload body) {
		
		Cliente clienteId = cr.findById(body.getCliente().getId_cliente())
				.orElseThrow(() -> new IllegalArgumentException("Cliente non trovato"));
		
		Optional<Fattura> fatturaMax = fr.findAll().stream()
				.max((f1, f2) -> Double.compare(f1.getNumeroFattura(), f2.getNumeroFattura()));
		
		double f = 1;
		
		if(fatturaMax.isPresent())
			f = fatturaMax.get().getNumeroFattura() + 1;
		
		Fattura newFattura = Fattura.builder()
				.anno(body.getAnno())
				.data(body.getData())
				.importo(body.getImporto())
				.numeroFattura(f)
				.statoFattura(body.getStatoFattura())
				.cliente(clienteId)
				.build();
		
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
	
	//--------------------------------------------------------------------------- filtro per cliente
	public Page<Fattura> filterByCliente(String ragioneSociale, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByCliente(ragioneSociale, pageable);
    }

    public Page<Fattura> filterByStato(StatoFattura stato, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByStatoFattura(stato, pageable);
    }

    public Page<Fattura> filterByData(LocalDate data, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByData(data, pageable);
    }

    public Page<Fattura> filterByAnno(int anno, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByAnno(anno, pageable);
    }

    public Page<Fattura> filterByImportRange(BigDecimal minImporto, BigDecimal maxImporto, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByImportoBetsween(minImporto, maxImporto, pageable);
    }

}
