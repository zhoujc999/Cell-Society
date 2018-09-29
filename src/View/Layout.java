package View;

import Controller.Controller_API;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Layout extends UIManager{

    public static int STARTING_COL = 5;
    public static double LABEL_MIN_WIDTH = 50.0;
    private Controller_API controller;
    private GridPane layoutPane;

    private TextField widthTextField;
    private TextField heightTextField;
    private Slider slider;
    private Pane pane;
    private LineChart lineChart;
    private ComboBox dropDownMenu;
    private NumberAxis xAxis;
    private NumberAxis yAxis;

    public Layout(GridPane gridPane){
        layoutPane = gridPane;
        addSimulationPane();
        addLabels();
        addInputFields();
        addButtons();
        addLineChart();
        forceInputToBeNumeric(widthTextField);
        forceInputToBeNumeric(heightTextField);

        controller = new Controller_API(pane, lineChart);
        controller.start();
        STARTING_COL += 5;
    }

    private void addSimulationPane(){
        pane = new Pane();
        HBox hBox = new HBox(pane);
        GridPane.setRowSpan(hBox, 2);
        layoutPane.add(hBox, STARTING_COL, 0);
    }

    private void addLabels(){
        Label widthLabel = new Label("Width:");
        widthLabel.setMinWidth(LABEL_MIN_WIDTH);
        layoutPane.add(widthLabel, STARTING_COL+1, 0);
        Label heightLabel = new Label("Height:");
        heightLabel.setMinWidth(LABEL_MIN_WIDTH);
        layoutPane.add(heightLabel, STARTING_COL+3, 0);

        Label speedLabel = new Label("Speed");
        HBox hBox = new HBox(speedLabel);
        hBox.setAlignment(Pos.TOP_CENTER);
        layoutPane.add(hBox, STARTING_COL+1, 1);
    }

    private void addButtons(){
        addTimeLineRelatedAndCellShapeButtons();

        Button apply = new Button("Apply");
        apply.setOnAction(event -> handleApply());
        HBox hBox = new HBox(apply);
        hBox.setAlignment(Pos.TOP_CENTER);
        layoutPane.add(hBox, STARTING_COL+4, 1);

        Button filechooser = new Button("Choose a file...");
        Button removeSimulation = new Button("Remove this simulation...");
        removeSimulation.setOnAction(event -> handleRemoveSimulation());
        HBox secondRowButtons = new HBox(filechooser, removeSimulation);
        secondRowButtons.setSpacing(10.0);
        secondRowButtons.setAlignment(Pos.CENTER);
        layoutPane.add(secondRowButtons, STARTING_COL, 3);
    }

    private void addTimeLineRelatedAndCellShapeButtons(){
        Button start = new Button("Start");
        start.setOnAction(event -> controller.resume());
        Button stop = new Button("Stop");
        stop.setOnAction(event -> controller.stop());
        Button reset = new Button("Reset");
        reset.setOnAction(event -> controller.reset());
        Button step = new Button("Step");
        step.setOnAction(event -> controller.animationStep());
        ComboBox dropdown = new ComboBox();
        dropdown.setItems(FXCollections.observableArrayList(
                "Rectangle", "Hexagon"));
        dropdown.getSelectionModel().selectFirst();
        HBox buttons = new HBox(start, stop, reset, step, dropdown);
        buttons.setSpacing(10.0);
        buttons.setAlignment(Pos.CENTER);
        layoutPane.add(buttons, STARTING_COL, 2);
    }

    private void addInputFields(){
        widthTextField = new TextField();
        widthTextField.setMaxWidth(50.0);
        layoutPane.add(widthTextField, STARTING_COL+2, 0);

        heightTextField = new TextField();
        heightTextField.setMaxWidth(50.0);
        layoutPane.add(heightTextField, STARTING_COL+4, 0);

        slider = new Slider();
        slider.setMaxWidth(110.0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        HBox hBox = new HBox(slider);
        hBox.setAlignment(Pos.TOP_CENTER);
        GridPane.setColumnSpan(hBox, 2);
        layoutPane.add(hBox, STARTING_COL+2, 1);
    }

    private void addLineChart(){
        xAxis = new NumberAxis();
        xAxis.setMinorTickVisible(false);
        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        xAxis.setSide(Side.BOTTOM);
        yAxis = new NumberAxis();
        yAxis.setSide(Side.LEFT);
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setMaxHeight(100.0);
        lineChart.setCreateSymbols(false);
        GridPane.setColumnSpan(lineChart, 4);
        layoutPane.add(lineChart, 5,4);
    }

    private void handleRemoveSimulation(){
        layoutPane.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node)==null||isInRange(GridPane.getColumnIndex(node)));
    }

    private boolean isInRange(int target){
        return target>=STARTING_COL-5&&target<=STARTING_COL-1;
    }

    private void handleApply(){
        super.handleApplyButtonAction(heightTextField, widthTextField, slider, controller, pane);
    }

}
