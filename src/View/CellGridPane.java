package View;

import Controller.Controller_API;
import Model.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;

/*
    @author xp19 This class represents the gridpane that displays cell simulation
 */

public class CellGridPane {

    private static final double MAX_GRID_WIDTH = 400.0;
    private static final double MAX_GRID_HEIGHT = 400.0;
    private GridPane gridPane;
    private int numRows;
    private int numCols;
    private Rectangle[] rects;


    public CellGridPane(GridPane gridPane){
        this.gridPane = gridPane;
    }

    public void create(Map<String, String> attributes, Simulation initialSimulation){
        numRows = Integer.parseInt(attributes.get(Controller_API.NUM_ROW_ATTR));
        numCols = Integer.parseInt(attributes.get(Controller_API.NUM_COL_ATTR));
        initialize(numRows, numCols, initialSimulation);
    }

    private void initialize(int numRows, int numCols, Simulation simulation){
        gridPane.getChildren().clear();
        rects = new Rectangle[numRows*numCols];
        for(int i = 0; i < rects.length; i++){
            rects[i] = new Rectangle(MAX_GRID_WIDTH/numCols,MAX_GRID_HEIGHT/numRows);
        }

        Map<Point, CellStates.GameOfLifeStates> myMap = simulation.getView();

        int index = 0;
        for(Point p: myMap.keySet()){
            if(myMap.get(p) == CellStates.GameOfLifeStates.LIVE){
                rects[index].setFill(Color.BLACK);
            }
            else{
                rects[index].setFill(Color.WHITE);
            }
            gridPane.add(rects[index++], p.getY(), p.getX());
        }
    }

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
}
