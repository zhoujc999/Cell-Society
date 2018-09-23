package Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import View.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import Model.Simulation;
import Model.*;

import javafx.util.Duration;
import org.w3c.dom.Element;

import static javafx.application.Application.launch;

public class Controller_API{
    public static final String DATA_FILE_EXTENSION = "*.xml";
    public static final int SPEEDBUFF = 1;
    private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);

    private int frames_per_sec;
    private Timeline myTime;
    private CellGridPane myView;
    private Simulation mySimulation;
    private GridPane gridPane;
    private Map<String, String> originalAttributes;
    private Map<Point, Integer> myMap;

    public Controller_API(GridPane gridPane)
    {
        this.gridPane =gridPane;
    }
    public void start(){
        var dataFile = myChooser.showOpenDialog(null);

        XMLParser parser = new XMLParser("game");
        Map<String, String> attributes = parser.getAttribute(dataFile);
        originalAttributes = attributes;
        setUp(attributes);
    }


    public void setUp(Map<String, String> attributes){
        //retrieve parameters needed to build a new Simulation

        int numRows = Integer.parseInt(attributes.get("numRows"));
        int numColumns = Integer.parseInt(attributes.get("numColumns"));
        double cellRatio = Double.parseDouble(attributes.getOrDefault("ratio1", "0.5"));
        double emptyRatio = Double.parseDouble(attributes.getOrDefault("ratio2", "0.5"));
        int speed = Integer.parseInt(attributes.get("frames_per_sec"));
        double threshold = Double.parseDouble(attributes.getOrDefault("threshold", "0.5"));
        String type = attributes.get("type");

        myMap = simulationMap(numRows,numColumns,cellRatio,emptyRatio);
        mySimulation = getSimulation(numRows, numColumns,type, threshold);

//        if(myView==null){
            myView = new CellGridPane(gridPane);
            myView.create(attributes, mySimulation);
//        }


        var frame = new KeyFrame(Duration.millis(1000/(speed+SPEEDBUFF)),e->step((double)(1.0/(speed+SPEEDBUFF))));
        myTime = new Timeline();
        myTime.setCycleCount(Timeline.INDEFINITE);
        myTime.getKeyFrames().add(frame);
        myTime.play();

        //build a new simulation*/

    }

    public void update(Map<String, String> map){
        for(String s: map.keySet())
        {
            originalAttributes.put(s,map.get(s));
        }
        setUp(originalAttributes);
    }

    private void step(double elapsedTime) {
        //ask mySimulation to update
        mySimulation.step();
        mySimulation.render();

        //pass the new Simulation to myView
        myView.render(mySimulation.getView());
    }

    public void stop() {
        myTime.stop();
    }

    public void resume() {
        myTime.play();
    }

    public void apply(Map<String, String> attributes){
        setUp(attributes);
    }

    public void reset() {
        stop();
        setUp(originalAttributes);
        stop();
    }

//    private void end() {
//        //myView.endGreeting();
//        //pause for a while
//        myStage.close();
//    }

    private FileChooser makeChooser(String extension) {
        var result = new FileChooser();
        result.setTitle("choose data file");
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text files", extension));
        return result;
    }

    Simulation getSimulation(int numRows, int numCols, String type, double threshold){
        Simulation simulation = null;
        switch (type){
            case "gameOfLife":
                simulation = new GameOfLifeSimulation(numRows,numCols,myMap);
                break;
            case "segregation":
                simulation = new SegregationSimulation(numRows,numCols,myMap, threshold);
                break;
            case "fire":
                simulation = new FireSimulation(numRows,numCols,myMap, threshold);
                break;
        }
        return simulation;
    }
    private Map<Point, Integer> simulationMap(int numRows, int numColumns, double cellRatio, double emptyRatio) {
        Map<Point, Integer> initialState = new HashMap<>();

        Random r = new Random();
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                Point p = new Point(i, j);
                int state;
                double level = r.nextDouble();
                if (level < emptyRatio)
                    state = 2;
                else if (level < emptyRatio+(1-emptyRatio)*cellRatio)
                    state = 0;
                else
                    state = 1;
                initialState.put(p, state);
            }
        }

        /*int rowIndex = 0;
        int colIndex = 0;
        for(int i = 0; i < 25; i++){
            if(colIndex >= 5){
                rowIndex += 1;
                colIndex = 0;
            }
            if((rowIndex==1&&colIndex==2)||(rowIndex==2&&colIndex==2)||(rowIndex==3&&colIndex==2)){
                initialState.put(new Point(rowIndex, colIndex), CellStates.GameOfLifeStates.LIVE);
            }
            else initialState.put(new Point(rowIndex, colIndex), CellStates.GameOfLifeStates.DEAD);
            colIndex++;
        }*/

        return initialState;
    }

    public void setMyView(CellGridPane myView){
        this.myView = myView;
    }
}
