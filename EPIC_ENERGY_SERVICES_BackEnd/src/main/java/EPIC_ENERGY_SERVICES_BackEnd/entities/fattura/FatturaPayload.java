package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FatturaPayload {
	private UUID id;
	private int anno;
	private LocalDate data;
	private double importo;
	private StatoFattura statoFattura;
	private UUID idCliente;
}
