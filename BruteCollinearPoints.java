/*************************************************************************
 * Name: Paul Solomon
 *
 * Compilation:  javac-algs4 BruteCollinearPoints.java
 * Execution: 
 * Dependencies:
 *
 *  A BruteForce solution for finding collinear points in a set of points. 
 *
 *************************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints 
{

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) 
    {
        checkDuplicatedEntries(points);
        ArrayList<LineSegment> foundSegments = new ArrayList<>();

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        for (int p = 0; p < pointsCopy.length - 3; p++) 
        {
            for (int q = p + 1; q < pointsCopy.length - 2; q++)
            {
                for (int r = q + 1; r < pointsCopy.length - 1; r++)
                {
                    for (int s = r + 1; s < pointsCopy.length; s++) 
                    {

                        if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]) &&
                                pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) 
                        {
                            foundSegments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }

        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    public int numberOfSegments()
    {
        return segments.length;
    }

    public LineSegment[] segments()
    {
        return Arrays.copyOf(segments, numberOfSegments());
    }

    private void checkDuplicatedEntries(Point[] points)
    {
        for (int i = 0; i < points.length - 1; i++)
        {
            for (int j = i + 1; j < points.length; j++)
            {
                if (points[i].compareTo(points[j]) == 0)
                {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++)
        {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(.005);
        for (Point p : points)
        {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments())
        {
            StdOut.println(segment);
            segment.draw();
        }
    }
}