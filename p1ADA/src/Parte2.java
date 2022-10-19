/*Parte 2
 * Pablo Gutiérrez Martínez
 * Víctor Jorge Sibaja*/

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.Instant;


public class Parte2 {
    static long comparaciones;
    static long asignaciones;
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    Algoritmo de Quicksort base que funciona
     */
    public static void quicksort(int a[], int izq, int der) {

        int pivote = a[izq]; // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        int aux;

        while (i < j) {// mientras no se crucen las búsquedas
            comparaciones++;
            while (a[i] <= pivote && i < j){
                comparaciones++;
                i++;                           // busca elemento mayor que pivote
            }
            comparaciones++;
            while (a[j] > pivote) {
                comparaciones++;
                j--;                          // busca elemento menor que pivote
            }
            if (i < j) {                        // si no se han cruzado
                asignaciones++;
                aux = a[i];                      // los intercambia
                asignaciones++;
                a[i] = a[j];
                asignaciones++;
                a[j] = aux;
            }
        }
        asignaciones++;
        a[izq] = a[j];      // se coloca el pivote en su lugar de forma que tendremos
        asignaciones++;
        a[j] = pivote;      // los menores a su izquierda y los mayores a su derecha

        if (izq < j - 1)
            quicksort(a, izq, j - 1);          // ordenamos subarray izquierdo
        if (j + 1 < der)
            quicksort(a, j + 1, der);          // ordenamos subarray derecho

    }

    /*
    Modificación del algoritmo Quicksort
     */
    public static void quicksortModificado(int[] a, int izq, int der, int k) {

        //En caso de que el subarray que se pasa como parámetro a la llamada sea de longitud <= 3

        if ((der - izq + 1) <= k) {
            insercionDirectaConExtremos(a, izq, der);
        }

        //Si el subarray que se inspecciona es de longitud > k
        else {
            int pospivote = elegirPivote(a, izq, der); //Devuelve la posición del pivote
            int i = izq;
            int j = der;
            int aux;

            while (i < j) {
                comparaciones ++;
                while (a[i] <= a[pospivote] && i < j) {
                    i++;
                    comparaciones++;                                 // busca elemento mayor que pivote
                }
                comparaciones ++;
                while (a[j] > a[pospivote]) {
                    j--;
                    comparaciones++;                                 // busca elemento menor que pivote
                }
                if (i < j) {                        // si no se han cruzado
                    if (j == pospivote) {

                        pospivote = i;
                    }
                    asignaciones++;
                    aux = a[i];                      // los intercambia
                    asignaciones++;
                    a[i] = a[j];
                    asignaciones++;
                    a[j] = aux;
                }
            }


            asignaciones++;
            int valorPivote = a[pospivote];
            asignaciones++;
            a[pospivote] = a[j];      // se coloca el pivote en su lugar de forma que tendremos
            asignaciones++;
            a[j] = valorPivote;      // los menores a su izquierda y los mayores a su derecha


            if (izq < j - 1) {
                quicksortModificado(a, izq, j - 1, k);     // ordenamos subarray izquierdo
            }

            if (j + 1 < der) {
                quicksortModificado(a, j + 1, der, k);          // ordenamos subarray derecho
            }
        }


    }

    /*
    Funcion que retorna la posición del pivote
     */
    public static int elegirPivote(int[] a, int izq, int der) {

        int mitad;

        mitad = (izq + der) / 2;

        comparaciones ++;
        if (a[izq] <= a[mitad]){
            comparaciones ++;
            if (a[mitad] <= a[der]){
                return mitad;
            }
        }

        comparaciones ++;
        if (a[der] <= a[mitad]){
            comparaciones ++;
            if (a[mitad] <= a[izq]){
                return mitad;
            }
        }


        comparaciones++;
        //elementoCentral <= extremoDerecho <= extremoIzquierdo o extremoIzquierdo <= extremoDerecho <= elementoCentral
        if (a[mitad] <= a[der]){
            comparaciones++;
            if (a[der] <= a[izq]){
                return der;
            }
        }
        comparaciones++;
        if (a[izq] <= a[der]){
            comparaciones++;
            if (a[der] <= a[mitad]){
                return der;
            }
        }

        //En cualquier otro caso el pivote será el elemento izquierdo
        return izq;
    }


