package Model;

public class SegregationCell extends Cell {

    private double satisfactionThreshold;
    private int numRedNeighbors;
    private int numBlueNeighbors;
    private boolean satisfied;

    public SegregationCell(Point position, SegregationGrid grid, CellStates.SegregationStates state, double satisfactionThreshold) {
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
        if (currentState != CellStates.SegregationStates.EMPTY) {
            Point neighborPosition;
            for (Directions.EightDirections direction : Directions.EightDirections.values()) {
                neighborPosition = position.add(direction.getDirection());
                if (!grid.outOfBounds(neighborPosition)) {
                    SegregationCell neighbor = (SegregationCell) grid.getCell(neighborPosition);
                    if (neighbor.currentState == CellStates.SegregationStates.RED) {
                        numRedNeighbors++;
                    }
                    else if (neighbor.currentState == CellStates.SegregationStates.BLUE) {
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
            SegregationGrid g = (SegregationGrid) this.grid;
            g.swapPositions(position);
        }
    }


    private void determineSatisfied() {
        double satisfaction;
        if (numRedNeighbors + numBlueNeighbors == 0 || currentState == CellStates.SegregationStates.EMPTY) {
            satisfied = true;
        }
        else if (currentState == CellStates.SegregationStates.RED) {
            satisfaction = (double) numRedNeighbors / (numBlueNeighbors + numRedNeighbors);
            satisfied = satisfaction >= satisfactionThreshold;
        }
        else if (currentState == CellStates.SegregationStates.BLUE) {
            satisfaction = (double) numBlueNeighbors / (numBlueNeighbors + numRedNeighbors);
            satisfied = satisfaction >= satisfactionThreshold;
        }
    }

}
