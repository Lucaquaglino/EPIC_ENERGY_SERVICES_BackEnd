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

        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYjllZTY2MS01Y2NlLTQ2ZjMtYjc1NC02NjU5N2MwZDE1MjYiLCJpYXQiOjE2OTM0MTE5NjUsImV4cCI6MTY5NDAxNjc2NX0.HiT0nvJ4OIganWtyY23ts6UX0ZFJXQ2KkYS8eOboyVI";
        
        mockMvc.perform(MockMvcRequestBuilders.get("/utenti")
                    .header("Authorization", "Bearer " + jwtToken))
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    public void testLogin() throws Exception {

        String loginPayload = "{\"email\":\"luca@quaglino.com\", \"password\":\"1234\"}";
        
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                    .contentType("application/json")
                    .content(loginPayload))
               .andExpect(status().isOk())
               .andDo(print());
    }
}
