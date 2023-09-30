package EPIC_ENERGY_SERVICES_BackEnd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.LoginSuccessfullPayload;
import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.NuovoUtentePayload;
import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.Utente;
import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.UtenteLoginPayload;
import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.UtenteService;
import EPIC_ENERGY_SERVICES_BackEnd.exceptions.UnauthorizedException;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UtenteService utenteService;

	@Autowired
	JWTTools jwtTools;

	@Autowired
	PasswordEncoder bcrypt;

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public Utente saveUtente(@RequestBody NuovoUtentePayload body) {

		body.setPassword(bcrypt.encode(body.getPassword()));
		Utente created = utenteService.save(body);
		return created;
	}

	@PostMapping("/login")
	public LoginSuccessfullPayload login(@RequestBody UtenteLoginPayload body) {

		Utente utente = utenteService.findByEmail(body.getEmail());

		if (bcrypt.matches(body.getPassword(), utente.getPassword())) {
			String token = jwtTools.createToken(utente);
			return new LoginSuccessfullPayload(token);
		} else {
			throw new UnauthorizedException("Credenziali non valide");
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
		System.out.println("Logout effettuato con successo");
		return ResponseEntity.ok("Logout effettuato con successo");

	}
}
