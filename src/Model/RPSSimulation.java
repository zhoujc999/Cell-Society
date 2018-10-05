package Model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract representation of a Rock Paper Scissors Simulation
 * Subclass of Simulation
 * @author jz192
 */

public class RPSSimulation extends Simulation {
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;

    private int maxHit;

    /**
     * GameOfLife simulation creates an instance of this simulation.
     * @param initialState a map of points to cell states
     * @param noOfSides shape of cell
     * @param numColumns number of columns for the grid
     * @param numRows number of rows for the grid
     * @param maxHit maximum number of hits before a cell dies
     * Subclass of Simulation.
     */
    public RPSSimulation(int numRows, int numColumns, Map<Point, Integer> initialState, int noOfSides, int maxHit) {
        super(numRows, numColumns, initialState, noOfSides);
        initializeAllNeighbors();
        this.maxHit = maxHit;
        initializeMaxHit();
        render();
    }


    protected void initializeGrid() {
        this.grid = new RPSGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP, gridConfig);
    }

    protected void initializeCells(Map<Point, Integer> initialParam) {
        for (Map.Entry entry : initialParam.entrySet()) {
            Point position = (Point) entry.getKey();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            RPSCell cell = new RPSCell(position, (RPSGrid) grid, CellStates.RPSStates.fromInt((int) entry.getValue()), gridConfig, maxHit);
            super.grid.getMatrix().put(position, cell);
        }
    }

    protected void initializeAllNeighbors() {
        for (Cell cell: grid.getMatrix().values()) {
            ((RPSCell) cell).initializeNeighbors();
        }
    }

    protected void initializeMaxHit() {
        for (Cell cell : this.grid.getMatrix().values()) {
            ((RPSCell) cell).setMaxHit(this.maxHit);
        }
    }

    protected void initializeStatistics() {
        this.statistics = new EnumMap<CellStates.RPSStates, Integer>(CellStates.RPSStates.class);
        statistics.put(CellStates.RPSStates.BLUE, 0);
        statistics.put(CellStates.RPSStates.GREEN, 0);
        statistics.put(CellStates.RPSStates.RED, 0);
        statistics.put(CellStates.RPSStates.WHITE, 0);
    }

    protected void initializeView() {
        this.view = new HashMap<Point, CellStates.RPSStates>();
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
        int numBlue = 0;
        int numGreen = 0;
        int numRed = 0;
        int numWhite = 0;
        view.clear();
        for (Map.Entry<Point, ? extends Cell> entry: grid.getMatrix().entrySet()) {
            RPSCell cell = (RPSCell) entry.getValue();
            if (cell.currentState == CellStates.RPSStates.BLUE) {
                numBlue++;
            }
            else if (cell.currentState == CellStates.RPSStates.GREEN) {
                numGreen++;
            }
            else if (cell.currentState == CellStates.RPSStates.RED) {
                numRed++;
            }

            else if (cell.currentState == CellStates.RPSStates.WHITE) {
                numWhite++;
            }

            view.put(entry.getKey(), entry.getValue().currentState.ordinal());
        }
        statistics.put(CellStates.RPSStates.BLUE, numBlue);
        statistics.put(CellStates.RPSStates.GREEN, numGreen);
        statistics.put(CellStates.RPSStates.RED, numRed);
        statistics.put(CellStates.RPSStates.WHITE, numWhite);
    }


    @Override
    public String toString() {
        return "Rock_Paper_Scissors Simulation";
    }


}
