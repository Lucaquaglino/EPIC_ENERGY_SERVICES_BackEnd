package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FatturaPayload {
	private int anno;
	private LocalDate data;
	private double importo;
	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;
	private UUID id_cliente;
}
