package Model;

/**
 * Abstract representation of a grid for Fire Simulation.
 * A subclass of Grid.
 * @author jz192
 */



public class FireGrid extends Grid {

    protected FireGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap, Directions.NoOfNeighbors gridConfig) {
        super(numRows, numColumns, rowWrap, columnWrap, gridConfig);
    }
}