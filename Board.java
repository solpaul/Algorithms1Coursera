/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 Board.java
 * Execution: java-algs4 Board
 * Dependencies:
 *
 *  Data type for the 8-puzzle problem board
 *
 *************************************************************************/
import edu.princeton.cs.algs4.StdOut;
import java.util.LinkedList;
import java.util.List;


public class Board 
{    
    private final int[][] blocks;
    private int moves;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)
    {
        this(blocks, 0);
    }
    
    private Board(int[][] blocks, int moves)
    {
        this.moves = moves;
        this.blocks = new int[blocks.length][blocks.length];
  
        for (int row = 0; row < this.blocks.length; row++)
        {
            for (int col = 0; col < this.blocks.length; col++)
            {
                this.blocks[row][col] = blocks[row][col];
            }
        }
    }
    
    // board dimension N                                       
    public int dimension()
    {
        return blocks.length;
    }
    
    // number of blocks out of place    
    public int hamming()
    {
        int hammingScore = 0;
  
        for (int row = 0; row < this.dimension(); row++)
        {
            for (int column = 0; column < this.dimension(); column++)
            {
                if (blocks[row][column] != 0 && blocks[row][column] != getGoalValueForBlock(row, column))
                {
                    hammingScore++;
                }
            }
        }
  
        return hammingScore;        
    }
    
    // sum of Manhattan distances between blocks and goal    
    public int manhattan()
    {
        int manhattanScore = 0;
     
        for (int row = 0; row < this.dimension(); row++)
        {
            for (int column = 0; column < this.dimension(); column++) 
            {
                if (blocks[row][column] != 0) 
                {
                    int compareValue = blocks[row][column];
        
                    int expectedRow = (compareValue - 1) / this.dimension();
                    int expectedColumn = (compareValue - 1) - (expectedRow * this.dimension());
        
                    manhattanScore += (Math.abs(expectedRow - row) + Math.abs(expectedColumn - column));
                }
            }
        }
     
        return manhattanScore;        
    }
    
    // is this board the goal board?    
    public boolean isGoal()
    {
        for (int row = 0; row < this.dimension(); row++) 
        {
            for (int column = 0; column < this.dimension(); column++) 
            {
                if (blocks[row][column] != getGoalValueForBlock(row, column)) 
                {
                    return false;
                }
            }
        }
     
        return true;        
    }
    
    // a board that is obtained by exchanging any pair of blocks    
    public Board twin()
    {
        int[][] newBlocks = new int[dimension()][dimension()];
        for (int row = 0; row < dimension(); row++) 
        {
            for (int col = 0; col < dimension(); col++) 
            {
                newBlocks [row][col] = blocks[row][col];
            }
        }
     
        //Swap 2 blocks that are not 0
        int rowSwap = 0;
        if (newBlocks[0][0] == 0 || newBlocks[0][1] == 0) {
            rowSwap = 1;
        }
     
        int temp = newBlocks[rowSwap][0];
        newBlocks[rowSwap][0] = newBlocks[rowSwap][1];
        newBlocks[rowSwap][1] = temp;
     
        //Create new board to return
        return new Board(newBlocks, moves);        
    }
    
    // does this board equal y?    
    public boolean equals(Object y)
    {
        if (y == null) 
        {
            return false;
        }
     
        if (this == y) 
        {
            return true;
        }
     
        if (this.getClass() != y.getClass()) 
        {
            return false;
        }
     
        Board that = (Board) y;
     
        if (this.dimension() != that.dimension()) 
        {
            return false;
        }
     
        for (int row = 0; row < this.dimension(); row++) 
        {
            for (int col = 0; col < this.dimension(); col++) 
            {
                if (this.blocks[row][col] != that.blocks[row][col]) 
                {
                    return false;
                }
            }
        }
     
        return true;        
    }
    
    // all neighboring boards    

    public Iterable<Board> neighbors() 
    {
        int spaceRowPos = 0;
        int spaceColPos = 0;
        
        //Find the empty square
        for (int row = 0; row < dimension(); row++) 
        {
            for (int column = 0; column < dimension(); column++)
            {
                if (blocks[row][column] == 0) 
                {
                    spaceRowPos = row;
                    spaceColPos = column;
                }
            }
        }
        
        List<Board> neighbors = new LinkedList<Board>();
        
        //Down
        if (spaceRowPos < dimension() - 1)
        {
            int[][] downBlocks = new int[dimension()][dimension()];
            for (int row = 0; row < dimension(); row++) 
            {
                for (int col = 0; col < dimension(); col++) 
                {
                    downBlocks[row][col] = blocks[row][col];
                }
            }
            
            int temp = downBlocks[spaceRowPos][spaceColPos];
            downBlocks[spaceRowPos][spaceColPos] = downBlocks[spaceRowPos + 1][spaceColPos];
            downBlocks[spaceRowPos + 1][spaceColPos] = temp;
            
            neighbors.add(new Board(downBlocks, moves + 1));
        }
        
        //Up
        if (spaceRowPos > 0) 
        {
            int[][] upBlocks = new int[dimension()][dimension()];
            for (int row = 0; row < dimension(); row++) 
            {
                for (int col = 0; col < dimension(); col++) 
                {
                    upBlocks[row][col] = blocks[row][col];
                }
            }
            
            int temp = upBlocks[spaceRowPos][spaceColPos];
            upBlocks[spaceRowPos][spaceColPos] = upBlocks[spaceRowPos - 1][spaceColPos];
            upBlocks[spaceRowPos - 1][spaceColPos] = temp;
            
            neighbors.add(new Board(upBlocks, moves + 1));
        }
        
        //Left
        if (spaceColPos > 0) 
        {
            int[][] leftBlocks = new int[dimension()][dimension()];
            for (int row = 0; row < dimension(); row++) 
            {
                for (int col = 0; col < dimension(); col++) 
                {
                    leftBlocks[row][col] = blocks[row][col];
                }
            }
            
            int temp = leftBlocks[spaceRowPos][spaceColPos];
            leftBlocks[spaceRowPos][spaceColPos] = leftBlocks[spaceRowPos][spaceColPos - 1];
            leftBlocks[spaceRowPos][spaceColPos - 1] = temp;
            
            neighbors.add(new Board(leftBlocks, moves + 1));
        }
        
        //Right
        if (spaceColPos < dimension() - 1) {
            int[][] rightBlocks = new int[dimension()][dimension()];
            for (int row = 0; row < dimension(); row++) 
            {
                for (int col = 0; col < dimension(); col++) 
                {
                    rightBlocks[row][col] = blocks[row][col];
                }
            }
            
            int temp = rightBlocks[spaceRowPos][spaceColPos];
            rightBlocks[spaceRowPos][spaceColPos] = rightBlocks[spaceRowPos][spaceColPos + 1];
            rightBlocks[spaceRowPos][spaceColPos + 1] = temp;
            
            neighbors.add(new Board(rightBlocks, moves + 1));
        }
        
        return neighbors;    
    }

    
    // string representation of this board (in the output format specified below)    
    public String toString()
    {
        StringBuilder sb = new StringBuilder(dimension() + " \n ");
        
        for (int row = 0; row < dimension(); row++) 
        {
            for (int column = 0; column < dimension(); column++) {
                sb.append(blocks[row][column]);
                sb.append(" ");
            }
      
            sb.append("\n ");
        }
     
        return sb.toString();
    }

    private int getGoalValueForBlock(int row, int column) 
    {
        //Last block is assumed to be the empty one, i.e. 0
        if (row == dimension() - 1 && column == dimension() - 1) 
        {
            return 0; 
        } 
        else 
        {
            return (row * dimension()) + column + 1;
        }
    }
    public static void main(String[] args) 
    {
        int[][] input = new int[][]{{1, 2, 3, 4}, {5, 6, 0, 8}, {9, 10, 11, 12}, {13, 14, 15, 7}};
        Board testBoard = new Board(input);
        
        StdOut.println(testBoard.hamming());
        StdOut.println(testBoard.moves);
        
        StdOut.println(testBoard.toString());
     
        Iterable<Board> result = testBoard.neighbors();
     
        for (Board b : result) 
        {
            StdOut.println(b.hamming());
            StdOut.println(b.moves);
            StdOut.println(b.toString());        
        }
    }
}