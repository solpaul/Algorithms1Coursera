/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac QuickFindUF.java
 * Execution: java QuickFindUF
 * Dependencies:
 *
 * An implementation of the Quick Find algorithm for Union Find, with built 
 * in client to perform union and connected calls (printing after each call).
 * 
 *************************************************************************/

public class QuickFindUF
{
    private int[] id;
    private int count;
    
    // create array with N elements
    public QuickFindUF(int N)
    {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
        }
    }
    
    // check if index values are the same
    public boolean connected(int p, int q)
    {
        return id[p] == id[q];
    }
    
    // connect two nodes
    public void union(int p, int q)
    {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++)
        {
            if (id[i] == pid)
            {
                id[i] = qid;
            }
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
        QuickFindUF qf = new QuickFindUF(10);
        qf.print();
        qf.union(8,9);
        qf.print();
        qf.union(4,0);
        qf.print();
        qf.union(8,5);
        qf.print();
        qf.union(2,6);
        qf.print();
        qf.union(1,7);
        qf.print();
        qf.union(0,3);
        qf.print();
    }
}