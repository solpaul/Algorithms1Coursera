/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac WeightedCompressedQuickUnionUF.java
 * Execution: java WeightedCompressedQuickUnionUF
 * Dependencies:
 *
 * An implementation of the Weighted Compressed Quick Union algorithm for 
 * Union Find, with built in client to perform union and connected calls 
 * (printing after each call).
 * 
 *************************************************************************/

public class WeightedCompressedQuickUnionUF
{
    private int[] id;
    private int[] sz;
    private int count;
    
    // create arrays with N elements
    public WeightedCompressedQuickUnionUF(int N)
    {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
            sz[i] = 1;
        }        
    }
    
    // chase parent pointers until reach root
    private int root(int i)
    {
        while (i != id[i])
        {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    // check if index values are the same
    public boolean connected(int p, int q)
    {
        return id[p] == id[q];
    }
    
    // connect two nodes
    public void union(int p, int q)
    {
        int i = root(p);
        int j = root(q);
        if (i == j)
        {
            return;
        }
        
        if (sz[i] < sz[j])
        {
            id[i] = j;
            sz[j] += sz[i];
        }
        else
        {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
    
    public void print()
    {
        StringBuilder temp = new StringBuilder();
        temp.append("");
        
        for (int i = 0; i < count; i++)
        {
            temp.append(id[i]);
            temp.append(" ");
        }
        String strI = temp.toString();
        System.out.println(strI);
    }
    
    public static void main(String[] args)
    {
        WeightedCompressedQuickUnionUF qf = new WeightedCompressedQuickUnionUF(10);
        qf.print();
        qf.union(5,6);
        qf.print();
        qf.union(0,9);
        qf.print();
    }
}