package View;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class UIManager {

    // fields with @FXML annotation are automatically populated to reference the UI element
    // with the same name as its id in fxml
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private Slider slider;

    // this method will be called automatically by the FXML loader
    public void initialize(){

    }

    // you can get the updated value from the user input fields from this method
    public void handleApplyButtonAction(){
        System.out.println(widthTextField.getText());
        System.out.println(heightTextField.getText());
        System.out.println(slider.getValue());
    }

    public void handleStartButtonAction(){

    }

    public void handleStopButtonAction(){

    }

    public void handleResetButtonAction(){

    }

    public void handleStepButtonAction(){

    }

}
