package Models;
import java.util.*;

public class Grid {
    private List<List<Cell>> grid;
    private int rows;
    private int columns;
    private boolean rowWrap;
    private boolean columnWrap;


    public Grid(List<List<Cell>> grid, boolean rowWrap, boolean columnWrap) {
        this.grid = grid;
        this.rows = grid.size();
        this.columns = grid.get(0).size();
        this.rowWrap = rowWrap;
        this.columnWrap = columnWrap;
    }


    public List<List<Cell>> getMatrix() {
        return grid;
    }

    public int getWidth() {
        return rows;
    }

    public int getHeight() {
        return columns;
    }

    public Map<Enum, Integer> getNumCellsForEachState() {
        Map<Enum, Integer> stateToNumCells = new HashMap<Enum, Integer>();
        for (List<Cell> row : grid) {
            for (Cell cell : row) {
                if (stateToNumCells.containsKey(cell.getState())) {
                    stateToNumCells.put(cell.getState(), stateToNumCells.get(cell.getState()) + 1);
                } else {
                    stateToNumCells.put(cell.getState(), 1);
                }
            }
        }
        return stateToNumCells;
    }
    private int gridRowWrap(int newRowPos) {
        int wrapRowPos;
        if (newRowPos < 0) {
            wrapRowPos = getNumRows() + newRowPos;
        } else {
            wrapRowPos = newRowPos - getNumRows();
        }
        return wrapRowPos;
    }

    private int gridColWrap(int newColPos) {
        int wrapColPos;
        if (newColPos < 0) {
            wrapColPos = getNumCols() + newColPos;
        } else {
            wrapColPos = newColPos - getNumCols();
        }
        return wrapColPos;
    }

    private boolean rowOutOfBounds(int row) {
        return row < 0 || row >= getNumRows();
    }

    private boolean colOutOfBounds(int col) {
        return col < 0 || col >= getNumCols();
    }




}
