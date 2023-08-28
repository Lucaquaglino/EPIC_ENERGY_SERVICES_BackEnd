package EPIC_ENERGY_SERVICES_BackEnd.entities.Fattura;

import java.util.UUID;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(UUID id) {
		super("Siamo spiacenti l'ID: " + id + " non è stato trovato");
	}
}