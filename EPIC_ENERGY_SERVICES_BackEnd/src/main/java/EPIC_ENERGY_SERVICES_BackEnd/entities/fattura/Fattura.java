package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fattura {
	@Id
	@GeneratedValue
	private UUID id;
	private int anno;
	private LocalDate data;
	private BigDecimal importo;
	private double numeroFattura = 0;
	
	@ManyToMany
	private StatoFattura statoFattura;
	
	@ManyToOne
	private Cliente cliente;
	
	public Fattura(int _anno, LocalDate _data, BigDecimal _importo, StatoFattura _statoFattura, Cliente _cliente) {
		this.anno = _anno;
		this.data = _data;
		this.importo = _importo;
		this.statoFattura = _statoFattura;
		this.cliente = _cliente;
	}
}
