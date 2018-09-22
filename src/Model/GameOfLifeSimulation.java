package Model;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract representation of the Game of Life Simulation
 * After
 *
 * @author jz192
 */


public class GameOfLifeSimulation extends Simulation {
//    No wrap arounds for Game of Life
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;
    private GameOfLifeGrid grid;

    private Map<CellStates.GameOfLifeStates, Integer> statistics;

    public GameOfLifeSimulation(int numRows, int numColumns, Map<Point, CellStates.GameOfLifeStates> initialState) {
        if (initialState.size() != numRows * numColumns) {
            throw new IllegalArgumentException("InitialState - Number of Points Error");
        }
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numCells = numRows * numColumns;

        initializeStatistics();
        initializeView();
        initializeGrid();
        initializeCells(initialState);
        initializeAllNeighbors(grid);
    }




    protected void initializeGrid() {
        this.grid = new GameOfLifeGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }

    /**
     * initialize Cells and put them on grid
     */
    protected void initializeCells(Map<Point, CellStates.GameOfLifeStates> initialParam) {
//        System.out.println(initialParam.size());
        for (Map.Entry<Point, CellStates.GameOfLifeStates> entry : initialParam.entrySet()) {
            Point position = entry.getKey();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState - Duplicate Points Error");
            }
            GameOfLifeCell cell = new GameOfLifeCell(position, grid, entry.getValue());
            grid.getMatrix().put(position, cell);
        }
//        System.out.println(grid.getMatrix());
        Point p = new Point(0, 0);
    }

    protected void initializeStatistics() {
        this.statistics = new EnumMap<>(CellStates.GameOfLifeStates.class);
        statistics.put(CellStates.GameOfLifeStates.LIVE, 0);
        statistics.put(CellStates.GameOfLifeStates.DEAD, 0);
    }
    protected void initializeView() {
        this.view = new HashMap<Point, CellStates.GameOfLifeStates>();

    }
    /**
     * call this method at every time-step to update and evolve the model
     */
    public void step() {
        for (Cell cell: grid.getMatrix().values()) {
            cell.calculateNextState();
        }

        for (Cell cell: grid.getMatrix().values()) {
            cell.updateState();
        }
    }


    public void render() {
        int numDead = 0;
        int numLive = 0;
        view.clear();
        for (Map.Entry<Point, Cell> entry: grid.getMatrix().entrySet()) {
            GameOfLifeCell cell = (GameOfLifeCell) entry.getValue();
            if (cell.currentState == CellStates.GameOfLifeStates.LIVE) {
                numLive++;
            }
            else if (cell.currentState == CellStates.GameOfLifeStates.DEAD) {
                numDead++;
            }
            if (entry.getValue().stateChanged) {
                view.put(entry.getKey(), entry.getValue().currentState);
            }
        }
        statistics.put(CellStates.GameOfLifeStates.LIVE, numLive);
        statistics.put(CellStates.GameOfLifeStates.DEAD, numDead);
    }



    @Override
    public String toString() {
        return "Game Of Life";
    }
}
