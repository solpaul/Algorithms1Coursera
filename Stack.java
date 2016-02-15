/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 Stack.java
 * Execution: java-algs4 Stack
 * Dependencies: StdIn.java, StdOut.java
 *
 * Description: Data type to create a linked generic stack
 *
 *************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stack<Item>
{
    private Node first = null;
    
    // linked node - string and pointer to next node
    private class Node
    {
        Item item;
        Node next;
    }
    
    // is the stack empty?
    public boolean isEmpty()
    {
        return first == null;
    }
    
    // push new item onto stack
    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    
    // pop last item and return
    public Item pop()
    {
        Item item = first.item;
        first = first.next;
        return item;
    }
    
    public static void main(String[] args)
    {
        Stack stack = new Stack();
        
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