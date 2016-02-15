/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 PercolationStats.java
 * Execution: java-algs4 PercolationStats N T
 * Dependencies: StdRandom.java, StdStats.java
 *
 * Description: Data type to perform monte carlo simulation and estimate
 * percolation threshold
 * 
 *************************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats
{
    private double[] thresholds;
    private int T;
    
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T)
    {
        if (N <= 0 || T <= 0)
        {
            throw new IllegalArgumentException("Illegal argument");
        }
        
        this.T = T;
        Percolation percolation;
        thresholds = new double[T];
        
        for (int t = 0; t < T; t++)
        {
            int n = 0;
            percolation = new Percolation(N);
            
            while (n < N * N)
            {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;
                
                if (percolation.isOpen(i, j))
                {
                    continue;
                }
                
                n++;
                percolation.open(i, j);
                
                if (percolation.percolates())
                {
                    thresholds[t] = (double) n / (N * N);
                    break;
                }
            }
        }
    }
   
    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(thresholds);
    }
   
    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(thresholds);
    }
    
    // low  endpoint of 95% confidence interval
    public double confidenceLo()
    {
       return mean() - 1.96 * stddev() / Math.sqrt(T); 
    }
    
    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

   // test client (described below)
   public static void main(String[] args)
   {
       int N = Integer.parseInt(args[0]);
       int T = Integer.parseInt(args[1]);
       
       PercolationStats stats = new PercolationStats(N, T);
       
       double mean = stats.mean();
       double stddev = stats.stddev();
       double low = stats.confidenceLo();
       double high = stats.confidenceHi();
       
       System.out.println("mean                    = " + mean);
       System.out.println("stddev                  = " + stddev);
       System.out.println("95% confidence interval = " + low + ", " + high);
   }
}