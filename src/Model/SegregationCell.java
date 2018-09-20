package Model;

public class SegregationCell extends Cell{

    private double satisfactionThreshold;

    public SegregationCell(Point position, Grid grid, CellStates.SegregrationStates state, double satisfactionThreshold) {
        super (position, grid, state);
        this.satisfactionThreshold = satisfactionThreshold;
    }

    public void initializeNeighbors() {
        if (currentState != CellStates.SegregrationStates.EMPTY) {
            neighbors.clear();
            Point neighborPosition;
            for (Directions.EightDirections direction : Directions.EightDirections.values()) {
                neighborPosition = position.add(direction.getDirection());
                if (!grid.outOfBounds(neighborPosition)) {
                    neighbors.add(grid.getCell(neighborPosition));
                }
            }
        }
    }

    @Override
    public void calculateNextState() {



    }

}
