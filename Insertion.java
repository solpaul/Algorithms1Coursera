/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac Insertion.java
 * Execution: java Insertion
 * Dependencies: java.util.Comparator
 *
 * An implementation of Insertion Sort, with built in client to define an array
 * and sort it (printing after each call to exch). 
 * 
 *************************************************************************/

import java.util.Comparator;

public class Insertion
{   
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (less(a[j], a[j-1]))
                {
                    exch(a, j, j-1);
                    print(a);
                }
                else break;
            }
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
        Integer[] intArray = {13,22,48,84,97,64,92,87,42,88};
 
        print(intArray);
        
        sort(intArray);
    }
}