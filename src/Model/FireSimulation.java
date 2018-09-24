package Model;

/**
 * Fire Simulation instantiates the grid and cells from the input map. It returns the state of the simulation through <code> render()</code> and <code>getView()</code>
 *
 *
 * @author jz192
 */


import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class FireSimulation extends Simulation {
    //    No wrap arounds for Game of Life
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;
    private double probCatchFire;


    public FireSimulation(int numRows, int numColumns, Map<Point, Integer> initialState, double proCatchFire) {

        super(numRows, numColumns, initialState);
        this.probCatchFire = proCatchFire;
        initializeCellsProbability();
    }


    protected void initializeGrid() {
        this.grid = new FireGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }


    /**
     * initialize Cells and put them on grid
     */
    protected void initializeCells(Map<Point, Integer> initialParam) {
        for (Map.Entry entry : initialParam.entrySet()) {
            Point position = (Point) entry.getKey();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            FireCell cell = new FireCell(position, (FireGrid) grid, CellStates.FireStates.fromInt((int) entry.getValue()));
            super.grid.getMatrix().put(position, cell);
        }
    }

    protected void initializeCellsProbability() {
        for (Cell cell : this.grid.getMatrix().values()) {
            FireCell c = (FireCell) cell;
            c.setProbCatchFire(this.probCatchFire);
        }
    }



    protected void initializeStatistics() {
        this.statistics = new EnumMap<CellStates.FireStates, Integer>(CellStates.FireStates.class);
        statistics.put(CellStates.FireStates.TREE, 0);
        statistics.put(CellStates.FireStates.BURNING, 0);
        statistics.put(CellStates.FireStates.EMPTY, 0);

    }
    protected void initializeView() {
        this.view = new HashMap<Point, CellStates.FireStates>();

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
        int numTree = 0;
        int numBurning = 0;
        int numEmpty = 0;
        view.clear();
        for (Map.Entry<Point, ? extends Cell> entry: grid.getMatrix().entrySet()) {
            FireCell cell = (FireCell) entry.getValue();
            if (cell.currentState == CellStates.FireStates.TREE) {
                numTree++;
            }
            else if (cell.currentState == CellStates.FireStates.BURNING) {
                numBurning++;
            }
            else if (cell.currentState == CellStates.FireStates.EMPTY) {
                numEmpty++;
            }

            view.put(entry.getKey(), entry.getValue().currentState.ordinal());
        }
        statistics.put(CellStates.FireStates.TREE, numTree);
        statistics.put(CellStates.FireStates.BURNING, numBurning);
        statistics.put(CellStates.FireStates.EMPTY, numEmpty);
    }



    @Override
    public String toString() {
        return "Fire Simulation";
    }

}
