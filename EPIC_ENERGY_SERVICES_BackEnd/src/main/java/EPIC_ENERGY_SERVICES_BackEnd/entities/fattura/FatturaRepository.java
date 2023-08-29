package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {
	
	List<Fattura> findByClienteId(UUID idCliente);
	List<Fattura> findByStato(StatoFattura stato);
	List<Fattura> findByData(LocalDate data);
	List<Fattura> findByAnno(int anno);
	List<Fattura> findByRangeImporti(BigDecimal primoImporto, BigDecimal secImporto);
	
}
