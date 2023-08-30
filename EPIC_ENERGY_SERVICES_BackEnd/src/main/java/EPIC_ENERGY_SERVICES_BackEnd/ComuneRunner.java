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

import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.Comune;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneRepository;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.ProvinciaService;

@Component
@Order(2)
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
				FileReader fileReader = new FileReader(csvFile);

				CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').parse(fileReader);
				List<CSVRecord> records = csvParser.getRecords();

				System.out.println("numero righe csv: " + records.size());

				for (CSVRecord record : records) {
					if (record.size() >= 4) {
						String codiceProvincia = record.get(0);
						String codiceComune = record.get(1);
						String nomeComune = record.get(2);
						String provinciaStr = record.get(3);
						if (provinciaStr.equalsIgnoreCase("Verbano-Cusio-Ossola")) {
							provinciaStr = "verbania";
						}
						Provincia provincia = provinciaService.findByName(provinciaStr);

						comuneService.create(codiceProvincia, codiceComune, nomeComune, provincia);
					}

				}

				csvParser.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
