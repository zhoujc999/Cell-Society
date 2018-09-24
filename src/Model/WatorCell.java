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

    public void resetTurn() {
        turn = 0;
    }

    public void initializeNeighbors() {
        fishNeighborPositions.clear();
        emptyNeighborPositions.clear();
        if (currentState != CellStates.WatorStates.EMPTY) {
            Point neighborPosition;
            for (Directions.FourDirections direction : Directions.FourDirections.values()) {
                neighborPosition = position.add(direction.getDirection());
                if (!grid.outOfBounds(neighborPosition)) {
                    if (grid.getCell(neighborPosition).getNextState() == CellStates.WatorStates.EMPTY) {
                        emptyNeighborPositions.add(neighborPosition);
                    }
                    else if (grid.getCell(neighborPosition).getNextState() == CellStates.WatorStates.FISH) {
                        fishNeighborPositions.add(neighborPosition);
                    }
                }
            }
        }
    }

    @Override
    public void calculateNextState() {
        WatorGrid g = (WatorGrid) this.grid;
        switch ((CellStates.WatorStates) currentState) {
            case SHARK:
                if (fishNeighborPositions.size() != 0) {
                    eat(g);
                }
                else {
                    move(g);
                }
                reproduceShark(g);
                turn++;
                break;

            case FISH:
                move(g);
                reproduceFish(g);
                turn++;
                break;

            case EMPTY:

                break;
        }
    }


    private void eat(WatorGrid g) {
        Point victimPosition = (Point) fishNeighborPositions.remove(random.nextInt(fishNeighborPositions.size()));
        g.changeNeighborState(victimPosition, CellStates.WatorStates.EMPTY);
    }

    private void move(WatorGrid g) {
        Point emptyPosition = (Point) emptyNeighborPositions.remove(random.nextInt(emptyNeighborPositions.size()));
        g.swapPositions(position, emptyPosition);
    }

    private void reproduceShark(WatorGrid g) {
        if (turn == sharkTurnsToBreed && emptyNeighborPositions.size() != 0) {
            Point emptyPosition = (Point) emptyNeighborPositions.remove(random.nextInt(emptyNeighborPositions.size()));
            g.changeNeighborState(emptyPosition, CellStates.WatorStates.SHARK);
        }
    }

    private void reproduceFish(WatorGrid g) {
        if (turn == fishTurnsToBreed && emptyNeighborPositions.size() != 0) {
            Point emptyPosition = (Point) emptyNeighborPositions.remove(random.nextInt(emptyNeighborPositions.size()));
            g.changeNeighborState(emptyPosition, CellStates.WatorStates.FISH);
        }
    }

}
