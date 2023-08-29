package EPIC_ENERGY_SERVICES_BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EpicEnergyServicesBackEndApplication {

	@Autowired
	private ProvinciaRunner provinceRunner;

	@Autowired
	private ComuneRunner comuneRunner;

	public static void main(String[] args) {
		SpringApplication.run(EpicEnergyServicesBackEndApplication.class, args);
//		String csvFilePath = "comuni-italiani.csv";
//		int rowCount = countRows(csvFilePath);
//		System.out.println("Numero di righe nel file CSV: " + rowCount);
//	}
//
//	public static int countRows(String filePath) {
//		int rowCount = 0;
//		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				rowCount++;
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return rowCount;
//	}

	}

//	@PostConstruct
//	public void init() throws Exception {
//		provinceRunner.run();
//		comuneRunner.run();
//	}
}
