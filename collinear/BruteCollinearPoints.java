import java.util.ArrayList;
import java.util.Arrays;

/* *****************************************************************************
 *  Name: Roy Wang
 *  Date: 11/3/2020
 *  Description: Brute Force Collinear Points
 **************************************************************************** */
public class BruteCollinearPoints {
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points

        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) { // test for null points
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
        }

        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy);

        for (int i = 0; i < copy.length - 1; i++) { // test for duplicate point
            if (copy[i].compareTo(copy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        ArrayList<LineSegment> segs = new ArrayList<>();
        
        for (int first = 0; first < copy.length - 3; first++)
            for (int second = first + 1; second < copy.length - 2; second++)
                for (int third = second + 1; third < copy.length - 1; third++)
                    for (int fourth = third + 1; fourth < copy.length; fourth++) {
                        if (copy[first].slopeTo(copy[second]) == copy[first].slopeTo(copy[third]) &&
                                copy[first].slopeTo(copy[second]) == copy[first]
                                        .slopeTo(copy[fourth])) {
                            LineSegment connected = new LineSegment(copy[first], copy[fourth]);
                            if (!segs.contains(connected)) {
                                segs.add(connected);
                            }
                        }
                    }


        lines = segs.toArray(new LineSegment[segs.size()]);
    }

    public int numberOfSegments() {
        // the number of line segments
        return lines.length;
    }


    public LineSegment[] segments() { // the line segments
        LineSegment[] copy = Arrays.copyOf(lines, lines.length);
        return copy;
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(3, 2),
                new Point(6, 4), new Point(9, 6), new Point(12, 8)
        };
        BruteCollinearPoints test = new BruteCollinearPoints(points);
        System.out.println(test.numberOfSegments());
    }
}

