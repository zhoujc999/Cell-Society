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

    protected void swapPositions(Point currentPosition) {
        if (emptyPositions.size() > 0) {
            SegregationCell activeCell = (SegregationCell) matrix.get(currentPosition);
            SegregationCell passiveCell = (SegregationCell) matrix.get(emptyPositions.remove(random.nextInt(emptyPositions.size())));
            activeCell.setNextState(CellStates.SegregationStates.EMPTY);
            passiveCell.setNextState(activeCell.getCurrentState());
            addEmptyPosition(currentPosition);

        }
    }

}