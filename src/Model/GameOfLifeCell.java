package Model;

import java.util.ArrayList;

/**
 * Abstract representation of a GameOfLife Cell.
 *
 * @author jz192
 */


public class GameOfLifeCell extends Cell {

    public GameOfLifeCell(Point position, GameOfLifeGrid grid, CellStates.GameOfLifeStates state, Directions.NoOfNeighbors gridConfig) {
        super(position, grid, state, gridConfig);
    }


    /**
     * calculates and sets the next state of the cell
     */
    @Override
    protected void calculateNextState() {
        int numOfLiveNeighbors = 0;
        Point neighborPosition;
        ArrayList<Point> neighborConfig = Directions.getshape(gridConfig);
        for (Point direction : neighborConfig) {
            neighborPosition = getPosition().add(direction);
            if (!grid.outOfBounds(neighborPosition)) {
                if (grid.getCell(neighborPosition).getCurrentState() == CellStates.GameOfLifeStates.LIVE) {
                    numOfLiveNeighbors++;
                }
            }
        }

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

}
