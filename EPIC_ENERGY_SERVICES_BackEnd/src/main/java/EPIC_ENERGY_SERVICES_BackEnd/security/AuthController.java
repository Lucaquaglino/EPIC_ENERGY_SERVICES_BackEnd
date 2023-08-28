package EPIC_ENERGY_SERVICES_BackEnd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import EPIC_ENERGY_SERVICES_BackEnd.utente.Utente;
import EPIC_ENERGY_SERVICES_BackEnd.exceptions.UnauthorizedException;
import EPIC_ENERGY_SERVICES_BackEnd.utente.LoginSuccessfullPayload;
import EPIC_ENERGY_SERVICES_BackEnd.utente.NuovoUtentePayload;
import EPIC_ENERGY_SERVICES_BackEnd.utente.UtenteLoginPayload;
import EPIC_ENERGY_SERVICES_BackEnd.utente.UtenteService;

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
}
