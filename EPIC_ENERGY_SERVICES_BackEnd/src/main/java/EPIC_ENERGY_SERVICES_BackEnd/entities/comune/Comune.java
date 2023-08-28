package EPIC_ENERGY_SERVICES_BackEnd.entities.comune;

import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Comune {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String codiceProvincia;
	private String codiceComune;
	private String nomeComune;
	@ManyToOne
	private Provincia provincia;

	public Comune(String codiceProvincia, String codiceComune, String nomeComune, Provincia provincia) {
		this.codiceProvincia = codiceProvincia;
		this.codiceComune = codiceComune;
		this.nomeComune = nomeComune;
		this.provincia = provincia;
	}

}