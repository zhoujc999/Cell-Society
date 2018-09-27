package View;

import Model.CellStates;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class GameOfLifeStatsGraph extends StatsGraph {
    private static final int NUM_OF_STATES = 2;
    private XYChart.Series seriesLive;
    private XYChart.Series seriesDead;
    private int time = 0;
    
    public GameOfLifeStatsGraph(LineChart lineChart){
        super(lineChart);
        seriesLive = new XYChart.Series<Number, Number>();
        seriesDead = new XYChart.Series<Number, Number>();
        lineChart.getData().add(seriesLive);
        lineChart.getData().add(seriesDead);
    }

    @Override
    public void update(Map<CellStates, Integer> stats) {
        seriesLive.getData().add(new XYChart.Data(time, stats.get(CellStates.GameOfLifeStates.LIVE)));
        seriesDead.getData().add(new XYChart.Data(time, stats.get(CellStates.GameOfLifeStates.DEAD)));
        time++;
    }

}
