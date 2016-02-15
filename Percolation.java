/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 Percolation.java
 * Execution:
 * Dependencies: WeightedQuickUnionUF.java
 *
 * Description: Data type to model a percolation system
 *
 *************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{    
    private int n;
    
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufBottom;
    
    private boolean[][] nodes; // false - closed site, true - open site
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N)
    {
        if (N <= 0)
        {
            throw new IllegalArgumentException("Illegal argument");
        }
        
        n = N;
        nodes = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
        ufBottom = new WeightedQuickUnionUF(n * n + 2);
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nodes[i][j] = false;
            }
        }
    }
    
    private int xyTo1d(int i, int j)
    {
        int pos = n*(i - 1) + j - 1;
        return pos;
    }
    
    // open site (row i, column j) if it is not open already
    public void open(int i, int j)
    {
        isInbounds(i, j);        
        nodes[i - 1][j - 1] = true;
        int currentNode = xyTo1d(i, j);

        // union with top virtual node
        if (i == 1)
        {
            uf.union(currentNode, n * n);
            ufBottom.union(currentNode, n * n);
        }
        
        // union with bottom virtual node
        if (i == n)
        {
            ufBottom.union(currentNode, n*n + 1);
        }
        
        // union with below node
        if (i < n && isOpen(i + 1, j))
        {          
            uf.union(currentNode, xyTo1d(i + 1, j));
            ufBottom.union(currentNode, xyTo1d(i + 1, j));
        }
        
        // union with above node
        if (i > 1 && isOpen(i - 1, j))
        {
            uf.union(currentNode, xyTo1d(i - 1, j));
            ufBottom.union(currentNode, xyTo1d(i - 1, j));
        }
        
        // union with right node
        if (j < n && isOpen(i, j + 1))
        {
            uf.union(currentNode, xyTo1d(i, j + 1));
            ufBottom.union(currentNode, xyTo1d(i, j + 1));
        }
        
        // union with left node
        if (j > 1 && isOpen(i, j - 1))
        {
            uf.union(currentNode, xyTo1d(i, j - 1));
            ufBottom.union(currentNode, xyTo1d(i, j - 1));
        }
    }
    
    // is site (row i column j) in bounds?
    private void isInbounds(int i, int j)
    {
        if (i < 1 || i > n || j < 1 || j > n)
        {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j)
    {
        isInbounds(i, j);
        return nodes[i - 1][j - 1];
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j)
    {
        isInbounds(i, j);
        return uf.connected(n*n, xyTo1d(i, j)) && isOpen(i, j);
    }
    
    // does the system percolate?
    public boolean percolates()
    {
        return ufBottom.connected(n*n, n*n + 1);
    }
}