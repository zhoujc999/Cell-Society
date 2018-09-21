package Model;

import java.util.ArrayList;

public class SegregationGrid extends Grid {
    private ArrayList<Point> emptyPositions;

    public SegregationGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        super(numRows, numColumns, rowWrap, columnWrap);
        emptyPositions = new ArrayList<>();
    }


}