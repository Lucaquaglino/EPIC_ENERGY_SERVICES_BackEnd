package EPIC_ENERGY_SERVICES_BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.FatturaService;

@Component
public class FatturaRunner implements CommandLineRunner{

	@Autowired
	FatturaService fattSrv;
	
	@Autowired
	FatturaService fs;

	@Override
	public void run(String... args) throws Exception {
		
	}
	
}
