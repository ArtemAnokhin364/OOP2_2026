public class SvgScene {
    private Polygon[] polygons;
    private int i;  // index pod który wstawić kolejny obiekt



    private BoundingBox boundingBox() {
        double minWidth = 0;
        double minHeight = 0;
        for (Polygon poly : polygons) {
            if (poly == null) continue;

            BoundingBox bb = poly.boundingBox();
            double width = bb.x() + bb.width();
            if (width > minWidth) minWidth = width;
            double height = bb.y() + bb.height();
            if (height > minHeight) minHeight = height;
        }

        return new BoundingBox(0, 0, minWidth, minHeight);
    }

    public SvgScene() {
        polygons = new Polygon[3];
        i = 0;
    }

    public void addPolygon(Polygon poly) {
        polygons[i] = poly;
        i = (i + 1) % 3; // 0 -> 1 -> 2 -> 0 -> 1 -> ...
    }

    public String toSvg() {
        String svg = "";
        for (Polygon p : polygons) {
            if (p == null) continue;

            svg += p.toSvg() + "\n";
        }
        return svg;
    }
}
