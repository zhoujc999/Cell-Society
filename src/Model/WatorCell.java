package Model;//package Model;

import java.util.ArrayList;

/**
 * Abstract representation of a Wa-Tor Cell.
 *
 * @author jz192
 */



public class WatorCell extends Cell {

    private ArrayList<Point> fishNeighborPositions;
    private ArrayList<Point> emptyNeighborPositions;
    private int fishTurnsToBreed;
    private int sharkTurnsToBreed;
    private int turn;

    public WatorCell(Point position, WatorGrid grid, CellStates.WatorStates state, Directions.NoOfNeighbors gridConfig, int fishTurnsToBreed, int sharkTurnsToBreed) {
        super(position, grid, state, gridConfig);
        this.fishNeighborPositions = new ArrayList<>();
        this.emptyNeighborPositions = new ArrayList<>();
        this.fishTurnsToBreed = fishTurnsToBreed;
        this.sharkTurnsToBreed = sharkTurnsToBreed;
        this.turn = 0;
    }

    protected int getFishTurnsToBreed() {
        return fishTurnsToBreed;
    }

    protected int getSharkTurnsToBreed() {
        return sharkTurnsToBreed;
    }

    protected void setFishTurnsToBreed(int fishTurnsToBreed) {
        this.fishTurnsToBreed = fishTurnsToBreed;
    }

    protected void setSharkTurnsToBreed(int fishTurnsToBreed) {
        this.sharkTurnsToBreed = fishTurnsToBreed;
    }

    protected void resetTurn() {
        turn = 0;
    }

    protected void initializeNeighbors() {
        fishNeighborPositions.clear();
        emptyNeighborPositions.clear();
        Point neighborPosition;
        ArrayList<Point> neighborConfig = Directions.getshape(gridConfig);
        for (Point direction : neighborConfig) {
            neighborPosition = getPosition().add(direction);
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

    protected void initializeNeighborsNeighbors() {
        Point neighborPosition;
        ArrayList<Point> neighborConfig = Directions.getshape(gridConfig);
        for (Point direction : neighborConfig) {
            neighborPosition = getPosition().add(direction);
            if (!grid.outOfBounds(neighborPosition)) {
                ((WatorCell) grid.getCell(neighborPosition)).initializeNeighbors();
            }
        }
    }

    @Override
    public void calculateNextState() {
        WatorGrid g = (WatorGrid) this.grid;
        switch ((CellStates.WatorStates) currentState) {
            case SHARK:
                if (!fishNeighborPositions.isEmpty()) {
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
        }
    }

    @Override
    protected void updateState() {

    }

    private void eat(WatorGrid g) {
        Point victimPosition = fishNeighborPositions.get(random.nextInt(fishNeighborPositions.size()));
        g.changeNeighborState(victimPosition, CellStates.WatorStates.EMPTY);
    }

    private void move(WatorGrid g) {
        if (!emptyNeighborPositions.isEmpty()) {
            Point emptyPosition = emptyNeighborPositions.get(random.nextInt(emptyNeighborPositions.size()));
            g.swapPositions(position, emptyPosition);
        }
    }

    private void reproduceShark(WatorGrid g) {
        if (turn == sharkTurnsToBreed && !emptyNeighborPositions.isEmpty()) {
            Point emptyPosition = emptyNeighborPositions.get(random.nextInt(emptyNeighborPositions.size()));
            g.changeNeighborState(emptyPosition, CellStates.WatorStates.SHARK);
            resetTurn();
        }
    }

    private void reproduceFish(WatorGrid g) {
        if (turn == fishTurnsToBreed && !emptyNeighborPositions.isEmpty()) {
            Point emptyPosition = emptyNeighborPositions.get(random.nextInt(emptyNeighborPositions.size()));
            g.changeNeighborState(emptyPosition, CellStates.WatorStates.FISH);
            resetTurn();
        }
    }

}
