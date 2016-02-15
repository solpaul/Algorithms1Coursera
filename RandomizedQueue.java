/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 RandomizedQueue.java
 * Execution: java-algs4 RandomizedQueue
 * Dependencies: StdIn.java, StdOut.java
 *
 * Description: Data type that is a randomized queue i.e. a queue where the
 * item removed is chosen uniformly at random from the items
 *
 *************************************************************************/
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
    private Item[] s;
    private int N = 0;
    
    // construct an empty randomized queue
    public RandomizedQueue()
    {
        s = (Item[]) new Object[1];
    }
    
    // is the queue empty?
    public boolean isEmpty()
    {
        return N == 0;
    }
    
    // return the number of items on the queue
    public int size()
    {
        return N;
    }
    
    // increase size if at capacity and add new item
    public void enqueue(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException();
        }
        
        if (N == s.length)
        {
            resize(2 * s.length);
        }
        // index into array then increment N
        s[N++] = item;
    }
    
    // remove and return a random item, decrease size if at 1/4 capacity
    public Item dequeue()
    {
        if (N == 0)
        {
            throw new java.util.NoSuchElementException();
        }
        
        // pick random item
        int random = StdRandom.uniform(N);
        Item item = s[random];
        
        // if not last item in array, swap with last item 
        if (random != N - 1)
        {
            s[random] = s[N - 1];
        }
        
        // remove last item
        s[N - 1] = null;
        N--;
        
        if (N > 0 && N == s.length/4)
        {
            resize(s.length/4);
        }
        return item;
    }
    
    // return (but do not remove) a random item
    public Item sample()
    {
        if (N == 0)
        {
            throw new java.util.NoSuchElementException();
        }
        
        return s[StdRandom.uniform(N)];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomListIterator();
    }
    
    private class RandomListIterator implements Iterator<Item>
    {
        private int i = 0;
        private int[] index;
        
        public RandomListIterator()
        {
            index = new int[N];
            for (int j = 0; j < N; j++)
            {
                index[j] = j;
            }
            StdRandom.shuffle(index);
        }
        
        public boolean hasNext()
        {
            return i < N;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (!hasNext())
            {
                throw new java.util.NoSuchElementException();
            }
            
            return s[index[i++]];
        }
    }
    
    // copy queue into new array of size capacity
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
        {
            copy[i] = s[i];
        }
        
        s = copy;
    }
    
    // unit testing
    public static void main(String[] args)
    {
        RandomizedQueue queue = new RandomizedQueue();
        
        for (int i = 1; i < 16; i++)
        {
            queue.enqueue(i);
        }
        
        for (int i = 1; i < 8; i++)
        {
            queue.dequeue();    
        }
        
        StdOut.println(queue.sample());
        
        for (Object s : queue)
        {
            StdOut.println(s);
        }
    }
}