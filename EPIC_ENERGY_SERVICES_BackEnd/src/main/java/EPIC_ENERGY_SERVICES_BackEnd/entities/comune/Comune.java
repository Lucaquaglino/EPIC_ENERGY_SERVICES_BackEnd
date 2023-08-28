package EPIC_ENERGY_SERVICES_BackEnd.entities.comune;

import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Comune {

	@Id
	@GeneratedValue
	private int id;
	private String codiceProvincia;
	private String codiceComune;
	private String nomeComune;
	@ManyToOne
	private Provincia provincia;

}