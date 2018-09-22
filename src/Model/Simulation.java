package Model;

import java.util.EnumMap;
import java.util.Map;

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


    public Simulation(int numRows, int numColumns, Map initialState) {
        if (initialState.size() != numRows * numColumns) {
            throw new IllegalArgumentException("InitialState - Number of Points Error");
        }
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numCells = numRows * numColumns;

        initializeStatistics();
        initializeView();
        initializeGrid();
        initializeCells(initialState);
        initializeAllNeighbors();
    }

    protected abstract void initializeGrid();

    protected abstract void initializeCells(Map<Point, ? extends Enum> m);

    protected void initializeAllNeighbors(){
        for (Cell cell: grid.getMatrix().values()) {
            cell.initializeNeighbors();
        }
    }

    /**
     * call this method at every time-step to update and evolve the model
     */
    public abstract void step();


    public abstract void render();


    protected abstract void initializeView();


    protected abstract void initializeStatistics();


    public Map getView() {
        return view;
    }

    public Map getStatistics() {
        return statistics;
    }

//    /**
//     * call this method to get the number of Cells in each state
//     * @return map mapping enum states to ints
//     */
//    public Map getNumCellsForEachCurrentState() {
//        Map<Enum, Integer> stateToNumCells = new HashMap<>();
//        for (Cell cell: grid.getMatrix().values()) {
//            if (stateToNumCells.containsKey(cell.getCurrentState())) {
//                stateToNumCells.put(cell.getCurrentState(), stateToNumCells.get(cell.getCurrentState()) + 1);
//            } else {
//                stateToNumCells.put(cell.getCurrentState(), 1);
//            }
//        }
//        return stateToNumCells;
//    }
//
//    /**
//     * call this method to get view of current cell states
//     * @return map mapping enum states to ints
//     */
//    public Map renderView() {
//        Map view = new HashMap();
//        for (Map.Entry<Point, Cell> entry: grid.getMatrix().entrySet()) {
//            if (entry.getValue().stateChanged) {
//                view.put(entry.getKey(), entry.getValue().currentState);
//            }
//        }
//        return view;
//    }

    public abstract String toString();
}
