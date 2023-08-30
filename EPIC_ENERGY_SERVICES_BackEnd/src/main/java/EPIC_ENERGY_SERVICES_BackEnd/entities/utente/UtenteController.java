package EPIC_ENERGY_SERVICES_BackEnd.entities.utente;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/utenti")
public class UtenteController {

	@Autowired
	UtenteService utenteService;

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUtente(@RequestBody NuovoUtentePayload body) {
		Utente createdUser = utenteService.save(body);
		return createdUser;
	}

	@GetMapping("")
	public List<Utente> getUsers() {
		return utenteService.getUsers();
	}
	
	@GetMapping("")
	public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "id") String order) {
		return utenteService.findAll(page, order);
	}

	@GetMapping("/{userId}")
	public Utente findById(@PathVariable UUID userId) {
		return utenteService.findById(userId);
	}

	@PutMapping("/{userId}")
	public Utente updateUser(@PathVariable UUID userId, @RequestBody NuovoUtentePayload body) {
		return utenteService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) {
		utenteService.findByIdAndDelete(userId);
	}
}
