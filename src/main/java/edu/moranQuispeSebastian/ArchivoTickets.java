package edu.moranQuispeSebastian;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ArchivoTickets {

    private final Path ruta;

    public ArchivoTickets(Path ruta) {
        this.ruta = ruta;
    }

    public void guardar(ArrayList<Ticket> tickets) throws IOException {
        ArrayList<String> lineas = new ArrayList<>();

        for (Ticket ticket : tickets) {
            String linea = ticket.getId() + ";"
                    + ticket.estaCerrado() + ";"
                    + ticket.getDescripcion();

            lineas.add(linea);
        }

        Files.write(ruta, lineas, StandardCharsets.UTF_8);
    }

    public ArrayList<Ticket> cargar() throws IOException {
        List<String> lineas;

        try {
            lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
        } catch (NoSuchFileException e) {

            return new ArrayList<>();
        }

        ArrayList<Ticket> recuperados = new ArrayList<>();

        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            int numeroLinea = i + 1;

            String[] partes = linea.split(";", 3);

            if (partes.length != 3) {
                throw new IOException("Línea " + numeroLinea + ": se esperaban tres campos.");
            }

            try {
                int id = Integer.parseInt(partes[0]);

                if (!partes[1].equals("true") && !partes[1].equals("false")) {
                    throw new IllegalArgumentException("El estado debe ser true o false.");
                }

                boolean cerrado = Boolean.parseBoolean(partes[1]);
                String descripcion = partes[2];

                Ticket ticket = new Ticket(id, descripcion, cerrado);

                recuperados.add(ticket);

            } catch (IllegalArgumentException e) {

                throw new IOException("Línea " + numeroLinea + ": " + e.getMessage(), e);
            }
        }

        return recuperados;


    }



}
