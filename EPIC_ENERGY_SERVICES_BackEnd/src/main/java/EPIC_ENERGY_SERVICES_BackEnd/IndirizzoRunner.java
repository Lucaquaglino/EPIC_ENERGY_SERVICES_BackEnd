package EPIC_ENERGY_SERVICES_BackEnd;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.Comune;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.Indirizzo;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.IndirizzoRepository;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.IndirizzoService;

@Component

public class IndirizzoRunner implements CommandLineRunner {

	@Autowired
	private IndirizzoService ind;

	@Autowired
	private IndirizzoRepository indRepo;

	@Autowired
	private ComuneService comuneService;

	@Override
	public void run(String... args) throws Exception {
		List<Indirizzo> indirizziDB = indRepo.findAll();

		Faker faker = new Faker(new Locale("it"));
		if (indirizziDB.isEmpty()) {
			List<Comune> comuni = comuneService.find();
			int comuniSize = comuni.size();

			for (int i = 0; i < 20; i++) {
				String via = faker.address().streetAddress();
				String civico = faker.address().streetAddressNumber();
				String localita = faker.address().cityName();
				String cap = faker.address().zipCode();

				int comuneIndex = faker.random().nextInt(comuniSize); // Scelta casuale di un comune
				Comune c = comuni.get(comuneIndex);

				ind.create(via, civico, localita, cap, c.getNomeComune());
			}
		}
	}
}
