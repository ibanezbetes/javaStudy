import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.
import java.util.Random; // Importa la clase Random para generar posiciones aleatorias.

public class Main {
    private static final int MAX_FILA_TABLERO = 10; // Tamaño máximo de filas del tablero.
    private static final int MAX_COLUMNA_TABLERO = 10; // Tamaño máximo de columnas del tablero.
    public static char[][] tablero1; // Tablero de juego 1.
    public static char[][] tablero2; // Tablero de juego 2.
    private static int filaYoda; // Posición actual de la fila de Yoda.
    private static int columnaYoda; // Posición actual de la columna de Yoda.
    private static int filaVader; // Posición actual de la fila de Vader.
    private static int columnaVader; // Posición actual de la columna de Vader.
    static int vidasVader = 3; // Cantidad inicial de vidas de Vader.
    static int vidasYoda = 3; // Cantidad inicial de vidas de Yoda.

    //* Repartir 5 pócimas en el tablero.
    //* Cuando el jugador coja en una de ellas...
    // El jugador podrá intercambiar su posición con CUALQUIER otra casilla que tenga una 'L' en el turno siguiente.

    private static void asignarLibresATablero1(char caracter) {
        for (int i = 0; i < MAX_FILA_TABLERO; i++) { // Recorre todas las filas del tablero1.
            for (int j = 0; j < MAX_COLUMNA_TABLERO; j++) { // Recorre todas las columnas del tablero1.
                tablero1[i][j] = caracter; // Asigna el carácter a la celda actual del tablero1.
            }
        }
    }

    private static void asignarLibresATablero2(char caracter) {
        for (int i = 0; i < MAX_FILA_TABLERO; i++) { // Recorre todas las filas del tablero2.
            for (int j = 0; j < MAX_COLUMNA_TABLERO; j++) { // Recorre todas las columnas del tablero2.
                tablero2[i][j] = caracter; // Asigna el carácter a la celda actual del tablero2.
            }
        }
    }

    private static void asignarPersonajesATablero1(char caracter, int numRepeticiones) {
        Random aleatorio = new Random(); // Objeto Random para generar posiciones aleatorias.
        int filaAleatorioYoda = -1; // Inicializa la fila aleatoria de Yoda.
        int columnaAleatorioYoda = -1; // Inicializa la columna aleatoria de Yoda.
        for (int i = 0; i < numRepeticiones; i++) { // Bucle para colocar cada personaje.
            do {
                filaAleatorioYoda = aleatorio.nextInt(MAX_FILA_TABLERO); // Genera una fila aleatoria.
                columnaAleatorioYoda = aleatorio.nextInt(MAX_COLUMNA_TABLERO); // Genera una columna aleatoria.
            } while (tablero1[filaAleatorioYoda][columnaAleatorioYoda] != 'L'); // Repite si no está libre.
            tablero1[filaAleatorioYoda][columnaAleatorioYoda] = caracter; // Coloca el carácter en la posición.
        }
        if (caracter == 'Y') { // Si el carácter es Yoda, almacena su posición inicial.
            filaYoda = filaAleatorioYoda;
            columnaYoda = columnaAleatorioYoda;
        }
    }

    private static void asignarPersonajesATablero2(char caracter, int numRepeticiones) {
        Random aleatorio = new Random(); // Objeto Random para generar posiciones aleatorias.
        int filaAleatorioVader = -1; // Inicializa la fila aleatoria de Vader.
        int columnaAleatorioVader = -1; // Inicializa la columna aleatoria de Vader.
        for (int i = 0; i < numRepeticiones; i++) { // Bucle para colocar cada personaje.
            do {
                filaAleatorioVader = aleatorio.nextInt(MAX_FILA_TABLERO); // Genera una fila aleatoria.
                columnaAleatorioVader = aleatorio.nextInt(MAX_COLUMNA_TABLERO); // Genera una columna aleatoria.
            } while (tablero2[filaAleatorioVader][columnaAleatorioVader] != 'L'); // Repite si no está libre.
            tablero2[filaAleatorioVader][columnaAleatorioVader] = caracter; // Coloca el carácter en la posición.
        }
        if (caracter == 'V') { // Si el carácter es Vader, almacena su posición inicial.
            filaVader = filaAleatorioVader;
            columnaVader = columnaAleatorioVader;
        }
    }

