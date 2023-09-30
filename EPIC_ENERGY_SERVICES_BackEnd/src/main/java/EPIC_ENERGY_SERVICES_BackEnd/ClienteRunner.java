package EPIC_ENERGY_SERVICES_BackEnd;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.Cliente;
import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.ClienteRepository;
import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.ClienteService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.TIPO_CLIENTE;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.Indirizzo;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.IndirizzoService;

@Component
@DependsOn("indirizzoRunner")
public class ClienteRunner implements CommandLineRunner {

	@Autowired
	ClienteService clienteService;

	@Autowired
	ClienteRepository clienteRepo;

	@Autowired
	ComuneService comuneService;

	@Autowired
	IndirizzoService indirizzoService;

	@Override
	public void run(String... args) throws Exception {
		List<Cliente> clientiDB = clienteRepo.findAll();
		Faker faker = new Faker(new Locale("it"));
		if (clientiDB.isEmpty()) {
			for (int y = 0; y < 100; y++) {
				Cliente clientePayload = new Cliente();
				clientePayload.setRagioneSociale(faker.company().name());
				clientePayload.setPartitaIva(faker.number().numberBetween(100000000, 999999999));
				clientePayload.setEmailCliente(faker.internet().emailAddress());
				clientePayload.setPec(UUID.randomUUID().toString());
				clientePayload.setTelefonoCliente(faker.number().numberBetween(1000000000, 999999999));
				clientePayload.setTipoCliente(faker.options().option(TIPO_CLIENTE.class));

				clientePayload.setNomeContatto(faker.name().firstName());
				clientePayload.setCognomeContatto(faker.name().lastName());
				clientePayload.setEmailContatto(faker.internet().emailAddress());
				int telefonoContatto = faker.number().numberBetween(1000000000, 99999999);
				clientePayload.setTelefonoContatto(telefonoContatto);

				List<Indirizzo> allIndirizzi = indirizzoService.find();

				Random rand = new Random();
				int randomNumber1 = allIndirizzi.size() > 0 ? rand.nextInt(allIndirizzi.size()) : 0;

				int randomNumber2 = allIndirizzi.size() > 0 ? rand.nextInt(allIndirizzi.size()) : 0;

				Indirizzo indLegale = allIndirizzi.get(randomNumber1);
				clientePayload.setIndirizzoSedeLegale(indLegale);
				Indirizzo indSedeOperativa = allIndirizzi.get(randomNumber2);
				clientePayload.setIndirizzoSedeOperativa(indSedeOperativa);

				// Genera date casuali
				LocalDate dataInserimento = LocalDate.of(faker.number().numberBetween(2012, 2023),
						faker.number().numberBetween(1, 12), faker.number().numberBetween(1, 28));
				LocalDate dataUltimoContatto = dataInserimento.plusDays(faker.number().numberBetween(1, 1000));
				clientePayload.setDataInserimento(dataInserimento);
				clientePayload.setUltimoContatto(dataUltimoContatto);

				clientePayload.setFatturatoAnnuale(faker.number().randomDouble(2, 1000, 1000000));
				// Salva il cliente utilizzando il repository
				clienteRepo.save(clientePayload);
			}
		}
	}
}