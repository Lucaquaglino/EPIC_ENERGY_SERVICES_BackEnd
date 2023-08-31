package EPIC_ENERGY_SERVICES_BackEnd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.Fattura;
import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.FatturaPayload;
import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.FatturaService;
import EPIC_ENERGY_SERVICES_BackEnd.entities.fattura.StatoFattura;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class FatturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FatturaService fatturaService;

    private final String mockToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNjEzMGIyMC04YjRiLTQ0YjUtYWI3MS03ZWZmZTUwNjFkN2IiLCJpYXQiOjE2OTM0OTU0ODAsImV4cCI6MTY5NDEwMDI4MH0.ztVZDe2LxrW8UfiK5sYeEGtGy1ozK7Jow6G3NuBG3i4";  // Questo è un token di esempio, non effettivamente valido

    @Test
    public void getAllInvoicesTest() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/fattura")
               .header("Authorization", "Bearer " + mockToken))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getInvoiceByIdTest() throws Exception {
        
        UUID id = UUID.randomUUID();

        when(fatturaService.findById(id)).thenReturn(new Fattura());

        mockMvc.perform(MockMvcRequestBuilders.get("/fattura/" + id.toString())
               .header("Authorization", "Bearer " + mockToken))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andDo(MockMvcResultHandlers.print());
    }
}
