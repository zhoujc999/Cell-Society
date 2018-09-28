package Model;//package Model;

import java.util.ArrayList;

/**
 * Abstract representation of a grid for segregation simulation.
 *
 * @author jz192
 */


public class SegregationGrid extends Grid {
    private ArrayList<Point> emptyPositions;

    public SegregationGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        super(numRows, numColumns, rowWrap, columnWrap);
        emptyPositions = new ArrayList<>();
    }


    protected void addEmptyPosition(Point p) {
        emptyPositions.add(p);
    }

    protected void swapPositions(Point current) {
        if (!emptyPositions.isEmpty()) {
            Point destination = emptyPositions.remove(random.nextInt(emptyPositions.size()));
            super.swapPositions(current, destination);
            addEmptyPosition(current);
        }
    }

}