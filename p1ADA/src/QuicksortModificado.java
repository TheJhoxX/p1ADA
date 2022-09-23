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

        //System.out.println("SALE CONCATENADO: " + lizquierda.toString());
        return lizquierda;
    }

    /*
    Algoritmo de Quicksort base que funciona
     */
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

    public static void quicksortModificado(ArrayList<Integer> a, int izq, int der, int k) {

        //System.out.println("I:" + izq + " D:" + der);
        //En caso de que el subarray que se pasa como parámetro a la llamada sea de longitud <= 3
        if ((der - izq + 1) <= k)
        {
            insercionDirectaConExtremos(a, izq, der);


        }
        //Si el subarray que se inspecciona es de longitud > k
        else
        {
            int pivote = elegirPivote(a,izq,der); //Devuelve la posición del pivote
            int pospivote = pivote; //Backup de la posición del pivote
            System.out.println(pospivote);
            int i = izq;         // i realiza la búsqueda de izquierda a derecha
            int j = der;         // j realiza la búsqueda de derecha a izquierda
            int aux;

            while (i < j) {                          // mientras no se crucen las búsquedas
                while (a.get(i) <= a.get(pivote) && i < j) i++; // busca elemento mayor que pivote
                while (a.get(j) > a.get(pivote)) j--;           // busca elemento menor que pivote
                if (i < j) {                        // si no se han cruzado
                    aux = a.get(i);                      // los intercambia
                    a.set(i, a.get(j));
                    a.set(j, aux);
                }
            }

            System.out.println(a.toString());
            System.out.println("POS PIVOTE: "  + pospivote + " VALOR DE PIVOTE: " + a.get(pospivote));
            System.out.println("J:" + j + " VALOR: " + a.get(j));
            System.out.println("IZQ: " + izq + " VALOR: " + a.get(izq));


            a.set(pospivote, a.get(j));      // se coloca el pivote en su lugar de forma que tendremos
            a.set(j, a.get(pospivote));      // los menores a su izquierda y los mayores a su derecha


            System.out.println(a.toString());


            if (izq < j - 1)
                quicksortModificado(a, izq, j - 1, k);          // ordenamos subarray izquierdo
            if (j + 1 < der)
                quicksortModificado(a, j + 1, der, k);          // ordenamos subarray derecho

        }


    }

    public static int elegirPivote(ArrayList<Integer> a, int izq, int der){

        int primero,ultimo,mitad, valorMitad;

        primero = a.get(izq);
        ultimo = a.get(der);

        //En caso de longitud impar
        if (a.size() % 2 != 0) {
            mitad = (int) Math.ceil(a.size() / 2);
            valorMitad = a.get(mitad);
        }
        //En caso de longitud par
        else{
            mitad = (a.size()/2) - 1;
            valorMitad = a.get(mitad);
        }

        ArrayList<Integer> pivotes = new ArrayList<>();
        pivotes.add(primero);
        pivotes.add(valorMitad);
        pivotes.add(ultimo);
        insercionDirectaConExtremos(pivotes, 0, pivotes.size()-1);

        //Se retorna la posición de la mediana de los 3 elementos
        if (pivotes.get(1) == primero){
            return izq;
        }
        else if (pivotes.get(1) == ultimo){
            return der;
        }
        else {
            System.out.println("Devuelve la mitad: " + mitad);
            return mitad;
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

    public static void insercionDirectaConExtremos(ArrayList<Integer> a, int izq, int der){
        int p, j;
        int aux;
        for (p = izq + 1; p <= der; p++){ // desde el segundo elemento hasta
            aux = a.get(p);           // el final, guardamos el elemento y
            j = p - 1;            // empezamos a comprobar con el anterior
            while ((j >= izq) && (aux < a.get(j))){ // mientras queden posiciones y el
                // valor de aux sea menor que los
                a.set(j+1, a.get(j));   // de la izquierda, se desplaza a
                j--;               // la derecha
            }
            a.set(j+1, aux);       // colocamos aux en su sitio
        }
        //System.out.println("Sale de inserción directa");
    }

    public static void probarValores(){

        for(int i=1; i<=30;i++){
            ArrayList<Integer> vector;
            vector=generarVector(100000);

            double inicio=System.currentTimeMillis();
            quicksortModificado(vector,0,vector.size()-1, i);
            double fin = System.currentTimeMillis();

            double tiempo = (double) ((fin - inicio));
            System.out.println("K" + i +": " + tiempo +" milisegundos");
        }




    }
    public static void main(String []args)
    {
        ArrayList<Integer> vector1, vector2;
        //probarValores();

        vector1 = generarVector(10);
        System.out.println("V1:" + vector1.toString());
        quicksortModificado(vector1,0,vector1.size()-1, 3);
        System.out.println("V1:" + vector1.toString());



    }

}
