package EPIC_ENERGY_SERVICES_BackEnd.utente;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EPIC_ENERGY_SERVICES_BackEnd.exceptions.BadRequestException;
import EPIC_ENERGY_SERVICES_BackEnd.exceptions.NotFoundException;




@Service
public class UtenteService {

	@Autowired
	UtenteRepository utenteRepository;

	// SALVA NUOVO UTENTE + ECCEZIONE SE VIENE USATA LA STESSA EMAIL
	public Utente save(NuovoUtentePayload body) {
		utenteRepository.findByEmail(body.getEmail()).ifPresent(utente -> {
			throw new BadRequestException("L'email " + body.getEmail() + " è gia stata utilizzata");
		});
		Utente newUtente = new Utente(body.getUsername(), body.getNome(), body.getCognome(), body.getEmail(),body.getPassword(), body.getRuolo());
		return utenteRepository.save(newUtente);
	}

	// TORNA LA LISTA DEGLI UTENTI
	public List<Utente> getUsers() {
		return utenteRepository.findAll();
	}

	// CERCA UTENTE TRAMITE ID
	public Utente findById(UUID id) throws NotFoundException {
		return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	// CERCA E MODIFICA UTENTE TRAMITE ID
	public Utente findByIdAndUpdate(UUID id, NuovoUtentePayload body) throws NotFoundException {
		Utente found = this.findById(id);
		found.setNome(body.getNome());
		found.setCognome(body.getCognome());
		found.setEmail(body.getEmail());
		return utenteRepository.save(found);
	}

	// CERCA E CANCELLA UTENTE TRAMITE ID
	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Utente found = this.findById(id);
		utenteRepository.delete(found);
	}

	public Utente findByEmail(String email) {
		return utenteRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}
}
