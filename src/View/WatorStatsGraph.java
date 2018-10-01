package View;

import Model.CellStates;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class WatorStatsGraph extends StatsGraph {

    private static final int NUM_OF_STATES = 3;
    private XYChart.Series seriesFish;
    private XYChart.Series seriesShark;
    private XYChart.Series seriesEmpty;
    private int time = 0;

    public WatorStatsGraph(LineChart lineChart){
        super(lineChart);
        seriesFish = new XYChart.Series<Number, Number>();
        seriesShark = new XYChart.Series<Number, Number>();
        seriesEmpty = new XYChart.Series<Number, Number>();
        lineChart.getData().addAll(seriesFish, seriesShark, seriesEmpty);
    }


    @Override
    public void update(Map<CellStates, Integer> stats) {
        seriesFish.getData().add(new XYChart.Data(time, stats.get(CellStates.WatorStates.FISH)));
        seriesShark.getData().add(new XYChart.Data(time, stats.get(CellStates.WatorStates.SHARK)));
        seriesEmpty.getData().add(new XYChart.Data(time, stats.get(CellStates.WatorStates.EMPTY)));
        time++;
    }
}
