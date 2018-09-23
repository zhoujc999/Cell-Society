package Model;

public class SegregationCell extends Cell {

    private double satisfactionThreshold;
    private int numRedNeighbors;
    private int numBlueNeighbors;
    private boolean satisfied;

    public SegregationCell(Point position, SegregationGrid grid, CellStates.SegregrationStates state, double satisfactionThreshold) {
        super(position, grid,state);
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
        determineSatisfied();
        if (!satisfied) {
            this.grid.swapPositions(position);
        }
    }


    private void determineSatisfied() {
        double satisfaction;
        if (numRedNeighbors + numBlueNeighbors == 0 || currentState == CellStates.SegregrationStates.EMPTY) {
            satisfied = true;
        }
        else if (currentState == CellStates.SegregrationStates.RED) {
            satisfaction = (double) numRedNeighbors / (numBlueNeighbors + numRedNeighbors);
            satisfied = satisfaction >= satisfactionThreshold;
        }
        else if (currentState == CellStates.SegregrationStates.BLUE) {
            satisfaction = (double) numBlueNeighbors / (numBlueNeighbors + numRedNeighbors);
            satisfied = satisfaction >= satisfactionThreshold;
        }
    }

}
