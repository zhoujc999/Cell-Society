package Model;//package Model;

public class WatorGrid extends Grid {

    public WatorGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        super(numRows, numColumns, rowWrap, columnWrap);
    }

    public void changeNeighborState(Point position, CellStates.WatorStates outcome) {
        WatorCell passiveCell = (WatorCell) getCell(position);
        passiveCell.setNextState(outcome);
        passiveCell.initializeNeighborsNeighbors();
    }

    public void swapPositions(Point current, Point destination) {
        WatorCell activeCell = (WatorCell) getCell(current);
        WatorCell passiveCell = (WatorCell) getCell(destination);
        activeCell.setNextState(passiveCell.getCurrentState());
        passiveCell.setNextState(activeCell.getCurrentState());
        activeCell.initializeNeighborsNeighbors();
        passiveCell.initializeNeighborsNeighbors();
    }

}
