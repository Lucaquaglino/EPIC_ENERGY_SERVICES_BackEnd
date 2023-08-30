package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fattura {
	@Id
	@GeneratedValue
	private UUID id;
	private int anno;
	private LocalDate data;
	private BigDecimal importo;
	private double numeroFattura;
	private StatoFattura statoFattura;
	
	@ManyToOne
	private Cliente cliente;
	
}
