package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		//--------------------------------------------------------------------------- ordinamento ragione sociale
		public List<Cliente> orderByRagioneSociale(){	
			
			List<Cliente> result = cr.findAll().stream()
					.sorted(Comparator.comparing(Cliente::getRagioneSociale))
					.collect(Collectors.toList());
			
			return result;
		}
		
		//--------------------------------------------------------------------------- ordinamento fatturazione annuale
		public List<Cliente> orderByFatturatoAnnuale(){		
						
			List<Cliente> result = cr.findAll().stream()
					.sorted((c1, c2) -> Double.compare(c1.getFatturatoAnnuale(), c2.getFatturatoAnnuale()))
					.collect(Collectors.toList());
			
			return result;
		}
		
		//--------------------------------------------------------------------------- ordinamento per data di inserimento
		public List<Cliente> orderByDataInserimento() { 
			
	        List<Cliente> result = cr.findAll().stream()
	            .sorted(Comparator.comparing(Cliente::getDataInserimento))
	            .collect(Collectors.toList());
	        
	        return result;
	    }
		
		//--------------------------------------------------------------------------- ordinamento per data di ultimo contatto
		public List<Cliente> orderByUltimoContatto() { 
					
			List<Cliente> result = cr.findAll().stream()
					.sorted(Comparator.comparing(Cliente::getUltimoContatto))
					.collect(Collectors.toList());
			        
			return result;
		}
		
		//--------------------------------------------------------------------------- filtro fatturazione annuale
		public List<Cliente> filterFatturatoAnnuale(double fatturatoAnnuale){
			
			List<Cliente> lista = cr.findAll().stream()
					.filter(c -> c.getFatturatoAnnuale()==fatturatoAnnuale)
					.collect(Collectors.toList());
			
			return lista;
			
		}
		
		//--------------------------------------------------------------------------- filtro data di inserimento
		public List<Cliente> filterDataInserimento(LocalDate dataInserimento){
					
			List<Cliente> lista = cr.findAll().stream()
					.filter(c -> c.getDataInserimento().equals(dataInserimento))
					.collect(Collectors.toList());
					
			return lista;
					
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
