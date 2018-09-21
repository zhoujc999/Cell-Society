package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

/*
    @author xp19 This class represents the gridpane that displays cell simulation
 */

public class CellGridPane {

    private GridPane gridPane;
    private static final int CELL_SIZE = 10;

    public CellGridPane(GridPane gridPane){
        this.gridPane = gridPane;
        // testing a 50*50 grid
        initialize(50,50);
    }

    // create a simulation grid
    private void initialize(int width, int height){
        Rectangle[] rects = new Rectangle[width*height];

        for(int i = 0; i < rects.length; i++){
            rects[i] = new Rectangle(CELL_SIZE,CELL_SIZE);
        }

        // testing some random cell simulation
        Color[] palette = new Color[] { Color.RED, Color.BLUE, Color.ORANGE };
        Random rng = new Random();

        Timeline tt1 = new Timeline( new KeyFrame(
                Duration.millis(1000),
                e -> {
                    for(int i = 0; i < rects.length; i++){
                        rects[i].setFill(palette[rng.nextInt(palette.length)]);
                    }
                }
        ));
        tt1.setCycleCount(Timeline.INDEFINITE);
        tt1.setAutoReverse(true);
        tt1.play();

        // add all rectangle objects to the gridpane for displaying
        int rowIndex = 0;
        int colIndex = 0;
        for(int i = 0; i < rects.length; i++){
            if(colIndex >= width){
                rowIndex += 1;
                colIndex = 0;
            }
            gridPane.add(rects[i], colIndex, rowIndex);
            colIndex++;
        }
    }
}
