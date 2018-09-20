package Models;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Grid {
    private List<List<Cell>> grid;
    private int numRows;
    private int numColumns;
    private boolean rowWrap;
    private boolean columnWrap;


    public Grid(List<List<Cell>> grid, boolean rowWrap, boolean columnWrap) {
        this.grid = grid;
        this.numRows = grid.size();
        this.numColumns = grid.get(0).size();
        this.rowWrap = rowWrap;
        this.columnWrap = columnWrap;
    }


    public List<List<Cell>> getGrid() {
        return grid;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numColumns;
    }

    public Map<Enum, Integer> getNumCellsForEachCurrentState() {
        Map<Enum, Integer> stateToNumCells = new HashMap<Enum, Integer>();
        for (List<Cell> row : grid) {
            for (Cell cell : row) {
                if (stateToNumCells.containsKey(cell.getCurrentState())) {
                    stateToNumCells.put(cell.getCurrentState(), stateToNumCells.get(cell.getCurrentState()) + 1);
                } else {
                    stateToNumCells.put(cell.getCurrentState(), 1);
                }
            }
        }
        return stateToNumCells;
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

        return grid.get(y).get(x);
    }


}
