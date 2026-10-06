package edu.moranQuispeSebastian;

public class Ticket {

    private final int id;
    private final String descripcion;
    private boolean cerrado;

    public Ticket(int id, String descripcion, boolean cerrado) {
        if (id <= 0) {
            throw new IllegalArgumentException("Error: el identificador debe ser positivo");
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("Error: el descripcion no puede estar vacia");
        }
        this.id = id;
        this.descripcion = descripcion;
        this.cerrado = cerrado;
    }

    public void cerrar() {
         cerrado = true;
    }

    public boolean estaCerrado() {

        return cerrado;

    }

    public int getId() {

        return id;

    }

    public String getDescripcion() {

        return descripcion;

    }

}


