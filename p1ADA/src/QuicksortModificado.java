/*Algoritmo modificado de QuickSort
 * Pablo Gutiérrez Martínez
 * Víctor Jorge Sibaja*/

import javax.sound.midi.SysexMessage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuicksortModificado {
    static int comparaciones;
    static int asignaciones;
    public static void swap (int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
    Algoritmo de Quicksort base que funciona
     */
    public static void quicksort(int[] a, int izq, int der) {

        int pivote=a[izq]; // tomamos primer elemento como pivote
        int i=izq;         // i realiza la búsqueda de izquierda a derecha
        int j=der;         // j realiza la búsqueda de derecha a izquierda
        int aux;

        while(i < j){                          // mientras no se crucen las búsquedas
            while(a[i] <= pivote && i < j) i++; // busca elemento mayor que pivote
            while(a[j] > pivote) j--;           // busca elemento menor que pivote
            if (i < j) {                        // si no se han cruzado
                aux= a[j];                      // los intercambia
                a[i] = a[j];
                a[j] = aux;
            }
        }

        a[izq] = a[j];      // se coloca el pivote en su lugar de forma que tendremos
        a[j] = pivote;      // los menores a su izquierda y los mayores a su derecha

        if(izq < j-1)
            quicksort(a,izq,j-1);          // ordenamos subarray izquierdo
        if(j+1 < der)
            quicksort(a,j+1,der);          // ordenamos subarray derecho

    }

    public static void quicksortModificado(int[] a, int izq, int der, int k) {

        //En caso de que el subarray que se pasa como parámetro a la llamada sea de longitud <= 3

        if ((der - izq + 1) <= k)
        {
            insercionDirectaConExtremos(a, izq, der);
        }
        //Si el subarray que se inspecciona es de longitud > k
        else
        {
            int pospivote = elegirPivote(a,izq,der); //Devuelve la posición del pivote
            int i = izq;         // i realiza la búsqueda de izquierda a derecha
            int j = der;         // j realiza la búsqueda de derecha a izquierda
            int aux;

            while (i < j) {                          // mientras no se crucen las búsquedas
                while (a[i] <= a[pospivote] && i < j){
                    i++;
                    comparaciones++;                                 // busca elemento mayor que pivote
                }
                while (a[j] > a[pospivote]){
                    j--;
                    comparaciones++;                                 // busca elemento menor que pivote
                }
                if (i < j) {                        // si no se han cruzado
                    if (j == pospivote){

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

    public static int elegirPivote(int[] a, int izq, int der){

        int primero,ultimo,mitad, valorMitad, mediana;


        primero = a[izq];
        ultimo = a[der];

        mitad = (izq+der)/2;
        valorMitad = a[mitad];

        int[] pivotes = new int[3];
        pivotes[0] = primero;
        pivotes[1] = valorMitad;
        pivotes[2] = ultimo;
        insercionDirectaConExtremos(pivotes, 0, 2);

        mediana = pivotes[1];


        if (mediana == primero){
            mediana = izq;
        }

        if (mediana == valorMitad){
            mediana = mitad;
        }
        if (mediana == ultimo){
            mediana = der;
        }

        return mediana;
    }

    public static int[] generarVector(int m){

        int[] vector = new int[m];

        for (int i = 0; i<m; i++){
            vector[i] = i+1;
        }

        int x,y;

        for(int i = 0; i<m; i++){
            x = (int)(Math.random()*((m-1)+1)) ;    /*No poner +1 para no exceder capacidad del array*/
            y = (int)(Math.random()*((m-1)+1)) ;
            swap(vector, x, y);
        }

        return vector;
    }

    public static void insercionDirectaConExtremos(int[] a, int izq, int der){
        int p, j;
        int aux;
        for (p = izq + 1; p <= der; p++){ // desde el segundo elemento hasta
            aux = a[p];           // el final, guardamos el elemento y
            j = p - 1;            // empezamos a comprobar con el anterior
            while ((j >= izq) && (aux < a[j])){ // mientras queden posiciones y el
                                                // valor de aux sea menor que los
                comparaciones++;
                asignaciones++;
                a[j+1] = a[j];   // de la izquierda, se desplaza a
                j--;               // la derecha
            }
            asignaciones++;
            a[j+1] = aux;       // colocamos aux en su sitio
        }
        //System.out.println("Sale de inserción directa");
    }

    public static void probarValores(int[] a){
        ArrayList<Integer> listaComparaciones = new ArrayList<>();
        ArrayList<Integer> listaAsignaciones = new ArrayList<>();
        int min;
        for(int i=3;i<=33;i++){
            quicksortModificado(a,0,a.length - 1, i);
            System.out.println("Comparaciones("+ i + "): "+ comparaciones);
            System.out.println("Asignaciones("+ i + "): "+ asignaciones);
            listaAsignaciones.add(asignaciones);
            listaComparaciones.add(comparaciones);
            comparaciones=0;
            asignaciones=0;
        }

        min=listaAsignaciones.get(0);
        for (int i = 1; i < listaAsignaciones.size(); i++) {
            if (listaAsignaciones.get(i) <= min) {
                min = listaAsignaciones.get(i);
            }
        }
        System.out.println("Minimo valor k y asignaciones: " + listaAsignaciones.indexOf(min)+3);

        min=listaComparaciones.get(0);

        for (int i = 1; i < listaComparaciones.size(); i++) {
            if (listaComparaciones.get(i) <= min) {
                min = listaComparaciones.get(i);
            }
        }

        System.out.println("Minimo valor k y comparaciones: " + listaComparaciones.indexOf(min)+3);

        System.out.println(listaComparaciones.toString());
        System.out.println(listaAsignaciones.toString());
    }




    public static void main(String []args)
    {
        int[] vector1, vector2;
        //probarValores();

        vector1 = generarVector(1000000);

        probarValores(vector1);

        //quicksortModificado(vector1,0,vector1.length - 1, 3);




    }

}
