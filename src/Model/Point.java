package Model;
import java.util.Objects;

/**
 * Abstract representation of a position in the grid.
 * Can be used as keys in a hashmap
 * @author jz192
 */

public class Point {
    private final int x;
    private final int y;
    public static final Point ZERO = new Point(0, 0);


    /**
     * Instantiate a point given x and y coordinates.
     * @param x is the x-coord
     * @param y is the y-coord
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    protected Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    protected Point add(Point p) {
        return new Point(this.x + p.getX(), this.y + p.getY());
    }

    @Override
    public boolean equals(Object p) {
        if (p == null || !(p instanceof Point)) {
            return false;
        }
        Point position = (Point) p;
        return this.x == position.getX() && this.y == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point " + "(" + x + ", " + y + ")";
    }
}
