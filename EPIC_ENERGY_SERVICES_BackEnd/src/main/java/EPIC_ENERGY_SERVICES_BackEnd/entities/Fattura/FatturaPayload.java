package EPIC_ENERGY_SERVICES_BackEnd.entities.Fattura;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FatturaPayload {
	private BigDecimal importo;
	private StatoFattura statoFattura;
}
