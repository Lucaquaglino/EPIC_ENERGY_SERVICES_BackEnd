package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientePayload {

	//-------------------------------- dati cliente
	private String ragioneSociale;
	private int partitaIva;
	private String emailCliente;
	private String pec;
	private int telefonoCliente;
	@Enumerated(EnumType.STRING)
	private TIPO_CLIENTE tipoCliente;
		
	//-------------------------------- dati contatto
	private String nomeContatto;
	private String cognomeContatto;
	private String emailContatto;
	private int telefonoContatto;
	
	//-------------------------------- dati indirizzo
	private String via;
	private String civico;
	private String localita;
	private String cap;
	
	//-------------------------------- nome comune
	private String comune;
}
