/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 PointSET.java
 * Execution: java-algs4 PointSET
 * Dependencies: lots
 *
 * Data type that represent a set of points in the unit square 
 *
 *************************************************************************/
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
//import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class PointSET {
    
    private SET<Point2D> set;

    // construct an empty set of points 
    public PointSET()
    {
        set = new SET<Point2D>();
    }
    
    // is the set empty? 
    public boolean isEmpty()
    {
        return set.isEmpty();
    }
    
    // number of points in the set 
    public int size()
    {
        return set.size();
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p)
    {
        if (p == null)
        {
            throw new java.lang.NullPointerException();
        }
        
        if (this.contains(p))
        {
            return;
        }
        else
        {
            set.add(p);
        }
    }
    
    // does the set contain point p? 
    public boolean contains(Point2D p)
    {
        if (p == null)
        {
            throw new java.lang.NullPointerException();
        }
        
        return set.contains(p);
    }
    
    // draw all points to standard draw 
    public void draw()
    {
//        StdDraw.setCanvasSize(800, 800);
//        StdDraw.setXscale(0, 1);
//        StdDraw.setYscale(0, 1);
//        StdDraw.setPenRadius(.005);
        
        for (Point2D s : set)
        {
            s.draw();
        }
    }
    
    // all points that are inside the rectangle 
    public Iterable<Point2D> range(RectHV rect)  
    {
        if (rect == null)
        {
            throw new java.lang.NullPointerException();
        }
        
        // create a new list and return it
        Stack<Point2D> points = new Stack<Point2D>();
            
        for (Point2D s : set)
        {
            if (rect.contains(s))
            {
                points.push(s);
            }
        }
      
        return points;
    }
    
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p)
    {
        if (p == null)
        {
            throw new java.lang.NullPointerException();
        }
        
        if (this.isEmpty())
        {
            return null;
        }
        
        Point2D nearest = new Point2D(2, 2);
                
        for (Point2D s : set)
        {
            if (p.distanceTo(s) <= p.distanceTo(nearest))
            {
                nearest = s;
            }
        }
        
        return nearest;
    }
    
    // unit testing of the methods (optional) 
    public static void main(String[] args)
    {
        PointSET newset = new PointSET();
        
        Point2D point1 = new Point2D(0.5, 0.5);
        Point2D point2 = new Point2D(0.25, 0.5);
        Point2D point3 = new Point2D(0.4, 0.5);
        Point2D point4 = new Point2D(0.75, 0.75);
        
        newset.insert(point1);
        newset.insert(point2);
        newset.insert(point3);
                      
        newset.draw();
        
        RectHV rect = new RectHV(0.35, 0.4, 0.6, 0.8);
        
        rect.draw();
        
        Point2D nearest;        
        nearest = newset.nearest(point4);        
        StdOut.println("Nearest = " + nearest.toString());
        
        for (Point2D point : newset.range(rect))
        {
            StdOut.println(point);
        }
    }
}