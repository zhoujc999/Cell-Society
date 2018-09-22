package Model;

public class SegregationCell extends Cell {
    private SegregationGrid grid;

    private double satisfactionThreshold;
    private int numRedNeighbors;
    private int numBlueNeighbors;
    private boolean satisfied;

    public SegregationCell(Point position, SegregationGrid grid, CellStates.SegregrationStates state, double satisfactionThreshold) {
        super(position, state);
        this.grid = grid;
        this.satisfactionThreshold = satisfactionThreshold;
        this.satisfied = true;
    }


    public void setSatisfactionThreshold(double satisfactionThreshold) {
        this.satisfactionThreshold = satisfactionThreshold;
    }

    public double getSatisfactionThreshold() {
        return satisfactionThreshold;
    }

    public void setSatisfed(boolean satisfied) {
        this.satisfied = satisfied;
    }

//    public void incrementNumRedNeighbors() {
//        if (currentState != CellStates.SegregrationStates.EMPTY && numRedNeighbors < 8) {
//            numRedNeighbors++;
//        }
//    }
//
//    public void decrementNumRedNeighbors() {
//        if (currentState != CellStates.SegregrationStates.EMPTY && numRedNeighbors > 0) {
//            numRedNeighbors--;
//        }
//    }
//
//    public void incrementNumBlueNeighbors() {
//        if (currentState != CellStates.SegregrationStates.EMPTY && numBlueNeighbors < 8) {
//            numBlueNeighbors++;
//        }
//    }
//
//    public void decrementNumBlueNeighbors() {
//        if (currentState != CellStates.SegregrationStates.EMPTY && numBlueNeighbors > 0) {
//            numBlueNeighbors--;
//        }
//    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public void initializeNeighbors() {
        neighbors.clear();
        numRedNeighbors = 0;
        numBlueNeighbors = 0;
        if (currentState != CellStates.SegregrationStates.EMPTY) {
            Point neighborPosition;
            for (Directions.EightDirections direction : Directions.EightDirections.values()) {
                neighborPosition = position.add(direction.getDirection());
                if (!grid.outOfBounds(neighborPosition)) {
                    SegregationCell neighbor = (SegregationCell) grid.getCell(neighborPosition);
                    if (neighbor.currentState == CellStates.SegregrationStates.RED) {
                        numRedNeighbors++;
                    }
                    else if (neighbor.currentState == CellStates.SegregrationStates.BLUE) {
                        numBlueNeighbors++;
                    }
                    neighbors.add(grid.getCell(neighborPosition));
                }
            }
        }
    }

    @Override
    public void calculateNextState() {
        if (currentState != CellStates.SegregrationStates.EMPTY) {
            determineSatisfied();
            if (!satisfied) {
                grid.swapPositions(position);
            }

        }
    }

    private void determineSatisfied() {
        if (currentState == CellStates.SegregrationStates.RED) {
            satisfied = (numRedNeighbors / (numBlueNeighbors + numRedNeighbors)) > satisfactionThreshold;
        }
        else if (currentState == CellStates.SegregrationStates.BLUE) {
            satisfied = (numBlueNeighbors / (numBlueNeighbors + numRedNeighbors)) > satisfactionThreshold;
        }
        else {
            satisfied = true;
        }
    }

}
