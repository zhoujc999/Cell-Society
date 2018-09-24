package Model;//package Model;

/**
 * Abstract representation of the grid for Wa-Tor.
 *
 * @author jz192
 */



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
        super.swapPositions(current, destination);
        getCell(current).initializeNeighbors();
        ((WatorCell) getCell(current)).initializeNeighborsNeighbors();
        getCell(destination).initializeNeighbors();
        ((WatorCell) getCell(destination)).initializeNeighborsNeighbors();
    }

}
