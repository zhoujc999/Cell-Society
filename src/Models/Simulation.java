package Models;
import java.util.HashMap;
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




    protected abstract void initializeGrid();

    protected void initializeNeighbors(){
        for (Cell cell: grid.getMatrix().values()) {
            cell.initializeNeighbors();
        }
    }

    /**
     * call this method at every time-step to update and evolve the model
     */
    protected void step() {
        for (Cell cell: grid.getMatrix().values()) {
            cell.calculateNextState();
        }

        for (Cell cell: grid.getMatrix().values()) {
            cell.updateState();
        }
    }

    /**
     * call this method to get the number of Cells in each state
     * @return map mapping enum states to ints
     */
    public Map getNumCellsForEachCurrentState() {
        Map<Enum, Integer> stateToNumCells = new HashMap<>();
        for (Cell cell: grid.getMatrix().values()) {
            if (stateToNumCells.containsKey(cell.getCurrentState())) {
                stateToNumCells.put(cell.getCurrentState(), stateToNumCells.get(cell.getCurrentState()) + 1);
            } else {
                stateToNumCells.put(cell.getCurrentState(), 1);
            }
        }
        return stateToNumCells;
    }

    /**
     * call this method to get view of current cell states
     * @return map mapping enum states to ints
     */
    public Map renderView() {
        Map view = new HashMap();
        for (Map.Entry<Point, Cell> entry: grid.getMatrix().entrySet()) {
            if (entry.getValue().stateChanged) {
                view.put(entry.getKey(), entry.getValue().currentState);
            }
        }
        return view;
    }

    public abstract String toString();
}
