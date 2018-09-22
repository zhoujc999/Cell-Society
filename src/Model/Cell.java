package Model;


import java.util.HashSet;


/**
 * Abstract representation of a Cell. Includes the minimum methods a Cell has to implement.
 * position is where the cell is located in the grid.
 * neighbors are the adjacent (as defined in problem) Cells
 * currentState and nextState are wrapped in enum classes
 * @author jz192
 */

public abstract class Cell {
    protected Point position;
    protected HashSet<Cell> neighbors;
    protected Enum currentState;
    protected Enum nextState;
    protected boolean stateChanged;

    /**
     * Constructor for Model
     * @param position
     * @param state
     */
    protected Cell(Point position, Enum state) {
        this.position = position;
        this.neighbors = new HashSet<>();
        this.currentState = state;
        this.nextState = state;
        this.stateChanged = true;
    }


    /**
     *
     * @return the cell's neighbours
     */
    public HashSet<Cell> getNeighbors() {
        return this.neighbors;
    }

    public void setNeighbors(HashSet<Cell> neighbors) {
        this.neighbors = neighbors;
    }

    public abstract void initializeNeighbors();

    public void initializeNeighborsNeighbors() {
        for (Cell neighbor : neighbors) {
            neighbor.initializeNeighbors();
        }
    }

    public void clearNeighors() {
        this.neighbors.clear();
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @param state sets the state of the cell
     */
    public void setCurrentState(Enum state) {
        this.currentState = state;
    }

    public Enum getCurrentState() {
        return this.currentState;
    }


    public void setNextState(Enum state) {
        this.nextState = state;
    }

    public Enum getNextState() {
        return this.nextState;
    }
    /**
     * calculate and update nextState variable
     */
    public abstract void calculateNextState();

    /**
     * update currentState variable
     */
    public void updateState() {
        if (nextState != currentState) {
            currentState = nextState;
            stateChanged = true;
        } else {
            stateChanged = false;
        }

    }

    @Override
    public String toString() {
        return "Cell" + position;
    }
}
