package EPIC_ENERGY_SERVICES_BackEnd;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.Comune;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneRepository;
import EPIC_ENERGY_SERVICES_BackEnd.entities.comune.ComuneService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ComuneServiceTest {

    @Autowired
    private ComuneService comuneService;

    @Autowired
    private ComuneRepository comuneRepo;

    private Comune comuneTest;

    @BeforeEach
    public void setUp() {
        Provincia provinciaTest = new Provincia(); 
        comuneTest = comuneService.create("CodiceProvinciaTest", "CodiceComuneTest", "NomeComuneTest", provinciaTest);
    }

    @Test
    public void testCreateComune() {
        Comune foundComune = comuneRepo.findById(comuneTest.getId()).orElse(null);
        assertNotNull(foundComune);
        assertEquals(comuneTest.getNomeComune(), foundComune.getNomeComune());
    }


    @Test
    public void testFindComuneById() {
        Comune foundComune = null;
        try {
            foundComune = comuneService.findById(comuneTest.getId());
        } catch (NotFoundException e) {
            fail("NotFoundException was thrown");
        }
        assertNotNull(foundComune);
        assertEquals(comuneTest.getNomeComune(), foundComune.getNomeComune());
    }

}
