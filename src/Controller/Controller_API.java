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
    private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);

    private int frames_per_sec;
    private Timeline myTime;
    private CellGridPane myView;
    private Simulation mySimulation;
    private Stage myStage;


    public void start(Stage mainStage) throws Exception {
        var dataFile = myChooser.showOpenDialog(mainStage);
        Stage myStage = mainStage;
        XMLParser parser = new XMLParser("game");
        Map<String, String> attributes = parser.getAttribute(dataFile);

        setUp(mainStage, attributes);
    }


    private void setUp(Stage mainStage, Map<String, String> attributes) throws Exception {
        //retrieve parameters needed to build a new Simulation

        int numRows = Integer.parseInt(attributes.get("numRows"));
        int numColumns = Integer.parseInt(attributes.get("numColumns"));
        double cellRatio = Double.parseDouble(attributes.get("ratio1"));
        double emptyRatio = Double.parseDouble(attributes.get("ratio2"));
        int speed = Integer.parseInt(attributes.get("frames_per_sec"));
        String type = attributes.get("type");
        mySimulation = golSimulation(numRows, numColumns, cellRatio);

        //pass the mxlObj, stage and Simulation obj for the viewer to create for the first time
        myView  = new CellGridPane();
        myView.create(mainStage, attributes, mySimulation);


        var frame = new KeyFrame(Duration.millis(1000/speed),e->step((double)(1.0/speed)));
        myTime = new Timeline();
        myTime.setCycleCount(Timeline.INDEFINITE);
        myTime.getKeyFrames().add(frame);
        myTime.play();

        //build a new simulation*/

    }

    private void setUp(Map<String, String> attributes){
        //retrieve parameters needed to build a new Simulation

        int numRows = Integer.parseInt(attributes.get("numRows"));
        int numColumns = Integer.parseInt(attributes.get("numColumns"));
        double cellRatio = Double.parseDouble(attributes.get("ratio1"));
        double emptyRatio = Double.parseDouble(attributes.get("ratio2"));
        int speed = Integer.parseInt(attributes.get("frames_per_sec"));
        String type = attributes.get("type");
        mySimulation = golSimulation(numRows, numColumns, cellRatio);

        //pass the mxlObj, stage and Simulation obj for the viewer to create for the first time


        var frame = new KeyFrame(Duration.millis(1000/speed),e->step((double)(1.0/speed)));
        myTime = new Timeline();
        myTime.setCycleCount(Timeline.INDEFINITE);
        myTime.getKeyFrames().add(frame);
        myTime.play();

        //build a new simulation*/

    }

    private void step(double elapsedTime) {
        //ask mySimulation to update
        mySimulation.step();

        //pass the new Simulation to myView
        myView.render(mySimulation);
    }

    public void stop() {
        myTime.stop();
    }

    public void resume() {
        myTime.play();
    }

    public void reset(String type, int size, double Ratio) {
        //Simulation initialSimulation = Simulation(size, ratio, type);
        //myView.create(mainStage, xmlObj, initialSimulation);
    }

    private void end() {
        //myView.endGreeting();
        //pause for a while
        myStage.close();
    }

    private FileChooser makeChooser(String extension) {
        var result = new FileChooser();
        result.setTitle("choose data file");
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text files", extension));
        return result;
    }

    private Simulation golSimulation(int numRows, int numColumns, double cellRatio) {
        Map<Point, CellStates.GameOfLifeStates> initialState = new HashMap<Point, CellStates.GameOfLifeStates>();

        Random r = new Random();
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                Point p = new Point(i, j);
                CellStates.GameOfLifeStates state;
                double level = r.nextDouble();
                if (level < cellRatio)
                    state = CellStates.GameOfLifeStates.DEAD;
                else
                    state = CellStates.GameOfLifeStates.DEAD;
                initialState.put(p, state);
            }
        }
        return new GameOfLifeSimulation(numRows, numColumns, initialState);
    }

    public void setMyView(CellGridPane myView){
        this.myView = myView;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
