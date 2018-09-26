package View;

import Model.CellStates;
import javafx.scene.chart.LineChart;

import java.util.Map;

public abstract class StatsGraph {
    LineChart lineChart;

    public StatsGraph(LineChart lineChart){
        this.lineChart = lineChart;
    }

    public abstract void add(Map<CellStates, Integer> stats);

    public void clear(){
        lineChart.getData().clear();
    }
}
