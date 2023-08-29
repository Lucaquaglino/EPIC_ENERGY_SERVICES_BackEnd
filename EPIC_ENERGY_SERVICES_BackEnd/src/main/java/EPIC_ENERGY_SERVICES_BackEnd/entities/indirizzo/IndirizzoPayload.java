package EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo;

import lombok.Data;

@Data
public class IndirizzoPayload {
	private String via;
	private String civico;
	private String localita;
	private int cap;
	private String nomeComune;
}
