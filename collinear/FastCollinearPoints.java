/* *****************************************************************************
 *  Name: Roy Wang
 *  Date: 11/18/20
 *  Description: fast collinear points
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] segments;
    private ArrayList<LineSegment> segmentsAL;

    public FastCollinearPoints(
            Point[] points) { // finds all line segments containing 4 or more points

        if (points == null) { // test for null input array
            throw new IllegalArgumentException();
        }

        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy);

        for (int i = 0; i < copy.length; i++) { // test for null points
            if (copy[i] == null) {
                throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < copy.length - 1; i++) {  // test for duplicate points
            if (copy[i].compareTo(copy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }


        segmentsAL = new ArrayList<>();

        for (int i = 0; i < copy.length; i++) {
            Arrays.sort(copy); // reset
            Arrays.sort(copy,
                        copy[i].slopeOrder()); // order by slope
            int start = 1;
            for (int end = 2; end < copy.length; end++) {
                Point origin = copy[0]; // treat this point as origin

                // find end of current line segment
                while (end < copy.length
                        && origin.slopeOrder().compare(copy[start], copy[end]) == 0) {
                    end++;
                }

                // if line segment has length 4 and is not a duplicate, add to ArrayList
                if (end - start >= 3 && copy[start].compareTo(origin) > 0) {
                    LineSegment segment = new LineSegment(origin, copy[end - 1]);
                    segmentsAL.add(segment);
                }

                start = end; // jump to next set of points
            }
        }
        segments = segmentsAL.toArray(new LineSegment[segmentsAL.size()]);
    }

    public int numberOfSegments() { // the number of line segments
        return segments.length;
    }

    public LineSegment[] segments() { // the line segments
        return segments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);

        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
