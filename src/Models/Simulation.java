package Models;

import java.util.HashMap;
import java.util.Map;

public abstract class Simulation {
    protected Grid grid;
    protected int numRows;
    protected int numColumns;



    protected abstract void initializeGrid();


    protected void step() {
        for (Cell cell: grid.getMatrix().values()) {
            cell.calculateNextState();
        }

        for (Cell cell: grid.getMatrix().values()) {
            cell.updateState();
        }
    }

    public Map<Enum, Integer> getNumCellsForEachCurrentState() {
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



    public abstract String toString();
}
