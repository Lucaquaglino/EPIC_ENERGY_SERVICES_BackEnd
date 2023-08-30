package EPIC_ENERGY_SERVICES_BackEnd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import EPIC_ENERGY_SERVICES_BackEnd.exceptions.BadRequestException;

public class BadRequestExceptionTest {

    @Test
    public void testMessaggio() {
        String message = "Bad request error";
        BadRequestException exception = new BadRequestException(message);

        assertEquals(message, exception.getMessage());
    }
}
