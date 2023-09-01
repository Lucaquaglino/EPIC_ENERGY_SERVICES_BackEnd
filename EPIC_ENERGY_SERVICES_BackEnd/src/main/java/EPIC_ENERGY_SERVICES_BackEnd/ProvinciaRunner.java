package EPIC_ENERGY_SERVICES_BackEnd;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.ProvinciaRepository;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.ProvinciaService;

@Component
@Order(1)
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
				FileReader fileReader = new FileReader(csvFile);

				CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').parse(fileReader);
				for (CSVRecord record : csvParser) {
					if (record.size() >= 3) {
						String sigla = record.get(0);
						String provincia = record.get(1);
						String regione = record.get(2);
						provincia = transformRegionName(provincia);
						provinciaService.create(sigla, provincia, regione);
					}
				}

				csvParser.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String transformRegionName(String provincia) {
		switch (provincia) {
		case "Carbonia Iglesias":
		case "Medio Campidano":
		case "Ogliastra":
		case "Olbia Tempio":
			return "Sud Sardegna";

		default:
			return provincia;
		}
	}
}
