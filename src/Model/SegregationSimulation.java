package Model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class SegregationSimulation extends Simulation {
    //    No wrap arounds for Game of Life
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;
    private SegregationGrid grid;
    private double satisfactionThreshold;
    private int numEmptyCells;

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
        initializeStatistics();
        initializeView();
        initializeGrid();
        initializeCells(initialState);
//        initializeAllNeighbors();
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

    protected void initializeStatistics() {
        this.statistics = new EnumMap<CellStates.SegregrationMood, Integer>(CellStates.SegregrationMood.class);
        statistics.put(CellStates.SegregrationMood.SATISFIED, 0);
        statistics.put(CellStates.SegregrationMood.DISSATISFIED, 0);
    }
    protected void initializeView() {
        this.view = new HashMap<Point, CellStates.SegregrationStates>();
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
        int numSatisfied = 0;
        int numDissatisfied = 0;
        view.clear();
        for (Map.Entry<Point, Cell> entry: grid.getMatrix().entrySet()) {
            SegregationCell cell = (SegregationCell) entry.getValue();
            if (cell.isSatisfied()) {
                numSatisfied++;
            }
            else {
                numDissatisfied++;
            }
            if (entry.getValue().stateChanged) {
                view.put(entry.getKey(), entry.getValue().currentState);
            }
        }
        statistics.put(CellStates.SegregrationMood.SATISFIED, numSatisfied);
        statistics.put(CellStates.SegregrationMood.DISSATISFIED, numDissatisfied);
    }


    public String toString() {
        return "Segregation Simulation";
    }
}
