package View;

import Controller.Controller_API;
import Model.CellStates;
import Model.Point;
import Model.Simulation;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import java.util.Map;

public class HexCellGridPane extends CellGridPane {

    private final static double HEX_RAD_DELTA = Math.PI / 3;
    private final static int SIDES = 6;
    private final static double COS30 = Math.sqrt(3) / 2;
    private Pane pane;
    private Polygon[][] grid;
    private StatsGraph statsGraph;

//    public HexCellGridPane(Pane pane){
//        this.pane = pane;
//    }

    public HexCellGridPane(Pane pane, StatsGraph statsGraph){
        super(statsGraph);
        this.pane = pane;
    }

    private Polygon createCell(double centerX, double centerY, double radius, Paint color) {
        Polygon hex = new Polygon();

        // comparing to 6 is enough to ensure every angle is used once here
        // since (5/6) * 2 * PI < 6 < 2 * PI
        for (double rad = 0; rad < SIDES; rad += HEX_RAD_DELTA) {
            hex.getPoints().addAll(Math.cos(rad) * radius + centerX, Math.sin(rad) * radius + centerY);
        }

        hex.setFill(color);
        hex.setStroke(Color.BLACK);
        return hex;
    }


//    public void createEasy(int numRows, int numCols){
//        double radius = MAX_GRID_WIDTH / (1.5 * numCols);
//        double dY = radius * COS30;
//        int counter = 0;
//
//        grid = new Polygon[numRows*numCols];
//
//        for (int r = 0; r < numRows; r++) {
//            double offsetY  = 2 * dY * r + radius;
//            for (int c = 0; c < numCols; c++) {
//                Polygon hex = createCell(
//                        1.5 * radius * c + radius,
//                        (c & 1) == 0 ? offsetY : offsetY + dY,
//                        radius,
//                        Color.PINK);
//
//                pane.getChildren().add(hex);
//                grid[counter] = hex;
//                counter++;
//            }
//        }
//    }

    @Override
    public void create(Map<String, String> attributes, Simulation initialSimulation) {
        int numRows = Integer.parseInt(attributes.get(Controller_API.NUM_ROW_ATTR));
        int numCols = Integer.parseInt(attributes.get(Controller_API.NUM_COL_ATTR));
        initialize(numRows, numCols, initialSimulation);
    }


    @Override
    public void initialize(int numRows, int numCols, Simulation simulation) {
        pane.getChildren().clear();
        double radius = MAX_GRID_WIDTH / (1.5 * numCols);
        double dY = radius * COS30;

        grid = new Polygon[numRows][numCols];

        for (int r = 0; r < numRows; r++) {
            double offsetY  = 2 * dY * r + radius;
            for (int c = 0; c < numCols; c++) {
                Polygon hex = createCell(
                        1.5 * radius * c + radius,
                        (c & 1) == 0 ? offsetY : offsetY + dY,
                        radius,
                        Color.PINK);

                pane.getChildren().add(hex);
                grid[r][c] = hex;
            }
        }
    }

    public void render(Map<Point, Integer> updatedMap) {
        render(updatedMap, grid);
    }

}
