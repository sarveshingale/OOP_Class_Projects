
public class Line {

    // Constructor
    public Line(double x1, double y1, double x2, double y2, Color color) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;        
    }

    // Line length
    public double length() {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    // Line toString()
    @Override
    public String toString() {
        String text = String.format("%-10s (%.3f, %.3f) - (%.3f, %.3f) ", color, x1, y1, x2, y2);
        return text;
    }

    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Color color;
}