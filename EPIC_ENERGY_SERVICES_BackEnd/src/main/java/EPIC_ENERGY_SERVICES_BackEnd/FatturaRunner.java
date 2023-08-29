//package EPIC_ENERGY_SERVICES_BackEnd;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.Cliente;
//import EPIC_ENERGY_SERVICES_BackEnd.entities.Cliente.ClienteService;
//import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.Fattura;
//import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.FatturaService;
//import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.StatoFattura;
//
//@Component
//public class FatturaRunner implements CommandLineRunner{
//
//	@Autowired
//	FatturaService fs;
//	
//	@Autowired
//	ClienteService cs;
//
//	@Override
//	public void run(String... args) throws Exception {
//		
//		Cliente cliente1 = new Cliente(null, null, 0, null, null, 0, null, null, null, null, null, 0, null, null)
// 		
//		Fattura fattura1 = new Fattura(2023, LocalDate.now(), (new BigDecimal("845.695")), "Da pagare");
//		
//		cs.creaCliente(cliente1)
//		fs.create(fattura1);
//		
//	}
//	
//}
