package View;

import Model.CellStates;
import Model.Point;
import Model.Simulation;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Map;

public abstract class CellGridPane {

    double MAX_GRID_WIDTH = 400.0;
    double MAX_GRID_HEIGHT = 400.0;
    StatsGraph statsGraph;

    protected CellGridPane(StatsGraph statsGraph){
        this.statsGraph = statsGraph;
    }

    public abstract void create(Map<String, String> attributes, Simulation initialSimulation);

    abstract void initialize(int numRows, int numCols, Simulation simulation);

    public void render(Map<Point, Integer> updatedMap, Shape[][] grid){
        for(Point p: updatedMap.keySet()){
            if(updatedMap.get(p) == 0){
                grid[p.getY()][p.getX()].setFill(Color.BLUE);
            }
            else if(updatedMap.get(p) == 1){
                grid[p.getY()][p.getX()].setFill(Color.PINK);
            }
            else
            {
                grid[p.getY()][p.getX()].setFill(Color.WHITE);
            }
        }
    }

    public abstract void render(Map<Point, Integer> updatedMap);

    public void updateStatsGraph(Map<CellStates, Integer> map){
        statsGraph.update(map);
    }

}
