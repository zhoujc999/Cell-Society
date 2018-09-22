package Model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FireSimulation extends Simulation{
    //    No wrap arounds for Game of Life
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;
    private double probCatchFire;
    private Random random;

    private Map<CellStates.FireStates, Integer> statistics;

    public FireSimulation(int numRows, int numColumns, Map<Point, CellStates.FireStates> initialState, double proCatchFire) {
        super(numRows, numColumns, initialState);
        this.probCatchFire = proCatchFire;
        this.random = new Random();
    }


    protected void initializeGrid() {
        this.grid = new FireGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }


    /**
     * initialize Cells and put them on grid
     */
    protected void initializeCells(Map<Point, ? extends Enum> initialParam) {
        for (Map.Entry entry : initialParam.entrySet()) {
            Point position = (Point) entry.getKey();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            FireCell cell = new FireCell(position, (FireGrid) grid, (CellStates.FireStates) entry.getValue(), probCatchFire, random);
            grid.getMatrix().put(position, cell);
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

    }



    @Override
    public String toString() {
        return "Fire Simulation";
    }

}
