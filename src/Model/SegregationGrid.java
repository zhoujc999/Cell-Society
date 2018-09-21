package Model;

import java.util.ArrayList;

public class SegregationGrid extends Grid {
    private ArrayList<Point> emptyPositions;

    public SegregationGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        super(numRows, numColumns, rowWrap, columnWrap);
        emptyPositions = new ArrayList<>();
    }

    public ArrayList<Point> getEmptyPositions() {
        return emptyPositions;
    }

    public void swapPositions(Point currentPosition) {
        if (emptyPositions.size() > 0) {
            SegregationCell activeCell = (SegregationCell) matrix.get(currentPosition);
            SegregationCell passiveCell = (SegregationCell) matrix.get(emptyPositions.remove(0));
            activeCell.setNextState(CellStates.SegregrationStates.EMPTY);
            passiveCell.setNextState(activeCell.getCurrentState());

        }
    }
}