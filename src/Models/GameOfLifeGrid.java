package Models;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeGrid extends Grid {
    public GameOfLifeGrid(int numRows, int numColumns, boolean rowWrap, boolean columnWrap) {
        super(numRows, numColumns, rowWrap, columnWrap);
    }
}

