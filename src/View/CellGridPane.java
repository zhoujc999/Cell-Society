package View;

import Model.CellStates;
import Model.Point;
import Model.Simulation;

import java.util.Map;

public interface CellGridPane {

    double MAX_GRID_WIDTH = 400.0;
    double MAX_GRID_HEIGHT = 400.0;

    void create(Map<String, String> attributes, Simulation initialSimulation);

    void initialize(int numRows, int numCols, Simulation simulation);

    void render(Map<Point, Integer> updatedMap);

    void updateStatsGraph(Map<CellStates, Integer> map);

}
