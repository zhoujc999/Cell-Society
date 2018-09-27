package Model;
//
//import java.util.ArrayList;
//
//public class RPSCell extends Cell {
//    private ArrayList<RPSCell> neighbors;
//    private int maxHit;
//    private int currentHitCount;
//    private int nextHitCount;
//
//    public RPSCell(Point position, RPSGrid grid, CellStates.RPSStates state, int maxHit) {
//        super(position, grid, state);
//        this.maxHit = maxHit;
//        this.currentHitCount = 0;
//        this.nextHitCount = 0;
//        this.neighbors = new ArrayList<>();
//    }
//
//
////    protected void setCurrentTurnLife(int currentHitCount) {
////        this.currentHitCount = currentHitCount;
////    }
////
//    protected void gotHit() {
//        if (currentHitCount < maxHit) {
//            currentHitCount++;
//        }
//    }
////
////    protected boolean isDead() {
////        return currentHitCount == 0;
////    }
////
////    protected int getMaxLife() {
////        return maxHit;
////    }
//
//    protected void setMaxHit(int maxHit) {
//        this.maxHit = maxHit;
//    }
//
//    @Override
//    public void calculateNextState() {
//        neighbors.clear();
//        Point neighborPosition;
//        for (Directions.EightDirections direction : Directions.EightDirections.values()) {
//            neighborPosition = getPosition().add(direction.getDirection());
//            if (!grid.outOfBounds(neighborPosition)) {
//                neighbors.add((RPSCell) grid.getCell(neighborPosition));
//            }
//        }
//
//        RPSCell neighbor = neighbors.get(random.nextInt(neighbors.size()));
//        switch ((CellStates.RPSStates) currentState) {
//            case RED:
//                if (neighbor.getCurrentState() == CellStates.RPSStates.BLUE) {
//                    attack(neighbor);
//                }
//                break;
//            case GREEN:
//
//
//                break;
//            case BLUE:
//
//
//                break;
//        }
//    }
//
//    @Override
//    protected void updateState() {
//
//    }
//
//    private void attack(RPSCell cell) {
//        cell.gotHit();
//    }
//}
