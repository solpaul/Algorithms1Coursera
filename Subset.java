/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 Subset.java
 * Execution: java-algs4 Subset
 * Dependencies: StdIn.java, StdOut.java, Iterator
 *
 * Description: Data type that takes a command-line integer k; reads in a 
 * sequence of N strings from standard input using StdIn.readString(); and 
 * prints out exactly k of them, uniformly at random.
 *
 *************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Subset 
{ 
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("Provide k as a first param.");
            return;
        }
        int k = Integer.parseInt(args[0]);
        
        Deque<String> queue = new Deque<String>();
        
        String[] nToken = new String[1];
        int N = 0;
        
        while (true)
        {           
            if (!StdIn.isEmpty())
            {
                if (N == nToken.length)
                {
                    String[] copy = new String[2 * N];
                    for (int i = 0; i < N; i++)
                    {
                        copy[i] = nToken[i];
                    }
                    
                    nToken = copy;
                }
                // index into array then increment N
                nToken[N++] = StdIn.readString();
            }
            else
            {
                for (int i = 0; i < k; i++)
                {
                    int random = StdRandom.uniform(N);
                    queue.addFirst(nToken[random]);
                    
                    // if not last item in array, swap with last item 
                    if (random != N - 1)
                    {
                        nToken[random] = nToken[N - 1];
                    }
                    
                    // remove last item
                    nToken[N - 1] = null;
                    N--;
                }
                break;
            }
        }
        
        for (int i = 0; i < k; i++)
        {
            StdOut.println(queue.removeFirst());
        }
    }
}
