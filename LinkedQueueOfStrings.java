/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 LinkedQueueOfStrings.java
 * Execution: java-algs4 LinkedQueueOfStrings
 * Dependencies: StdIn.java, StdOut.java
 *
 * Description: Data type to create a linked queue of strings
 *
 *************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedQueueOfStrings
{
    private Node first, last;
    
    // linked node - string and pointer to next node
    private class Node
    {
        String item;
        Node next;
    }
    
    // is the stack empty?
    public boolean isEmpty()
    {
        return first == null;
    }
    
    // add item to end of list
    public void enqueue(String item)
    {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        
        if (isEmpty())
        {
            first = last;
        }
        else
        {
            oldlast.next = last;
        }
    }
    
    // remove item from start of list and return
    public String dequeue()
    {
        String item = first.item;
        first = first.next;
        if (isEmpty())
        {
            last = null;
        }
        return item;
    }
    
    public static void main(String[] args)
    {
        LinkedQueueOfStrings stack = new LinkedQueueOfStrings();
        
        // loop through input strings
        while(!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            if (s.equals("-")) 
            {
                StdOut.print(stack.dequeue() + " ");
            }
            else
            {
                stack.enqueue(s);
            }
        }
    }
}