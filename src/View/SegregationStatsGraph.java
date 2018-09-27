package View;

import Model.CellStates;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class SegregationStatsGraph extends StatsGraph{

    private XYChart.Series seriesUnsatisfied;
    private XYChart.Series seriesSatisfied;
    private int time;

    public SegregationStatsGraph(LineChart lineChart){
        super(lineChart);
        seriesUnsatisfied = new XYChart.Series<Number, Number>();
        seriesSatisfied = new XYChart.Series<Number, Number>();
        lineChart.getData().addAll(seriesUnsatisfied, seriesSatisfied);
    }

    @Override
    public void update(Map<CellStates, Integer> stats) {
        seriesUnsatisfied.getData().add(new XYChart.Data(time, stats.get(CellStates.SegregationMoods.DISSATISFIED)));
        seriesSatisfied.getData().add(new XYChart.Data(time, stats.get(CellStates.SegregationMoods.SATISFIED)));
        time++;
    }
}
