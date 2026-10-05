package edu.moranQuispeSebastian;
import java.util.ArrayList;

public class GestorTickets {

    ArrayList<Ticket> tickets = new ArrayList<>();

    private int idSiguiente = 1;

    private Ticket encontrarId(int idBuscado) {

        for (Ticket ticket : tickets) {

            if (ticket.getId() == idBuscado) {

                return ticket;

                }
            }
        return null;
    }






    public void crearTickets(String descripcion) {

        try{

            Ticket nuevoTicket = new Ticket(idSiguiente, descripcion, false);

            tickets.add(nuevoTicket);
            idSiguiente++;

            System.out.println("Ticket guardado");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }







}
