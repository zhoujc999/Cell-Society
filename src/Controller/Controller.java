package Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import View.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import Model.Simulation;
import Model.*;

import javafx.util.Duration;

/*
    @author sz165
    @author xp19
 */

public class Controller {
    public static final String DATA_FILE_EXTENSION = "*.xml";
    public static final int SPEEDBUFF = 1;
    public static final String NUM_ROW_ATTR = "numRows";
    public static final String NUM_COL_ATTR = "numColumns";
    public static final String CELL_RATIO = "ratio1";
    public static final String CELL_RATIO2 = "ratio2";
    public static final String EMPTY_RATIO = "occupiedRatio";
    public static final String DEFAULT_RATIO = "0.5";
    public static final String FPS = "frames_per_sec";
    public static final double FPS_DIVISION = 1000.0;
    public static final String THRESHOLD = "threshold";
    public static final String TYPE = "type";
    public static final String GAME_TYPE = "game";
    public static final String GAME_OF_LIFE = "gameOfLife";
    public static final String SEGREGATION = "segregation";
    public static final String RPS = "RPS";
    public static final String WATOR = "wator";
    public static final String FIRE = "fire";
    public static final String SHARK_RATE = "sharkRate";
    public static final String MAX_HIT = "maxHit";
    public static final String SIDES = "sides";
    public static final String DEFAULT_SHARK_RATE = "5";
    public static final String FISH_RATE = "fishRate";
    public static final String DEFAULT_FISH_RATE = "50";
    public static final String DEFAULT_SETUP = "defaultSetup";
    public static final String XMLHEADING = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n <data game=\"game\">";
    public static final HashSet<String> VALID_TYPES = new HashSet<>(Arrays.asList(FIRE,WATOR,RPS, GAME_OF_LIFE,SEGREGATION));

