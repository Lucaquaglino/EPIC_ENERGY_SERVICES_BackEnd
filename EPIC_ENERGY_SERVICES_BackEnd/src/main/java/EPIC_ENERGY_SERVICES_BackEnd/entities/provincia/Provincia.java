package EPIC_ENERGY_SERVICES_BackEnd.entities.provincia;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Provincia {

	@Id
	private String provincia;
	private String sigla;
	private String regione;
}
