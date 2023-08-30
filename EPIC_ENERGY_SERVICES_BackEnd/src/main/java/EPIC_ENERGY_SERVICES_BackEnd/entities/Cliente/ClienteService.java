package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

		@Autowired
		ClienteRepository cr;
		
		//--------------------------------------------------------------------------- creazione cliente
		public Cliente creaCliente(ClientePayload body) {
			
			//check se il cliente già esiste tramite la pec
			cr.findByPec(body.getPec()).ifPresent(cliente -> {
				throw new Error("L'email è già stata utilizzata");
			});
			
			List<String> indirizzi = new ArrayList<String>();
			
			indirizzi.add(body.getIndirizzoUno());
			indirizzi.add(body.getIndirizzoDue());
			
			Cliente newCliente = Cliente.builder()
					.ragioneSociale(body.getRagioneSociale())
					.partitaIva(body.getPartitaIva())
					.emailCliente(body.getEmailCliente())
					.pec(body.getPec())
					.telefonoCliente(body.getTelefonoCliente())
					.indirizzi(indirizzi)
					.tipoCliente(body.getTipoCliente())
					.nomeContatto(body.getNomeContatto())
					.cognomeContatto(body.getCognomeContatto())
					.emailContatto(body.getEmailContatto())
					.telefonoContatto(body.getTelefonoContatto())
					.dataInserimento(LocalDate.now())
					.ultimoContatto(LocalDate.now())
					.build();
			
			return cr.save(newCliente);
			
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
		public List<Cliente> filterDataUltimoContatto(LocalDate dataUltimoContatto){
							
			List<Cliente> lista = cr.findAll().stream()
					.filter(c -> c.getUltimoContatto().equals(dataUltimoContatto))
					.collect(Collectors.toList());
							
			return lista;
							
		}
		
		//--------------------------------------------------------------------------- filtro parte del nome della ragione sociale
		public List<Cliente> filterParteRagioneSociale(String parteRagioneSociale){
									
			List<Cliente> lista = cr.findAll().stream()
					.filter(c -> c.getRagioneSociale().contains(parteRagioneSociale))
					.collect(Collectors.toList());
									
			return lista;
									
		}
}
