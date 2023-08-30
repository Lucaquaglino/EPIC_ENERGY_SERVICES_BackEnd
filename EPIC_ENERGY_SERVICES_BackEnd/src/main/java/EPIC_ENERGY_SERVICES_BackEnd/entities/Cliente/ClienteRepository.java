package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
	
	Optional<Cliente> findByPec(String pec);
	
	Page<Cliente> findByFatturatoAnnuale(double fatturatoAnnuale, Pageable pageable);
	
	Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);
	
}
