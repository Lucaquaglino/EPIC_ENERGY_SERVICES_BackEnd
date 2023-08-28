package EPIC_ENERGY_SERVICES_BackEnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.ProvinciaRepository;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.ProvinciaService;

@Component
@Order(0)
public class ProvinciaRunner implements CommandLineRunner {

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	ProvinciaRepository provinciaRepo;

	@Override
	public void run(String... args) throws Exception {
		String csvFilePath = "province-italiane.csv";
		List<Provincia> provinceDb = provinciaRepo.findAll();
		if (provinceDb.isEmpty()) {
			try {
				File csvFile = new File(csvFilePath);
				Scanner scanner = new Scanner(csvFile);

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] values = line.split(System.lineSeparator());
					for (String value : values) {
						String sigla = value.split(";")[0];
						String provincia = value.split(";")[1];
						String regione = value.split(";")[2];

						provinciaService.create(sigla, provincia, regione);
					}
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
