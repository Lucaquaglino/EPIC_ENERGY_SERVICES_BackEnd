package EPIC_ENERGY_SERVICES_BackEnd;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import EPIC_ENERGY_SERVICES_BackEnd.utente.Utente;
import EPIC_ENERGY_SERVICES_BackEnd.utente.NuovoUtentePayload;
import EPIC_ENERGY_SERVICES_BackEnd.utente.Ruolo;
import EPIC_ENERGY_SERVICES_BackEnd.utente.UtenteRepository;

import EPIC_ENERGY_SERVICES_BackEnd.utente.UtenteService;

@Component
public class UtenteRunner implements CommandLineRunner {

	@Autowired
	UtenteService userService;

	@Autowired
	UtenteRepository utenteRepo;

	
	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker(new Locale("it"));

		List<Utente> utentiDb = utenteRepo.findAll();
		if (utentiDb.isEmpty()) {

			for (int i = 0; i < 10; i++) {
				String username = faker.funnyName().name();
				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String email = faker.internet().emailAddress();
				String password = "1234";
				Ruolo ruolo = (i % 2 == 0) ? Ruolo.UTENTE : Ruolo.ADMIN;
				
				
				NuovoUtentePayload utente = new NuovoUtentePayload(username, name, surname, email, password, ruolo);
				userService.save(utente);


			}
		}

	}

}