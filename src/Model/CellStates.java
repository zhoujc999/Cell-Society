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

    public enum SegregationStates {
        RED,
        BLUE,
        EMPTY;
    }

    public enum SegregationMoods {
        SATISFIED,
        DISSATISFIED;
    }

    public enum FireStates {
        TREE,
        BURNING,
        EMPTY;
    }

    public enum WatorStates {
        SHARK,
        FISH,
        EMPTY;
    }

}