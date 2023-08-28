package EPIC_ENERGY_SERVICES_BackEnd.entities.Fattura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table
@Entity
@Data
@AllArgsConstructor
public class Fattura {
	@Id
	@GeneratedValue
	private UUID id;
	private int anno;
	private LocalDate data;
	private BigDecimal importo;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private double numeroFattura;
	@ManyToMany
	@Enumerated(EnumType.STRING)
	private StatoFattura statoFattura;
	@ManyToOne
	private Cliente cliente;
}
