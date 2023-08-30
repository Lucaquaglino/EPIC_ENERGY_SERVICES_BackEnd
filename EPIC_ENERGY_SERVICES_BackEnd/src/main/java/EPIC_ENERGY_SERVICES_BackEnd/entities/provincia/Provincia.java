package EPIC_ENERGY_SERVICES_BackEnd.entities.provincia;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Provincia {

	@Id
//	@GeneratedValue
//	private UUID id;
	private String provincia;
	private String sigla;
	private String regione;

	public Provincia(String sigla, String provincia, String regione) {
		this.sigla = sigla;
		this.provincia = provincia;
		this.regione = regione;
	}

}
