import java.util.ArrayList;

public class BruteCollinearPoints {
    private LineSegment[] lineSegments;

    private Point min(Point lhs, Point rhs) {
        return lhs.compareTo(rhs) <= 0 ? lhs : rhs;
    }

    private Point max(Point lhs, Point rhs) {
        return lhs.compareTo(rhs) >= 0 ? lhs : rhs;
    }

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<LineSegment> segments = new ArrayList<LineSegment>();
        for (int first = 0; first < points.length; ++first) {
            for (int second = first + 1; second < points.length; ++second) {
                for (int third = second + 1; third < points.length; ++third) {
                    for (int four = third + 1; four < points.length; ++four) {
                        if (points[first] == null || points[second] == null
                            || points[third] == null
                            || points[four] == null) {
                            continue;
                        }
                        double firstToSecondSlope = points[first].slopeTo(points[second]);
                        double firstToThirdSlope = points[first].slopeTo(points[third]);
                        double firstToFourSlope = points[first].slopeTo(points[four]);

                        if (firstToThirdSlope == firstToThirdSlope && firstToThirdSlope == firstToFourSlope) {
                            Point firstMin = min(points[first], points[second]);
                            Point secondMin = min(points[third], points[four]);
                            Point min = min(firstMin, secondMin);

                            Point firstMax = max(points[first], points[second]);
                            Point secondMax = max(points[third], points[four]);
                            Point max = max(firstMax, secondMax);

                            points[first] = null;
                            points[second] = null;
                            points[third] = null;
                            points[four] = null;

                            segments.add(new LineSegment(min, max));
                        }
                    }
                }
            }
        }

        lineSegments = segments.toArray(new LineSegment[segments.size()]);
        System.out.println(lineSegments);
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments;
    }
}
