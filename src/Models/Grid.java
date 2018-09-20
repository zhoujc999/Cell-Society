package Models;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public abstract class Grid {
    protected Map<Point, Cell> matrix;
    protected int numRows;
    protected int numColumns;
    protected boolean rowWrap;
    protected boolean columnWrap;

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

    public Map<Point, Cell> getMatrix() {
        return matrix;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numColumns;
    }


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

    public boolean outOfBounds(int x, int y) {
        if (!rowWrap && (y < 0 || y >= numRows)) {
            return true;
        }
        if (!columnWrap && (x < 0 || x >= numColumns)) {
            return true;
        }
        return false;
    }

    public Cell getCell(int x, int y) {

        return matrix.get(new Point(x, y));
    }

    public Cell getCell(Point position) {

        return matrix.get(position);
    }


}
