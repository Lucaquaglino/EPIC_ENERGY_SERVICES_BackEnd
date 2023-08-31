package EPIC_ENERGY_SERVICES_BackEnd.entities.provincia;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaRepository extends JpaRepository<Provincia, String> {

	Provincia findByProvinciaIgnoreCase(String provincia);

	Optional<Provincia> findById(UUID id);

}
