package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

		@Autowired
		ClienteRepository cr;
		
		// creazione cliente
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
		
		//ordinamento
		public List<Cliente> find(String sort){		
			return cr.findAll(Sort.by(sort));
		}
		
	
}
