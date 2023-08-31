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

import java.util.UUID;

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
        UUID idProvincia = UUID.fromString("00e38250-a5bc-4683-95e8-f1c268676a31") ;

        try {
            Provincia result = provinciaService.findById(idProvincia);
            assertEquals(idProvincia, result.getId());

        } catch (NotFoundException e) {
            fail("NotFoundException should not have been thrown");
        }
    }
    
    @Test
    public void testFindByName() throws NotFoundException {
        Provincia result = null;
        result = provinciaService.findByName("Siracusa");
        assertEquals("Siracusa", result.getProvincia());
    }

}
