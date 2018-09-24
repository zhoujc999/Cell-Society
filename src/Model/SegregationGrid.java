package Model;

import java.util.ArrayList;

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
        if (emptyPositions.size() > 0) {
            Point destination = emptyPositions.remove(random.nextInt(emptyPositions.size()));
            super.swapPositions(current, destination);
            addEmptyPosition(current);
            getCell(current).initializeNeighbors();
            ((SegregationCell) getCell(current)).initializeNeighborsNeighbors();
            getCell(destination).initializeNeighbors();
            ((SegregationCell) getCell(destination)).initializeNeighborsNeighbors();
        }
    }

}