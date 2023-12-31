package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.Comune;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.Fattura;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.Indirizzo;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.IndirizzoService;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository cr;

	@Autowired
	ComuneService comuneService;

	@Autowired
	IndirizzoService is;

	// ---------------------------------------------------------------------------
	// creazione cliente
	public Cliente creaCliente(ClientePayload body) throws NotFoundException {

		// crea indirizzo
		Comune comuneUno = comuneService.findByNameIgnoreCase(body.getComuneUno());
		Indirizzo indirizzoUno = is.create(body.getViaUno(), body.getCivicoUno(), body.getLocalitaUno(),
				body.getCapUno(), comuneUno);

		// crea indirizzo
		Comune comuneDue = comuneService.findByNameIgnoreCase(body.getComuneDue());
		Indirizzo indirizzoDue = is.create(body.getViaDue(), body.getCivicoDue(), body.getLocalitaDue(),
				body.getCapDue(), comuneDue);
		// ...

		// crea cliente
		Cliente newCliente = Cliente.builder().ragioneSociale(body.getRagioneSociale()).partitaIva(body.getPartitaIva())
				.emailCliente(body.getEmailCliente()).pec(body.getPec()).telefonoCliente(body.getTelefonoCliente())
				.tipoCliente(body.getTipoCliente()).nomeContatto(body.getNomeContatto())
				.cognomeContatto(body.getCognomeContatto()).emailContatto(body.getEmailContatto())
				.telefonoContatto(body.getTelefonoContatto()).dataInserimento(LocalDate.now())
				.ultimoContatto(LocalDate.now()).indirizzoSedeLegale(indirizzoUno).indirizzoSedeOperativa(indirizzoDue)
				.build();

		return cr.save(newCliente);

	}

	// ---------------------------------------------------------------------------
	// modifica cliente a inserimento fattura
	public Cliente findByIdAndUpdateFattura(UUID id, double importo, Fattura nuovaFattura) throws NotFoundException {
		Cliente cliente = cr.findById(id).orElseThrow(() -> new NotFoundException());

		// Aggiorna l'ultimo contatto e il fatturato annuale
		cliente.setUltimoContatto(LocalDate.now());
		cliente.setFatturatoAnnuale(cliente.getFatturatoAnnuale() + importo);

		// Aggiungi la nuova fattura all'array di fatture del cliente
		cliente.getFatture().add(nuovaFattura);

		// Salva il cliente aggiornato nel repository
		return cr.save(cliente);
	}
	
	// ---------------------------------------------------------------------------
	// modifica cliente
	public Cliente findByIdAndUpdate(UUID id, ClientePayload body) throws NotFoundException {
		
		Cliente found = this.findById(id);
		
		found.setRagioneSociale(body.getRagioneSociale());
		found.setPartitaIva(body.getPartitaIva());
		found.setEmailCliente(body.getEmailCliente());
		found.setPec(body.getPec());
		found.setTelefonoCliente(body.getTelefonoCliente());
		found.setTipoCliente(body.getTipoCliente());
		found.setNomeContatto(body.getNomeContatto());
		found.setCognomeContatto(body.getCognomeContatto());
		found.setEmailContatto(body.getEmailContatto());
		found.setTelefonoContatto(body.getTelefonoContatto());
		
		if(!body.getViaUno().equals(found.getIndirizzoSedeLegale().getVia())) {
			Comune comune = comuneService.findByNameIgnoreCase(body.getComuneUno());
			Indirizzo indirizzo = is.create(body.getViaUno(), body.getCivicoUno(), body.getLocalitaUno(), body.getCapUno(), comune);
			found.setIndirizzoSedeLegale(indirizzo);
		}
			
		if(!body.getViaDue().equals(found.getIndirizzoSedeOperativa().getVia())) {
			Comune comune = comuneService.findByNameIgnoreCase(body.getComuneDue());
			Indirizzo indirizzo = is.create(body.getViaDue(), body.getCivicoDue(), body.getLocalitaDue(), body.getCapDue(), comune);
			found.setIndirizzoSedeLegale(indirizzo);
		}
			
		return cr.save(found);
	}

	// ---------------------------------------------------------------------------
	// trova cliente per id
	public Cliente findById(UUID id) throws NotFoundException {
		return cr.findById(id).orElseThrow(() -> new NotFoundException());
	}

	// ---------------------------------------------------------------------------
	// trova cliente per id
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Cliente clienteTrovato = this.findById(id);
		cr.delete(clienteTrovato);
	}

	// ---------------------------------------------------------------------------
	// ordinamenti
	public Page<Cliente> findAll(int page, String ordinamento) {
		Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
		return cr.findAll(pagina);
	}

	// ---------------------------------------------------------------------------
	// filtro fatturazione annuale
	public Page<Cliente> filterFatturatoAnnuale(double fatturatoAnnuale, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return cr.findByFatturatoAnnuale(fatturatoAnnuale, pageable);
	}

	// ---------------------------------------------------------------------------
	// filtro data di inserimento
	public Page<Cliente> filterDataInserimento(LocalDate dataInserimento, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return cr.findByDataInserimento(dataInserimento, pageable);
	}

	// ---------------------------------------------------------------------------
	// filtro data ultimo contatto
	public Page<Cliente> filterUltimoContatto(LocalDate ultimoContatto, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return cr.findByUltimoContatto(ultimoContatto, pageable);
	}

	// ---------------------------------------------------------------------------
	// filtro parte del nome della ragione sociale
	public Page<Cliente> filterRagioneSociale(String parteRagioneSociale, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		String nomeCapitalized = parteRagioneSociale.substring(0, 1).toUpperCase() + parteRagioneSociale.substring(1).toLowerCase();
		return cr.findByRagioneSocialeContaining(nomeCapitalized, pageable);
	}

	// ---------------------------------------------------------------------------
	// filtro parte del nome della ragione sociale
	public Page<Cliente> filterProvincia(String provinciaSedeLegale, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);

		List<Cliente> clientiTrovati = cr.findAll().stream().filter(
				c -> c.getIndirizzoSedeLegale().getComune().getProvincia().getProvincia().equals(provinciaSedeLegale))
				.collect(Collectors.toList());

		return new PageImpl<>(clientiTrovati, pageable, clientiTrovati.size());
	}
}
