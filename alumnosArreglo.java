import java.util.Random;
import java.util.Scanner;

public class alumnosArreglo {

    public static void main(String[] args) {
        int numAlumnos = 100000;
        int numMaterias = 6;
        

        int[][] calificaciones = new int[numAlumnos][numMaterias];
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);


        long inicioGeneracion = System.nanoTime();
        
        for (int i = 0; i < numAlumnos; i++) {
            for (int j = 0; j < numMaterias; j++) {
                calificaciones[i][j] = rand.nextInt(101);
            }
        }


        long finGeneracion = System.nanoTime();
        double tiempoMilisegundos = (finGeneracion - inicioGeneracion) / 1_000_000.0;

        mostrarTablaCompleta(calificaciones, numAlumnos, numMaterias);
        System.out.println("Tiempo exacto de generación y llenado de la matriz: " + tiempoMilisegundos + " ms\n");

        int opcion = 0;
        do {
            System.out.println("================ MENÚ DE BÚSQUEDA ================");
            System.out.println("1. Buscar todas las calificaciones de un Alumno específico");
            System.out.println("2. Buscar la calificación de un Alumno en una Materia específica");
            System.out.println("3. Volver a imprimir la tabla completa (500x6)");
            System.out.println("4. Salir");
            System.out.print("Escribe tu opción (1-4): ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingresa un número.");
                scanner.next();
            }
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el ID del alumno (1 a " + numAlumnos + "): ");
                    int idAlumno = scanner.nextInt();
                    
                    if (idAlumno >= 1 && idAlumno <= numAlumnos) {
                        System.out.println("\n--- Calificaciones del Alumno " + idAlumno + " ---");
                        for (int j = 0; j < numMaterias; j++) {
                            // Se ajusta el índice interno restando 1 al ID del usuario
                            System.out.println("Materia " + (j + 1) + ": " + calificaciones[idAlumno - 1][j]);
                        }
                        System.out.println();
                    } else {
                        System.out.println("Error: El alumno no existe (Fuera de rango).\n");
                    }
                    break;

                case 2:
                    System.out.print("Ingresa el ID de la materia (1 a " + numMaterias + "): ");
                    int idMat = scanner.nextInt();
                    System.out.print("Ingresa el ID del alumno (1 a " + numAlumnos + "): ");
                    int idAl = scanner.nextInt();
                    
                    if (idMat >= 1 && idMat <= numMaterias && idAl >= 1 && idAl <= numAlumnos) {
                        // Se resta 1 a ambos IDs para acceder a la posición real en memoria (0-based)
                        System.out.println("\n-> La calificación del Alumno " + idAl + " en la Materia " + idMat + " es: " + calificaciones[idAl - 1][idMat - 1] + "\n");
                    } else {
                        System.out.println("Error: IDs fuera de rango.\n");
                    }
                    break;

                case 3:
                    mostrarTablaCompleta(calificaciones, numAlumnos, numMaterias);
                    break;

                case 4:
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.\n");
            }
        } while (opcion != 4);
        
        scanner.close();
    }

    private static void mostrarTablaCompleta(int[][] matriz, int alumnos, int materias) {
        System.out.println("\n================ TABLA COMPLETA DE CALIFICACIONES (VERTICAL - MÁS RÁPIDO) ================");
        
        System.out.printf("%-10s", "ALUMNO");
        for (int m = 0; m < materias; m++) {

            System.out.printf("| Materia %-2d", (m + 1));
        }
        System.out.println("|");
        
        System.out.print("----------");
        for (int m = 0; m < materias; m++) {
            System.out.print("+-----------");
        }
        System.out.println("+");

        for (int a = 0; a < alumnos; a++) {

            System.out.printf("Alumno %-3d", (a + 1));
            for (int m = 0; m < materias; m++) {
                System.out.printf("|    %-7d", matriz[a][m]);
            }
            System.out.println("|");
        }
        System.out.println("==========================================================================================");
    }
}