public class Main {
    public static void main(String[] args) {
        Point p1 = new Point();
        p1.x = 50.0;
        p1.y = 50.0;
        System.out.println(p1);
        System.out.println(p1.toSvg());

        p1.translate(10, -20);
        System.out.println(p1);
        System.out.println(p1.toSvg());

        Point p2 = p1.translated(-30, 40);
        System.out.println(p2);
        System.out.println(p2.toSvg());

        Segment s1 = new Segment();
        s1.a = p1;
        s1.b = p2;
        System.out.println("długość s1 = " + s1.length());
        System.out.println(s1);
        System.out.println(s1.toSvg());

        // TODO: Zadanie 5 na aktywność przy tablicy
    }
}