    private static void imprimirTablero1() {
        for (int i = 0; i < MAX_FILA_TABLERO; i++) { // Recorre todas las filas del tablero1.
            for (int j = 0; j < MAX_COLUMNA_TABLERO; j++) { // Recorre todas las columnas del tablero1.
                System.out.print(tablero1[i][j] + " "); // Imprime el contenido de la celda.
            }
            System.out.println(""); // Salto de línea al final de cada fila.
        }
        System.out.println(""); // Salto de línea adicional para separar.
        System.out.println("");
        System.out.println("");
    }

    private static void imprimirTablero2() {
        for (int i = 0; i < MAX_FILA_TABLERO; i++) { // Recorre todas las filas del tablero2.
            for (int j = 0; j < MAX_COLUMNA_TABLERO; j++) { // Recorre todas las columnas del tablero2.
                System.out.print(tablero2[i][j] + " "); // Imprime el contenido de la celda.
            }
            System.out.println(""); // Salto de línea al final de cada fila.
        }
        System.out.println(""); // Salto de línea adicional para separar.
        System.out.println("");
        System.out.println("");
    }

    public static void main(String[] args) {
        tablero1 = new char[MAX_FILA_TABLERO][MAX_COLUMNA_TABLERO]; // Inicializa tablero1.
        asignarLibresATablero1('L'); // Llena tablero1 con 'L'.
        tablero1[9][9] = 'F'; // Establece la meta ('F') en tablero1.
        asignarPersonajesATablero1('Y', 1); // Coloca a Yoda en tablero1.
        asignarPersonajesATablero1('D', 5); // Coloca 5 enemigos ('D') en tablero1.
        asignarPersonajesATablero1('M', 5); // Coloca 5 muros ('M') en tablero1.
        asignarPersonajesATablero1('P', 5); // Coloca 5 pociones ('P') en tablero1.

        tablero2 = new char[MAX_FILA_TABLERO][MAX_COLUMNA_TABLERO]; // Inicializa tablero2.
        asignarLibresATablero2('L'); // Llena tablero2 con 'L'.
        tablero2[9][9] = 'F'; // Establece la meta ('F') en tablero2.
        asignarPersonajesATablero2('V', 1); // Coloca a Vader en tablero2.
        asignarPersonajesATablero2('R', 5); // Coloca 5 enemigos ('R') en tablero2.
        asignarPersonajesATablero2('M', 5); // Coloca 5 muros ('M') en tablero2.
        asignarPersonajesATablero2('P', 5); // Coloca 5 pociones ('P') en tablero2.

        Scanner lector = new Scanner(System.in); // Crea un objeto Scanner para capturar la entrada del usuario.
        System.out.println("Introduce el nombre del jugador 1: "); // Pide nombre del jugador 1.
        Scanner J1 = new Scanner(System.in); // Scanner para capturar el nombre del jugador 1.
        String jugador1 = J1.nextLine(); // Almacena el nombre del jugador 1.
        System.out.println("Introduce el nombre del jugador 2: "); // Pide nombre del jugador 2.
        Scanner J2 = new Scanner(System.in); // Scanner para capturar el nombre del jugador 2.
        String jugador2 = J2.nextLine(); // Almacena el nombre del jugador 2.


        while (vidasYoda > 0 && vidasVader > 0) { // Bucle de juego, continúa hasta que uno pierda.
            imprimirTablero1(); // Imprime el tablero1.
            System.out.println("Es el turno de " + jugador1 + "(Y)"); // Muestra el turno de jugador1.
            String inputYoda = lector.nextLine().toUpperCase(); // Captura el movimiento de Yoda.
            int pasosYoda = inputYoda.length() > 1 ? Integer.parseInt(inputYoda.substring(0, inputYoda.length() - 1)) : 1; // Calcula los pasos.
            char direccionYoda = inputYoda.charAt(inputYoda.length() - 1); // Obtiene la dirección de movimiento.

            moverJugador(tablero1, 'Y', direccionYoda, pasosYoda); // Llama al método para mover Yoda.
            imprimirTablero1(); // Imprime el tablero1 actualizado.

            try {
                Thread.sleep(3000); // Pausa de 3 segundos entre turnos.
            } catch (InterruptedException e) {
                e.printStackTrace(); // Imprime la excepción si ocurre.
            }

            imprimirTablero2(); // Imprime el tablero2.
            System.out.println("Es el turno de " + jugador2 + "(V)"); // Muestra el turno de jugador2.
            String inputVader = lector.nextLine().toUpperCase(); // Captura el movimiento de Vader.
            int pasosVader = inputVader.length() > 1 ? Integer.parseInt(inputVader.substring(0, inputVader.length() - 1)) : 1; // Calcula los pasos.
            char direccionVader = inputVader.charAt(inputVader.length() - 1); // Obtiene la dirección de movimiento.

            moverJugador(tablero2, 'V', direccionVader, pasosVader); // Llama al método para mover Vader.
            imprimirTablero2(); // Imprime el tablero2 actualizado.

            try {
                Thread.sleep(3000); // Pausa de 3 segundos entre turnos.
            } catch (InterruptedException e) {
                e.printStackTrace(); // Imprime la excepción si ocurre.
            }
        }

        if (vidasYoda <= 0) { // Verifica si Yoda ha perdido todas las vidas.
            System.out.println(jugador1 + " ha perdido todas las vidas. GAME OVER.");
            System.out.println(jugador2 + " GG");
        } else if (vidasVader <= 0) { // Verifica si Vader ha perdido todas las vidas.
            System.out.println(jugador2 + " ha perdido todas las vidas. GAME OVER.");
            System.out.println(jugador1 + " GG");
        }
    }

//...continuación del método moverJugador y comentarios adicionales...

