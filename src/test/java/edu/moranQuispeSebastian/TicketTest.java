package edu.moranQuispeSebastian;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testIdInvalido(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(0, "Fallo en red", false);
        });

    }

    @Test
    void testDescripcionVacia(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, "", false);
        });
    }

    @Test
    void testCerrarTicketDosVeces(){

        Ticket ticket = new Ticket(1, "Proyecto", false);
        ticket.cerrar();
        ticket.cerrar();
        assertTrue(ticket.estaCerrado());
    }

}
