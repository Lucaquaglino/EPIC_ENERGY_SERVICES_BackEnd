package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FatturaPayload {
	private BigDecimal importo;
	private StatoFattura statoFattura;
}
