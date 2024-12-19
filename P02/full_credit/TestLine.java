
public class TestLine {

    public static void main() {

        Line l1 = new Line(22.3213, 123.1235, 234.1246, 19.321, LIME);
        Line l2 = new Line(123.65432, 76.214, 213.23427, 234.1753, PINK);
        Line l3 = new Line(754.1275, 980.2346, 9867.32478, 81.324, TEAL);
        Line l4 = new Line(890.213, 90.324, 45.3214, 789.3211, ORANGE);

        System.out.println(l1 + "has length " + l1.lenghth());
        System.out.println(l2 + "has length " + l2.lenghth());
        System.out.println(l3 + "has length " + l3.lenghth());
        System.out.println(l4 + "has length " + l4.lenghth());
    }
}