package Model;

import java.util.ArrayList;

public class WatorCell extends Cell {
    private ArrayList fishNeighborPositions;
    private ArrayList emptyNeighborPositions;
    private int fishTurnsToBreed;
    private int sharkTurnsToBreed;
    private int turn;
    public WatorCell(Point position, WatorGrid grid, CellStates.WatorStates state, int fishTurnsToBreed, int sharkTurnsToBreed) {
        super(position, grid, state);
        this.fishNeighborPositions = new ArrayList<Point>();
        this.emptyNeighborPositions = new ArrayList<Point>();
        this.fishTurnsToBreed = fishTurnsToBreed;
        this.sharkTurnsToBreed = sharkTurnsToBreed;
        this.turn = 0;
    }

    public void setFishTurnsToBreed(int fishTurnsToBreed) {
        this.fishTurnsToBreed = fishTurnsToBreed;
    }
    public void setSharkTurnsToBreed(int fishTurnsToBreed) {
        this.sharkTurnsToBreed = fishTurnsToBreed;
    }

    public void initializeNeighbors() {
        fishNeighborPositions.clear();
        emptyNeighborPositions.clear();
        if (currentState != CellStates.WatorStates.EMPTY) {
            Point neighborPosition;
            for (Directions.FourDirections direction : Directions.FourDirections.values()) {
                neighborPosition = position.add(direction.getDirection());
                if (!grid.outOfBounds(neighborPosition)) {
                    if (grid.getCell(neighborPosition).getCurrentState() == CellStates.WatorStates.EMPTY) {
                        emptyNeighborPositions.add(neighborPosition);
                    }
                    else if (grid.getCell(neighborPosition).getCurrentState() == CellStates.WatorStates.FISH) {
                        fishNeighborPositions.add(neighborPosition);
                    }
                }
            }
        }
    }

    @Override
    public void calculateNextState() {
        switch ((CellStates.WatorStates) currentState) {
            case SHARK:
                if (fishNeighborPositions.size() != 0) {
                    eat();
                }
                else {
                    move();
                }
                reproduce();
                break;

            case FISH:
                move();
                reproduce();
                break;

            case EMPTY:

                break;
        }
    }


    private void eat() {
        Point victimPosition = (Point) fishNeighborPositions.remove(random.nextInt(fishNeighborPositions.size()));
        grid.changeNeighborState(victimPosition, CellStates.WatorStates.EMPTY);
    }

    private void move() {
        Point emptyPosition = (Point) fishNeighborPositions.remove(random.nextInt(fishNeighborPositions.size()));
        grid.swapPositions(position, emptyPosition);

    }

    private void reproduce() {

    }

}
