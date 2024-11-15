import java.util.Random;
//array de 10 dimensiones con numeros random
public class bingo {
    static Random aleatorio = new Random();
    static final int MAX_FILA = 3;
    static final int MAX_COLUMNA = 9;
    static int tablero[][] = new int[MAX_FILA][MAX_COLUMNA];
    static int ganadores[] = new int[15];
    static int numCarton = 0;

    public static void definirArray() {
        boolean filasLlenas;
        do {
            filasLlenas = true;  // suponemos q todas las filas estan llenas
            int numerosGenerados = 0;
            int randomizado;
            int[] numerosPorFila = new int[MAX_FILA];  // array para contar los numeros asignados a cada fila
            // llenamos el array con el valor (-1).
            for (int i = 0; i < MAX_FILA; i++) {
                for (int j = 0; j < MAX_COLUMNA; j++) {
                    tablero[i][j] = -1;
                }
            }

            // seleccionamos 15 posiciones random del array que está llenito de -1.
            while (numerosGenerados < 15) {
                // generamos posiciones aleatorias (fila y columna).
                int fila = aleatorio.nextInt(MAX_FILA);
                int columna = aleatorio.nextInt(MAX_COLUMNA);
                // si la posición aleatoria generadaa esta vacía (-1), generamos un número para esa posicion.
                if (tablero[fila][columna] == -1) {
                    do {
                        randomizado = aleatorio.nextInt(10) + (columna * 10);  // generar número en su decena
                        // correcta según la columnaº
                    } while (comprobarRepetido(randomizado));  // llamamos al proceso para q no se repita ninguno
                    randomizado += 10;
                    // comprobamos si el numero ya está en el array tablero
                    if (!comprobarRepetido(randomizado)) {
                        tablero[fila][columna] = randomizado;  // asignamos el número en la posición aleatoria.
                        numerosPorFila[fila]++;  // incrementamos el contador de números en la fila
                        numerosGenerados++;
                    }
                }
            }
            // verificamos si todas las filas tienen al menos un número rellenado
            for (int i = 0; i < MAX_FILA; i++) {
                if (numerosPorFila[i] == 0) {  // si la fila no tiene ningún numero
                    filasLlenas = false;  // indicamos que NO todas las filas están llenas
                    break;
                }
            }
        } while (!filasLlenas);  // repetimos hasta que todas las filas tengan al menos un número
    }

    public static void definirArrayGanadora() {
        boolean repetido;
        int randomizado;
        for (int i=0; i < 15; i++) {
            do {
                randomizado = aleatorio.nextInt(90) + 10;
            } while (comprobarRepetidoGanadores(randomizado));  // comprobamos si ya está en el array
            ganadores[i] = randomizado;
        }
    }

    public static void generarNumCarton() {
        numCarton =aleatorio.nextInt(900) + 100;
        System.out.println("---CARTÓN DE BINGO Nº"+numCarton +"--");
        System.out.println("..........................");
    }

