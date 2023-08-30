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
	
	Optional<Cliente> findByRagioneSociale(String ragioneSociale);
	
	Page<Cliente> findByFatturatoAnnuale(double fatturatoAnnuale, Pageable pageable);
	
	Page<Cliente> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);
	
	Page<Cliente> findByUltimoInserimento(LocalDate ultimoInserimento, Pageable pageable);
	
	Page<Cliente> findByRagioneSocialeContaining(String parteRagioneSociale, Pageable pageable);
	
}
