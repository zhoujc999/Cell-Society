package Model;
import java.util.Map;
import java.util.HashMap;


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

    /**
     *
     * @param numRows number of rows
     * @param numColumns number of columns
     * @param rowWrap does the rows wrap around
     * @param columnWrap does the columns wrap around
     */
    protected Grid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        this.matrix = new HashMap<>();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                matrix.put(new Point(j, i), null);
            }
        }
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.rowWrap = rowWrap;
        this.columnWrap = columnWrap;
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
        int wrapRow;
        if (row < 0) {
            wrapRow = numRows + (row % numRows);
        }
        else {
            wrapRow = 0 + (row % numRows);
        }
        return wrapRow;
    }

    private int gridColumnWrap(int column) {
        int wrapColumn;
        if (column < 0) {
            wrapColumn = numColumns + (column % numColumns);
        }
        else {
            wrapColumn = 0 + (column % numColumns);
        }
        return wrapColumn;
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
    protected Cell getCell(int x, int y) {

        return matrix.get(new Point(x, y));
    }

    /**
     * get Cell with Point
     * @param position
     */
    public Cell getCell(Point position) {

        return matrix.get(position);
    }


}
