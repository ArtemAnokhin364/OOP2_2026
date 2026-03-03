public class Segment {
    public Point a, b;

    public double length() {
        double dx = b.x - a.x;
        double dy = b.y - a.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public String toString() {
        return "Segment{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public String toSvg() {
        return "<line x1=\"" + a.x + "\" y1=\""+ a.y + "\"" +
                " x2=\"" + b.x + "\" y2=\"" + b.y + "\"" +
                " style=\"stroke:red;stroke-width:2\" />";
    }
}
