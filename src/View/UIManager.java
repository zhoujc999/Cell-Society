package View;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import java.io.File;

/*
    UIManager is a class that keeps track of all UI elements and handle their actions
    @author xp19
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

    // this method will be called automatically by the FXML loader
    public void initialize(){
        forceInputToBeNumeric(widthTextField);
        forceInputToBeNumeric(heightTextField);
        CellGridPane societyGridPane = new CellGridPane(gridPane);
    }

    // you can get the updated value from the user input fields from this method
    public void handleApplyButtonAction(){
        System.out.println(widthTextField.getText());
        System.out.println(heightTextField.getText());
        System.out.println(slider.getValue());
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) { return; }

        try {
            // we obtain the path to the selected file
            System.out.println(selectedFile.getName());
            System.out.println(selectedFile.getAbsolutePath());
            // we can now do whatever we need with this file

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleStartButtonAction(){
        System.out.println("Testing");
    }

    public void handleStopButtonAction(){

    }

    public void handleResetButtonAction(){

    }

    public void handleStepButtonAction(){

    }

    public GridPane getGridPane(){
        return gridPane;
    }

}
