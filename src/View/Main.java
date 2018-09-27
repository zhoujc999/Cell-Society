package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;


public class Main extends Application {

    public static final String TITLE = "Cell Society";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final String FXML_FILE = "UI.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        ResourceBundle bundle = ResourceBundle.getBundle("Resource.UILabel");
        Parent root = FXMLLoader.load(getClass().getResource(FXML_FILE), bundle);

        primaryStage.setTitle(TITLE);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}