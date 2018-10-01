package Model;

/**
 * Abstract representation of a grid for Fire Simulation.
 *
 * @author jz192
 */



public class FireGrid extends Grid {

    public FireGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap, Directions.NoOfNeighbors gridConfig) {
        super(numRows, numColumns, rowWrap, columnWrap, gridConfig);
    }
}