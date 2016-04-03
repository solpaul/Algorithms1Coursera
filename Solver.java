/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 Solver.java
 * Execution: 
 * Dependencies:
 *
 * Data type to solve the 8-puzzle problem
 *
 *************************************************************************/
import java.util.Comparator;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class Solver 
{   
    private static Comparator<Node> boardComparator = new Comparator<Node>() 
    {
        @Override
        public int compare(Node o1, Node o2) 
        {
            return (o1.board.manhattan() +
                o1.moves) - 
                (o2.board.manhattan() +
                o2.moves);
        }
    }; 
    
    private MinPQ<Node> queue;
    private MinPQ<Node> swapQueue;
    private boolean solvable;
    private boolean swapSolvable;
    private Node endNode;
    
    
    private class Node 
    {
        private Board board;
        private Node parent;
        private int moves;
        
        public Node(Board board, Node parent, int moves) 
        {
            this.board = board;
            this.parent = parent;
            this.moves = moves;
        }
    }
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        solvable = false;
        swapSolvable = false;
     
        queue = new MinPQ<Node>(boardComparator);
        swapQueue = new MinPQ<Node>(boardComparator);
     
        Node initialNode = new Node(initial, null, 0);     
        queue.insert(initialNode);
     
        Node initialSwap = new Node(initial.twin(), null, 0);
        swapQueue.insert(initialSwap);
     
        while (!solvable && !swapSolvable) 
        {
            solvable = solveStep(queue);
            swapSolvable = solveStep(swapQueue);
        }        
    }
    
    private boolean solveStep(MinPQ<Node> q) 
    {     
        //Pop min from queue, check if solved, if not, queue neighbours
        Node current = q.delMin();
        
        if (current.board.isGoal()) 
        {
            endNode = current;
            return true;
        }
     
        for (Board b : current.board.neighbors()) 
        {
            if (current.parent == null || !b.equals(current.parent.board)) 
            {
                Node neighbor = new Node(b, current, current.moves + 1);
                q.insert(neighbor);
            }
        }

        return false;
    }    
    
    // is the initial board solvable?    
    public boolean isSolvable()      
    {
        return solvable;
    }
    // min number of moves to solve initial board; -1 if unsolvable    
    public int moves()
    {
        if (isSolvable()) 
        {
            Node current = endNode;
            int moves = 0;
            
            while (current.parent != null) 
            {
                moves++;
                current = current.parent;
            }
      
            return moves;
        } 
        else 
        {
            return -1;
        }
    }
    
    // sequence of boards in a shortest solution; null if unsolvable    
    public Iterable<Board> solution()
    {
        if (isSolvable()) 
        {
            //create new list and return it
            Stack<Board> sol = new Stack<Board>();
            
            Node current = endNode;
            sol.push(endNode.board);
      
            while (current.parent != null) 
            {
                sol.push(current.parent.board);
                current = current.parent;
            }
      
            return sol;
        } 
        else 
        {
            return null;
        }
    }
    
    // solve a slider puzzle (given below)    
    public static void main(String[] args)
    {   
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}