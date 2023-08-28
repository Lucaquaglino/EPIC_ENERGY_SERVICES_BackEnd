package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientePayload {

	//dati cliente
	private String ragioneSociale;
	private int partitaIva;
	private String emailCliente;
	private String pec;
	private int telefonoCliente;
	private String indirizzoUno;
	private String indirizzoDue;
	private TIPO_CLIENTE tipoCliente;
		
	//dati contatto
	private String nomeContatto;
	private String cognomeContatto;
	private String emailContatto;
	private int telefonoContatto;
	
}
