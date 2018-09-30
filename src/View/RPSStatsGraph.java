package View;

import Model.Cell;
import Model.CellStates;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class RPSStatsGraph extends StatsGraph{

    private static final int NUM_OF_STATES = 4;
    private XYChart.Series seriesWhite;
    private XYChart.Series seriesRed;
    private XYChart.Series seriesGreen;
    private XYChart.Series seriesBlue;
    private int time = 0;

    public RPSStatsGraph(LineChart lineChart){
        super(lineChart);
        seriesWhite = new XYChart.Series<Number, Number>();
        seriesRed = new XYChart.Series<Number, Number>();
        seriesGreen = new XYChart.Series<Number, Number>();
        seriesBlue = new XYChart.Series<Number, Number>();
        lineChart.getData().addAll(seriesWhite, seriesRed, seriesGreen, seriesBlue);
    }

    @Override
    public void update(Map<CellStates, Integer> stats) {
        seriesWhite.getData().add(new XYChart.Data(time, stats.get(CellStates.RPSStates.WHITE)));
        seriesRed.getData().add(new XYChart.Data(time, stats.get(CellStates.RPSStates.RED)));
        seriesGreen.getData().add(new XYChart.Data(time, stats.get(CellStates.RPSStates.GREEN)));
        seriesBlue.getData().add(new XYChart.Data(time, stats.get(CellStates.RPSStates.BLUE)));
        time++;
    }
}
