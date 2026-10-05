package edu.moranQuispeSebastian;

public class Ticket {

    private int id;
    private String descripcion;
    private boolean cerrado;

    public Ticket(int id, String descripcion, boolean cerrado) {
        if (id <= 0) {
            throw new IllegalArgumentException("Error: el identificador no puede ser negativo");
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("Error: el descripcion no puede estar vacia");
        }
        this.id = id;
        this.descripcion = descripcion;
        this.cerrado = cerrado;
    }

    public void cerrar() {

        if (cerrado == false) {
            cerrado = true;
        } else {
            System.out.println("El ticket ya esta cerrado");
        }

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


