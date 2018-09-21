package Model;

public class SegregationCell extends Cell{

    private double satisfactionThreshold;
    private int numRedNeighbors;
    private int numBlueNeighbors;
    private boolean satisfied;

    public SegregationCell(Point position, Grid grid, CellStates.SegregrationStates state, double satisfactionThreshold) {
        super(position, grid, state);
        this.satisfactionThreshold = satisfactionThreshold;
    }


    public void setSatisfactionThreshold(double satisfactionThreshold) {
        this.satisfactionThreshold = satisfactionThreshold;
    }

    public double getSatisfactionThreshold() {
        return satisfactionThreshold;
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
            double satisfactionLevel = calculateSatisfactionLevel();
            Point neighborPosition;
            for (Directions.EightDirections direction : Directions.EightDirections.values()) {
                neighborPosition = position.add(direction.getDirection());
                if (!grid.outOfBounds(neighborPosition)) {
                    neighbors.add(grid.getCell(neighborPosition));
                }
            }
        }
    }

    private double calculateSatisfactionLevel() {
        if (currentState == CellStates.SegregrationStates.RED) {
            return numRedNeighbors / (numBlueNeighbors + numRedNeighbors);
        }
        if (currentState == CellStates.SegregrationStates.BLUE) {
            return numBlueNeighbors / (numBlueNeighbors + numRedNeighbors);
        }
        return 1;
    }




}
