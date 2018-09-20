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
        EMPTY,
        RED_SATISFIED,
        BLUE_SATISFIED,
        RED_DISSATISFIED,
        BLUE_DISSATISFIED;

    }


}