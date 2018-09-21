package Model;

import java.util.Map;

public class SegregationSimulation extends Simulation {
    //    No wrap arounds for Game of Life
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;
    private SegregationGrid grid;
    private double satisfactionThreshold;
    private int numEmptyCells;

    private Map<CellStates.SegregrationStates, Integer> statistics;

    public SegregationSimulation(int numRows, int numColumns, Map<Point, CellStates.SegregrationStates> initialState, double threshold) {
        if (initialState.size() != numRows * numColumns) {
            throw new IllegalArgumentException("InitialState Number of Points Error");
        }
        if (threshold > 1) {
            throw new IllegalArgumentException("Threshold Error");
        }
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numCells = numRows * numColumns;
        this.satisfactionThreshold = threshold;
        this.numEmptyCells = 0;
        initializeGrid();
        initializeCells(initialState);
        initializeAllNeighbors();
    }




    protected void initializeGrid() {
        this.grid = new SegregationGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }

    /**
     * initialize Cells and put them on grid
     */
    protected void initializeCells(Map<Point, CellStates.SegregrationStates> initialParam) {
        for (Map.Entry<Point, CellStates.SegregrationStates> entry : initialParam.entrySet()) {
            Point position = entry.getKey();
            CellStates.SegregrationStates state = entry.getValue();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            if (state == CellStates.SegregrationStates.EMPTY) {
                grid.getEmptyPositions();
                numEmptyCells++;
            }
            SegregationCell cell = new SegregationCell(position, grid, state, satisfactionThreshold);
            grid.getMatrix().put(position, cell);
        }
    }

    public void step() {
        grid.setSwapQuota(numEmptyCells);
        for (Cell cell: grid.getMatrix().values()) {
            cell.calculateNextState();
        }
        for (Cell cell: grid.getMatrix().values()) {
            cell.updateState();
            if (cell.stateChanged) {
                cell.initializeNeighbors();
                cell.initializeNeighborsNeighbors();
            }
        }
    }
    public void render() {
        
    }


    public String toString() {
        return "Segregation Simulation";
    }
}
