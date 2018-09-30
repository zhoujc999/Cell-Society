package Model;//package Model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract representation of the Segregation Simulation
 *
 * @author jz192
 */



public class SegregationSimulation extends Simulation {
    //    No wrap arounds for Game of Life
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;

    private double satisfactionThreshold;
    private int numEmptyCells;

    public SegregationSimulation(int numRows, int numColumns, Map<Point, Integer> initialState, int noOfSides, double threshold) {
        super(numRows, numColumns, initialState, noOfSides);
        this.satisfactionThreshold = threshold;
        initializeCellsThreshold();
        render();
    }


    protected void initializeGrid() {
        this.grid = new SegregationGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }

    /**
     * initialize Cells and put them on grid
     */
    protected void initializeCells(Map<Point, Integer> initialParam) {
        numEmptyCells = 0;
        for (Map.Entry entry : initialParam.entrySet()) {
            Point position = (Point) entry.getKey();
            CellStates.SegregationStates state = CellStates.SegregationStates.fromInt((int) entry.getValue());
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            if (state == CellStates.SegregationStates.EMPTY) {
                ((SegregationGrid) this.grid).addEmptyPosition(position);
                numEmptyCells++;
            }
            SegregationCell cell = new SegregationCell(position, (SegregationGrid) grid, state, gridConfig, satisfactionThreshold);
            grid.getMatrix().put(position, cell);
        }
    }

    protected void initializeCellsThreshold() {
        for (Cell cell : this.grid.getMatrix().values()) {
            ((SegregationCell) cell).setSatisfactionThreshold(this.satisfactionThreshold);
        }
    }

    protected void initializeStatistics() {
        this.statistics = new EnumMap<CellStates.SegregationMoods, Integer>(CellStates.SegregationMoods.class);
        statistics.put(CellStates.SegregationMoods.SATISFIED, 0);
        statistics.put(CellStates.SegregationMoods.DISSATISFIED, 0);
    }
    protected void initializeView() {
        this.view = new HashMap<Point, CellStates.SegregationStates>();
    }

    public void step() {
        for (Cell cell: grid.getMatrix().values()) {
            cell.calculateNextState();
        }
        for (Cell cell: grid.getMatrix().values()) {
            cell.updateState();
        }
        render();
    }
    protected void render() {
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
            view.put(entry.getKey(), entry.getValue().currentState.ordinal());

        }
        statistics.put(CellStates.SegregationMoods.SATISFIED, numSatisfied - numEmptyCells);
        statistics.put(CellStates.SegregationMoods.DISSATISFIED, numDissatisfied);
    }


    public String toString() {
        return "Segregation Simulation";
    }
}
