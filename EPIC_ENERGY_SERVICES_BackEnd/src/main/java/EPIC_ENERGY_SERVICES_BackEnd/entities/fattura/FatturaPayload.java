package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;

import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FatturaPayload {
	private int anno;
	private LocalDate data;
	private BigDecimal importo;
	private StatoFattura statoFattura;
	private Cliente cliente;
}
