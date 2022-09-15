/*Algoritmo básico de QuickSort*/

import java.util.Arrays;

public class QuicksortBase {
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
    public static void quicksort(int[] a, int start, int end)
    {
        // condición base
        if (start >= end) {
            return;
        }

        // reorganizar los elementos a través del pivote
        int pivot = partition(a, start, end);

        // recurre en un subarray que contiene elementos menores que el pivote
        quicksort(a, start, pivot - 1);

        // se repite en el subarray que contiene más elementos que el pivote
        quicksort(a, pivot + 1, end);
    }

    public static void insercionDirecta(int A[]){
        int p, j;
        int aux;
        for (p = 1; p < A.length; p++){ // desde el segundo elemento hasta
            aux = A[p];           // el final, guardamos el elemento y
            j = p - 1;            // empezamos a comprobar con el anterior
            while ((j >= 0) && (aux < A[j])){ // mientras queden posiciones y el
                // valor de aux sea menor que los
                A[j + 1] = A[j];   // de la izquierda, se desplaza a
                j--;               // la derecha
            }
            A[j + 1] = aux;       // colocamos aux en su sitio
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
        int[] vector2;

        vector1 = generarVector(15);
        vector2 = generarVector(15);
        System.out.println(Arrays.toString(vector1));
        quicksort(vector1, 0, vector1.length - 1);

        System.out.println(Arrays.toString(vector2));
        insercionDirecta(vector2);


        // imprime la array ordenada
        System.out.println(Arrays.toString(vector1));
        System.out.println(Arrays.toString(vector2));
    }

}
