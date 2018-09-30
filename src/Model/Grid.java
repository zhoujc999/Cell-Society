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
    protected Directions.NoOfNeighbors gridConfig;

    protected Random random;
    

    /**
     *
     * @param numRows number of rows
     * @param numColumns number of columns
     * @param rowWrap does the rows wrap around
     * @param columnWrap does the columns wrap around
     */
    protected Grid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap, Directions.NoOfNeighbors gridConfig) {
        this.matrix = new HashMap<>();
        this.numRows = numRows;
        this.rowWrap = rowWrap;
        this.numColumns = numColumns;
        this.columnWrap = columnWrap;
        this.gridConfig = gridConfig;
        this.random = new Random();
    }

    /**
     * @return matrix
     */
    protected Map<Point, Cell> getMatrix() {
        return matrix;
    }

    protected int getNumRows() {
        return numRows;
    }

    protected int getNumColumns() {
        return numColumns;
    }

    /**
     * calculates the actual row with wrap around
     */
    private int gridRowWrap(int row) {
        int remainder = row % numRows;
        if (row < 0 && remainder < 0) {
            return numRows + remainder;
        }
        else if (row > 0) {
            return remainder;
        }
        else {
            return 0;
        }
    }

    private int gridColumnWrap(int column) {
        int remainder = column % numColumns;

        if (column < 0 && remainder < 0) {
            return numColumns + remainder;
        }
        else if (column > 0) {
            return remainder;
        }
        else {
            return 0;
        }
    }



    /**
     *@return true if x, y is out of bounds
     */
    protected boolean outOfXBounds(int x) {
        return (!columnWrap && (x < 0 || x >= numColumns));
    }

    protected boolean outOfYBounds(int y) {
        return (!rowWrap && (y < 0 || y >= numRows));
    }

    protected boolean outOfBounds(Point position) {
        return outOfXBounds(position.getX()) && outOfYBounds(position.getY());
    }

    /**
     * get Cell with coordinate
     * @param x x-coordinate
     * @param y y-coordinate
     */
    protected Point getPosition(int x, int y) {
        return new Point(gridColumnWrap(x), gridRowWrap(y));
    }

    protected Point getPosition(Point position) {
        return getPosition(position.getX(), position.getY());
    }

    protected Cell getCell(Point position) {
        return matrix.get(getPosition(position));
    }

    protected void setCell(Point position, Cell cell) {
        matrix.put(getPosition(position), cell);
    }



    protected void swapPositions(Point current, Point destination) {
        Cell activeCell = getCell(current);
        Cell passiveCell = getCell(destination);
        setCell(current, passiveCell);
        activeCell.setPosition(destination);
        setCell(destination, activeCell);
        passiveCell.setPosition(current);
    }
}
