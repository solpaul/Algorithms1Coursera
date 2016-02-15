/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 FixedCapacityStackOfStrings.java
 * Execution: java-algs4 FixedCapacityStackOfStrings
 * Dependencies: StdIn.java, StdOut.java
 *
 * Description: Data type to create a fixed capacity array stack of strings
 *
 *************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStackOfStrings
{
    private String[] s;
    private int N = 0;
    
    // stack array of fixed capacity
    public FixedCapacityStackOfStrings(int capacity)
    {
        s = new String[capacity];
    }
    
    // is the stack empty?
    public boolean isEmpty()
    {
        return N == 0;
    }
    
    // push new item on stack array
    public void push(String item)
    {
        // index into array then increment N
        s[N++] = item;
    }
    
    // pop last item and return
    public String pop()
    {
        String item = s[--N];
        s[N] = null;
        return item;
    }

    public static void main(String[] args)
    {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
        
        // loop through input strings
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