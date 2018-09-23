//package Model;
//
//public class WatorCell extends Cell {
//    private int numTurnsToBreed;
//    private int turn;
//    public WatorCell(Point position, WatorGrid grid, CellStates.WatorStates state, int numTurnsToBreed) {
//        super(position, grid, state);
//        this.numTurnsToBreed = numTurnsToBreed;
//    }
//
//    public void setNumTurnsToBreed(int numTurnsToBreed) {
//        this.numTurnsToBreed = numTurnsToBreed;
//    }
//
//    public void initializeNeighbors() {
//        neighbors.clear();
//        if (currentState != CellStates.WatorStates.EMPTY) {
//            Point neighborPosition;
//            for (Directions.FourDirections direction : Directions.FourDirections.values()) {
//                neighborPosition = position.add(direction.getDirection());
//                if (!grid.outOfBounds(neighborPosition)) {
//                    neighbors.add(grid.getCell(neighborPosition));
//                }
//            }
//        }
//    }
//
//    @Override
//    public void calculateNextState() {
//        switch ((CellStates.WatorStates) currentState) {
//            case SHARK:
//
//                for (Cell neighbor : neighbors) {
//                    if (neighbor.currentState == CellStates.WatorStates.FISH) {
//                        fishNearby = true;
//                    }
//                }
//                if (fishNearby)
//
//
//                break;
//            case FISH:
//
//
//                break;
//
//            case EMPTY:
//
//
//                break;
//        }
//    }
//
//}
