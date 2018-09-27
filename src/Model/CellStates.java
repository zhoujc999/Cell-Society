package Model;

/**
 * Abstract representation of a cell state.
 *
 * @author jz192
 */

public class CellStates {

    public enum GameOfLifeStates {
        LIVE,
        DEAD;
        private static GameOfLifeStates[] cachedValues = null;
        public static GameOfLifeStates fromInt(int i) {
            if(GameOfLifeStates.cachedValues == null) {
                GameOfLifeStates.cachedValues = GameOfLifeStates.values();
            }
            return GameOfLifeStates.cachedValues[i];
        }
    }

    public enum SegregationStates {
        RED,
        BLUE,
        EMPTY;
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
        TREE,
        BURNING,
        EMPTY;
        private static FireStates [] cachedValues = null;
        public static FireStates fromInt(int i) {
            if(FireStates.cachedValues == null) {
                FireStates.cachedValues = FireStates.values();
            }
            return FireStates.cachedValues[i];
        }
    }

    public enum WatorStates {
        FISH,
        SHARK,
        EMPTY;
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