package Model;//package Model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class WatorSimulation extends Simulation {
    private static final boolean ROW_WRAP = true;
    private static final boolean COLUMN_WRAP = true;

    private int fishTurnsToBreed;
    private int sharkTurnsToBreed;

    public WatorSimulation(int numRows, int numColumns, Map<Point, Integer> initialState, int fishTurnsToBreed, int sharkTurnsToBreed) {
        super(numRows, numColumns, initialState);
        this.fishTurnsToBreed = fishTurnsToBreed;
        this.sharkTurnsToBreed = sharkTurnsToBreed;
        initializeTurns();
    }

    protected void initializeGrid() {
        this.grid = new WatorGrid(numRows, numColumns, ROW_WRAP, COLUMN_WRAP);
    }

    protected void initializeCells(Map<Point, Integer> initialParam) {
        for (Map.Entry entry : initialParam.entrySet()) {
            Point position = (Point) entry.getKey();
            CellStates.WatorStates state = CellStates.WatorStates.fromInt((int) entry.getValue());
            if (grid.getMatrix().get(position) != null) {
                throw new IllegalArgumentException("InitialState Duplicate Point Error");
            }
            WatorCell cell = new WatorCell(position, (WatorGrid) grid, state, fishTurnsToBreed, sharkTurnsToBreed);
            grid.getMatrix().put(position, cell);
        }
    }

    protected void initializeTurns() {
        for (Cell cell : this.grid.getMatrix().values()) {
            WatorCell c = (WatorCell) cell;
            c.setFishTurnsToBreed(this.fishTurnsToBreed);
            c.setSharkTurnsToBreed(this.sharkTurnsToBreed);
        }
    }

    protected void initializeStatistics() {
        this.statistics = new EnumMap<CellStates.WatorStates, Integer>(CellStates.WatorStates.class);
        statistics.put(CellStates.WatorStates.FISH, 0);
        statistics.put(CellStates.WatorStates.SHARK, 0);
        statistics.put(CellStates.WatorStates.EMPTY, 0);
    }

    protected void initializeView() {
        this.view = new HashMap<Point, CellStates.WatorStates>();
    }

    public void step() {
        for (Cell cell: grid.getMatrix().values()) {
            cell.calculateNextState();
        }
        for (Cell cell: grid.getMatrix().values()) {
            cell.updateState();
            if (cell.stateChanged) {
                ((WatorCell) cell).resetTurn();
            }
        }
    }

    public void render() {
        int numFish = 0;
        int numShark = 0;
        int numEmpty = 0;
        view.clear();
        for (Map.Entry<Point, Cell> entry: grid.getMatrix().entrySet()) {
            WatorCell cell = (WatorCell) entry.getValue();
            if (cell.currentState == CellStates.WatorStates.FISH) {
                numFish++;
            }
            else if (cell.currentState == CellStates.WatorStates.SHARK) {
                numShark++;
            }
            else if (cell.currentState == CellStates.WatorStates.EMPTY) {
                numEmpty++;
            }
            view.put(entry.getKey(), entry.getValue().currentState);

        }
        statistics.put(CellStates.WatorStates.FISH, numFish);
        statistics.put(CellStates.WatorStates.SHARK, numShark);
        statistics.put(CellStates.WatorStates.EMPTY, numEmpty);
    }


    public String toString() {
        return "Wa-Tor Simulation";
    }

}
