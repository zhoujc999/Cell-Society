package Model;

import java.util.ArrayList;

public class RPSCell extends Cell {
    private ArrayList<RPSCell> neighbors;
    private int maxHit;
    private int currentHitCount;
    private int nextHitCount;
    private boolean firstHit;

    public RPSCell(Point position, RPSGrid grid, CellStates.RPSStates state, int maxHit) {
        super(position, grid, state);
        this.maxHit = maxHit;
        this.currentHitCount = 0;
        this.nextHitCount = 0;
        this.neighbors = new ArrayList<>();
    }

    protected void initializeNeighbors() {
        neighbors.clear();
        Point neighborPosition;
        for (Directions.EightDirections direction : Directions.EightDirections.values()) {
            neighborPosition = getPosition().add(direction.getDirection());
            if (!grid.outOfBounds(neighborPosition)) {
                neighbors.add((RPSCell) grid.getCell(neighborPosition));
            }
        }
    }

    protected void gotHit() {
        if (firstHit && currentHitCount < maxHit) {
            nextHitCount++;
        }
        firstHit = false;
    }


    protected boolean isDead() {
        return currentHitCount == maxHit;
    }

    protected int getCurrentHitCount() {
        return currentHitCount;
    }

    protected void setNextHitCount(int count) {
        this.nextHitCount = count;
    }
//
//    protected int getMaxLife() {
//        return maxHit;
//    }

    protected void setMaxHit(int maxHit) {
        this.maxHit = maxHit;
    }

    @Override
    public void calculateNextState() {
        RPSCell neighbor = neighbors.get(random.nextInt(neighbors.size()));
        switch ((CellStates.RPSStates) currentState) {
            case RED:
                if (neighbor.getCurrentState() == CellStates.RPSStates.BLUE) {
                    attack(neighbor);
                }
                else if (neighbor.getCurrentState() == CellStates.RPSStates.WHITE) {
                    stain(neighbor);
                }
                break;
            case GREEN:
                if (neighbor.getCurrentState() == CellStates.RPSStates.RED) {
                    attack(neighbor);
                }
                else if (neighbor.getCurrentState() == CellStates.RPSStates.WHITE) {
                    stain(neighbor);
                }
                break;
            case BLUE:
                if (neighbor.getCurrentState() == CellStates.RPSStates.GREEN) {
                    attack(neighbor);
                }
                else if (neighbor.getCurrentState() == CellStates.RPSStates.WHITE) {
                    stain(neighbor);
                }
                break;
        }
    }

    @Override
    protected void updateState() {
        currentState = nextState;
        currentHitCount = nextHitCount;
        firstHit = true;




    }

    private void attack(RPSCell cell) {
        cell.gotHit();
        if (cell.isDead()) {
            cell.setNextState(getCurrentState());
            cell.setNextHitCount(getCurrentHitCount());
            nextHitCount--;
        }
    }

    private void stain(RPSCell cell) {
        cell.setNextState(getCurrentState());
        cell.setNextHitCount(getCurrentHitCount() + 1);
    }

}
