/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 Quick.java
 * Execution: java-algs4 Quick
 * Dependencies: java.util.Comparator
 *
 * An implementation of Quick Sort, with built in client to define an array
 * and sort it (printing after each call to the partitioning sub routine). 
 * 
 *************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdRandom;

public class Quick
{
    
    public static void sort(Comparable[] a)
    {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
        
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
        
    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi+1;
        while (true)
        {
            // find item on left to swap
            while (less(a[++i], a[lo]))
            {
                if (i == hi) break;
            }
            // find item on right to swap
            while (less(a[lo], a[--j]))
            {
                if (j == lo) break;
            }
            
            // check if pointers cross
            if (i >= j) break;
            
            // swap
            exch(a, i, j);
        }
        
        // swap with partitioning item
        exch(a, lo, j);
        
        print(a);
        
        // return index of item now know to be in place
        return j;
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
    
    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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
        //Integer[] intArray = {66,85,63,41,78,36,89,12,18,94,68,59};

        Comparable[] charArray = {'B','A','B','B','A','A','A','B','B','B','B','B'};
        
        print(charArray);
        
        sort(charArray);
        
        print(charArray);   
    }
}