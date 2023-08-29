package EPIC_ENERGY_SERVICES_BackEnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.Comune;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneRepository;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.ProvinciaService;

@Component
@Order(1)
public class ComuneRunner implements CommandLineRunner {

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	ComuneService comuneService;

	@Autowired
	ComuneRepository comuneRepo;

	@Override
	public void run(String... args) throws Exception {
		String csvFilePath = "comuni-italiani.csv";
		List<Comune> comuniDb = comuneRepo.findAll();
		if (comuniDb.isEmpty()) {
			try {
				File csvFile = new File(csvFilePath);
				Scanner scanner = new Scanner(csvFile);

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] values = line.split(";");
					if (values.length >= 4) {
						String codiceProvincia = values[0];
						String codiceComune = values[1];
						String nomeComune = values[2];
						String provinciaStr = values[3];
						Provincia provincia = provinciaService.findById(provinciaStr);

						comuneService.create(codiceProvincia, codiceComune, nomeComune, provincia);
					}
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
