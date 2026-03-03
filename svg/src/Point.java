public class Point {
    public double x, y;

    @Override
    public String toString() {
        return "(" + this.x + ", " + y + ")";
    }

    public String toSvg() {
        return "<circle r=\"2\" cx=\"" + this.x +
                "\" cy=\"" + this.y + "\" fill=\"black\" />";
    }

    public void translate(double dx, double dy) {
        x += dx;
        this.y += dy;
    }

    public Point translated(double dx, double dy) {
        Point newPoint = new Point();
        newPoint.x = this.x + dx;
        newPoint.y = y + dy;
        return newPoint;
    }
}
