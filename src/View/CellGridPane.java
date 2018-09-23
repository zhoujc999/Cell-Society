package View;

import Model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.stage.Stage;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    private void initialize(int width, int height){

        Rectangle[] rects = new Rectangle[width*height];
        for(int i = 0; i < rects.length; i++){
            rects[i] = new Rectangle(CELL_SIZE,CELL_SIZE);
        }

        Map<Point, CellStates.GameOfLifeStates> map = new HashMap<>();
        int rowIndex = 0;
        int colIndex = 0;
        for(int i = 0; i < rects.length; i++){
            if(colIndex >= width){
                rowIndex += 1;
                colIndex = 0;
            }
            if((rowIndex==1&&colIndex==2)||(rowIndex==2&&colIndex==2)||(rowIndex==3&&colIndex==2)){
                map.put(new Point(rowIndex, colIndex), CellStates.GameOfLifeStates.LIVE);
            }
            else map.put(new Point(rowIndex, colIndex), CellStates.GameOfLifeStates.DEAD);
            colIndex++;
        }

        GameOfLifeSimulation simulation= new GameOfLifeSimulation(width, height, map);
        simulation.render();
        Map<Point, CellStates.GameOfLifeStates> myMap = simulation.getView();

        Map<Point, CellStates.GameOfLifeStates> fireMap = new HashMap<>();
        rowIndex = 0;
        colIndex = 0;
        for(int i = 0; i < rects.length; i++){
            if(colIndex >= width){
                rowIndex += 1;
                colIndex = 0;
            }
//            if((rowIndex==2&&colIndex==2)||(rowIndex==2&&colIndex==3)||(rowIndex==3&&colIndex==4)||(rowIndex==1&&colIndex==1)||(rowIndex==3&&colIndex==3)){
//
//            if((rowIndex==2&&colIndex==2)){
//                map.put(new Point(rowIndex, colIndex), CellStates.FireStates.BURNING);
//            }
//            else map.put(new Point(rowIndex, colIndex), CellStates.GameOfLifeStates.DEAD);
//            colIndex++;
        }

//        FireSimulation fireSimulation = new FireSimulation(width, height, )

    }


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
