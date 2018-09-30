package View;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class FireStatsGraph extends StatsGraph {

    private static final int NUM_OF_STATES = 3;
    private XYChart.Series seriesBurning;
    private XYChart.Series seriesTree;
    private XYChart.Series seriesEmpty;
    private int time = 0;

    public FireStatsGraph(LineChart lineChart){
        super(lineChart);
        seriesBurning = new XYChart.Series<Number, Number>();
        seriesTree = new XYChart.Series<Number, Number>();
        seriesEmpty = new XYChart.Series<Number, Number>();
        lineChart.getData().addAll(seriesBurning, seriesTree, seriesEmpty);
    }

    @Override
    public void update(Map<CellStates, Integer> stats) {
        seriesBurning.getData().add(new XYChart.Data(time, stats.get(CellStates.FireStates.BURNING)));
        seriesTree.getData().add(new XYChart.Data(time, stats.get(CellStates.FireStates.TREE)));
        seriesEmpty.getData().add(new XYChart.Data(time, stats.get(CellStates.FireStates.EMPTY)));
        time++;
    }
}
