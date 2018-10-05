package Model;

/**
 * Abstract representation of cell states.
 * This class is contains the enums for all the cell states of the variants of cellular automaton.
 * It has dependency on Cell.
 * @author jz192
 */

public class CellStates {

    /**
     * Enum for the cell states for game of life.
     * Includes a method that sets the state with an integer
     *
     */
    public enum GameOfLifeStates {
        EMPTY,
        LIVE,
        DEAD;
        private static GameOfLifeStates[] cachedValues = null;

        /**
         * @param i
         * @return gameOfLifeStates
         *
         */
        public static GameOfLifeStates fromInt(int i) {
            if(GameOfLifeStates.cachedValues == null) {
                GameOfLifeStates.cachedValues = GameOfLifeStates.values();
            }
            return GameOfLifeStates.cachedValues[i];
        }
    }

    public enum SegregationStates {
        EMPTY,
        RED,
        BLUE;
        private static SegregationStates[] cachedValues = null;
        public static SegregationStates fromInt(int i) {
            if(SegregationStates.cachedValues == null) {
                SegregationStates.cachedValues = SegregationStates.values();
            }
            return SegregationStates.cachedValues[i];
        }
    }

    public enum SegregationMoods {
        SATISFIED,
        DISSATISFIED;

    }

    public enum FireStates {
        EMPTY,
        TREE,
        BURNING;
        private static FireStates [] cachedValues = null;
        public static FireStates fromInt(int i) {
            if(FireStates.cachedValues == null) {
                FireStates.cachedValues = FireStates.values();
            }
            return FireStates.cachedValues[i];
        }
    }

    public enum WatorStates {
        EMPTY,
        FISH,
        SHARK;
        private static WatorStates [] cachedValues = null;
        public static WatorStates fromInt(int i) {
            if(WatorStates.cachedValues == null) {
                WatorStates.cachedValues = WatorStates.values();
            }
            return WatorStates.cachedValues[i];
        }
    }


    public enum RPSStates {
        WHITE,
        RED,
        GREEN,
        BLUE;
        private static RPSStates [] cachedValues = null;
        public static RPSStates fromInt(int i) {
            if(RPSStates.cachedValues == null) {
                RPSStates.cachedValues = RPSStates.values();
            }
            return RPSStates.cachedValues[i];
        }
    }
}