package game.board;

import game.position.Cell;
import game.settings.MnkParameters;

public final class MnkRhombusBoard extends MnkAbstractBoard {
    public MnkRhombusBoard(MnkParameters mnkParameters) {
        super(mnkParameters);
    }

    @Override
    protected int calculateEmptyCells(int mCols, int nRows) {
        return mCols * nRows + (mCols - 1) * (nRows - 1);
    }

    @Override
    protected Cell[][] createCellsTable(int mCols, int nRows) {
        Cell[][] result = new Cell[mCols][nRows];
        int side = (mCols - 1) / 2 + 1;

        for (int i = 0; i < mCols; i++) {
            for (int j = 0; j < nRows; j++) {
                if (Math.abs(i - (side - 1)) + Math.abs(j - (side - 1)) < side) {
                    result[i][j] = Cell.E;
                } else {
                    result[i][j] = Cell.B;
                }
            }
        }
        return result;
    }
}
