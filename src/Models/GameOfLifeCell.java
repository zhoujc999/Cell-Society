package Models;

import javafx.geometry.Point2D;

import java.util.HashSet;

public class GameOfLifeCell extends Cell {
    public GameOfLifeCell(Point position, Grid grid, GameOfLifeStates state) {
        this.position = position;
        this.grid = grid;
        neighbors = new HashSet<>();
        this.currentState = state;
        this.nextState = state;
    }

    public void buildNeighbors() {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (!grid.outOfBounds(i, j) && grid.getCell(i, j) != this) {
                    neighbors.add(grid.getCell(i, j));
                }
            }
        }
    }




    @Override
    public void calculateNextState() {
        int numOfLiveNeighbors = 0;
        for (Cell neighbor : neighbors) {
            if (neighbor.getCurrentState() == GameOfLifeStates.LIVE) {
                numOfLiveNeighbors++;
            }
        }
        switch ((GameOfLifeStates) currentState) {
            case LIVE:
                if (numOfLiveNeighbors < 2 || numOfLiveNeighbors > 3) {
                    nextState = GameOfLifeStates.DEAD;
                }

                break;
            case DEAD:
                if (numOfLiveNeighbors == 3) {
                    nextState = GameOfLifeStates.LIVE;
                }
        }

    }
}
