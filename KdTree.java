/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 KdTree.java
 * Execution: java-algs4 KdTree
 * Dependencies: lots
 *
 * Data type that represent a set of points in the unit square 
 *
 *************************************************************************/
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
    
    // root of KdTree
    private Node root;
    private int size;
    
    private class Node
    {
        private Point2D p;      // the point
        //private Value value;    // the symbol table maps the point to this value
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
    
        public Node(Point2D p, RectHV rect)
        {
            this.p = p;
            this.rect = rect;
        }
    }
    
    // construct an empty tree 
    public KdTree()
    {
        root = null;
        size = 0;
    }
    
    // is the set empty? 
    public boolean isEmpty()
    {
        return root == null;
    }
    
    // number of points in the set 
    public int size() 
    {
        return size;
    }
    
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p)
    {
        if (p == null)
        {
            throw new java.lang.NullPointerException();
        }
        
        RectHV rect; 
        rect = new RectHV(0, 0, 1, 1);
        root = insertV(root, p, rect);
    }
    
    private Node insertV(Node x, Point2D p, RectHV rect)
    {
        if (x == null) 
        {
            size++;
            //rect = new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax());
            return new Node(p, rect);
        }
        
        RectHV r;    
        int cmp = Point2D.X_ORDER.compare(p, x.p);
        if      (cmp < 0)
        {
            if (x.lb == null)
            {
                r = new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax());
            }
            else
            {
                r = x.lb.rect;
            }
            x.lb = insertH(x.lb, p, r);
        }
        else if  (cmp > 0)
        {
            if (x.rt == null)
            {
                r = new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
            }
            else
            {
                r = x.rt.rect;
            }
            x.rt = insertH(x.rt, p, r);
        }
        else
        {
            int cmpy = Point2D.Y_ORDER.compare(p, x.p);
            if (cmpy == 0) return x;
            else
            {
                if (x.rt == null)
                {
                    r = new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
                }
                else
                {
                    r = x.rt.rect;
                }
                x.rt = insertH(x.rt, p, r);
            }
        }
        
        return x;
    }
    
    private Node insertH(Node x, Point2D p, RectHV rect)
    {
        if (x == null) 
        {
            size++;
            //rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
            return new Node(p, rect);
        }
        
        RectHV r;
        int cmp = Point2D.Y_ORDER.compare(p, x.p);
        if   (cmp < 0) 
        {
            if (x.lb == null)
            {
                r = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y());
            }
            else
            {
                r = x.lb.rect;
            }
            x.lb = insertV(x.lb, p, r);
        }
        else if  (cmp > 0)    
        {
            if (x.rt == null)
            {
                r = new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
            }
            else
            {
                r = x.rt.rect;
            }
            x.rt = insertV(x.rt, p, r);
        }
        else
        {
            int cmpx = Point2D.X_ORDER.compare(p, x.p);
            if (cmpx == 0) return x;
            else
            {
                if (x.rt == null)
                {
                    r = new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
                }
                else
                {
                    r = x.rt.rect;
                }
                x.rt = insertV(x.rt, p, r);
            }
        }
        
        return x;       
    }
    
    // does the set contain point p? 
    public boolean contains(Point2D p)
    {
        if (p == null)
        {
            throw new java.lang.NullPointerException();
        }
        
        return containsV(root, p);
    }
    
    private boolean containsV(Node x, Point2D p)
    {
        if (x == null) return false;
        
        if (p.equals(x.p)) return true;
        
        int cmp = Point2D.X_ORDER.compare(p, x.p);
        if   (cmp < 0) return containsH(x.lb, p);
        else           return containsH(x.rt, p);        
    }
    
    private boolean containsH(Node x, Point2D p)
    {
        if (x == null) return false;
        
        if (p.equals(x.p)) return true;
        
        int cmp = Point2D.Y_ORDER.compare(p, x.p);
        if   (cmp < 0) return containsV(x.lb, p);
        else           return containsV(x.rt, p); 
    }
    
    // draw all points to standard draw 
    public void draw()
    {
        //StdDraw.setCanvasSize(800, 800);
        //StdDraw.setXscale(0, 1);
        //StdDraw.setYscale(0, 1);
        //StdDraw.setPenRadius(.005);
        
        drawV(root);
    }
    
    private void drawV(Node x)       
    {
        if (x.lb != null) drawH(x.lb);
        if (x.rt != null) drawH(x.rt);
        
        // draw the line
        StdDraw.setPenRadius(.005);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        
        // draw the point
        StdDraw.setPenRadius(.02);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(x.p.x(), x.p.y());
    }
    
    private void drawH(Node x)       
    {
        if (x.lb != null) drawV(x.lb);
        if (x.rt != null) drawV(x.rt);
        
        // draw the line
        StdDraw.setPenRadius(.005);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        
        // draw the point
        StdDraw.setPenRadius(.02);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.point(x.p.x(), x.p.y());
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

        range(root, rect, points);
        return points;
    }

    private void range(Node x, RectHV rect, Stack<Point2D> points)
    {
        if (x == null) return;
        
        if (rect.contains(x.p)) points.push(x.p);
        
        if (x.lb != null && rect.intersects(x.lb.rect))
        {
            range(x.lb, rect, points);
        }
        if (x.rt != null && rect.intersects(x.rt.rect))
        {
            range(x.rt, rect, points);
        }
           
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
        
        return nearestV(root, p, root.p);
    }
    
    private Point2D nearestV(Node x, Point2D p, Point2D min)
    {
        if (x == null) return min;
        if (p.distanceSquaredTo(x.p) < p.distanceSquaredTo(min))
        {
            min = x.p;
        }
        
        // choose the side with the query point
        if (x.p.x() < p.x())
        {
            min = nearestH(x.rt, p, min);
            if (x.lb != null
                    && min.distanceSquaredTo(p) > x.lb.rect.distanceSquaredTo(p))
            {
                min = nearestH(x.lb, p, min);
            }
        }
        else
        {
            min = nearestH(x.lb, p, min);
            if (x.rt != null
                    && min.distanceSquaredTo(p) > x.rt.rect.distanceSquaredTo(p))
            {
                min = nearestH(x.rt, p, min);
            }
        }
        return min;
    }
        
    private Point2D nearestH(Node x, Point2D p, Point2D min)
    {
        if (x == null) return min;
        if (p.distanceSquaredTo(x.p) < p.distanceSquaredTo(min))
        {
            min = x.p;
        }
        
        // choose the side with the query point
        if (x.p.y() < p.y())
        {
            min = nearestV(x.rt, p, min);
            if (x.lb != null
                    && min.distanceSquaredTo(p) > x.lb.rect.distanceSquaredTo(p))
            {
                min = nearestV(x.lb, p, min);
            }
        }
        else
        {
            min = nearestV(x.lb, p, min);
            if (x.rt != null
                    && min.distanceSquaredTo(p) > x.rt.rect.distanceSquaredTo(p))
            {
                min = nearestV(x.rt, p, min);
            }
        }
        return min;
    }
    
    // unit testing of the methods (optional) 
    public static void main(String[] args)
    {
        KdTree kdtree = new KdTree();
        
        Point2D point1 = new Point2D(0.5, 0.5);
        Point2D point2 = new Point2D(0.4, 0.4);
        Point2D point3 = new Point2D(0.8, 0.2);
        Point2D point4 = new Point2D(0.4, 0.4);
        Point2D point5 = new Point2D(0.8, 0.2);
        
        kdtree.insert(point1);
        kdtree.insert(point2);
        kdtree.insert(point3);
        kdtree.insert(point5);
        
        boolean contained = kdtree.contains(point4);
        
        StdOut.println("Contains = " + String.valueOf(contained));
        
        kdtree.draw();
        
        RectHV rect = new RectHV(0.35, 0.4, 0.6, 0.8);
        
        StdDraw.setPenRadius(0.005);
        rect.draw();
        
        Point2D nearest;        
        nearest = kdtree.nearest(point4);        
        StdOut.println("Nearest = " + nearest.toString());
        
        for (Point2D point : kdtree.range(rect))
        {
            StdOut.println(point);
        }
    }
}