package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
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
	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;
	
//	@ManyToOne
//	private Cliente cliente;
	
	public Fattura(int _anno, LocalDate _data, BigDecimal _importo, StatoFattura _statoFattura) {
		this.anno = _anno;
		this.data = _data;
		this.importo = _importo;
		this.statoFattura = _statoFattura;
	}
}
