package EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo;

import java.util.UUID;

import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.Comune;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Indirizzo {
	@Id
	@GeneratedValue
	private UUID id;
	private String via;
	private String civico;
	private String localita;
	private String cap;
	@ManyToOne
	private Comune comune;

	public Indirizzo(String via, String civico, String localita, String cap, Comune comune) {

		this.via = via;
		this.civico = civico;
		this.localita = localita;
		this.cap = cap;
		this.comune = comune;
	}

}
