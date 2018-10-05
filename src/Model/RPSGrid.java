package Model;

/**
 * Abstract representation of a grid for Rock Paper Scissors simulation.
 * Subclass of Grid.
 * @author jz192
 */


public class RPSGrid extends Grid {
    protected RPSGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap, Directions.NoOfNeighbors gridConfig) {
        super(numRows, numColumns, rowWrap, columnWrap, gridConfig);
    }
}
