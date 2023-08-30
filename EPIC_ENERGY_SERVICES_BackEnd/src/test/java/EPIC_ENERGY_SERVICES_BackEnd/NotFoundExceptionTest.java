package EPIC_ENERGY_SERVICES_BackEnd;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import EPIC_ENERGY_SERVICES_BackEnd.exceptions.NotFoundException;

public class NotFoundExceptionTest {

    @Test
    public void testMessaggioId() {
        UUID id = UUID.randomUUID();
        NotFoundException exception = new NotFoundException(id);

        assertEquals("Siamo spiacenti l'ID: " + id + " non è stato trovato", exception.getMessage());
    }

    @Test
    public void testMessaggioString() {
        String message = "Not found error";
        NotFoundException exception = new NotFoundException(message);

        assertEquals(message, exception.getMessage());
    }
}
