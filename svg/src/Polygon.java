import java.util.Arrays;
import java.util.Locale;

public class Polygon {
    private Point[] points;

    public BoundingBox boundingBox() {
        Point p0 = new Point(points[0]);    // lewy górny
        Point p1 = new Point(points[0]);    // prawy dolny
        for (Point p : points) {
            if (p.getX() < p0.getX()) p0.setX(p.getX());
            if (p.getX() > p1.getX()) p1.setX(p.getX());
            if (p.getY() < p0.getY()) p0.setY(p.getY());
            if (p.getY() > p1.getY()) p1.setY(p.getY());
        }
        return new BoundingBox(
             p0.getX(),
             p0.getY(),
             p1.getX() - p0.getX(),
             p1.getY() - p0.getY()
        );
    }

    public Polygon(Point[] points) {
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = new Point(points[i]);
        }
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "points=" + Arrays.toString(points) +
                '}';
    }

    public String toSvg() {
        String pointsString = "";
        for (Point p : points) {
            pointsString += String.format(Locale.ENGLISH,"%.2f,%.2f ", p.getX(), p.getY());
        }

        return String.format("<polygon points=\"%s\" " +
                "style=\"fill:lime;stroke:purple;stroke-width:3\" />",
                pointsString);
    }
}
