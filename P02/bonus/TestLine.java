
public class TestLine {

    public static void main(String[] args) {

        Line l1 = new Line(22.3213, 123.1235, 234.1246, 19.321, Color.LIME);
        Line l2 = new Line(123.65432, 76.214, 213.23427, 234.1753, Color.PINK);
        Line l3 = new Line(754.1275, 980.2346, 9867.32478, 81.324, Color.TEAL);
        Line l4 = new Line(1, 2, 3, 4, Color.ORANGE);

        System.out.println(l1 + "has length " + l1.length());
        System.out.println(l2 + "has length " + l2.length());
        System.out.println(l3 + "has length " + l3.length());
        System.out.println(l4 + "has length " + l4.length());
    }
}