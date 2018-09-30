package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

public class Layout extends UIManager{

    public static int STARTING_COL = 5;
    public static double LABEL_MIN_WIDTH = 50.0;
    private static final int SIMULATION_COLUMN_SPACE = 5;
    private static final double LINE_CHART_MAX_HEIGHT = 100.0;
    private static final int LINE_CHART_COL_SPAN = 4;
    private static final int FIRST_ROW = 0;
    private static final int SECOND_ROW = 1;
    private static final int THIRD_ROW = 2;
    private static final int FOURTH_ROW = 3;
    private static final int FIFTH_ROW = 4;
    private static final int SECOND_COL = 1;
    private static final int THIRD_COL = 2;
    private static final int FOURTH_COL = 3;
    private static final int FIFTH_COL = 4;
    private static final String WidthButtonLabel = "WidthButtonLabel";
    private static final String HeightButtonLabel = "HeightButtonLabel";
    private static final String SpeedSliderLabel = "SpeedSliderLabel";
    private static final String ApplyButtonLabel = "ApplyButtonLabel";
    private static final String StartButtonLabel = "StartButtonLabel";
    private static final String StopButtonLabel = "StopButtonLabel";
    private static final String ResetButtonLabel = "ResetButtonLabel";
    private static final String StepButtonLabel = "StepButtonLabel";
    private static final String FileChooserButtonLabel = "FileChooserButtonLabel";
    private static final String RemoveSimulationLabel = "RemoveSimulationLabel";
    private static final double TEXTFIELD_MAX_WIDTH = 50.0;
    private static final double SLIDER_MAX_WIDTH = 110.0;
    private static final int SLIDER_COLUMN_SPAN = 2;
    private static final int SIMULATION_GRID_ROW_SPAN = 2;
    private static final double HBOX_SPACING = 10.0;

    private Controller controller;
    private GridPane layoutPane;
    private ResourceBundle UILabels;

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
        UILabels = ResourceBundle.getBundle("Resource.UILabel", AppLanguageManager.getCurrentLocale());

        addSimulationPane();
        addLabels();
        addInputFields();
        addButtons();
        addLineChart();
        forceInputToBeNumeric(widthTextField);
        forceInputToBeNumeric(heightTextField);

        controller = new Controller(pane, lineChart);
        controller.start();
        STARTING_COL += SIMULATION_COLUMN_SPACE;
    }

    private void addSimulationPane(){
        pane = new Pane();
        HBox hBox = new HBox(pane);
        GridPane.setRowSpan(hBox, SIMULATION_GRID_ROW_SPAN);
        layoutPane.add(hBox, STARTING_COL, FIRST_ROW);
    }

    private void addLabels(){
//        Label widthLabel = new Label("Width:");
        Label widthLabel = new Label(UILabels.getString(WidthButtonLabel));
        widthLabel.setMinWidth(LABEL_MIN_WIDTH);
        layoutPane.add(widthLabel, STARTING_COL+SECOND_COL, FIRST_ROW);
        Label heightLabel = new Label(UILabels.getString(HeightButtonLabel));
        heightLabel.setMinWidth(LABEL_MIN_WIDTH);
        layoutPane.add(heightLabel, STARTING_COL+FOURTH_COL, FIRST_ROW);

        Label speedLabel = new Label(UILabels.getString(SpeedSliderLabel));
        HBox hBox = new HBox(speedLabel);
        hBox.setAlignment(Pos.TOP_CENTER);
        layoutPane.add(hBox, STARTING_COL+SECOND_COL, SECOND_ROW);
    }

    private void addButtons(){
        addTimeLineRelatedAndCellShapeButtons();

        Button apply = new Button(UILabels.getString(ApplyButtonLabel));
        apply.setOnAction(event -> handleApply());
        HBox hBox = new HBox(apply);
        hBox.setAlignment(Pos.TOP_CENTER);
        layoutPane.add(hBox, STARTING_COL+FIFTH_COL, SECOND_ROW);

        Button filechooser = new Button(UILabels.getString(FileChooserButtonLabel));
        Button removeSimulation = new Button(UILabels.getString(RemoveSimulationLabel));
        removeSimulation.setOnAction(event -> handleRemoveSimulation());
        HBox secondRowButtons = new HBox(filechooser, removeSimulation);
        secondRowButtons.setSpacing(HBOX_SPACING);
        secondRowButtons.setAlignment(Pos.CENTER);
        layoutPane.add(secondRowButtons, STARTING_COL, FOURTH_ROW);
    }

    private void addTimeLineRelatedAndCellShapeButtons(){
        Button start = new Button(UILabels.getString(StartButtonLabel));
        start.setOnAction(event -> controller.resume());
        Button stop = new Button(UILabels.getString(StopButtonLabel));
        stop.setOnAction(event -> controller.stop());
        Button reset = new Button(UILabels.getString(ResetButtonLabel));
        reset.setOnAction(event -> controller.reset());
        Button step = new Button(UILabels.getString(StepButtonLabel));
        step.setOnAction(event -> controller.animationStep());
        ComboBox dropdown = new ComboBox();
        dropdown.setItems(FXCollections.observableArrayList(
                UILabels.getString("Rectangle"), UILabels.getString("Hexagon")));
        dropdown.getSelectionModel().selectFirst();
        HBox buttons = new HBox(start, stop, reset, step, dropdown);
        buttons.setSpacing(HBOX_SPACING);
        buttons.setAlignment(Pos.CENTER);
        layoutPane.add(buttons, STARTING_COL, THIRD_ROW);
    }

    private void addInputFields(){
        widthTextField = new TextField();
        widthTextField.setMaxWidth(TEXTFIELD_MAX_WIDTH);
        layoutPane.add(widthTextField, STARTING_COL+THIRD_COL, FIRST_ROW);

        heightTextField = new TextField();
        heightTextField.setMaxWidth(TEXTFIELD_MAX_WIDTH);
        layoutPane.add(heightTextField, STARTING_COL+FIFTH_COL, FIRST_ROW);

        slider = new Slider();
        slider.setMaxWidth(SLIDER_MAX_WIDTH);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        HBox hBox = new HBox(slider);
        hBox.setAlignment(Pos.TOP_CENTER);
        GridPane.setColumnSpan(hBox, SLIDER_COLUMN_SPAN);
        layoutPane.add(hBox, STARTING_COL+THIRD_COL, SECOND_ROW);
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
        lineChart.setMaxHeight(LINE_CHART_MAX_HEIGHT);
        lineChart.setCreateSymbols(false);
        GridPane.setColumnSpan(lineChart, LINE_CHART_COL_SPAN);
        layoutPane.add(lineChart, STARTING_COL,FIFTH_ROW);
    }

    private void handleRemoveSimulation(){
        layoutPane.getChildren().removeIf(node ->
                GridPane.getColumnIndex(node)==null||isInRange(GridPane.getColumnIndex(node)));
    }

    private boolean isInRange(int target){
        return target>=STARTING_COL-SIMULATION_COLUMN_SPACE&&target<=STARTING_COL-1;
    }

    private void handleApply(){
        super.handleApplyButtonAction(heightTextField, widthTextField, slider, controller, pane);
    }

}