    public static void imprimirArray() {
        for (int i=0; i < MAX_FILA; i++) {
            for (int j=0; j < MAX_COLUMNA; j++) {
                if (tablero[i][j] == -1) {
                    System.out.print("XX ");
                } else {
                    System.out.print(tablero[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void ordenarColumnas() {
        for (int j = 0; j < MAX_COLUMNA; j++) {
            // metodo burbu de ordenasión
            for (int i = 0; i < MAX_FILA; i++) {
                for (int k = i + 1; k < MAX_FILA; k++) {

                    if (tablero[i][j] > tablero[k][j]) {
                        // intercambiamos si el valor de la fila superior es mayor que el inferior
                        int numeroTemporal = tablero[i][j];
                        tablero[i][j] = tablero[k][j];
                        tablero[k][j] = numeroTemporal;
                    }
                }
            }
        }
    }

    /*public static void ordenarColumnas() {
        for (int j = 0; j < MAX_COLUMNAS; j++) {
            for (int k = 0; k < MAX_FILAS - 1; k++) {
                for (int i = 0; i < MAX_FILAS - 1; i++) {
                    int valorActual = Integer.parseInt(matriz[i][j]);
                    int valorSiguiente = Integer.parseInt(matriz[i + 1][j]);

                    if (valorActual > valorSiguiente) {
                        String aux = matriz[i][j];
                        matriz[i][j] = matriz[i + 1][j];
                        matriz[i + 1][j] = aux;
                    }
                }
            }
        }
    }
*/
    public static boolean comprobarRepetido (int randomizado) {
        for (int i = 0; i < MAX_FILA; i++) {
            for (int j = 0; j < MAX_COLUMNA; j++) {
                if (tablero[i][j] == randomizado) {
                    return true;  // si se encuentra el número, devolvemos true al booleano
                }
            }
        }
        return false;  // si no es asi devolvemos false
    }

    public static boolean comprobarRepetidoGanadores (int randomizado) {
        for (int i = 0; i < MAX_FILA; i++) {
            if (ganadores[i] == randomizado) {
                return true;  // si se encuentra el número, devolvemos true al booleano
            }
        }
        return false;  // si no es asi devolvemos false
    }

    public static void imprimirArrayGanadora() {
        System.out.println("..........................");
        System.out.println("Los números ganadores son: ");
        for (int i=0; i < ganadores.length; i++) {
            System.out.print(ganadores[i] + " ");
            if (i == 4 || i == 9 || i ==14) {
                System.out.println("");
            }
        }
        System.out.println();
    }

    public static void contarAciertos() {
        int aciertos = 0;
        int[] numerosAcertados = new int[15];  // array para almacenar los números acertados
        int indiceAciertos = 0;  // para llevar la cuenta de cuantos numeros acertados
        // comparar números en el array "tablero" con los números del array "ganadores"
        for (int i = 0; i < MAX_FILA; i++) {
            for (int j = 0; j < MAX_COLUMNA; j++) {
                if (tablero[i][j] != -1) {  // solo pillamos las posiciones con valor diferente a '-1' (números válidos)
                    for (int k = 0; k < ganadores.length; k++) {
                        if (tablero[i][j] == ganadores[k]) {
                            aciertos++;
                            numerosAcertados[indiceAciertos] = tablero[i][j];  // guardamos el número acertado en el array.
                            indiceAciertos++;  // aumentamos la cuenta de aciertos
                            break;
                        }
                    }
                }
            }
        }

        if (aciertos == 1) {
            System.out.println(".................................................");
            System.out.println("Has tenido " + aciertos + " único acierto en tu cartón de Bingo.");
            System.out.println("Número acertado: " + numerosAcertados[0]);
            System.out.println(".................................................");
        } else if (aciertos > 1) {
            System.out.println(".............................................");
            System.out.println("Has tenido " + aciertos + " aciertos en tu cartón de Bingo.");
            System.out.print("Números acertados: ");
            for (int i = 0; i < indiceAciertos; i++) {
                System.out.print(numerosAcertados[i] + " ");
            }
            System.out.println();
            System.out.println(".............................................");
        } else {
            System.out.println("No has tenido ningún acierto.");
        }
    }

    public static boolean comprobarLinea() {
        for (int i = 0; i < MAX_FILA; i++) {
            boolean esLineaGanadora = true;  // suponemos que la fila es ganadora
            for (int j = 0; j < MAX_COLUMNA; j++) {  // recorremos cada columna de la fila
                if (tablero[i][j] != -1) {  // solo verificamos los números que no son 'XX'
                    boolean encontrado = false;
                    // comprobamos que el número en el tablero está en la lista de ganadores
                    for (int k = 0; k < ganadores.length; k++) {
                        if (tablero[i][j] == ganadores[k]) {
                            encontrado = true;
                            break;
                        }
                    }
                    // si algún número de la fila no está en los ganadores, no es línea ganadora
                    if (!encontrado) {
                        esLineaGanadora = false;
                        break;
                    }
                }
            }
            // si todos los números de una fila están entre los 15 ganadores, devolvemos true
            if (esLineaGanadora) {
                System.out.println("¡Has cantado línea!");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        definirArray();
        definirArrayGanadora();
        generarNumCarton();
        ordenarColumnas();
        imprimirArray();
        imprimirArrayGanadora();
        contarAciertos();
        comprobarLinea();
    }
}
