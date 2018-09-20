package Models;


import java.util.Collection;
import java.util.HashSet;


/**
 * Abstract representation of a cell. Includes the minimum methods a cell has to implement.
 *
 * @author jz192
 */

public abstract class Cell {
    protected Point position;
    protected Grid grid;
    protected HashSet<Cell> neighbors;
    protected Enum currentState;
    protected Enum nextState;

//    /**
//     * Constructor for Models
//     * @param position of the cell on the grid
//     */
    protected Cell(Point position, Grid grid, Enum state) {
        this.position = position;
        this.grid = grid;
        this.neighbors = new HashSet<>();
        this.currentState = state;
        this.nextState = state;
    }


    /**
     *
     * @return returns array of adjacent cells
     */
    public HashSet<Cell> getNeighbors() {
        return this.neighbors;
    }

    public void setNeighbors(HashSet<Cell> neighbors) {
        this.neighbors = neighbors;
    }

    public abstract void initializeNeighbors();

//    /**
//     *
//     * adds adjacent cells to neighbors
//     */
//    public abstract void addNeighbors();

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
     *
     * @param state sets the state of the cell (e.g. Burning, Tree, Empty)
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

    public abstract void calculateNextState();

    public void updateState() {
        currentState = nextState;
    }

}
