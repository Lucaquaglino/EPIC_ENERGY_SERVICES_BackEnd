package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.util.UUID;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientePayload {
	private UUID idCliente;
	// -------------------------------- dati cliente
	private String ragioneSociale;
	private int partitaIva;
	private String emailCliente;
	private String pec;
	private int telefonoCliente;
	@Enumerated(EnumType.STRING)
	private TIPO_CLIENTE tipoCliente;

	// -------------------------------- dati contatto
	private String nomeContatto;
	private String cognomeContatto;
	private String emailContatto;
	private int telefonoContatto;

	// -------------------------------- dati indirizzo uno
	private String viaUno;
	private String civicoUno;
	private String localitaUno;
	private String capUno;

	// -------------------------------- nome comune uno
	private String comuneUno;

	// -------------------------------- dati indirizzo uno
	private String viaDue;
	private String civicoDue;
	private String localitaDue;
	private String capDue;

	// -------------------------------- nome comune due
	private String comuneDue;
}
