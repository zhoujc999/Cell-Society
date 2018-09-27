package View;

import Controller.Controller_API;
import Model.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;

/*
    @author xp19 This class represents the gridpane that displays cell simulation
 */

public class RectCellGridPane implements CellGridPane{

//    private static final double MAX_GRID_WIDTH = 400.0;
//    private static final double MAX_GRID_HEIGHT = 400.0;
    private GridPane gridPane;
//    private int numRows;
//    private int numCols;
    private Rectangle[] rects;
    private StatsGraph statsGraph;

    public RectCellGridPane(GridPane pane, StatsGraph statsGraph){
        this.gridPane = pane;
        this.statsGraph = statsGraph;
    }

    @Override
    public void create(Map<String, String> attributes, Simulation initialSimulation){
        int numRows = Integer.parseInt(attributes.get(Controller_API.NUM_ROW_ATTR));
        int numCols = Integer.parseInt(attributes.get(Controller_API.NUM_COL_ATTR));
        initialize(numRows, numCols, initialSimulation);
    }

    @Override
    public void initialize(int numRows, int numCols, Simulation simulation){
        gridPane.getChildren().clear();
        rects = new Rectangle[numRows*numCols];
        for(int i = 0; i < rects.length; i++){
            rects[i] = new Rectangle(MAX_GRID_WIDTH/numCols,MAX_GRID_HEIGHT/numRows);
        }

        Map<Point, CellStates> myMap = simulation.getView();

        int index = 0;
        for(Point p: myMap.keySet()){
            gridPane.add(rects[index++], p.getY(), p.getX());
        }

    }

    @Override
    public void render(Map<Point, Integer> updatedMap){
        int index = 0;
        for(Point p: updatedMap.keySet()){
            if(updatedMap.get(p) == 0){
                rects[index].setFill(Color.BLUE);
            }
            else if(updatedMap.get(p) == 1){
                rects[index].setFill(Color.PINK);
            }
            else
            {
                rects[index].setFill(Color.WHITE);
            }
            index++;
        }
    }

    @Override
    public void updateStatsGraph(Map<CellStates, Integer> map){
        System.out.println(map);
        statsGraph.update(map);
    }
}
