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
import edu.princeton.cs.algs4.Stopwatch;
import java.text.DecimalFormat;


public class PercolationRuntime
{
    private double[] thresholds;
    private int T;
    
    // perform T independent experiments on an N-by-N grid
    public PercolationRuntime(int N, int T)
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
        char M = args[2].charAt(0);
        
        if (M != 'N' && M != 'T')
        {
            throw new IllegalArgumentException("Illegal argument");
        }       
        
        double elapsedNew;
        double elapsedOld = 0;
        double ratio;
        DecimalFormat df = new DecimalFormat("#.##");
        
        if (M == 'N')
        {
            int NewN = N;        
            for (int i = 0; i < 12; i++)
            {
                Stopwatch stopwatch = new Stopwatch();
                
                PercolationRuntime runtime = new PercolationRuntime(NewN, T);
                elapsedNew = stopwatch.elapsedTime();
                ratio = elapsedNew/elapsedOld;
                
                
                System.out.println("N = " + NewN + 
                                   ", elapsed time = " + elapsedNew + 
                                   ", ratio = " + df.format(ratio) +
                                   " ln ratio = " + 
                                   df.format(Math.log(ratio)/Math.log(2)));
                
                elapsedOld = elapsedNew;
                NewN = NewN*2;
            }
        }
        else
        {
            int NewT = T;        
            for (int i = 0; i < 12; i++)
            {
                Stopwatch stopwatch = new Stopwatch();
                
                PercolationRuntime runtime = new PercolationRuntime(N, NewT);
                elapsedNew = stopwatch.elapsedTime();
                ratio = elapsedNew/elapsedOld;
                
                System.out.println("T = " + NewT + 
                                   ", elapsed time = " + elapsedNew + 
                                   ", ratio = " + df.format(ratio) +
                                   " ln ratio = " + 
                                   df.format(Math.log(ratio)/Math.log(2)));
                
                elapsedOld = elapsedNew;
                NewT = NewT*2;
            }            
        }
    }
}