package Model;


public class GameOfLifeCell extends Cell {
    private GameOfLifeGrid grid;

    public GameOfLifeCell(Point position, GameOfLifeGrid grid, CellStates.GameOfLifeStates state) {
        super(position, state);
        this.grid = grid;
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

    public void setNextState(Enum state) {
        this.nextState = state;
    }

    public Enum getNextState() {
        return this.nextState;
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
