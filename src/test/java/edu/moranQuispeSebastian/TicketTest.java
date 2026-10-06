package edu.moranQuispeSebastian;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testEstadoInicialAbierto(){
        Ticket ticket = new Ticket (1, "Problema", false);
        assertFalse(ticket.estaCerrado());
    }

    @Test
    void testCierre(){
        Ticket ticket = new Ticket (1, "Problema", false);
        ticket.cerrar();
        assertTrue(ticket.estaCerrado());
    }


    @Test
    void testRechazoIdentificadorCero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(0, "Fallo de red", false);
        });
    }

    @Test
    void testDescripcionVacia(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, "", false);
        });
    }

    @Test
    void testDescripcionNull(){

        assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, null , true);
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
