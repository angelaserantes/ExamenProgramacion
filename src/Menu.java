import java.time.LocalDate;
import java.util.*;
import java.io.*;
public class Menu {
    private ArrayList<Plato> platos;

    public Menu() {
        platos = new ArrayList<Plato>();
    }

    public static void  guardarMenuEnHistorico() {
        try (PrintWriter out = new PrintWriter(new FileWriter("menús_historico.txt", true))) {
            out.println("Fecha del menú: " + LocalDate.now());

        } catch (IOException e) {
            System.out.println("Error al guardar menú en histórico: " + e.getMessage());
        }
    }

    public void guardarPlatoEnArchivo(Plato plato, String nombreArchivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            out.println(plato.toString());
        } catch (IOException e) {
            System.out.println("Error al guardar plato en archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
    public void addPlato(Plato plato) {
        platos.add(plato);
        //guardar plato en su archivo correspondiente
        if (plato.getTipo() == TipoPlato.ENTRANTE) {
            guardarPlatoEnArchivo(plato, "entrantes.txt");
        } else if (plato.getTipo() == TipoPlato.PRIMER_PLATO) {
            guardarPlatoEnArchivo(plato, "primeros.txt");
        } else if (plato.getTipo() == TipoPlato.SEGUNDO_PLATO) {
            guardarPlatoEnArchivo(plato, "segundos.txt");
        } else if (plato.getTipo() == TipoPlato.POSTRE) {
            guardarPlatoEnArchivo(plato, "postres.txt");
        }
    }

    public ArrayList<Plato> getPlatos() {
        return platos;
    }
    public void generarMenuAleatorio(String entrantesFile, String primerosFile, String segundosFile, String postresFile) {
        try {
            // Abrir los archivos de texto
            BufferedReader entrantesReader = new BufferedReader(new FileReader(entrantesFile));
            BufferedReader primerosReader = new BufferedReader(new FileReader(primerosFile));
            BufferedReader segundosReader = new BufferedReader(new FileReader(segundosFile));
            BufferedReader postresReader = new BufferedReader(new FileReader(postresFile));
            String lineaprimeros;
            String lineaentrantes;
            String lineasegundos;
            String lineapostre;


            // Seleccionar un entrante aleatorio
            lineaentrantes = entrantesReader.readLine();
            int numEntrantes = 0;
            while (lineaentrantes != null) {
                numEntrantes++;
                lineaentrantes = entrantesReader.readLine();
            }
            entrantesReader.close();
            int entranteIndex = (int) (Math.random() * numEntrantes);
            entrantesReader = new BufferedReader(new FileReader(entrantesFile));
            for (int i = 0; i < entranteIndex; i++) {
                entrantesReader.readLine();
            }
            lineaentrantes = entrantesReader.readLine();
            Entrante entrante = new Entrante(lineaentrantes);

            // Seleccionar un primer plato aleatorio
            lineaprimeros = primerosReader.readLine();
            int numPrimeros = 0;
            while (lineaprimeros != null) {
                numPrimeros++;
                lineaprimeros = primerosReader.readLine();
            }
            primerosReader.close();
            int primerIndex = (int) (Math.random() * numPrimeros);
            primerosReader = new BufferedReader(new FileReader(primerosFile));
            for (int i = 0; i < primerIndex; i++) {
                primerosReader.readLine();
            }
            lineaprimeros = primerosReader.readLine();
            Primero primero = new Primero(lineaprimeros);

            // Seleccionar un segundo plato aleatorio
            lineasegundos = segundosReader.readLine();
            int numSegundos = 0;
            while (lineasegundos != null) {
                numSegundos++;
                lineasegundos = segundosReader.readLine();
            }
            segundosReader.close();
            int segundoIndex = (int) (Math.random() * numSegundos);
            segundosReader = new BufferedReader(new FileReader(segundosFile));
            for (int i = 0; i < segundoIndex; i++) {
                segundosReader.readLine();
            }
            lineasegundos = segundosReader.readLine();
            Segundo platoSegundo = new Segundo(lineasegundos);

            // Seleccionar un postre aleatorio
            lineapostre = postresReader.readLine();
            int numPostres = 0;
            while (lineapostre != null) {
                numPostres++;
                lineapostre = postresReader.readLine();
            }
            postresReader.close();
            int postreIndex = (int) (Math.random() * numPostres);
            postresReader = new BufferedReader(new FileReader(postresFile));
            for (int i = 0; i < postreIndex; i++) {
                postresReader.readLine();
            }
            lineapostre = postresReader.readLine();
            Postre postre = new Postre(lineapostre);

            // Agregar los platos al menú
            platos.add(entrante);
            platos.add(primero);
            platos.add(platoSegundo);
            platos.add(postre);

        } catch (IOException e) {
            System.out.println("Error al leer los ficheros de platos.");
        }
    }

    public void clear() {
    }
}