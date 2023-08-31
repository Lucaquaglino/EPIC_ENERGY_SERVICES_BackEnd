package EPIC_ENERGY_SERVICES_BackEnd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class UtenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUtenti() throws Exception {

        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzNjEzMGIyMC04YjRiLTQ0YjUtYWI3MS03ZWZmZTUwNjFkN2IiLCJpYXQiOjE2OTM0OTU0ODAsImV4cCI6MTY5NDEwMDI4MH0.ztVZDe2LxrW8UfiK5sYeEGtGy1ozK7Jow6G3NuBG3i4";
        
        mockMvc.perform(MockMvcRequestBuilders.get("/utenti")
                    .header("Authorization", "Bearer " + jwtToken))
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    public void testLogin() throws Exception {

        String loginPayload = "{\"email\":\"adriano.colombo@hotmail.com\", \"password\":\"1234\"}";
        
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                    .contentType("application/json")
                    .content(loginPayload))
               .andExpect(status().isOk())
               .andDo(print());
    }
}
   