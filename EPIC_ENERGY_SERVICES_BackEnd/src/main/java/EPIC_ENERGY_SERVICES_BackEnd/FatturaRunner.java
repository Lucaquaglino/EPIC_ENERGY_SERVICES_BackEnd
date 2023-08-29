package EPIC_ENERGY_SERVICES_BackEnd;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.Fattura;
import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.FatturaService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.StatoFattura;

@Component
public class FatturaRunner implements CommandLineRunner{

	@Autowired
	FatturaService fattSrv;
	
	@Autowired
	FatturaService fs;

	@Override
	public void run(String... args) throws Exception {
		
		Fattura fattura = new Fattura(2023, LocalDate.now(), (new BigDecimal("845.695")), StatoFattura.INVIATO);
		
		fs.create(fattura);
		
	}
	
}
