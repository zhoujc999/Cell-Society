package View;

import Model.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    @author xp19 This class represents the gridpane that displays cell simulation
 */

public class CellGridPane {

//    private static final int CELL_SIZE = 10;
//    private static final int GRID_WIDTH = 800;
    private static final double MAX_GRID_WIDTH = 400.0;
    private static final double MAX_GRID_HEIGHT = 400.0;
    private static final int MAX_FPS = 30;
    private GridPane gridPane;
    private int numRows;
    private int numCols;
    private Rectangle[] rects;


    public CellGridPane(GridPane gridPane){
        this.gridPane = gridPane;
    }

    public void create(Map<String, String> attributes, Simulation initialSimulation){
        numRows = Integer.parseInt(attributes.get("numRows"));
        numCols = Integer.parseInt(attributes.get("numColumns"));
        initialize(numRows, numCols, initialSimulation);
    }

    private void initialize(int numRows, int numCols, Simulation simulation){
        gridPane.getChildren().clear();
        rects = new Rectangle[numRows*numCols];
        for(int i = 0; i < rects.length; i++){
            rects[i] = new Rectangle(MAX_GRID_WIDTH/numCols,MAX_GRID_HEIGHT/numRows);
        }

        simulation.render();
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

        // add time line
    }

    public void render(Map<Point, CellStates.GameOfLifeStates> updatedMap){

        int index = 0;
//        System.out.println(updatedMap);
//        System.out.println(updatedMap.size());
        for(Point p: updatedMap.keySet()){
            if(updatedMap.get(p) == CellStates.GameOfLifeStates.LIVE){
                rects[index].setFill(Color.BLACK);
            }
            else{
                rects[index].setFill(Color.WHITE);
            }
            index++;
        }
    }
}
