/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 Deque.java
 * Execution: java-algs4 Deque
 * Dependencies: StdIn.java, StdOut.java, Iterator
 *
 * Description: Data type that is a generalisation of a stack and queue 
 * that supports adding and removing items from either the front of the 
 * back
 *
 *************************************************************************/
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;


public class Deque<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int N;
    
    // linked node - string and pointer to next node
    private class Node
    {
        private Item item;
        private Node next;
        private Node previous;
        
        Node(Item item)
        {
            this.item = item;
            this.next = null;
            this.previous = null;
        }
    }
    
    // construct an empty deque
    public Deque()
    {
        first = null;
        last = null;
        N = 0;
    }
    
    // is the deque empty
    public boolean isEmpty()
    {
        return size() == 0;
    }
    
    // return the number of items on the deque
    public int size()
    {
        return N;
    }
    
    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException();
        }
        
        Node oldfirst = first;
        first = new Node(item);
        first.next = oldfirst;
        
        if (isEmpty())
        {
            last = first;
        }
        else
        {
            oldfirst.previous = first;
        }
        
        N++;
    }
    
    // add the item to the end
    public void addLast(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException();
        }
        
        Node oldlast = last;
        last = new Node(item);
        last.previous = oldlast;
        
        if (isEmpty())
        {
            first = last;
        }
        else
        {
            oldlast.next = last;
        }
        
        N++;
    }
    
    // remove and return the item from the front
    public Item removeFirst()
    {
        if (N == 0)
        {
            throw new java.util.NoSuchElementException();
        }
        
        Item item = first.item;
        first = first.next;
        N--;
        
        if (isEmpty())
        {
            last = null;
        }
        else
        {
            first.previous = null;
        }
        
        return item;
    }
    
    // remove and return the item from the end
    public Item removeLast()
    {
        if (N == 0)
        {
            throw new java.util.NoSuchElementException();
        }
        
        Item item = last.item;
        last = last.previous;
        N--;
        
        if (isEmpty())
        {
            first = null;
        }
        else
        {
            last.next = null;
        }
        
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        
        public boolean hasNext()
        {
            return current != null;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (current == null)
            {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    // unit testing
    public static void main(String[] args)
    {
        Deque deque = new Deque();
        
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        
        for (Object s : deque)
        {
            StdOut.println(s);
        }
                
        // StdOut.print(deque.removeLast() + " ");
        // StdOut.print(deque.removeFirst() + " ");
    }
}