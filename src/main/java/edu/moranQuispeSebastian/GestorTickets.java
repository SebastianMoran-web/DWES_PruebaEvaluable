package edu.moranQuispeSebastian;
import java.util.ArrayList;

public class GestorTickets {

    private ArrayList<Ticket> tickets = new ArrayList<>();

    private int idSiguiente = 1;

    public Ticket encontrarId(int idBuscado) {

        for (Ticket ticket : tickets) {

            if (ticket.getId() == idBuscado) {

                return ticket;

            }
        }
        return null;
    }


    public void crearTickets(String descripcion) {
        Ticket nuevoTicket = new Ticket(idSiguiente, descripcion, false);
        tickets.add(nuevoTicket);
        idSiguiente++;
    }

    public ArrayList<Ticket> getTickets() {

        return new ArrayList<>(tickets);
    }

    public int getTotalTickets() {

        return tickets.size();
    }

    public int getTicketsCerrados() {

        int contador = 0;

        for (Ticket ticket : tickets) {

            if (ticket.estaCerrado()) {

                contador++;
            }
        } return contador;
    }

    public int getTicketsAbiertos () {

        return getTotalTickets() - getTicketsCerrados();
    }
    public void guardarDatos(ArrayList<Ticket> recuperados) {
        this.tickets = (recuperados != null) ? recuperados : new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.getId() >= idSiguiente) {
                idSiguiente = ticket.getId() + 1;
            }
        }
    }










}
