/*Algoritmo modificado de QuickSort
 * Pablo Gutiérrez Martínez
 * Víctor Jorge Sibaja*/

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


public class QuicksortModificadoMejorado {
    static int comparaciones;
    static int asignaciones;

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    Algoritmo de Quicksort base que funciona
     */
    public static void quicksort(int[] a, int izq, int der) {

        int pivote = a[izq]; // tomamos primer elemento como pivote
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        int aux;

        while (i < j) {                          // mientras no se crucen las búsquedas
            while (a[i] <= pivote && i < j) i++; // busca elemento mayor que pivote
            while (a[j] > pivote) j--;           // busca elemento menor que pivote
            if (i < j) {                        // si no se han cruzado
                aux = a[j];                      // los intercambia
                a[i] = a[j];
                a[j] = aux;
            }
        }

        a[izq] = a[j];      // se coloca el pivote en su lugar de forma que tendremos
        a[j] = pivote;      // los menores a su izquierda y los mayores a su derecha

        if (izq < j - 1)
            quicksort(a, izq, j - 1);          // ordenamos subarray izquierdo
        if (j + 1 < der)
            quicksort(a, j + 1, der);          // ordenamos subarray derecho

    }

    public static void quicksortModificado(int[] a, int izq, int der, int k) {

        //En caso de que el subarray que se pasa como parámetro a la llamada sea de longitud <= k

        if ((der - izq + 1) <= k) {
            insercionDirectaConExtremos(a, izq, der);
        }
        //Si el subarray que se inspecciona es de longitud > k
        else {
            int pospivote = elegirPivote(a, izq, der); //Devuelve la posición del pivote
            int i = izq;         // i realiza la búsqueda de izquierda a derecha
            int j = der;         // j realiza la búsqueda de derecha a izquierda
            int aux;

            while (i < j) {                          // mientras no se crucen las búsquedas
                while (a[i] <= a[pospivote] && i < j) {
                    i++;
                    comparaciones++;                                 // busca elemento mayor que pivote
                }
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

    public static int elegirPivote(int[] a, int izq, int der) {

        int mitad, pivote;

        //Inicializo el pivote como el extremo izquierdo
        pivote = izq;

        mitad = (izq + der) / 2;

        comparaciones ++;
        if (a[izq] <= a[mitad]){
            comparaciones ++;
            if (a[mitad] <= a[der]){
                pivote = mitad;
                return pivote;
            }
        }

        comparaciones ++;
        if (a[der] <= a[mitad]){
            comparaciones ++;
            if (a[mitad] <= a[izq]) {
                pivote = mitad;
                return pivote;
            }
        }

        comparaciones++;
        //elementoCentral <= extremoDerecho <= extremoIzquierdo o extremoIzquierdo <= extremoDerecho <= elementoCentral
        if ((a[mitad] <= a[der]) && (a[der] <= a[izq])){
            comparaciones++;
            if (a[der] <= a[izq]){
                pivote = der;
                return pivote;
            }
        }

        if (a[izq] <= a[der]){
            if (a[der] <= a[mitad]){
                pivote = der;
                return pivote;
            }
        }

        //En cualquier otro caso el pivote será el elemento izquierdo
        pivote = izq;
        return pivote;
    }

    public static int[] generarVector(int m) {

        int[] vector = new int[m];

        for (int i = 0; i < m; i++) {
            vector[i] = i + 1;
        }

        int x, y;

        for (int i = 0; i < m; i++) {
            x = (int) (Math.random() * ((m - 1) + 1));    /*No poner +1 para no exceder capacidad del array*/
            y = (int) (Math.random() * ((m - 1) + 1));
            swap(vector, x, y);
        }

        return vector;
    }



    public static void insercionDirectaConExtremos(int[] a, int izq, int der) {
        int p, j;
        int aux;
        for (p = izq + 1; p <= der; p++) { // desde el segundo elemento hasta
            aux = a[p];           // el final, guardamos el elemento y
            j = p - 1;            // empezamos a comprobar con el anterior
            while ((j >= izq)) { // mientras queden posiciones y el
                                 // valor de aux sea menor que los
                                 // de la izquierda, se desplaza a
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
            a[j + 1] = aux;       // colocamos aux en su sitio
        }
    }

    public static void probarValores() {
        int[] listaComparaciones = new int[28];
        int[] listaAsignaciones = new int[28];
        int[] a;

        for (int i = 3; i <= 30; i++) {

            for (int j = 0; j < 20; j++) {
                //Para cada ejecución para una k fija se reinician los contadores y se prueba con un vector distinto
                a = generarVector(100000);
                quicksortModificado(a, 0, a.length - 1, i);

            }

            //Se hace un promedio de las 20 ejecuciones de cada k
            listaAsignaciones[i - 3] = asignaciones / 20;
            listaComparaciones[i - 3] = comparaciones / 20;
            asignaciones=0;
            comparaciones=0;


        }

        FileWriter fichero = null;
        PrintWriter pw;

        try {
            fichero = new FileWriter("datos.csv");
            pw = new PrintWriter(fichero);

            pw.println("Valor K;Comparaciones;Asignaciones");

            //Imprime los valores de las asignaciones y las comparaciones
            for (int i=0;i<28;i++){
                pw.println(i+3+";"+listaComparaciones[i]+";"+ listaAsignaciones[i]);
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
