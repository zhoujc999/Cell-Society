package Model;

import java.util.ArrayList;

public class SegregationGrid extends Grid {
    private ArrayList<Point> emptyPositions;
    private int swapQuota;

    public SegregationGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        super(numRows, numColumns, rowWrap, columnWrap);
        emptyPositions = new ArrayList<>();
    }

    @Override
    protected void addEmptyPosition(Point p) {
        emptyPositions.add(p);
    }

    @Override
    protected void swapPositions(Point currentPosition) {
        if (emptyPositions.size() > 0 && swapQuota > 0) {
            SegregationCell activeCell = (SegregationCell) matrix.get(currentPosition);
            SegregationCell passiveCell = (SegregationCell) matrix.get(emptyPositions.remove(random.nextInt(emptyPositions.size())));
            activeCell.setNextState(CellStates.SegregationStates.EMPTY);
            passiveCell.setNextState(activeCell.getCurrentState());
            addEmptyPosition(currentPosition);
            swapQuota--;
        }
    }

    @Override
    protected void setSwapQuota(int quota) {
        swapQuota = quota;
    }
}