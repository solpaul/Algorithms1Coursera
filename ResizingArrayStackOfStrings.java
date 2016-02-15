/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 ResizingArrayStackOfStrings.java
 * Execution: java-algs4 ResizingArrayStackOfStrings
 * Dependencies: StdIn.java, StdOut.java
 *
 * Description: Data type to create a resizing array stack of strings
 *
 *************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStackOfStrings
{
    private String[] s;
    private int N = 0;
    
    // stack array of size 1
    public ResizingArrayStackOfStrings()
    {
        s = new String[1];
    }
    
    // is stack array empty?
    public boolean isEmpty()
    {
        return N == 0;
    }
    
    // increase size if at capacity and push new item onto stack array
    public void push(String item)
    {
        if (N == s.length)
        {
            resize(2 * s.length);
        }
        // index into array then increment N
        s[N++] = item;
    }
    
    // pop item, decrease size if at 1/4 of capacity, then return item
    public String pop()
    {
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4)
        {
            resize(s.length/2);
        }
        return item;
    }

    // copy stack into new array of size capacity
    public void resize(int capacity)
    {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++)
        {
            copy[i] = s[i];
        }
        
        s = copy;
    }
    
    public static void main(String[] args)
    {
        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
        while(!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if (s.equals("-")) 
            {
                StdOut.print(stack.pop() + " ");
            }
            else
            {
                stack.push(s);
            }
        }
    }
    
}