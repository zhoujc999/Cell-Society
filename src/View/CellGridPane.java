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
    private GridPane gridPane;
    private int width;
    private int height;
    private Rectangle[] rects;

    public CellGridPane(){

    }

    public CellGridPane(GridPane gridPane){
        this.gridPane = gridPane;
        // testing a 50*50 grid
        initialize(5,5);
    }


    public void create(Map<String, String> attributes, Simulation initialSimulation) throws Exception {
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

    public void update(Simulation simulation) {
        Map<Point, CellStates.GameOfLifeStates> temp = simulation.getView();
        int i = 0;
        for (Point p : temp.keySet()) {
            if (temp.get(p) == CellStates.GameOfLifeStates.LIVE) {
                rects[i].setFill(Color.BLACK);
            } else {
                rects[i].setFill(Color.WHITE);
            }
            i++;
        }
    }


    // create a simulation grid
//    private void initialize(int width, int height){
//        Rectangle[] rects = new Rectangle[width*height];
//
//        for(int i = 0; i < rects.length; i++){
//            rects[i] = new Rectangle(CELL_SIZE,CELL_SIZE);
//        }
//
//        // testing some random cell simulation
//        Color[] palette = new Color[] { Color.RED, Color.BLUE, Color.ORANGE };
//        Random rng = new Random();
//
//        Timeline tt1 = new Timeline( new KeyFrame(
//                Duration.millis(1000),
//                e -> {
//                    for(int i = 0; i < rects.length; i++){
//                        rects[i].setFill(palette[rng.nextInt(palette.length)]);
//                    }
//                }
//        ));
//        tt1.setCycleCount(Timeline.INDEFINITE);
//        tt1.setAutoReverse(true);
//        tt1.play();
//
//        // add all rectangle objects to the gridpane for displaying
//        int rowIndex = 0;
//        int colIndex = 0;
//        for(int i = 0; i < rects.length; i++){
//            if(colIndex >= width){
//                rowIndex += 1;
//                colIndex = 0;
//            }
//            gridPane.add(rects[i], colIndex, rowIndex);
//            colIndex++;
//        }
//    }

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


        int index = 0;
//        for(Point p: myMap.keySet()){
//            if(map.get(p) == CellStates.GameOfLifeStates.LIVE){
//                rects[index].setFill(Color.BLACK);
//            }
//            else{
//                rects[index].setFill(Color.WHITE);
//            }
//            gridPane.add(rects[index++], p.getY(), p.getX());
//        }

        // add time line
//        Timeline tt1 = new Timeline( new KeyFrame(
//                Duration.millis(1000),
//                e -> {
////                    simulation.render();
//                    simulation.step();
//                    simulation.render();
//                    Map<Point, CellStates.GameOfLifeStates> temp = simulation.getView();
//                    System.out.println(temp);
//                    int i = 0;
//                    for(Point p: temp.keySet()){
//                        System.out.println();
//                        if(temp.get(p) == CellStates.GameOfLifeStates.LIVE){
//                            rects[i].setFill(Color.BLACK);
//                        }
//                        else{
//                            rects[i].setFill(Color.WHITE);
//                        }
//                        i++;
//                    }
//                    System.out.println("Timeline called back");
//                }
//        ));


//        for(Point p: myMap.keySet()){
//            if(myMap.get(p) == CellStates.FireStates.BURNING){
//                rects[index].setFill(Color.RED);
//            }
//            else if(myMap.get(p) == CellStates.FireStates.TREE){
//                rects[index].setFill(Color.GREEN);
//            }
//            else{
//                rects[index].setFill(Color.WHITE);
//            }
//            gridPane.add(rects[index++], p.getY(), p.getX());
//        }


//        Timeline tt1 = new Timeline( new KeyFrame(
//                Duration.millis(1000),
//                e -> {
////                    simulation.render();
//                    simulation.step();
//                    simulation.render();
//                    Map<Point, CellStates.FireStates> temp = simulation.getView();
//                    System.out.println(temp);
//                    int i = 0;
//                    for(Point p: temp.keySet()){
//                        if(temp.get(p) == CellStates.FireStates.BURNING){
//                            rects[i].setFill(Color.RED);
//                        }
//                        else if(temp.get(p) == CellStates.FireStates.TREE){
//                            rects[i].setFill(Color.GREEN);
//                        }
//                        else{
//                            rects[i].setFill(Color.WHITE);
//                        }
//                        i++;
//                    }
//                    System.out.println("Timeline called back");
//                }
//        ));

//
//        tt1.setCycleCount(Timeline.INDEFINITE);
//        tt1.play();

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