    /*
    Funcion que retorna vector casi ordenado
     */
    public static int[] generarVectorCasiOrdenado(int m){

        int[] vector = new int[m];

        for (int i = 0; i < m; i++) {
            vector[i] = i + 1;
        }

        int x, y;


        for (int i = 0; i < m/10; i++) {
            x = (int) (Math.random() * ((m - 1) + 1));
            y = (int) (Math.random() * ((m - 1) + 1));
            swap(vector, x, y);
        }

        

        return vector;
    }


    /*
    Algoritmo de insercionDirecta modificado para funcionar con extremos
     */
    public static void insercionDirectaConExtremos(int[] a, int izq, int der) {
        int p, j;
        int aux;
        for (p = izq + 1; p <= der; p++) {
            aux = a[p];
            j = p - 1;
            while ((j >= izq)) {

                comparaciones++;
                if(aux<a[j]) {
                    asignaciones++;
                    a[j + 1] = a[j];
                    j--;
                }
                else{
                    break;
                }
            }
            asignaciones++;
            a[j + 1] = aux;
        }
    }

    /*
    Metodo encargado de las ejecuciones de los algoritmos y la exportación de datos
     */
    public static void probarValores() {
        long[] listaComparaciones = new long[10];
        long[] listaAsignaciones = new long[10];
        long[] listaTiempos = new long[10];
        int[] a;
        long tiempo=0;

        for (int i = 1; i <= 10; i++) {

            for (int j = 0; j < 20; j++) {
                //Para cada ejecución para una k fija se reinician los contadores y se prueba con un vector distinto
                a = generarVectorCasiOrdenado(i*10000);


                Instant start = Instant.now();
                quicksortModificado(a, 0, a.length - 1, 14);
                Instant finish = Instant.now();
                long timeElapsed = Duration.between(start,finish).toNanos();
                tiempo=tiempo+timeElapsed;


            }

            //Se hace un promedio de las 20 ejecuciones de cada k
            listaAsignaciones[i -1] = asignaciones / 20;
            listaComparaciones[i -1] = comparaciones / 20;
            listaTiempos[i -1] = tiempo/ 20;
            comparaciones = 0;
            asignaciones = 0;
            tiempo=0;

        }

        //Nuevas listas para Quicksot original (QO)
        long[] listaComparacionesQO = new long[10];
        long[] listaAsignacionesQO = new long[10];
        long[] listaTiemposQO = new long[10];
        tiempo=0;

        for (int i = 1; i <= 10; i++) {

            for (int j = 0; j < 20; j++) {
                //Para cada ejecución para una k fija se reinician los contadores y se prueba con un vector distinto
                a = generarVectorCasiOrdenado(i*10000);

                Instant start = Instant.now();
                quicksort(a, 0, a.length - 1);
                Instant finish = Instant.now();
                long timeElapsed = Duration.between(start,finish).toNanos();
                tiempo=tiempo+timeElapsed;


            }

            //Se hace un promedio de las 20 ejecuciones de cada k
            listaAsignacionesQO[i -1] = asignaciones / 20;
            listaComparacionesQO[i -1] = comparaciones / 20;
            listaTiemposQO[i -1] = tiempo/ 20;
            comparaciones = 0;
            asignaciones = 0;
            tiempo=0;

        }

        //Exportar datos QM y QO
        FileWriter fichero = null;
        PrintWriter pw;

        try {
            fichero = new FileWriter("datos2.csv");
            pw = new PrintWriter(fichero);

            //Quicksort modificado
            pw.println("n;ComparacionesQM;AsignacionesQM;TiemposQM");

            //Imprime los valores de las asignaciones y las comparaciones
            for (int i=0;i<10;i++){
                pw.println((i+1)*10000+";"+listaComparaciones[i]+";"+ listaAsignaciones[i]+";"+listaTiempos[i]);
            }

            pw.println("\n\n\n");

            //Quicksort original
            pw.println("n;ComparacionesQO;AsignacionesQO;TiemposQO");

            //Imprime los valores de las asignaciones y las comparaciones
            for (int i=0;i<10;i++){
                pw.println((i+1)*10000+";"+listaComparacionesQO[i]+";"+ listaAsignacionesQO[i]+";"+listaTiemposQO[i]);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {

        probarValores();



        
    }

}
