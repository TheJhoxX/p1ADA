/*Algoritmo modificado de QuickSort
 * Pablo Gutiérrez Martínez
 * Víctor Jorge Sibaja*/

import javax.sound.midi.SysexMessage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class QuicksortModificado {
    public static void swap (ArrayList<Integer> arr, int i, int j)
    {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static ArrayList<Integer> concat(ArrayList<Integer> lizquierda, ArrayList<Integer> lderecha){
        if ((lizquierda.size() == 0) && (lderecha.size() == 0)){
            return new ArrayList<>();
        }
        //En caso de que no estén vacías las dos listas
        else{
           for (int i = 0; i<lderecha.size(); i++){
               lizquierda.add(lderecha.get(i));
           }
        }

        System.out.println("SALE CONCATENADO: " + lizquierda.toString());
        return lizquierda;
    }

    public static void quicksort(ArrayList<Integer> a, int izq, int der) {

        int pivote=a.get(izq); // tomamos primer elemento como pivote
        int i=izq;         // i realiza la búsqueda de izquierda a derecha
        int j=der;         // j realiza la búsqueda de derecha a izquierda
        int aux;

        while(i < j){                          // mientras no se crucen las búsquedas
            while(a.get(i) <= pivote && i < j) i++; // busca elemento mayor que pivote
            while(a.get(j) > pivote) j--;           // busca elemento menor que pivote
            if (i < j) {                        // si no se han cruzado
                aux= a.get(i);                      // los intercambia
                a.set(i, a.get(j));
                a.set(j,aux);
            }
        }

        a.set(izq, a.get(j));      // se coloca el pivote en su lugar de forma que tendremos
        a.set(j, pivote);      // los menores a su izquierda y los mayores a su derecha

        if(izq < j-1)
            quicksort(a,izq,j-1);          // ordenamos subarray izquierdo
        if(j+1 < der)
            quicksort(a,j+1,der);          // ordenamos subarray derecho

    }





    public static ArrayList<Integer> generarVector(int m){

        ArrayList<Integer> vector = new ArrayList<>(m);

        for (int i = 0; i<m; i++){
            vector.add(i+1);
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
        ArrayList<Integer> vector1;

        vector1 = generarVector(10);
        System.out.println("V1:" + vector1.toString());
        quicksort(vector1,0,vector1.size()-1);
        System.out.println("V1:" + vector1.toString());



    }

}
