package EPIC_ENERGY_SERVICES_BackEnd;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.UUID;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.NuovoUtentePayload;
import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.Ruolo;
import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.Utente;
import EPIC_ENERGY_SERVICES_BackEnd.entities.utente.UtenteService;


@SpringBootTest
public class UtenteServiceTest {

   @Autowired
    private UtenteService utenteService;

   @Test
    public void testSaveUtente() {
        NuovoUtentePayload payload = new NuovoUtentePayload("testUsername2", "testNome2", "testCognome2", "test2@email.com", "testPassword2", Ruolo.ADMIN);
        
        Utente newUtente = utenteService.save(payload);
        assertNotNull(newUtente);
        assertEquals(payload.getUsername(), newUtente.getUsername());
        
    }

    @Test
    public void testGetUsers() {
        List<Utente> users = utenteService.getUsers();
        assertNotNull(users);
    }
    
    @Test
    public void testFindById() {
    	UUID userId = UUID.fromString("59aecd57-a2a4-45e5-9e7f-71433a4c5305");
        Utente user = utenteService.findById(userId);
        assertNotNull(user);
        assertEquals(userId, user.getId());
    }

    @Test
    public void testUpdateUser() {
    	UUID userId = UUID.fromString("59aecd57-a2a4-45e5-9e7f-71433a4c5305");
        NuovoUtentePayload payload = new NuovoUtentePayload("testUsername3", "testNome3", "testCognome3", "test3@email.com", "testPassword3", Ruolo.ADMIN);
        Utente updatedUser = utenteService.findByIdAndUpdate(userId, payload);
        assertNotNull(updatedUser);
      
    }

    @Test
    public void testDeleteUser() {
    	UUID userId = UUID.fromString("59aecd57-a2a4-45e5-9e7f-71433a4c5305");
        utenteService.findByIdAndDelete(userId);
        
    }

}
   