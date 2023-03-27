import java.util.*;
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        boolean menuGuardado = false;
        String menuFile = "menu.txt";

        System.out.println("¡Bienvenido al restaurante!");

        while (true) {
            System.out.println();
            System.out.println("Selecciona una opción:");
            System.out.println("1 - Generar un nuevo menú");
            System.out.println("2 - Crear un nuevo menú personalizado");
            System.out.println("3 - Ver el menú actual");
            System.out.println("4 - Guardar el menú actual");
            System.out.println("5 - Salir");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    if (menuGuardado) {
                        System.out.println("Atención: el menú actual será borrado al generar uno nuevo. ¿Estás seguro? (s/n)");
                        String confirmacion = scanner.nextLine().toLowerCase();
                        if (!confirmacion.equals("s")) {
                            continue;
                        }
                    }

                    menu.generarMenuAleatorio("entrantes.txt", "primeros.txt", "segundos.txt", "postres.txt");
                    System.out.println("Menú generado:");
                    System.out.println(menu);

                    menuGuardado = false;
                    break;

                case 2:
                    menu.clear();
                    System.out.println("Crea tu propio menú:");
                    System.out.println("Introduce el número de entrantes que deseas:");
                    int numEntrantes = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numEntrantes; i++) {
                        System.out.println("Introduce el nombre del entrante " + (i+1) + ":");
                        String nombreEntrante = scanner.nextLine();
                        Entrante entrante = new Entrante(nombreEntrante);
                        menu.addPlato(entrante);
                    }

                    System.out.println("Introduce el número de primeros platos que deseas:");
                    int numPrimeros = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numPrimeros; i++) {
                        System.out.println("Introduce el nombre del primer plato " + (i+1) + ":");
                        String nombrePrimer = scanner.nextLine();
                        Primero primer = new Primero(nombrePrimer);
                        menu.addPlato(primer);
                    }

                    System.out.println("Introduce el número de segundos platos que deseas:");
                    int numSegundos = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numSegundos; i++) {
                        System.out.println("Introduce el nombre del segundo plato " + (i+1) + ":");
                        String nombreSegundo = scanner.nextLine();
                        Segundo segundo = new Segundo(nombreSegundo);
                        menu.addPlato(segundo);
                    }

                    System.out.println("Introduce el número de postres que deseas:");
                    int numPostres = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numPostres; i++) {
                        System.out.println("Introduce el nombre del postre " + (i+1) + ":");
                        String nombrePostre = scanner.nextLine();
                        Postre postre = new Postre(nombrePostre);
                        menu.addPlato(postre);
                    }
                    System.out.println("Menú creado:");
                    System.out.println(menu);

                case 3:
                    if (menuGuardado) {
                        System.out.println("Menú guardado:");
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(menuFile));
                            String line = reader.readLine();
                            while (line != null) {
                                System.out.println(line);
                                line = reader.readLine();
                            }
                            reader.close();
                        } catch (IOException e) {
                            System.out.println("Error al leer el archivo del menú guardado.");
                        }
                    } else {
                        System.out.println("Menú actual:");
                        System.out.println(menu);
                    }
                    break;

                case 4:
                    if (menuGuardado) {
                        System.out.println("El menú actual ya está guardado.");
                        break;
                    }

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(menuFile));
                        for (Plato plato : menu.getPlatos()) {
                            writer.write(plato.toString());
                            writer.newLine();
                        }
                        writer.close();
                        System.out.println("Menú guardado correctamente en " + menuFile + ".");
                        menuGuardado = true;
                        Menu.guardarMenuEnHistorico();
                    } catch (IOException e) {
                        System.out.println("Error al guardar el menú.");
                    }
                    break;

                case 5:
                    System.out.println("¡Hasta luego!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Por favor, introduce una opción válida.");
                    break;
            }
        }
    }
}