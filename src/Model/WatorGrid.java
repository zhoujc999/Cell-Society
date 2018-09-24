package Model;//package Model;

public class WatorGrid extends Grid {

    public WatorGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        super(numRows, numColumns, rowWrap, columnWrap);
    }

    public void changeNeighborState(Point position, CellStates.WatorStates outcome) {
        WatorCell passiveCell = (WatorCell) matrix.get(position);
        passiveCell.setNextState(outcome);
        passiveCell.initializeNeighborsNeighbors();
    }

    public void swapPositions(Point current, Point destination) {
        SegregationCell activeCell = (SegregationCell) matrix.get(current);
        SegregationCell passiveCell = (SegregationCell) matrix.get(destination);
        activeCell.setNextState(passiveCell.getCurrentState());
        passiveCell.setNextState(activeCell.getCurrentState());
        activeCell.initializeNeighborsNeighbors();
        passiveCell.initializeNeighborsNeighbors();
    }

}
