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

    private static final int CELL_SIZE = 10;
    private static final int MAX_FPS = 30;
    private GridPane gridPane;
    private int width;
    private int height;
    private Rectangle[] rects;

    public CellGridPane(GridPane gridPane){
        this.gridPane = gridPane;
    }


    public void create(Map<String, String> attributes, Simulation initialSimulation){
        width = Integer.parseInt(attributes.get("width"));
        height = Integer.parseInt(attributes.get("length"));
        initialize(width, height, initialSimulation);
    }

    private void initialize(int width, int height, Simulation simulation){

        rects = new Rectangle[width*height];
        for(int i = 0; i < rects.length; i++){
            rects[i] = new Rectangle(CELL_SIZE,CELL_SIZE);
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


//            if((rowIndex==2&&colIndex==2)||(rowIndex==2&&colIndex==3)||(rowIndex==3&&colIndex==4)||(rowIndex==1&&colIndex==1)||(rowIndex==3&&colIndex==3)){
//
//            if((rowIndex==2&&colIndex==2)){
//                map.put(new Point(rowIndex, colIndex), CellStates.FireStates.BURNING);
//            }
//            else map.put(new Point(rowIndex, colIndex), CellStates.GameOfLifeStates.DEAD);
//            colIndex++;


//        FireSimulation fireSimulation = new FireSimulation(width, height, )



    public void render(Map<Point, CellStates.GameOfLifeStates> updatedMap){

        int index = 0;
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
