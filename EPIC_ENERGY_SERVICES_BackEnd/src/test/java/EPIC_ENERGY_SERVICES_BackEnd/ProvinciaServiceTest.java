package EPIC_ENERGY_SERVICES_BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.Provincia;

import EPIC_ENERGY_SERVICES_BackEnd.entities.provincia.ProvinciaService;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") 
@Transactional 
public class ProvinciaServiceTest {

    @Autowired
    private ProvinciaService provinciaService;


  

    @Test
    public void testCreateProvincia() {
        Provincia result = provinciaService.create("CS", "Cosa", "Cacca");
        assertNotNull(result);
        assertEquals("CS", result.getSigla());
        assertEquals("Cosa", result.getProvincia());
        assertEquals("Cacca", result.getRegione());
    }
    
 
    @Test
    public void testFindById() {
        Provincia result = null;
        try {
            result = provinciaService.findById("Salerno");
        } catch (NotFoundException e) {
            fail("NotFoundException should not have been thrown");
        }
        assertEquals("Salerno", result.getProvincia());
    }

}
