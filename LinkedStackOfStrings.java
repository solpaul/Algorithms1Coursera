/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 LinkedStackOfStrings.java
 * Execution: java-algs4 LinkedStackOfStrings
 * Dependencies: StdIn.java, StdOut.java
 *
 * Description: Data type to create a linked stack of strings
 *
 *************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStackOfStrings
{
    private Node first = null;
    
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
    
    // push new item onto stack
    public void push(String item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    
    // pop last item and return
    public String pop()
    {
        String item = first.item;
        first = first.next;
        return item;
    }
    
    public static void main(String[] args)
    {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        
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