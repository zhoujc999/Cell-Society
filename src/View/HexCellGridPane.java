package View;

import Model.CellStates;
import Model.Point;
import Model.Simulation;
import javafx.scene.layout.Pane;

import java.util.Map;

public class HexCellGridPane implements CellGridPane {

    private Pane pane;
    private StatsGraph statsGraph;

    public HexCellGridPane(Pane pane, StatsGraph statsGraph){
        this.pane = pane;
        this.statsGraph = statsGraph;
    }

    @Override
    public void create(Map<String, String> attributes, Simulation initialSimulation) {

    }

    @Override
    public void initialize(int numRows, int numCols, Simulation simulation) {

    }

    @Override
    public void render(Map<Point, Integer> updatedMap) {

    }

    @Override
    public void updateStatsGraph(Map<CellStates, Integer> map) {

    }
}
