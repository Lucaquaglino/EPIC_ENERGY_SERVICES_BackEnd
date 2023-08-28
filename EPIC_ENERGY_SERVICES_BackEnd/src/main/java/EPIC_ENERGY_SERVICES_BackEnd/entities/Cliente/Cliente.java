package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Entity
@Builder
@Data
@Table(name="clienti")

public class Cliente {

	@Id
	@GeneratedValue
	
}
