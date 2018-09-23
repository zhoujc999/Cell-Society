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


    public GameOfLifeSimulation(int numRows, int numColumns, Map<Point, Integer> initialState) {
        super(numRows, numColumns, initialState);
    }




    protected void initializeGrid() {
        this.grid = new GameOfLifeGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }

    /**
     * initialize Cells and put them on grid
     */
    protected void initializeCells(Map<Point, Integer> initialParam) {
        for (Map.Entry entry : initialParam.entrySet()) {
            Point position = (Point) entry.getKey();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState - Duplicate Points Error");
            }
            GameOfLifeCell cell = new GameOfLifeCell(position, (GameOfLifeGrid) grid, CellStates.GameOfLifeStates.fromInt((int) entry.getValue()));
            grid.getMatrix().put(position, cell);
        }
    }

    protected void initializeStatistics() {
        this.statistics = new EnumMap<CellStates.GameOfLifeStates, Integer>(CellStates.GameOfLifeStates.class);
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
//            if (cell.stateChanged) {
                cell.updateState();
//            }
        }
    }


    public void render() {
        int numDead = 0;
        int numLive = 0;
        view.clear();
        for (Map.Entry<Point, ? extends Cell> entry: grid.getMatrix().entrySet()) {
            GameOfLifeCell cell = (GameOfLifeCell) entry.getValue();
//            System.out.println(cell.neighbors);
            if (cell.currentState == CellStates.GameOfLifeStates.LIVE) {
                numLive++;
            }
            else if (cell.currentState == CellStates.GameOfLifeStates.DEAD) {
                numDead++;
            }
//            if (entry.getValue().stateChanged) {
                view.put(entry.getKey(), entry.getValue().currentState.ordinal());
//            }
        }
        statistics.put(CellStates.GameOfLifeStates.LIVE, numLive);
        statistics.put(CellStates.GameOfLifeStates.DEAD, numDead);
    }



    @Override
    public String toString() {
        return "Game Of Life";
    }
}
