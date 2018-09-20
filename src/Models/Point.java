package Models;

/**
 * Abstract representation of a cell. Includes the minimum methods a cell has to implement.
 *
 * @author jz192
 */

import java.util.Objects;

public class Point {
    private int x;
    private int y;
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

    public boolean equals(Point p) {
        return this.x == p.getX() && this.y == p.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
