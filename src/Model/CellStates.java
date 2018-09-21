package Model;

/**
 * Abstract representation of a cell. Includes the minimum methods a cell has to implement.
 *
 * @author jz192
 */

public class CellStates {
    public enum GameOfLifeStates {
        LIVE,
        DEAD;
    }

    public enum SegregrationStates {
        RED,
        BLUE,
        EMPTY;
    }

    public enum SegregrationMood {
        SATISFIED,
        DISSATISFIED;
    }

    public enum FireStates {
        TREE,
        BURNING,
        EMPTY;
    }

}