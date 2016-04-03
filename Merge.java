/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac Merge.java
 * Execution: java Merge
 * Dependencies: java.util.Comparator
 *
 * An implementation of Merge Sort, with built in client to define an array
 * and sort it (printing after each call to merge). 
 * 
 *************************************************************************/

import java.util.Comparator;

public class Merge
{
    
    public static void sort(Comparable[] a)
    {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
        
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        if (hi <= lo) return;
        int mid = lo + ((hi - lo) / 2);
        sort (a, aux, lo, mid);
        sort (a, aux, mid+1, hi);
        merge (a, aux, lo, mid, hi);
    }
        
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        assert isSorted(a, lo, mid);       // precondition: a[lo..mid] sorted
        assert isSorted(a, mid+1, hi);     // precondition: a[mid+1..hi] sorted
        
        // copy to auxillary array
        for (int k = lo; k <= hi; k++)
        {
            aux[k] = a[k];
        }
        
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
        {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
        
        assert isSorted(a, lo, hi);        // postcondition: a[lo..hi] sorted
        
        print(a);
    }
    
    private static boolean isSorted(Comparable[] a, int lo, int hi)
    {
        for (int i = lo; i <= hi; i++)
        {
            if (less(a[i], a[i-1]))
            {
                return false;
            }
        }
        return true;
    }
    
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }
    
    public static void print(Comparable[] a)
    {
        StringBuilder temp = new StringBuilder();
        temp.append("");
        
        for (int i = 0; i < a.length; i++)
        {
            temp.append(a[i]);
            temp.append(" ");
        }
        String strI = temp.toString();
        System.out.println(strI);
    }
    
    public static void main(String[] args) 
    { 
        Integer[] intArray = {81,26,58,54,99,49,98,94,64,25,18,45};
 
        print(intArray);
        
        sort(intArray);
        
        print(intArray);   
    }
}