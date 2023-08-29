package EPIC_ENERGY_SERVICES_BackEnd.entities.fattura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
	private final FatturaService fattSrv;
	
	@Autowired
	private FatturaController(FatturaService fattSrv) {
		super();
		this.fattSrv = fattSrv;
	}
}
