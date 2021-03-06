package Model;//package Model;//package Model;

/**
 * Abstract representation of the grid for Wa-Tor.
 * Subclass of Grid.
 * @author jz192
 */



public class WatorGrid extends Grid {

    protected WatorGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap, Directions.NoOfNeighbors gridConfig) {
        super(numRows, numColumns, rowWrap, columnWrap, gridConfig);
    }

    protected void changeNeighborState(Point position, CellStates.WatorStates outcome) {
        int fishTurnsToBreed = ((WatorCell) getCell(position)).getFishTurnsToBreed();
        int sharkTurnsToBreed = ((WatorCell) getCell(position)).getSharkTurnsToBreed();
        setCell(position, new WatorCell(position, this, outcome, gridConfig, fishTurnsToBreed, sharkTurnsToBreed));
        ((WatorCell) getCell(position)).initializeNeighbors();
        ((WatorCell) getCell(position)).initializeNeighborsNeighbors();
    }

    protected void swapPositions(Point current, Point destination) {
        super.swapPositions(current, destination);
        ((WatorCell) getCell(current)).initializeNeighbors();
        ((WatorCell) getCell(current)).initializeNeighborsNeighbors();
        ((WatorCell) getCell(destination)).initializeNeighbors();
        ((WatorCell) getCell(destination)).initializeNeighborsNeighbors();
    }

}
