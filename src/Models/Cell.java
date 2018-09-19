package Models;

import javafx.geometry.Point2D;
import java.util.ArrayList;


/**
 * Abstract representation of a cell. Includes the minimum methods a cell has to implement.
 *
 * @author jz192
 */

public abstract class Cell {
    private Point2D position;
    private ArrayList<Cell> neighbors;
    private Enum state;

    /**
     * Constructor for Models
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
