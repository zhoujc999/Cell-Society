package View;

import Controller.Controller_API;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/*
    UIManager is a class that keeps track of all UI elements and handle their actions
    @author xp19
    @author sz165
 */

public class UIManager {

    // fields with @FXML annotation are automatically populated to reference the UI element
    // with the same name as its id in fxml
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private Slider slider;
    @FXML
    public GridPane gridPane;

    private Controller_API controller;
    private static final int MAX_FPS = 30;
    private static final String DEFAULT_SIZE = "20";
    private int oldNumRows;
    private int oldNumCols;
    private int oldFPS;

    // this method will be called automatically by the FXML loader
    public void initialize(){
        forceInputToBeNumeric(widthTextField);
        forceInputToBeNumeric(heightTextField);
        controller = new Controller_API(gridPane);
       // CellGridPane societyGridPane = new CellGridPane(gridPane);
        controller.start();
    }

    // restrict the textfield to contain only numbers
    private void forceInputToBeNumeric(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        tf.setText(newValue.replaceAll("[^\\d]", ""));
                    }
        });
    }

    // you can get the file chosen from this method
    public void handleChooseAFileAction(){
        controller.start();
    }

    // you can get the updated value from the user input fields from this method
    public void handleApplyButtonAction(){
        Map<String, String> attributes = new HashMap<>();
        attributes.put("numRows", getOrDefaultValue(heightTextField.getText()) );
        attributes.put("numColumns", getOrDefaultValue(widthTextField.getText()) );
        attributes.put("frames_per_sec", String.valueOf((int)((slider.getValue()/100)*MAX_FPS)));
//        IntegerProperty integerProperty = new SimpleIntegerProperty((int)((slider.getValue()/100)*MAX_FPS));
//        integerProperty.bindBidirectional(controller.getSpeedProperty());
        if(oldNumCols!=Integer.parseInt(widthTextField.getText())||oldNumRows!=Integer.parseInt(heightTextField.getText())){
            oldNumCols = Integer.parseInt(widthTextField.getText());
            oldNumRows = Integer.parseInt(heightTextField.getText());
            gridPane.getChildren().clear();
            controller.update(attributes);
        }
        else if(oldFPS!=(int)((slider.getValue()/100)*MAX_FPS)){
            oldFPS = (int)((slider.getValue()/100)*MAX_FPS);
            controller.updateFPS((int)((slider.getValue()/100)*MAX_FPS));
        }
    }

    public void handleStartButtonAction(){
        controller.resume();
    }

    public void handleStopButtonAction(){
        controller.stop();
    }

    public void handleResetButtonAction(){
        controller.reset();
    }

    public void handleStepButtonAction(){
        controller.animationStep();
    }

    // if the input is empty, use default value
    private String getOrDefaultValue(String s){
        if(s.length()==0) return DEFAULT_SIZE;
        else return s;
    }

    public GridPane getGridPane(){
        return gridPane;
    }

}
