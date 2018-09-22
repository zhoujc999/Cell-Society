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
    private Rectangle[]rects;

    public CellGridPane(GridPane gridPane){
        this.gridPane = gridPane;
        // testing a 50*50 grid
    }

    public void create(Stage mainStage, Map<String, String> attributes, Simulation initialSimulation) throws IOException {
        width = Integer.parseInt(attributes.get("width"));
        height = Integer.parseInt(attributes.get("height"));
        initialize(width, height, initialSimulation);

        Parent root = FXMLLoader.load(getClass().getResource("UI.fxml"));
        mainStage.setTitle("Hello World");
        Scene scene = new Scene(root, width, height);
        mainStage.setScene(scene);
        mainStage.show();
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


    public void render(Simulation simulation){
        Map<Point, CellStates.GameOfLifeStates> map = simulation.getView();
        Rectangle[] rects = new Rectangle[width*height];

        int index = 0;
        for(Point p: map.keySet()){
            if(map.get(p) == CellStates.GameOfLifeStates.LIVE){
                rects[index].setFill(Color.BLACK);
            }
            else{
                rects[index].setFill(Color.WHITE);
            }
            gridPane.add(rects[index++], p.getY(), p.getX());
        }
    }
}
