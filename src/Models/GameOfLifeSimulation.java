package Models;

import java.util.HashSet;
import java.util.Map;

public class GameOfLifeSimulation extends Simulation {
    private static final boolean ROW_WRAP = false;
    private static final boolean COLUMN_WRAP = false;

    public GameOfLifeSimulation(int numRows, int numColumns, Map<Point, CellStates.GameOfLifeStates> initialState) {
        if (initialState.size() != numRows * numColumns) {
            throw new IllegalArgumentException("InitialState Number of Points Error");
        }
        this.numRows = numRows;
        this.numColumns = numColumns;
        initializeGrid();
        initializeCells(initialState);
    }

    protected void initializeGrid() {
        this.grid = new GameOfLifeGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }


    protected void initializeCells(Map<Point, CellStates.GameOfLifeStates> initialParam) {
        for (Map.Entry<Point, CellStates.GameOfLifeStates> entry : initialParam.entrySet()) {
            Point position = entry.getKey();
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            GameOfLifeCell cell = new GameOfLifeCell(position, grid, entry.getValue());
            grid.getMatrix().put(position, cell);
        }
    }



    @Override
    public String toString() {
        return "Game Of Life";
    }
}
