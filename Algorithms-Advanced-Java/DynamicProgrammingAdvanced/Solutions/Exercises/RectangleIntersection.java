import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RectangleIntersection {
    private static class Point {
        int coordinate;
        boolean isEnd;

        public Point(int coordinate, boolean isEnd) {
            this.coordinate = coordinate;
            this.isEnd = isEnd;
        }

        public int getCoordinate() {
            return coordinate;
        }
    }

    private static class Rectangle {
        Point left;
        Point right;
        Point bottom;
        Point top;

        public Rectangle(Point left, Point right, Point bottom, Point top) {
            this.left = left;
            this.right = right;
            this.bottom = bottom;
            this.top = top;
        }
    }

    private static class Section {
        int from;
        int to;
        List<Point> verticalPoints;

        public Section(int from, int to) {
            this.from = from;
            this.to = to;
            this.verticalPoints = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        Rectangle[] rectangles = new Rectangle[n];
        List<Point> horizontalPoints = new ArrayList<>(n * 2);

        for (int i = 0; i < n; i++) {
            int[] coordinates = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Point left = new Point(coordinates[0], false);
            Point right = new Point(coordinates[1], true);
            Point bottom = new Point(coordinates[2], false);
            Point top = new Point(coordinates[3], true);

            rectangles[i] = new Rectangle(left, right, bottom, top);

            horizontalPoints.add(left);
            horizontalPoints.add(right);
        }

        horizontalPoints.sort(Comparator.comparing(Point::getCoordinate));
        Arrays.sort(rectangles, Comparator.comparing(rectangle -> rectangle.left.coordinate));

        List<Section> sections = new ArrayList<>();
        for (int i = 1; i < horizontalPoints.size(); i++) {
            Section section = new Section(
                    horizontalPoints.get(i - 1).coordinate,
                    horizontalPoints.get(i).coordinate
            );
            sections.add(section);
        }

        for (Rectangle rectangle : rectangles) {
            int start = rectangle.left.getCoordinate();
            int end = rectangle.right.getCoordinate();
            for (Section section : sections) {
                if (section.from == start
                        || section.to == end
                        || (section.to < end && section.from > start)) {

                    section.verticalPoints.add(rectangle.bottom);
                    section.verticalPoints.add(rectangle.top);
                }
            }
        }

        int overlappedArea = 0;
        for (Section section : sections) {
            if (section.verticalPoints.size() < 4) {
                continue;
            }

            int wight = section.to - section.from;
            int height = calculateLineCoverLength(section.verticalPoints, 2);

            overlappedArea += wight * height;
        }

        System.out.println(overlappedArea);
    }

    public static int calculateLineCoverLength(List<Point> points, int coverRequired) {
        points.sort(Comparator.comparing(Point::getCoordinate));

        int lineCoverCount = 0;
        int start = points.get(0).getCoordinate();
        int coverLength = 0;

        for (Point point : points) {
            if (lineCoverCount >= coverRequired) {
                coverLength = coverLength + (point.getCoordinate() - start);
            }

            if (!point.isEnd) {
                lineCoverCount++;
            } else {
                lineCoverCount--;
            }

            start = point.getCoordinate();
        }

        return coverLength;
    }
}