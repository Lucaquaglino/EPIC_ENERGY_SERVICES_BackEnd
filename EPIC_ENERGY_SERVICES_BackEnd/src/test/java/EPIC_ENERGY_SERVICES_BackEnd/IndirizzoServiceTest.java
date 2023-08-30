package EPIC_ENERGY_SERVICES_BackEnd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.Comune;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.Indirizzo;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.IndirizzoRepository;
import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.IndirizzoService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class IndirizzoServiceTest {

    @Autowired
    private IndirizzoService indirizzoService;

    @Autowired
    private IndirizzoRepository indirizzoRepo;

    @Autowired
    private ComuneService comuneService;

    private Indirizzo indirizzoTest;
    private Comune comuneTest;

    @BeforeEach
    public void setUp() {
        Provincia provinciaTest = new Provincia(); 
        comuneTest = comuneService.create("CodiceProvincia", "CodiceComune", "NomeComune", provinciaTest);
        
        indirizzoTest = new Indirizzo("Via Roma", "10", "Roma", 12345, comuneTest);
        indirizzoRepo.save(indirizzoTest);
    }
    
    @Test
    public void testFindById() throws Exception {
        Indirizzo foundIndirizzo = indirizzoService.findById(indirizzoTest.getId().toString());
        assertNotNull(foundIndirizzo);
        assertEquals(indirizzoTest.getVia(), foundIndirizzo.getVia());
    }

}