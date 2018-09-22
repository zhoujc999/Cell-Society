package Model;

import java.util.Map;
import java.util.Random;

public class FireSimulation extends Simulation {
    //    No wrap arounds for Game of Life
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;
    private FireGrid grid;
    private double probCatchFire;
    private Random random;

    private Map<CellStates.FireStates, Integer> statistics;

    public FireSimulation(int numRows, int numColumns, Map<Point, CellStates.FireStates> initialState, double proCatchFire) {
        if (initialState.size() != numRows * numColumns) {
            throw new IllegalArgumentException("InitialState Number of Points Error");
        }
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numCells = numRows * numColumns;
        this.probCatchFire = proCatchFire;
        this.random = new Random();
        initializeGrid();
        initializeCells(initialState);
//        initializeAllNeighbors();
    }


    protected void initializeGrid() {
        this.grid = new FireGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }


    /**
     * initialize Cells and put them on grid
     */
    protected void initializeCells(Map<Point, CellStates.FireStates> initialParam) {
        for (Map.Entry<Point, CellStates.FireStates> entry : initialParam.entrySet()) {
            Point position = entry.getKey();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            FireCell cell = new FireCell(position, grid, entry.getValue(), probCatchFire, random);
            grid.getMatrix().put(position, cell);
        }
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
