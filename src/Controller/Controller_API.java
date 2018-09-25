package Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import View.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import Model.Simulation;
import Model.*;

import javafx.util.Duration;

/*
    @author sz165
    @author xp19
 */

public class Controller_API{
    public static final String DATA_FILE_EXTENSION = "*.xml";
    public static final int SPEEDBUFF = 1;
    public static final String NUM_ROW_ATTR = "numRows";
    public static final String NUM_COL_ATTR = "numColumns";
    public static final String CELL_RATIO = "ratio1";
    public static final String EMPTY_RATIO = "ratio2";
    public static final String DEFAULT_RATIO = "0.5";
    public static final String FPS = "frames_per_sec";
    public static final double FPS_DIVISION = 1000.0;
    public static final String THRESHOLD = "threshold";
    public static final String TYPE = "type";
    public static final String GAME_TYPE = "game";
    public static final String GAME_OF_LIFE = "gameOfLife";
    public static final String SEGREGATION = "segregation";
    public static final String WATOR = "wator";
    public static final String FIRE = "fire";
    public static final String SHARK_RATE = "sharkRate";
    public static final String DEFAULT_SHARK_RATE = "5";
    public static final String FISH_RATE = "fishRate";
    public static final String DEFAULT_FISH_RATE = "50";

    public static final String FILE_CHOOSER_PROMPT = "Choose data file";
    private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);
    private Timeline myTime;
    private CellGridPane myView;
    private Simulation mySimulation;
    private GridPane gridPane;
    private Map<String, String> originalAttributes;
    private Map<Point, Integer> myMap;
    private Map<Point, Integer> beginningStageMap;

    public Controller_API(GridPane gridPane)
    {
        this.gridPane =gridPane;
    }

    public void start(){
        var dataFile = myChooser.showOpenDialog(null);

        XMLParser parser = new XMLParser(GAME_TYPE);
        Map<String, String> attributes = parser.getAttribute(dataFile);
        originalAttributes = attributes;
        setUp(attributes, false);
    }

    public void setUp(Map<String, String> attributes, boolean isReset){
        //retrieve parameters needed to build a new Simulation
        int numRows = Integer.parseInt(attributes.get(NUM_ROW_ATTR));
        int numColumns = Integer.parseInt(attributes.get(NUM_COL_ATTR));
        double cellRatio = Double.parseDouble(attributes.getOrDefault(CELL_RATIO, DEFAULT_RATIO));
        double emptyRatio = Double.parseDouble(attributes.getOrDefault(EMPTY_RATIO, DEFAULT_RATIO));
        int speed = Integer.parseInt(attributes.get(FPS));
        double threshold = Double.parseDouble(attributes.getOrDefault(THRESHOLD, DEFAULT_RATIO));
        String type = attributes.get(TYPE);
        int sharkRate = Integer.parseInt(attributes.getOrDefault(SHARK_RATE, DEFAULT_SHARK_RATE));
        int fishRate = Integer.parseInt(attributes.getOrDefault(FISH_RATE, DEFAULT_FISH_RATE));

        if(isReset){
            mySimulation = getSimulation(numRows, numColumns,type, threshold, beginningStageMap, fishRate, sharkRate);
        }
        else {
            myMap = simulationMap(numRows, numColumns, cellRatio, emptyRatio);
            beginningStageMap = new HashMap<>(myMap);
            mySimulation = getSimulation(numRows, numColumns, type, threshold, myMap, fishRate, sharkRate);
        }

        myView = new CellGridPane(gridPane);
        myView.create(attributes, mySimulation);

        if(myTime==null){
            var frame = new KeyFrame(Duration.millis(FPS_DIVISION/(speed+SPEEDBUFF)),e->step());
            myTime = new Timeline();
            myTime.setCycleCount(Timeline.INDEFINITE);
            myTime.getKeyFrames().add(frame);
            myTime.play();
        }

    }

    public void update(Map<String, String> map){
        for(String s: map.keySet())
        {
            originalAttributes.put(s,map.get(s));
        }
        setUp(originalAttributes, false);
    }

    public void updateFPS(int updatedFPS){
        myTime.stop();
        myTime.getKeyFrames().clear();
        var frame = new KeyFrame(Duration.millis(FPS_DIVISION/(updatedFPS+SPEEDBUFF)),e->step());
        myTime.getKeyFrames().add(frame);
        myTime.play();
    }

    private void step() {
        //ask mySimulation to update
        mySimulation.step();

        //pass the new Simulation to myView
        myView.render(mySimulation.getView());
    }

    public void animationStep(){
        step();
        stop();
    }

    public void stop() {
        myTime.stop();
    }

    public void resume() {
        myTime.play();
    }

    public void reset() {
        stop();
        setUp(originalAttributes, true);
        animationStep();
    }

    private FileChooser makeChooser(String extension) {
        var result = new FileChooser();
        result.setTitle(FILE_CHOOSER_PROMPT);
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text files", extension));
        return result;
    }

    Simulation getSimulation(int numRows, int numCols, String type, double threshold, Map<Point, Integer> myMap, int fishRate, int sharkRate){
        Simulation simulation = null;
        switch (type){
            case GAME_OF_LIFE:
                simulation = new GameOfLifeSimulation(numRows,numCols,myMap);
                break;
            case SEGREGATION:
                simulation = new SegregationSimulation(numRows,numCols,myMap, threshold);
                break;
            case FIRE:
                simulation = new FireSimulation(numRows,numCols,myMap, threshold);
                break;
            case WATOR:
                simulation = new WatorSimulation(numRows,numCols,myMap,fishRate,sharkRate);
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

        return initialState;
    }

}
