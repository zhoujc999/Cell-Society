package Model;//package Model;//package Model;

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
        int fishTurnsToBreed = ((WatorCell) getCell(position)).getFishTurnsToBreed();
        int sharkTurnsToBreed = ((WatorCell) getCell(position)).getSharkTurnsToBreed();
        setCell(position, new WatorCell(position, this, outcome, fishTurnsToBreed, sharkTurnsToBreed));
        ((WatorCell) getCell(position)).initializeNeighbors();
        ((WatorCell) getCell(position)).initializeNeighborsNeighbors();
    }

    public void swapPositions(Point current, Point destination) {
        super.swapPositions(current, destination);
        ((WatorCell) getCell(current)).initializeNeighbors();
        ((WatorCell) getCell(current)).initializeNeighborsNeighbors();
        ((WatorCell) getCell(destination)).initializeNeighbors();
        ((WatorCell) getCell(destination)).initializeNeighborsNeighbors();
    }

}
