/*Algoritmo modificado de QuickSort
 * Pablo Gutiérrez Martínez
 * Víctor Jorge Sibaja*/

import java.util.ArrayList;

public class QuicksortModificado2 {
    public static void swap (ArrayList<Integer> arr, int i, int j)
    {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }


    public static ArrayList<Integer> quicksortBase(ArrayList<Integer> vector)
    {
        System.out.println("VECTOR QUE ENTRA:" + vector.toString());
        if(vector.size() <= 1){
            return vector;
        }

        else {
            int pivote = vector.get(0);
            System.out.println("PIVOTE:" + pivote);
            ArrayList<Integer> izquierda = new ArrayList<>();
            ArrayList<Integer> derecha = new ArrayList<>();

            for (int i = 0; i < vector.size(); i++) {
                if (vector.get(i) < pivote) {
                    izquierda.add(vector.get(i));
                }
                if (vector.get(i) > pivote) {
                    derecha.add(vector.get(i));
                }
            }

            izquierda.add(pivote);


            quicksortBase(izquierda);
            quicksortBase(derecha);

            System.out.println("I:" + izquierda.toString());
            System.out.println("D:" + derecha.toString());
            for (int i = 0; i < derecha.size(); i++) {
                izquierda.add(derecha.get(i));
            }

            System.out.println("Sale con: " + izquierda.toString());
            return izquierda;
        }

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
        quicksortBase(vector1);
        System.out.println("V1:" + vector1.toString());



    }

}
