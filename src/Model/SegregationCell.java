package Model;//package Model;


/**
 * Abstract representation of a Segregation Cell.
 *
 * @author jz192
 */


public class SegregationCell extends Cell {

    private double satisfactionThreshold;
    private boolean satisfied;
    private int numRedNeighbors;
    private int numBlueNeighbors;

    public SegregationCell(Point position, SegregationGrid grid, CellStates.SegregationStates state, double satisfactionThreshold) {
        super(position, grid,state);
        this.satisfactionThreshold = satisfactionThreshold;
        this.satisfied = true;
        this.numRedNeighbors = 0;
        this.numBlueNeighbors = 0;
    }


    public void setSatisfactionThreshold(double satisfactionThreshold) {
        this.satisfactionThreshold = satisfactionThreshold;
    }

    public double getSatisfactionThreshold() {
        return satisfactionThreshold;
    }

    public void setSatisfed(boolean satisfied) {
        this.satisfied = satisfied;
    }

    public boolean isSatisfied() {
        return satisfied;
    }




    @Override
    public void calculateNextState() {
        if (getCurrentState() != CellStates.SegregationStates.EMPTY) {
            numRedNeighbors = 0;
            numBlueNeighbors = 0;
            Point neighborPosition;
            for (Directions.EightDirections direction : Directions.EightDirections.values()) {
                neighborPosition = getPosition().add(direction.getDirection());
                countNeighborType(neighborPosition);
            }
            determineSatisfied(numRedNeighbors, numBlueNeighbors);
        }
    }

    private void countNeighborType(Point p) {
        if (!grid.outOfBounds(p)) {
            SegregationCell neighbor = (SegregationCell) grid.getCell(p);
            if (neighbor.currentState == CellStates.SegregationStates.RED) {
                numRedNeighbors++;
            }
            else if (neighbor.currentState == CellStates.SegregationStates.BLUE) {
                numBlueNeighbors++;
            }
        }
    }

    @Override
    protected void updateState() {
        if (!satisfied) {
            ((SegregationGrid) this.grid).swapPositions(position);
        }
    }

    private void determineSatisfied(int numRed, int numBlue) {
        double satisfaction;
        setSatisfed(true);
        if (numRed + numBlue > 0) {
            if (currentState == CellStates.SegregationStates.RED) {
                satisfaction = (double) numRed / (numBlue + numRed);
                setSatisfed(satisfaction >= satisfactionThreshold);
            }
            else if (currentState == CellStates.SegregationStates.BLUE) {
                satisfaction = (double) numBlue / (numBlue + numRed);
                setSatisfed(satisfaction >= satisfactionThreshold);
            }
        }
    }

}
