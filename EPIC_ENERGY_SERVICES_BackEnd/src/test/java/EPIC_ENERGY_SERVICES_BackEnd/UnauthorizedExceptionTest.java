package EPIC_ENERGY_SERVICES_BackEnd;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import EPIC_ENERGY_SERVICES_BackEnd.exceptions.UnauthorizedException;

public class UnauthorizedExceptionTest {

    @Test
    public void shouldReturnCorrectMessage() {
        String message = "Accesso non autorizzato";
        UnauthorizedException exception = new UnauthorizedException(message);

        assertEquals(message, exception.getMessage());
    }
}
