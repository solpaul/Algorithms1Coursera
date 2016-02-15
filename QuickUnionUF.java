/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac QuickUnionUF.java
 * Execution: java QuickUnionUF
 * Dependencies:
 *
 * An implementation of the Quick Union algorithm for Union Find, with 
 * built in client to perform union and connected calls (printing after 
 * each call).
 * 
 *************************************************************************/

public class QuickUnionUF
{
    private int[] id;
    private int count;
    
    // create array with N elements
    public QuickUnionUF(int N)
    {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
        }
    }
    
    // chase parent pointers until reach root
    private int root(int i)
    {
        while (i != id[i])
        {
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
        id[i] = j;
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
        QuickUnionUF qf = new QuickUnionUF(10);
        qf.print();
        qf.union(5,6);
        qf.print();
        qf.union(0,9);
        qf.print();
    }
}