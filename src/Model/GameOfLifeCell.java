package Model;

/**
 * Abstract representation of a GameOfLife Cell.
 *
 * @author jz192
 */


public class GameOfLifeCell extends Cell {

    public GameOfLifeCell(Point position, GameOfLifeGrid grid, CellStates.GameOfLifeStates state) {
        super(position, grid, state);
    }


    /**
     * calculates and sets the next state of the cell
     */
    @Override
    protected void calculateNextState() {
        int numOfLiveNeighbors = getnumOfLiveNeighbors();
        switch ((CellStates.GameOfLifeStates) currentState) {
            case LIVE:
                if (numOfLiveNeighbors < 2 || numOfLiveNeighbors > 3) {
                    setNextState(CellStates.GameOfLifeStates.DEAD);
                }
                break;
            case DEAD:
                if (numOfLiveNeighbors == 3) {
                    setNextState(CellStates.GameOfLifeStates.LIVE);
                }
                break;
        }
    }

    @Override
    protected void updateState() {
        if (nextState != currentState) {
            currentState = nextState;
        }

    }

    private int getnumOfLiveNeighbors() {
        int numOfLiveNeighbors = 0;
        Point neighborPosition;
        for (Directions.EightDirections direction : Directions.EightDirections.values()) {
            neighborPosition = getPosition().add(direction.getDirection());
            if (!grid.outOfBounds(neighborPosition) && grid.getCell(neighborPosition).getCurrentState() == CellStates.GameOfLifeStates.LIVE) {
                    numOfLiveNeighbors++;
                }
            }
        return numOfLiveNeighbors;
    }
}
