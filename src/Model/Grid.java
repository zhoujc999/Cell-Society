package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Abstract representation of a Grid. Grid is a class that contains a map from a cartesian coordinate to a Cell.
 * It has additional methods to support various kinds of grids.
 *
 * @author jz192
 */


public abstract class Grid {
    protected Map<Point, Cell> matrix;
    protected int numRows;
    protected int numColumns;
    protected boolean rowWrap;
    protected boolean columnWrap;

    protected Random random;
    

    /**
     *
     * @param numRows number of rows
     * @param numColumns number of columns
     * @param rowWrap does the rows wrap around
     * @param columnWrap does the columns wrap around
     */
    protected Grid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        this.matrix = new HashMap<>();
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.rowWrap = rowWrap;
        this.columnWrap = columnWrap;
        this.random = new Random();
    }

    /**
     * @return matrix
     */
    public Map<Point, Cell> getMatrix() {
        return matrix;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numColumns;
    }

    /**
     * calculates the actual row with wrap around
     */
    private int gridRowWrap(int row) {
        int remainder = row % numRows;
        if (row < 0) {
            if (remainder < 0) {
                return numRows + remainder;
            }
            else return 0;
        }
        else {
            return remainder;
        }
    }

    private int gridColumnWrap(int column) {
        int remainder = column % numColumns;
        if (column < 0) {
            if (remainder < 0) {
                return numColumns + remainder;
            }
            else return 0;
        }
        else {
            return remainder;
        }
    }

    /**
     *@return true if x, y is out of bounds
     */
    public boolean outOfBounds(int x, int y) {
        if (!rowWrap && (y < 0 || y >= numRows)) {
            return true;
        }
        if (!columnWrap && (x < 0 || x >= numColumns)) {
            return true;
        }
        return false;
    }

    public boolean outOfBounds(Point position) {
        int x = position.getX();
        int y = position.getY();
        return outOfBounds(x, y);
    }

    /**
     * get Cell with coordinate
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Point getPosition(int x, int y) {
        int newX = gridColumnWrap(x);
        int newY = gridRowWrap(y);
        return new Point(newX, newY);
    }

    public Point getPosition(Point position) {
        return getPosition(position.getX(), position.getY());
    }

    public Cell getCell(Point position) {
        return matrix.get(getPosition(position));
    }

    public void setCell(Point position, Cell cell) {
        matrix.put(getPosition(position), cell);
    }



    public void swapPositions(Point current, Point destination) {
        Cell activeCell = getCell(current);
        Cell passiveCell = getCell(destination);
        setCell(current, passiveCell);
        activeCell.setPosition(destination);
        setCell(destination, activeCell);
        passiveCell.setPosition(current);
    }
}
