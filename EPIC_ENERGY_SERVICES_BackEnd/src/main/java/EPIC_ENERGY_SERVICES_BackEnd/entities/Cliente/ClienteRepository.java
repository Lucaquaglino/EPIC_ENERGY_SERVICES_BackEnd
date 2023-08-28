package EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
	
	Optional<Cliente> findByPec(String pec);
	
	Optional<Cliente> findByOrderByRagioneSociale(String ragione_sociale);
	
}
