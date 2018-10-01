package Model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

/**
 * Abstract representation of a Simulation
 *
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
     * call this method at every time-step to update and evolve the model
     */
    public abstract void step();


    protected abstract void render();


    protected abstract void initializeView();


    protected abstract void initializeStatistics();


    public Map getView() {
        return view;
    }

    public Map getStatistics() {
        return statistics;
    }


    public abstract String toString();
}
