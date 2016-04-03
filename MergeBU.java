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
import java.lang.Math;

public class MergeBU
{
    
    private static Comparable[] aux;
        
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz  = sz+sz)
        {
            for (int lo = 0; lo < N-sz; lo += sz+sz)
            {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
            }
        }
    }
        
    private static void merge(Comparable[] a, int lo, int mid, int hi)
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
        Integer[] intArray = {48,38,96,12,11,92,86,22,42,80};
 
        print(intArray);
        
        sort(intArray);
        
        print(intArray);   
    }
}