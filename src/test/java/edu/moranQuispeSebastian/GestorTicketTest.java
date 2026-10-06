package edu.moranQuispeSebastian;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GestorTicketTest {

    @Test
    void testProteccionEncapsulación() {

        GestorTickets gestor = new GestorTickets();
        gestor.crearTickets("Sección no disponible");

        gestor.getTickets().clear();

        assertEquals(1, gestor.getTotalTickets());
    }
}