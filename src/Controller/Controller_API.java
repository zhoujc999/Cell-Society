package Controller;
import javafx.animation.Timeline;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import View.*;
import java.io.File;
import Model.*;

public class Controller_API {
    public static final String DATA_FILE_EXTENSION= "*.xml";
    private FileChooser myChooser = makeChooser(DATA_FILE_EXTENSION);

    private int frames_per_sec;
    private Timeline myTime;
    private View myView;
    private Simulation mySimulation;
    private Stage myStage;

    public void start(Stage mainStage){
        var dataFile = myChooser.showOpenDialog(mainStage);
        while(dataFile!=null){
            try{

            }
            catch {
                var
            }
        }
        Stage myStage = mainStage;
        var xmlObj = parse_XML(XMLDIR);


        setUp(Stage, xmlObj);

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

    private FileChooser makeChooser(String extension){
        var result = new FileChooser();
        result.setTitle("choose data file");
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Text files",extension));
        return result;
    }

    private XMLOBJ parse_XML(String directory){
        //return an XMLOBJ
    }


}
