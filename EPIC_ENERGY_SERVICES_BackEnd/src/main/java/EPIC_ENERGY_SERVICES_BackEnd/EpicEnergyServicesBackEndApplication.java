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
		
	}


}
