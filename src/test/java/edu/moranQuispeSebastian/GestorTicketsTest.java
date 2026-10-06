package edu.moranQuispeSebastian;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GestorTicketsTest {

    @Test
    void testColeccionInicialmenteVacia() {
        GestorTickets gestor = new GestorTickets();
        assertTrue(gestor.getTickets().isEmpty());
    }

    @Test
    void testIdentificadoresConsecutivos() {
        GestorTickets gestor = new GestorTickets();
        gestor.crearTickets("Fallo 1");
        gestor.crearTickets("Fallo 2");

        assertEquals(1, gestor.getTickets().get(0).getId());
        assertEquals(2, gestor.getTickets().get(1).getId());
    }

    @Test
    void testBusquedaMismoObjeto() {
        GestorTickets gestor = new GestorTickets();
        gestor.crearTickets("Teclado roto");

        Ticket original = gestor.getTickets().get(0);
        Ticket encontrado = gestor.encontrarId(1);

        assertSame(original, encontrado);
    }

    @Test
    void testBusquedaInexistente() {
        GestorTickets gestor = new GestorTickets();
        Ticket t = gestor.encontrarId(99);
        assertNull(t);
    }

    @Test
    void testCreacionInvalidaSinAlterarColeccionNiContador() {
        GestorTickets gestor = new GestorTickets();

        assertThrows(IllegalArgumentException.class, () -> gestor.crearTickets("   "));
        assertEquals(0, gestor.getTotalTickets());

        gestor.crearTickets("Ahora sí es válida");
        assertEquals(1, gestor.encontrarId(1).getId());
    }

    @Test
    void testProteccionListaInterna() {
        GestorTickets gestor = new GestorTickets();
        gestor.crearTickets("Cable pelado");

        gestor.getTickets().clear();

        assertEquals(1, gestor.getTotalTickets());
    }


    @Test
    void testEstadisticasGestorVacio() {
        GestorTickets gestor = new GestorTickets();
        assertEquals(0, gestor.getTotalTickets());
        assertEquals(0, gestor.getTicketsAbiertos());
        assertEquals(0, gestor.getTicketsCerrados());
    }

    @Test
    void testEstadisticasDosAbiertas() {
        GestorTickets gestor = new GestorTickets();
        gestor.crearTickets("Ratón no va");
        gestor.crearTickets("Proyector fundido");

        assertEquals(2, gestor.getTotalTickets());
        assertEquals(2, gestor.getTicketsAbiertos());
        assertEquals(0, gestor.getTicketsCerrados());
    }

    @Test
    void testEstadisticasDosConUnaCerrada() {
        GestorTickets gestor = new GestorTickets();
        gestor.crearTickets("Error Windows");
        gestor.crearTickets("Error Linux");

        gestor.encontrarId(1).cerrar();

        assertEquals(2, gestor.getTotalTickets());
        assertEquals(1, gestor.getTicketsAbiertos());
        assertEquals(1, gestor.getTicketsCerrados());
    }
}
