/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 FixedCapacity.java
 * Execution: java-algs4 FixedCapacity
 * Dependencies: StdIn.java, StdOut.java
 *
 * Description: Data type to create a fixed capacity generic array stack
 *
 *************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item>
{
    private Item[] s;
    private int N = 0;
    
    // stack array of fixed capacity
    public FixedCapacityStack(int capacity)
    {
        s = (Item[]) new Object[capacity];
    }
    
    // is the stack empty?
    public boolean isEmpty()
    {
        return N == 0;
    }
    
    // push new item on stack array
    public void push(Item item)
    {
        // index into array then increment N
        s[N++] = item;
    }
    
    // pop last item and return
    public Item pop()
    {
        Item item = s[--N];
        s[N] = null;
        return item;
    }

    public static void main(String[] args)
    {
        FixedCapacityStack stack = new FixedCapacityStack(10);
        
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