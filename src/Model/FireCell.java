package Model;//package Model;

/**
 * Abstract representation of a Fire Cell.
 *
 * @author jz192
 */



public class FireCell extends Cell {
    private double probCatchFire;
    public FireCell(Point position, FireGrid grid, CellStates.FireStates state) {
        super(position, grid, state);

    }

    public void setProbCatchFire(double probCatchFire) {
        this.probCatchFire = probCatchFire;
    }

    @Override
    protected void calculateNextState() {
        switch ((CellStates.FireStates) currentState) {
            case BURNING:
                nextState = CellStates.FireStates.EMPTY;
                break;
            case EMPTY:
                nextState = CellStates.FireStates.EMPTY;
                break;
            case TREE:
                nextState = catchFire();
        }
    }


    @Override
    protected void updateState() {
        if (nextState != currentState) {
            currentState = nextState;
        }
    }

    private CellStates.FireStates catchFire() {
        boolean burningNeighbors = false;
        for (Directions.FourDirections direction : Directions.FourDirections.values()) {
            Point neighborPosition = getPosition().add(direction.getDirection());
            if (!grid.outOfBounds(neighborPosition) && grid.getCell(neighborPosition).getCurrentState() == CellStates.FireStates.BURNING) {
                burningNeighbors = true;
            }
        }
        if (burningNeighbors && random.nextDouble() < probCatchFire) {
            return CellStates.FireStates.BURNING;
        }
        else {
            return CellStates.FireStates.TREE;
        }
    }
}


