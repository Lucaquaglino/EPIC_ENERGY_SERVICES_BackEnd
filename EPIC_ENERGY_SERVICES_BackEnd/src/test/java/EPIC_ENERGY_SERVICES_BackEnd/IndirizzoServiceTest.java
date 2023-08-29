package EPIC_ENERGY_SERVICES_BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;


import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.Indirizzo;

import EPIC_ENERGY_SERVICES_BackEnd.entities.indirizzo.IndirizzoService;




import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") 
@Transactional 
public class IndirizzoServiceTest {

    @Autowired
    private IndirizzoService indirizzoService;



    @Test
    public void testCreateIndirizzo() throws NotFoundException {
        String via = "Via Roma";
        String civico = "12";
        String localita = "Centro";
        int cap = 12345;
        String nomeComune = "Roma";

        Indirizzo indirizzo = indirizzoService.create(via, civico, localita, cap, nomeComune);

        assertNotNull(indirizzo);
        assertEquals(via, indirizzo.getVia());
        assertEquals(civico, indirizzo.getCivico());
        assertEquals(localita, indirizzo.getLocalita());
        assertEquals(cap, indirizzo.getCap());
        assertEquals(nomeComune, indirizzo.getComune().getNomeComune());
    }

   

}