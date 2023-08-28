package EPIC_ENERGY_SERVICES_BackEnd.entities.comune;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComuneRepository extends JpaRepository<Comune, Integer> {

	Optional<Comune> findByNomeComune(String nomeComune);
}
