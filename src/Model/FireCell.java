package Model;

import java.util.Random;

public class FireCell extends Cell {
    private double probCatchFire;
    private Random random;
    public FireCell(Point position, FireGrid grid, CellStates.FireStates state, double probCatchFire, Random random) {
        super(position, grid, state);
        this.probCatchFire = probCatchFire;
        this.random = random;
    }

    public void initializeNeighbors() {
        neighbors.clear();
        Point neighborPosition;
        for (Directions.FourDirections direction : Directions.FourDirections.values()) {
            neighborPosition = position.add(direction.getDirection());
            if (!grid.outOfBounds(neighborPosition)) {
                neighbors.add(grid.getCell(neighborPosition));
            }
        }
    }


    @Override
    public void calculateNextState() {
        switch ((CellStates.FireStates) currentState) {
            case BURNING:
                nextState = CellStates.FireStates.EMPTY;
                break;
            case EMPTY:
                nextState = CellStates.FireStates.EMPTY;
                break;
            case TREE:
                boolean burningNeighbors = false;
                for (Cell neighbor : neighbors) {
                    if (neighbor.getCurrentState() == CellStates.FireStates.BURNING) {
                        burningNeighbors = true;
                    }
                }
                if (burningNeighbors && random.nextDouble() < probCatchFire) {
                    nextState = CellStates.FireStates.BURNING;
                }
                else {
                    nextState = CellStates.FireStates.TREE;
                }

        }

    }
}
