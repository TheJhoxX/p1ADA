/*Algoritmo básico de QuickSort*/

import java.util.Arrays;

public class QuicksortModificado {
    public static void swap (int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Partición usando el esquema de partición de Lomuto
    public static int partition(int[] a, int start, int end)
    {
        // Elija el elemento más a la derecha como un pivote de la array
        int pivot = a[end];

        // los elementos menores que el pivote serán empujados a la izquierda de `pIndex`
        // elementos más que el pivote serán empujados a la derecha de `pIndex`
        // elementos iguales pueden ir en cualquier dirección
        int pIndex = start;

        // cada vez que encontramos un elemento menor o igual que el pivote,
        // `pIndex` se incrementa, y ese elemento se colocaría
        // antes del pivote.
        for (int i = start; i < end; i++)
        {
            if (a[i] <= pivot)
            {
                swap(a, i, pIndex);
                pIndex++;
            }
        }

        // intercambiar `pIndex` con pivote
        swap(a, end, pIndex);

        // devuelve `pIndex` (índice del elemento pivote)
        return pIndex;
    }

    // Rutina de clasificación rápida

    /*k corresponde al valor a partir del cual se aplica el algoritmo de ordenación rápida*/
    public static void quicksortModificado(int[] a, int start, int end, int k)
    {
        // condición base
        if (a.length >= k) {
            insercionDirecta(a);
            return;
        }

        // reorganizar los elementos a través del pivote
        int pivot = partition(a, start, end);

        // recurre en un subarray que contiene elementos menores que el pivote
        quicksortModificado(a, start, pivot - 1,k);

        // se repite en el subarray que contiene más elementos que el pivote
        quicksortModificado(a, pivot + 1, end, k);
    }

    public static void insercionDirecta(int a[]){
        int p, j;
        int aux;
        for (p = 1; p < a.length; p++){ // desde el segundo elemento hasta
            aux = a[p];           // el final, guardamos el elemento y
            j = p - 1;            // empezamos a comprobar con el anterior
            while ((j >= 0) && (aux < a[j])){ // mientras queden posiciones y el
                // valor de aux sea menor que los
                a[j + 1] = a[j];   // de la izquierda, se desplaza a
                j--;               // la derecha
            }
            a[j + 1] = aux;       // colocamos aux en su sitio
        }
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

    // Implementación en Java del algoritmo Quicksort
    public static void main(String []args)
    {
        int[] vector1;

        vector1 = generarVector(100);
        System.out.println("V1:" + Arrays.toString(vector1));
        quicksortModificado(vector1, 0, vector1.length - 1, 4);
        System.out.println("V1:" + Arrays.toString(vector1));



    }

}