    private static void moverJugador(char[][] tablero, char jugador, char direccion, int pasos) {
        int fila = jugador == 'Y' ? filaYoda : filaVader; // Define la fila inicial del jugador.
        int columna = jugador == 'Y' ? columnaYoda : columnaVader; // Define la columna inicial.

        for (int i = 0; i < pasos; i++) { // Realiza el movimiento en función del número de pasos.
            int filaAnterior = fila; // Almacena la posición anterior de la fila.
            int columnaAnterior = columna; // Almacena la posición anterior de la columna.

            switch (direccion) { // Cambia la posición según la dirección.
                case 'A': // Movimiento a la izquierda.
                    columna = (columna - 1 + MAX_COLUMNA_TABLERO) % MAX_COLUMNA_TABLERO;
                    break;
                case 'D': // Movimiento a la derecha.
                    columna = (columna + 1) % MAX_COLUMNA_TABLERO;
                    break;
                case 'W': // Movimiento hacia arriba.
                    fila = (fila - 1 + MAX_FILA_TABLERO) % MAX_FILA_TABLERO;
                    break;
                case 'S': // Movimiento hacia abajo.
                    fila = (fila + 1) % MAX_FILA_TABLERO;
                    break;
                case 'Q': // Movimiento diagonal superior izquierda.
                    fila = (fila - 1 + MAX_FILA_TABLERO) % MAX_FILA_TABLERO;
                    columna = (columna - 1 + MAX_COLUMNA_TABLERO) % MAX_COLUMNA_TABLERO;
                    break;
                case 'E': // Movimiento diagonal superior derecha.
                    fila = (fila - 1 + MAX_FILA_TABLERO) % MAX_FILA_TABLERO;
                    columna = (columna + 1) % MAX_COLUMNA_TABLERO;
                    break;
                case 'Z': // Movimiento diagonal inferior izquierda.
                    fila = (fila + 1) % MAX_FILA_TABLERO;
                    columna = (columna - 1 + MAX_COLUMNA_TABLERO) % MAX_COLUMNA_TABLERO;
                    break;
                case 'C': // Movimiento diagonal inferior derecha.
                    fila = (fila + 1) % MAX_FILA_TABLERO;
                    columna = (columna + 1) % MAX_COLUMNA_TABLERO;
                    break;
                default:
                    System.out.println("Movimiento no válido."); // Informa si la dirección es inválida.
                    return; // Sale del método sin mover.
            }

            char posicionActual = tablero[fila][columna]; // Obtiene el contenido de la posición actual.

            if (posicionActual == 'D' && jugador == 'Y') { // Si Yoda encuentra 'D', pierde vida.
                vidasYoda--; // Resta una vida a Yoda.
                System.out.println("Yoda ha perdido una vida. Le quedan " + vidasYoda + " vidas.");
            } else if (posicionActual == 'R' && jugador == 'V') { // Si Vader encuentra 'R', pierde vida.
                vidasVader--; // Resta una vida a Vader.
                System.out.println("Vader ha perdido una vida. Le quedan " + vidasVader + " vidas.");
            } else if (posicionActual == 'M') { // Si hay un muro en la posición actual, bloquea el movimiento.
                System.out.println("Hay un muro. No puedes avanzar.");
                return; // Finaliza el movimiento.
            } else if (posicionActual == 'F') { // Si se alcanza la meta, el jugador gana el juego.
                System.out.println("¡" + (jugador == 'Y' ? "Yoda" : "Vader") + " ha llegado a la meta! ¡HAS GANADO!");

                // Pausa de 3 segundos antes de cerrar el juego.
                try {
                    Thread.sleep(3000); // Pausa de 3 segundos.
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Imprime el error en caso de interrupción.
                }
                System.exit(0); // Termina la ejecución del juego.
                return;
            } else if (posicionActual == 'P') { // Encuentra poción y se teletransporta.
                Random aleatorio = new Random(); // Crea un objeto Random para la posición aleatoria.
                int nuevaFila, nuevaColumna;
                do {
                    nuevaFila = aleatorio.nextInt(MAX_FILA_TABLERO); // Genera una fila aleatoria.
                    nuevaColumna = aleatorio.nextInt(MAX_COLUMNA_TABLERO); // Genera una columna aleatoria.
                } while (tablero[nuevaFila][nuevaColumna] != 'L'); // Repite si la celda no está libre.

                tablero[filaAnterior][columnaAnterior] = 'L'; // Marca la posición anterior como libre.
                tablero[fila][columna] = 'L'; // Marca la posición actual como libre.
                tablero[nuevaFila][nuevaColumna] = jugador; // Coloca el jugador en la nueva posición.

                // Actualiza las posiciones de Yoda o Vader según el jugador.
                if (jugador == 'Y') {
                    filaYoda = nuevaFila;
                    columnaYoda = nuevaColumna;
                } else {
                    filaVader = nuevaFila;
                    columnaVader = nuevaColumna;
                }
                System.out.println(jugador + " ha encontrado una pócima y ha hecho random tp."); // Mensaje informativo.
                return;
            }

            tablero[filaAnterior][columnaAnterior] = 'L'; // Marca la posición anterior como libre.
            tablero[fila][columna] = jugador; // Coloca el jugador en la posición actual.

            // Actualiza las coordenadas de fila y columna según el jugador.
            if (jugador == 'Y') {
                filaYoda = fila;
                columnaYoda = columna;
            } else {
                filaVader = fila;
                columnaVader = columna;
            }
        }
    }
}