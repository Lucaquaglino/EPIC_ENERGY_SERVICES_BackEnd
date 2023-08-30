package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneService;
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
		
		//--------------------------------------------------------------------------- creazione cliente
		public Cliente creaCliente(ClientePayload body) throws NotFoundException {
			
			// check se il cliente già esiste tramite la pec
			cr.findByPec(body.getPec()).ifPresent(cliente -> {
				throw new Error("L'email è già stata utilizzata");
			});
					
			// crea indirizzo
			Indirizzo indirizzoUno = is.create(
					body.getVia(),
					body.getCivico(),
					body.getLocalita(),
					body.getCap(),
					body.getComune()
			);
									
			Cliente newCliente = Cliente.builder()
					.ragioneSociale(body.getRagioneSociale())
					.partitaIva(body.getPartitaIva())
					.emailCliente(body.getEmailCliente())
					.pec(body.getPec())
					.telefonoCliente(body.getTelefonoCliente())
					.tipoCliente(body.getTipoCliente())
					.nomeContatto(body.getNomeContatto())
					.cognomeContatto(body.getCognomeContatto())
					.emailContatto(body.getEmailContatto())
					.telefonoContatto(body.getTelefonoContatto())
					.dataInserimento(LocalDate.now())
					.ultimoContatto(LocalDate.now())
					.indirizzoUno(indirizzoUno)
					.build();	
			
			return cr.save(newCliente);
			
		}
		
		//--------------------------------------------------------------------------- modifica cliente a inserimento fattura
		public Cliente findByIdAndUpdateFattura(UUID id, double importo) {
			Cliente cliente = cr.findById(id).get();
			cliente.setUltimoContatto(LocalDate.now());
			cliente.setFatturatoAnnuale(cliente.getFatturatoAnnuale() + importo);
			return cr.save(cliente);
		}
		
		//--------------------------------------------------------------------------- ordinamenti
		public Page<Cliente> findAll(int page, String ordinamento) {
			Pageable pagina = PageRequest.of(page, 10, Sort.by(ordinamento));
			return cr.findAll(pagina);
		}
		
		//--------------------------------------------------------------------------- filtro fatturazione annuale
		public Page<Cliente> filterFatturatoAnnuale(double fatturatoAnnuale, int page, int pageSize) {
	        Pageable pageable = PageRequest.of(page, pageSize);
	        return cr.findByFatturatoAnnuale(fatturatoAnnuale, pageable);
	    }
		
		//--------------------------------------------------------------------------- filtro data di inserimento
		public Page<Cliente> filterDataInserimento(LocalDate dataInserimento, int page, int pageSize) {
	        Pageable pageable = PageRequest.of(page, pageSize);
	        return cr.findByDataInserimento(dataInserimento, pageable);
	    }
		
		//--------------------------------------------------------------------------- filtro data ultimo contatto
		public Page<Cliente> filterUltimoContatto(LocalDate ultimoContatto, int page, int pageSize) {
	        Pageable pageable = PageRequest.of(page, pageSize);
	        return cr.findByUltimoContatto(ultimoContatto, pageable);
	    }
		
		//--------------------------------------------------------------------------- filtro parte del nome della ragione sociale
		public Page<Cliente> filterRagioneSociale(String parteRagioneSociale, int page, int pageSize) {
	        Pageable pageable = PageRequest.of(page, pageSize);
	        return cr.findByRagioneSocialeContaining(parteRagioneSociale, pageable);
	    }
}
