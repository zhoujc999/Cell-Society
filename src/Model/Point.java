package Model;

/**
 * Abstract representation of a cell. Includes the minimum methods a cell has to implement.
 *
 * @author jz192
 */

import java.util.Objects;

public class Point {
    private final int x;
    private final int y;
    public static final Point ZERO = new Point(0, 0);

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
    public Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point add(Point p) {
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
