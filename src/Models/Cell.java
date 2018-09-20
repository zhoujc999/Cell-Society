package Models;


import java.util.Collection;
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
    protected Grid grid;
    protected HashSet<Cell> neighbors;
    protected Enum currentState;
    protected Enum nextState;

    /**
     * Constructor for Models
     * @param position
     * @param grid
     * @param state
     */
    protected Cell(Point position, Grid grid, Enum state) {
        this.position = position;
        this.grid = grid;
        this.neighbors = new HashSet<>();
        this.currentState = state;
        this.nextState = state;
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
        currentState = nextState;
    }

}
