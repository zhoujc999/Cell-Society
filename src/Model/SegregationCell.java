package Model;//package Model;


import java.util.ArrayList;

/**
 * Abstract representation of a Segregation Cell.
 * Subclass of Cell.
 * @author jz192
 */


public class SegregationCell extends Cell {

    private double satisfactionThreshold;
    private boolean satisfied;

    protected SegregationCell(Point position, SegregationGrid grid, CellStates.SegregationStates state, Directions.NoOfNeighbors gridConfig, double satisfactionThreshold) {
        super(position, grid, state, gridConfig);
        this.satisfactionThreshold = satisfactionThreshold;
        this.satisfied = true;
    }


    protected void setSatisfactionThreshold(double satisfactionThreshold) {
        this.satisfactionThreshold = satisfactionThreshold;
    }

    protected double getSatisfactionThreshold() {
        return satisfactionThreshold;
    }

    protected void setSatisfed(boolean satisfied) {
        this.satisfied = satisfied;
    }

    protected boolean isSatisfied() {
        return satisfied;
    }




    @Override
    protected void calculateNextState() {
        if (getCurrentState() != CellStates.SegregationStates.EMPTY) {
            int numRedNeighbors = 0;
            int numBlueNeighbors = 0;
            Point neighborPosition;
            ArrayList<Point> neighborConfig = Directions.getShape(gridConfig);
            for (Point direction : neighborConfig) {
                neighborPosition = getPosition().add(direction);
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
