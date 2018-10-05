package Model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

/**
 * Abstract representation of a Simulation.
 * It receives a map of points to initial states and instantiates the collection of cells onto the grid.
 * Throws Exception when the number of points given in initialState mismatches the number of rows * number of columns.
 * @author jz192
 */

public abstract class Simulation {
    protected Grid grid;

    protected int numRows;
    protected int numColumns;
    protected int numCells;
    protected EnumMap statistics;
    protected Map view;
    protected Directions.NoOfNeighbors gridConfig;
    protected Random random;


    public Simulation(int numRows, int numColumns, Map<Point, Integer> initialState, int noOfSides) {
        if (initialState.size() != numRows * numColumns) {
            throw new IllegalArgumentException("InitialState - Number of Points Error");
        }
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numCells = numRows * numColumns;
        this.gridConfig = getNeighborConfig(noOfSides);
        this.random = new Random();

        initializeStatistics();
        initializeView();
        initializeGrid();
        initializeCells(initialState);
    }

    protected Directions.NoOfNeighbors getNeighborConfig(int noOfSides) {
        switch (noOfSides) {
            case 3:
                return Directions.NoOfNeighbors.TWELVE;
            case 4:
                return Directions.NoOfNeighbors.EIGHT;
            case 6:
                return Directions.NoOfNeighbors.SIX;
            default:
                return Directions.NoOfNeighbors.FOUR;
        }

    }

    protected abstract void initializeGrid();

    protected abstract void initializeCells(Map<Point, Integer> m);


    /**
     * Call this method at every time-step to update and evolve the model.
     * This method updates the view and the statistics of the simulations.
     */
    public abstract void step();


    protected abstract void render();


    protected abstract void initializeView();


    protected abstract void initializeStatistics();


    /**
     * Call this method to receive a a map of points to the current cell state.
     */
    public Map getView() {
        return view;
    }

    /**
     * Call this method to receive a a map of cell states to the number of cells in that state.
     */
    public Map getStatistics() {
        return statistics;
    }


    public abstract String toString();
}
