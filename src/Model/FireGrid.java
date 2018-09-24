package Model;

/**
 * Abstract representation of a grid for Fire Simulation.
 *
 * @author jz192
 */



public class FireGrid extends Grid {

    public FireGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        super(numRows, numColumns, rowWrap, columnWrap);
    }
}