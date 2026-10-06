package edu.moranQuispeSebastian;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class AplicacionHelpDesk {

    private static Scanner teclado = new Scanner(System.in);
    private static GestorTickets gestor = new GestorTickets();
    private static ArchivoTickets archivo = new ArchivoTickets(Path.of("tickets.txt"));

    public static void main(String[] args) {

        try{
            ArrayList<Ticket> cargados = archivo.cargar();
            gestor.guardarDatos(cargados);
        }catch (IOException e) {

            System.out.println("Error al cargar archivo");
            System.out.println(e.getMessage());
            return;
        }

        int opcion =-1;

        do {
            imprimirMenu();

            if (teclado.hasNextInt()) {

                opcion = teclado.nextInt();
                teclado.nextLine();
                ejecutarOpcion(opcion);
            } else {
                System.out.println("Opcion no valida");
                teclado.nextLine();
            }
        }while (opcion != 0) ;

        }

    private static void imprimirMenu() {
        System.out.println("\nHELPDESK DEL CENTRO");
        System.out.println("1. Crear incidencia");
        System.out.println("2. Listar incidencias");
        System.out.println("3. Buscar incidencia por identificador");
        System.out.println("4. Cerrar incidencia");
        System.out.println("5. Mostrar estadísticas");
        System.out.println("6. Guardar incidencias");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> crearIncidencia();
            case 2 -> listaIncidencias();
            case 3 -> buscarIncidencia();
            case 4 -> cerrarIncidencia();
            case 5 -> mostratEstadisticas();
            case 6 -> guardarDatos();
            case 0 -> System.out.println("Cerrando HelpDesk.... Recuerda que los cambios no guardados se perderán");
            default -> System.out.println("Error:Opción no valida");
        }

    }

    private static void crearIncidencia() {
        System.out.println("Introduce la descripcion de la incidencia");
        String descripcion = teclado.nextLine();

        try {
            gestor.crearTickets(descripcion);
            System.out.println("Ticket guardado");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listaIncidencias() {
        ArrayList<Ticket> lista = gestor.getTickets();
        if(lista.isEmpty()){
            System.out.println("No hay incidencias");
            return;
        }
        for (Ticket ticket : lista) {
            String estado = ticket.estaCerrado() ? "Cerrado" : "Abierta";
            System.out.println("ID: " + ticket.getId() + "| Estado: " +  estado + "| Desc: " + ticket.getDescripcion());

        }

    }

    private static void buscarIncidencia() {
        System.out.println("Introduce el ID de la  incidencia: ");
        int id = leerId();
        Ticket ticket = gestor.encontrarId(id);
        if (ticket == null) {
            System.out.println("No se encontró el incidencia");
        } else {
            String estado = ticket.estaCerrado() ? "Cerrado" : "Abierta";
            System.out.println("ID: " + ticket.getId() + "| Estado: " + estado);
        }
    }
    private static void cerrarIncidencia(){
            System.out.println("Introduce el ID de la  incidencia para cerrarla: ");
            int id = leerId();
            Ticket ticket = gestor.encontrarId(id);
            if (ticket == null) {
                System.out.println("No se encontró la incidencia");
            }else if (ticket.estaCerrado()) {
                System.out.println("La Incidencia ya esta cerrada");
            }else {
                ticket.cerrar();
                System.out.println("Operación realizada");
            }
        }

    private static void mostratEstadisticas() {
        System.out.println("Total de Incidencias: " + gestor.getTotalTickets());
        System.out.println("Incidencias Abiertas: " + gestor.getTicketsAbiertos());
        System.out.println("Incidencias Cerradas: " + gestor.getTicketsCerrados());

    }

    private static void guardarDatos() {
        try{
            archivo.guardar(gestor.getTickets());
            System.out.println("Datos Guardados");
        }catch (IOException e) {
            System.out.println("Error al guardar el archivo" +  e.getMessage());
        }
    }

    private static int leerId() {
        while(!teclado.hasNextInt()) {
            System.out.println("Dato no valido. Introduce un ID valido");
            teclado.nextLine();
        }
        int id = teclado.nextInt();
        teclado.nextLine();
        return id;

    }

}
