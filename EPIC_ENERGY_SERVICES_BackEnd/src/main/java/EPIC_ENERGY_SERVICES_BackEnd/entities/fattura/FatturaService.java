package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.Cliente;
import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.ClienteRepository;
import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.ClienteService;
import EPIC_ENERGY_SERVICES_BackEnd.exceptions.NotFoundException;

@Service
public class FatturaService {

	@Autowired
	FatturaRepository fr;
	
	@Autowired
	ClienteRepository cr;
	
	@Autowired
	ClienteService cs;

	//--------------------------------------------------------------------------- creazione cliente
	public Fattura creaFattura(FatturaPayload body) {
		
		Cliente cliente = cr.findById(body.getId_cliente())
				.orElseThrow(() -> new NotFoundException("Cliente non trovato"));
		
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
				.cliente(cliente)
				.build();
		
		//aggiornamento dati cliente (ultimo contatto e fatturato annuo)
		cs.findByIdAndUpdateFattura(body.getId_cliente(), body.getImporto());
		 
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
	
	//--------------------------------------------------------------------------- filtro per cliente
	public Page<Fattura> filterByCliente(String ragioneSociale, int page, int pageSize) {
		UUID clienteId = cr.findByRagioneSociale(ragioneSociale).get().getIdCliente();
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByCliente(clienteId, pageable);
    }

	//--------------------------------------------------------------------------- filtro per stato fattura
    public Page<Fattura> filterByStatoFattura(StatoFattura stato, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByStatoFattura(stato, pageable);
    }

  //--------------------------------------------------------------------------- filtro per data
    public Page<Fattura> filterByData(LocalDate data, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByData(data, pageable);
    }

  //--------------------------------------------------------------------------- filtro per anno
    public Page<Fattura> filterByAnno(int anno, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByAnno(anno, pageable);
    }

  //--------------------------------------------------------------------------- filtro per importo
    public Page<Fattura> filterByImportRange(double minImporto, double maxImporto, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return fr.findByImportoBetween(minImporto, maxImporto, pageable);
    }

}
