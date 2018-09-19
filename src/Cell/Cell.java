package Cell;

import javafx.geometry.Point2D;
import java.util.ArrayList;

import java.lang.Object;


/**
 * Abstract representation of a cell. Includes the minimum methods a cell has to implement.
 *
 * @author jz192
 */

public abstract class Cell {
    Point2D position;
    ArrayList<Cell> neighbors;
    Enum state;

    /**
     * Constructor for Cell
     * @param position of the cell on the grid
     */
    public Cell(Point2D position, Enum state) {
        this.position = position;
        neighbors = new ArrayList<Cell>();
        this.state = state;
    }

    /**
     *
     * @return returns array of adjacent cells
     */
    public ArrayList getNeighbors() {
        return this.neighbors;
    }

    public void setNeighbors(ArrayList neighbors) {
        this.neighbors = neighbors;
    }

//    /**
//     *
//     * adds adjacent cells to neighbors
//     */
//    public abstract void addNeighbors();

    public void clearNeighors() {
        neighbors.clear();
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    /**
     *
     * @param state sets the state of the cell (e.g. Burning, Tree, Empty)
     */
    public void setState(Enum state) {
        this.state = state;
    }

    public Enum getState() {
        return state;
    }

    public abstract void updateState();


}
