package Model;


public class GameOfLifeCell extends Cell {
    public GameOfLifeCell(Point position, Grid grid, CellStates.GameOfLifeStates state) {
        super (position, grid, state);

    }


    public void initializeNeighbors() {
        neighbors.clear();
        Point neighborPosition;
        for (Directions.EightDirections direction : Directions.EightDirections.values()) {
            neighborPosition = position.add(direction.getDirection());
            if (!grid.outOfBounds(neighborPosition)) {
                neighbors.add(grid.getCell(neighborPosition));
            }
        }
    }


    @Override
    public void calculateNextState() {
        int numOfLiveNeighbors = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.getCurrentState() == CellStates.GameOfLifeStates.LIVE) {
                numOfLiveNeighbors++;
            }
        }

        switch ((CellStates.GameOfLifeStates) currentState) {
            case LIVE:
                if (numOfLiveNeighbors < 2 || numOfLiveNeighbors > 3) {
                    nextState = CellStates.GameOfLifeStates.DEAD;
                }
                break;
            case DEAD:
                if (numOfLiveNeighbors == 3) {
                    nextState = CellStates.GameOfLifeStates.LIVE;
                }
                break;
        }

    }
}
