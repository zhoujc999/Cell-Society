package View;

import Controller.Controller;
import Controller.XMLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;

import javafx.scene.control.*;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/*
    UIManager is a class that keeps track of all UI elements and handle their actions
    @author xp19
    @author sz165
 */

public class UIManager {

    // fields with @FXML annotation are automatically populated to reference the UI element
    // with the same name as its id in fxml
    @FXML
    private GridPane layoutGridPane;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private Slider slider;
    @FXML
    private Pane gridPane;
    @FXML
    private LineChart lineChart;
    @FXML
    private ComboBox dropDownMenu;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;


    private Controller controller;
    private ResourceBundle resourceBundle;
    private static final int MAX_FPS = 30;
    private static final String DEFAULT_SIZE = "20";
    private static final int HUNDRED = 100;
    private int oldNumRows;
    private int oldNumCols;
    private int oldFPS;

    // this method will be called automatically by the FXML loader
    public void initialize(){
        resourceBundle = ResourceBundle.getBundle("Resource.UILabel", AppLanguageManager.getCurrentLocale());
        forceInputToBeNumeric(widthTextField);
        forceInputToBeNumeric(heightTextField);
        initializeComboBox();
        controller = new Controller(gridPane, lineChart);
        try
        {
            controller.start();
        }
        catch(XMLException e)
        {
            showXMLError(e.getMessage());
        }
    }

    // restrict the textfield to contain only numbers
    protected void forceInputToBeNumeric(TextField tf) {
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        tf.setText(newValue.replaceAll("[^\\d]", ""));
                    }
        });
    }

    // you can get the file chosen from this method
    public void handleChooseAFileAction(){
        try{controller.start();}
        catch (XMLException e){
            showXMLError(e.getMessage());
        }
    }

    // you can get the updated value from the user input fields from this method
    public void handleApplyButtonAction(){
        handleApplyButtonAction(heightTextField, widthTextField, slider, controller, gridPane);
    }

    public void handleApplyButtonAction(TextField heightTextField, TextField widthTextField, Slider slider, Controller controller, Pane gridPane){
        Map<String, String> attributes = getNewAttribute(heightTextField, widthTextField, slider);
        if (attributes == null) return;
        if(oldNumCols!=Integer.parseInt(widthTextField.getText())||oldNumRows!=Integer.parseInt(heightTextField.getText())){
            oldNumCols = Integer.parseInt(widthTextField.getText());
            oldNumRows = Integer.parseInt(heightTextField.getText());
            gridPane.getChildren().clear();
            controller.update(attributes);
        }
        else if(oldFPS!=(int)((slider.getValue()/HUNDRED)*MAX_FPS)){
            oldFPS = (int)((slider.getValue()/HUNDRED)*MAX_FPS);
            controller.updateFPS((int)((slider.getValue()/HUNDRED)*MAX_FPS));
        }
    }

    private Map<String, String> getNewAttribute(TextField heightTextField, TextField widthTextField, Slider slider) {
        if(heightTextField.getText().length()==0||widthTextField.getText().length()==0){
            showWarningDialog();
            return null;
        }
        Map<String, String> attributes = new HashMap<>();
        attributes.put(Controller.NUM_ROW_ATTR, heightTextField.getText() );
        attributes.put(Controller.NUM_COL_ATTR, widthTextField.getText() );
        attributes.put(Controller.FPS, String.valueOf((int)((slider.getValue()/HUNDRED)*MAX_FPS)));
        return attributes;
    }

    public void changeCellShape(){
        System.out.println(dropDownMenu.getSelectionModel().getSelectedItem().toString());
//        controller.changeCellShape(dropDownMenu.getSelectionModel().getSelectedItem().toString());
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

    public void showXMLError(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error: " + s + "\nDo you" +
                "want to choose another configuration file?", ButtonType.NO, ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            controller.start();
        } else {
            return;
        }
    }

    private void showWarningDialog(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(resourceBundle.getString("WarningTitle"));
        alert.setHeaderText(resourceBundle.getString("WarningHeaderText"));
        alert.setContentText(resourceBundle.getString("WarningContentText"));
        alert.showAndWait();
    }

    private void initializeComboBox(){
        dropDownMenu.setItems(FXCollections.observableArrayList(
                resourceBundle.getString("Rectangle"), resourceBundle.getString("Hexagon")));
        dropDownMenu.getSelectionModel().selectFirst();
    }

    public void addNewSimulation(){
        Layout l = new Layout(layoutGridPane);
    }

    private void handleSave(TextField heightTextField, TextField widthTextField, Slider slider, Controller controller, Pane gridPane){
        Map<String, String> attributes = getNewAttribute(heightTextField, widthTextField, slider);
        try{controller.saveConfig(attributes);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Configuration saved",
                    ButtonType.OK);
            alert.showAndWait();
        }
        catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"IOException: "+ e.getMessage(),
                    ButtonType.OK);
            alert.showAndWait();
        }
    }


    public void handleSave() {
        handleSave(heightTextField, widthTextField, slider, controller, gridPane);
    }
}
