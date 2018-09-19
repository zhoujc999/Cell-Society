package Controller;

import javafx.animation.Timeline;
import javafx.stage.Stage;


public class Controller_API {
    public static final String XMLDIR = "";
    public static final int FREAMES_PER_SECOND = 20;

    private Timeline myTime;
    private View myView;
    private Simulation mySimulation;
    private Stage myStage;


    public void start(Stage mainStage){
        //parse XML to get default values
        Stage myStage = mainStage;
        var xmlObj = parse_XML(XMLDIR);
        //set the values on mainStage

        //call setUp
        setUp(Stage, xmlObj);

        //set up animation
    }

    private void setUp(Stage mainStage, var xmlObj){
        //retrieve parameters needed to build a new Simulation
        int initialSize = xmlObj.getLabel("size");
        double ratio = xmlObj.getLabel("ratio");
        String type = xmlObj.getLabel("type");

        //build a new simulation
        Simulation initialSimulation = Simulation(size, ratio, type);

        //pass the mxlObj, stage and Simulation obj for the viewer to create for the first time
        myView.create(mainStage, xmlObj, initialSimulation);
    }

    private void step (double elapsedTime){
        //ask mySimulation to update
        mySimulation.update();

        //pass the new Simulation to myView
        myView.render(mySimulation);
    }

    public void stop()
    {
        myTime.stop();
    }

    public void resume()
    {
        myTime.play();
    }

    public void reset(String type, int size, double Ratio)
    {
        Simulation initialSimulation = Simulation(size, ratio, type);
        myView.create(mainStage, xmlObj, initialSimulation);
    }

    private void end()
    {
        myView.endGreeting();
        //pause for a while
        myStage.close();
    }


    private XMLOBJ parse_XML(String directory){
        //return an XMLOBJ
    }


}
