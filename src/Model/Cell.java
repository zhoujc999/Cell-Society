package Model;


import java.util.Random;


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
    protected Directions.NoOfNeighbors gridConfig;
    protected Enum currentState;
    protected Enum nextState;
    protected Random random;

    /**
     * Constructor for Model
     * @param position
     * @param state
     */
    protected Cell(Point position, Grid grid, Enum state, Directions.NoOfNeighbors gridConfig) {
        this.position = position;
        this.grid = grid;
        this.gridConfig = gridConfig;
        this.currentState = state;
        this.nextState = state;
        this.random = new Random();
    }


    /**
     *
     * @return the cell's neighbours
     */



    protected Point getPosition() {
        return this.position;
    }

    protected void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @param state sets the state of the cell
     */
    protected void setCurrentState(Enum state) {
        this.currentState = state;
    }

    protected Enum getCurrentState() {
        return this.currentState;
    }


    protected void setNextState(Enum state) {
        this.nextState = state;
    }

    protected Enum getNextState() {
        return this.nextState;
    }
    /**
     * calculate and update nextState variable
     */
    protected abstract void calculateNextState();

    /**
     * update currentState variable
     */
    protected abstract void updateState();


    @Override
    public String toString() {
        return "Cell" +  " @ " + position;
    }

}
