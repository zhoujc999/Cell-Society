package Model;

/**
 * Abstract representation of a grid for Game of Life simulation.
 * Subclass of Grid.
 * @author jz192
 */

public class GameOfLifeGrid extends Grid {

    protected GameOfLifeGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap, Directions.NoOfNeighbors gridConfig) {
        super(numRows, numColumns, rowWrap, columnWrap, gridConfig);
    }
}

