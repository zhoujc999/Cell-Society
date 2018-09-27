package View;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public class HexCellView extends CellView {

    private final static double HEX_RAD_DELTA = Math.PI / 3;
    private final static int SIDES = 6;

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

    public void createGrid(Pane root) {
        Color[] fills = new Color[] {
                Color.RED,
                Color.BLUE,
                Color.LIME,
                Color.ORANGE,
                Color.TURQUOISE,
                Color.BROWN,
                Color.YELLOW
        };

        final double radius = 50;
        final double dY = radius * Math.sqrt(3) / 2;
        int counter = 0;

        Polygon[] grid = new Polygon[100];

        for (int y = 0, colorIndex = 0; y < 10; y++) {
            double offsetY  = 2 * dY * y + 50;
            for (int x = 0; x < 10; x++, colorIndex = (colorIndex + 1) % fills.length) {
                Polygon hex = createCell(
                        1.5 * radius * x + 50,
                        (x & 1) == 0 ? offsetY : offsetY + dY,
                        radius,
                        fills[colorIndex]);

                root.getChildren().add(hex);
                grid[counter] = hex;
                counter++;
            }
        }

    }
}