    public static final String FILE_CHOOSER_PROMPT = "Choose data file";
    private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);
    private Timeline myTime;
    //    private RectCellGridPaneOld myView;
    private CellGridPane myView;
    private Simulation mySimulation;
    //    private GridPane gridPane;
    private Pane gridPane;
    private Map<String, String> originalAttributes;
    private Map<Point, Integer> myMap;
    private Map<Point, Integer> beginningStageMap;
    private LineChart lineChart;
    private StatsGraph statsGraph;

    public Controller(Pane gridPane, LineChart lineChart) {
        this.gridPane = gridPane;
        this.lineChart = lineChart;
    }

    public void start() {
        var dataFile = myChooser.showOpenDialog(null);
//        System.out.println("Parsing begin.");
        XMLParser parser = new XMLParser(GAME_TYPE);
        Map<String, String> attributes = parser.getAttribute(dataFile);
        originalAttributes = attributes;
        System.out.println(originalAttributes);
        setUp(attributes, false);
    }

    public void setUp(Map<String, String> attributes, boolean isReset) {
        //retrieve parameters needed to build a new Simulation
        createSimulation(attributes, isReset);
        int speed = Integer.parseInt(attributes.get(FPS));
        myView = new HexCellGridPane(gridPane, statsGraph);
        myView.create(attributes, mySimulation);

        if(myTime==null){
            var frame = new KeyFrame(Duration.millis(FPS_DIVISION/(speed+SPEEDBUFF)),e->step());
            myTime = new Timeline();
            myTime.setCycleCount(Timeline.INDEFINITE);
            myTime.getKeyFrames().add(frame);
            myTime.play();
        }

    }

    private void createSimulation(Map<String, String> attributes, boolean isReset) {
        int numRows = Integer.parseInt(attributes.get(NUM_ROW_ATTR));
        int numColumns = Integer.parseInt(attributes.get(NUM_COL_ATTR));
        double cellRatio = Double.parseDouble(attributes.getOrDefault(CELL_RATIO, DEFAULT_RATIO));
        double emptyRatio = Double.parseDouble(attributes.getOrDefault(EMPTY_RATIO, DEFAULT_RATIO));
        double cellRatio2 = Double.parseDouble(attributes.getOrDefault(CELL_RATIO2, "0"));
        double threshold = Double.parseDouble(attributes.getOrDefault(THRESHOLD, DEFAULT_RATIO));
        String type = attributes.get(TYPE);
        if(type == null || !VALID_TYPES.contains(type))
            {throw new XMLException("invalid type in xml");}
        int sharkRate = Integer.parseInt(attributes.getOrDefault(SHARK_RATE, DEFAULT_SHARK_RATE));
        int fishRate = Integer.parseInt(attributes.getOrDefault(FISH_RATE, DEFAULT_FISH_RATE));
        int maxHit = Integer.parseInt(attributes.getOrDefault(MAX_HIT, DEFAULT_FISH_RATE));
        int sizes = Integer.parseInt(attributes.getOrDefault(SIDES, "4"));
        String defaultMap = attributes.getOrDefault(DEFAULT_SETUP, null);
        if(isReset){
            mySimulation = getSimulation(numRows, numColumns,type, threshold, beginningStageMap, fishRate, sharkRate,maxHit);
        }
        else {
            myMap = simulationMap(numRows, numColumns, cellRatio, cellRatio2, emptyRatio, defaultMap,getMaxState(type));
            beginningStageMap = new HashMap<>(myMap);
            mySimulation = getSimulation(numRows, numColumns, type, threshold, myMap, fishRate, sharkRate, maxHit);
        }
    }

    public void update(Map<String, String> map) {
        for (String s : map.keySet()) {
            originalAttributes.put(s, map.get(s));
        }
        statsGraph.clear();
        setUp(originalAttributes, false);
    }

    public void saveConfig (Map<String, String> config) throws IOException
    {
        Map<String, String> toSave = new HashMap<>(originalAttributes);
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Please choose the directory to save ur config");
        chooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("text file ",DATA_FILE_EXTENSION));
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File selectedFile = null;
        while (selectedFile == null){
            selectedFile = chooser.showSaveDialog(null);
        }
        createAndWrite(selectedFile);
    }

    private void createAndWrite(File selectedFile) throws FileNotFoundException {
        PrintWriter cout= new PrintWriter(selectedFile);
        cout.println(XMLHEADING);
        System.out.println(originalAttributes);
        for (String s : originalAttributes.keySet()) {
            if(originalAttributes.get(s)== null||originalAttributes.get(s).trim().isEmpty())
            {continue;}
            cout.print("<"+s+">");
            cout.print(originalAttributes.get(s));
            cout.println("</"+s+">");
        }
        cout.println("</data>");
        cout.close();
    }

    public void updateFPS(int updatedFPS) {
        myTime.stop();
        myTime.getKeyFrames().clear();
        var frame = new KeyFrame(Duration.millis(FPS_DIVISION / (updatedFPS + SPEEDBUFF)), e -> step());
        myTime.getKeyFrames().add(frame);
        myTime.play();
    }

    private void step() {
        //ask mySimulation to update
        mySimulation.step();
        //pass the new Simulation to myView
        myView.render(mySimulation.getView());
        myView.updateStatsGraph(mySimulation.getStatistics());
    }

    public void animationStep() {
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
        statsGraph.clear();
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

    Simulation getSimulation(int numRows, int numCols, String type, double threshold, Map<Point, Integer> myMap, int fishRate, int sharkRate, int maxHit) {
        Simulation simulation = null;
        switch (type) {
            case GAME_OF_LIFE:
                simulation = new GameOfLifeSimulation(numRows, numCols, myMap);
                statsGraph = new GameOfLifeStatsGraph(lineChart);
                break;
            case SEGREGATION:
                simulation = new SegregationSimulation(numRows, numCols, myMap, threshold);
                statsGraph = new SegregationStatsGraph(lineChart);
                break;
            case FIRE:
                simulation = new FireSimulation(numRows, numCols, myMap, threshold);
                statsGraph = new FireStatsGraph(lineChart);
                break;
            case WATOR:
                simulation = new WatorSimulation(numRows, numCols, myMap, fishRate, sharkRate);
                statsGraph = new WatorStatsGraph(lineChart);
                break;
            case RPS:
                simulation = new RPSSimulation(numRows, numCols, myMap, maxHit);
                statsGraph = new RPSStatsGraph(lineChart);
                break;
        }
        return simulation;
    }


    private Map<Point, Integer> simulationMap(int numRows, int numColumns, double cellRatio, double cellRatio2, double emptyRatio, String defaultMap, int maxState) {
        Map<Point, Integer> initialState = new HashMap<>();
        if (defaultMap != null && !defaultMap.isEmpty()) {
            initialState = getDefaultMap(defaultMap, numRows, numColumns, maxState);
        }
        if (initialState != null && !initialState.isEmpty()) return initialState;
        else initialState = new HashMap<>();
        Random r = new Random();
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                Point p = new Point(i, j);
                int state;
                double level = r.nextDouble();
                if (level < emptyRatio)
                    state = 0;
                else if (level < emptyRatio + (1 - emptyRatio) * cellRatio)
                    state = 1;
                else if (level < emptyRatio + (1 - emptyRatio) * cellRatio2)
                    state = 2;
                else
                    state = maxState;
                initialState.put(p, state);
            }

        }
            return initialState;
    }

    private Map<Point, Integer> getDefaultMap(String defaultMap, int numRows, int numColumns, int maxState)
    {
        String map = defaultMap.trim();
        String[] lines= map.split("\n");
        if (lines.length!=numRows)
            { return null; }
        Map<Point, Integer> returnMap = new HashMap<>();
        for (int i =0; i<lines.length;i++)
        {
            String[] states = lines[i].trim().split(" ");
            if(states.length!=numColumns)
                {return null;}
            for(int j = 0; j<numColumns; j++)
            {
                int theValue = Integer.parseInt(states[j]);
                if (theValue > maxState)
                {throw new XMLException("Invalid initial State: %d; Maximum allowable: %d", theValue, maxState);}
                returnMap.put(new Point(i,j),theValue);
            }


        }
        return returnMap;

    }

    private int getMaxState(String type){
        switch (type){
            case GAME_OF_LIFE:
            case SEGREGATION:
            case WATOR:
            case FIRE:
                return 2;
            case RPS:
                return 3;
            default:
                return 0;
        }
    }

}
