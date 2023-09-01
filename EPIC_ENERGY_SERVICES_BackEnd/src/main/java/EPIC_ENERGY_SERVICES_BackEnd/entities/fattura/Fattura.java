package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	private double importo;
	private double numeroFattura;
	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;
	private UUID idCliente;

}
