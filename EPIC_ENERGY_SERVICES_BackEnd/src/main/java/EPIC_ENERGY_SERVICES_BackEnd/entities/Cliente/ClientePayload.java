package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientePayload {

	//dati cliente
	private String ragione_sociale;
	private int partita_iva;
	private String email_cliente;
	private String pec;
	private int telefono_cliente;
	private String indirizzo_uno;
	private String indirizzo_due;
	private TIPO_CLIENTE tipo_cliente;
		
	//dati contatto
	private String nome_contatto;
	private String cognome_contatto;
	private String email_contatto;
	private int telefono_contatto;
	
}
