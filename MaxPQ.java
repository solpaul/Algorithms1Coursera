/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 MaxPQ.java
 * Execution: java-algs4 MaxPQ
 * Dependencies: java.util.Comparator
 *
 * An implementation of binary heap, with built in client to perform 
 * operations, printing after each insert or deletion. 
 * 
 *************************************************************************/

import java.util.Comparator;

public class MaxPQ<Key extends Comparable<Key>>
{
    private Key[] pq;
    private int N;
    
    public MaxPQ(int capacity)
    {
        pq = (Key[]) new Comparable[capacity+1];
    }
    
    public boolean isEmpty()
    {
        return N == 0;
    }
    
    public void insert(Key key)
    {
        pq[++N] = key;
        swim(N);
        print(pq);
    }
     
    public Key delMax()
    {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N+1] = null;
        
        print(pq);
        return max;
    }
    
    private void swim(int k)
    {
        // parent of node at k is k/2
        while (k > 1 && less(k/2, k))
        {
            exch(k, k/2);
            k = k/2;
        }
    }
    
    private void sink(int k)
    {
        while (2*k <= N)
        {
            // children of node at k are 2k and 2k+1
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    
    private boolean less(int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }
    
    private  void exch(int i, int j)
    {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
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
        MaxPQ intArray = new MaxPQ(13);
        
        intArray.insert(97);
        intArray.insert(69);
        intArray.insert(78);
        intArray.insert(63);
        intArray.insert(61);
        intArray.insert(11);
        intArray.insert(24);
        intArray.insert(33);
        intArray.insert(43);
        intArray.insert(42);
        
        //intArray.delMax();
        //intArray.delMax();
        //intArray.delMax();
        
        intArray.insert(88);
        intArray.insert(34);
        intArray.insert(64);
        
    }
}