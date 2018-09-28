package Model;//package Model;


/**
 * Abstract representation of a Segregation Cell.
 *
 * @author jz192
 */


public class SegregationCell extends Cell {

    private double satisfactionThreshold;
    private boolean satisfied;

    public SegregationCell(Point position, SegregationGrid grid, CellStates.SegregationStates state, double satisfactionThreshold) {
        super(position, grid,state);
        this.satisfactionThreshold = satisfactionThreshold;
        this.satisfied = true;
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
            int numRedNeighbors = 0;
            int numBlueNeighbors = 0;
            Point neighborPosition;
            for (Directions.EightDirections direction : Directions.EightDirections.values()) {
                neighborPosition = getPosition().add(direction.getDirection());
                if (!grid.outOfBounds(neighborPosition)) {
                    SegregationCell neighbor = (SegregationCell) grid.getCell(neighborPosition);
                    if (neighbor.currentState == CellStates.SegregationStates.RED) {
                        numRedNeighbors++;
                    }
                    else if (neighbor.currentState == CellStates.SegregationStates.BLUE) {
                        numBlueNeighbors++;
                    }
                }
            }
            determineSatisfied(numRedNeighbors, numBlueNeighbors);

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
