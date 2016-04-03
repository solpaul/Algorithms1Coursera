/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac Selection.java
 * Execution: java Selection
 * Dependencies: java.util.Comparator
 *
 * An implementation of Selection Sort, with built in client to define an array
 * and sort it (printing after each call to exch). 
 * 
 *************************************************************************/

import java.util.Comparator;

public class Selection
{
    
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            int min = i;
            for (int j = i+1; j < N; j++)
            {
                if (less(a[j], a[min]))
                {
                    min = j;
                }
            }
            exch(a, i, min);
            print(a);
        }
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
        Integer[] intArray = {12,13,14,13,12,9,3,12,93,21,99,32};
 
        print(intArray);
        
        sort(intArray);
    }
}