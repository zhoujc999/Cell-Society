package View;

import Controller.Controller;
import Model.Point;
import Model.Simulation;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Map;

public class RectCellGridPane extends CellGridPane {

    private Pane pane;
    private Rectangle[][] grid;

    public RectCellGridPane(Pane pane, StatsGraph statsGraph){
        super(statsGraph);
        this.pane = pane;
    }

    private Rectangle createRectCell(double width, double height, double x, double y, Paint color){
        Rectangle cell = new Rectangle(width, height);
        cell.setLayoutX(x);
        cell.setLayoutY(y);
        cell.setFill(color);
        return cell;
    }

    @Override
    public void create(Map<String, String> attributes, Simulation initialSimulation) {
        int numRows = Integer.parseInt(attributes.get(Controller.NUM_ROW_ATTR));
        int numCols = Integer.parseInt(attributes.get(Controller.NUM_COL_ATTR));
        initialize(numRows, numCols, initialSimulation);
    }

    @Override
    public void initialize(int numRows, int numCols, Simulation simulation) {
        pane.getChildren().clear();
        grid = new Rectangle[numRows][numCols];
        double width = MAX_GRID_WIDTH/numCols;
        double height = MAX_GRID_HEIGHT/numRows;
        double y = 0;
        for(int r = 0; r < numRows; r++){
            double x = 0;
            for(int c = 0; c < numCols; c++){
                grid[r][c] = createRectCell(width, height, x, y, Color.PINK);
//                Rectangle rect = grid[r][c];
//                grid[r][c].setOnMouseClicked(event -> rect.setFill(Color.BLACK));
                pane.getChildren().add(grid[r][c]);
                x += width;
            }
            y += height;
        }

    }

    @Override
    public void render(Map<Point, Integer> updatedMap){
        render(updatedMap, grid);
    }

}
