package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

		@Autowired
		ClienteRepository cr;
		
		// creazione cliente
		public Cliente create(ClientePayload body) {
			
			//check se il cliente già esiste tramite la pec
			cr.findByPec(body.getPec()).ifPresent(cliente -> {
				throw new Error("L'email è già stata utilizzata");
			});
			
			List<String> indirizzi = new ArrayList<String>();
			
			indirizzi.add(body.getIndirizzo_uno());
			indirizzi.add(body.getIndirizzo_due());
			
			Cliente newCliente = Cliente.builder()
					.ragione_sociale(body.getRagione_sociale())
					.partita_iva(body.getPartita_iva())
					.email_cliente(body.getEmail_cliente())
					.pec(body.getPec())
					.telefono_cliente(body.getTelefono_cliente())
					.indirizzi(indirizzi)
					.tipo_cliente(body.getTipo_cliente())
					.nome_contatto(body.getNome_contatto())
					.cognome_contatto(body.getCognome_contatto())
					.email_contatto(body.getEmail_contatto())
					.telefono_contatto(body.getTelefono_contatto())
					.data_inserimento(LocalDate.now())
					.ultimo_contatto(LocalDate.now())
					.build();
			
			return cr.save(newCliente);
		}
	
}
