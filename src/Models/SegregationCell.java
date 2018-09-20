package Models;

public class SegregationCell extends Cell{
    public SegregationCell(Point position, Grid grid, CellStates.SegregrationStates state) {
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
//        TODO


    }

}
