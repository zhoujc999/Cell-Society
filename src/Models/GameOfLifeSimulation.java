package Models;
import java.util.HashSet;
import java.util.Map;

/**
 * Abstract representation of the Game of Life Simulation
 *
 * @author jz192
 */


public class GameOfLifeSimulation extends Simulation {
//    No wrap arounds for Game of Life
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;

    private Map<CellStates.GameOfLifeStates, Integer> statistics;

    public GameOfLifeSimulation(int numRows, int numColumns, Map<Point, CellStates.GameOfLifeStates> initialState) {
        if (initialState.size() != numRows * numColumns) {
            throw new IllegalArgumentException("InitialState Number of Points Error");
        }
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numCells = numRows * numColumns;
        initializeGrid();
        initializeCells(initialState);
    }

    protected void initializeGrid() {
        this.grid = new GameOfLifeGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }

    /**
     * initialize Cells and put them on grid
     */
    protected void initializeCells(Map<Point, CellStates.GameOfLifeStates> initialParam) {
        for (Map.Entry<Point, CellStates.GameOfLifeStates> entry : initialParam.entrySet()) {
            Point position = entry.getKey();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            GameOfLifeCell cell = new GameOfLifeCell(position, grid, entry.getValue());
            grid.getMatrix().put(position, cell);
        }
    }

    /**
     * @return the number of live Cells/ total number of Cells
     */
    public double liveRatio() {
        statistics = super.getNumCellsForEachCurrentState();
        return statistics.get(CellStates.GameOfLifeStates.LIVE) / numCells;
    }



    @Override
    public String toString() {
        return "Game Of Life";
    }
}
