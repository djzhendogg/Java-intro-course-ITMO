package game.board;

import game.*;
import game.position.MnkPosition;
import game.position.Position;
import game.position.Cell;
import game.Result;
import game.settings.MnkParameters;

public abstract class MnkAbstractBoard implements Board {
    private final int mCols;
    private final int nRows;
    private final int kCellsToWin;
    private final boolean shouldCheckDiagonal;
    private final boolean shouldCheckHorizontal;
    private final boolean shouldCheckVertical;
    private final MnkPosition position;
    private final Cell[][] cells;
    private int emptyCells;
    private Cell turnCell;

    public MnkAbstractBoard(MnkParameters mnkParameters) {
        this.turnCell = Cell.X;
        this.mCols = mnkParameters.getM();
        this.nRows = mnkParameters.getN();
        this.kCellsToWin = mnkParameters.getK();
        this.shouldCheckDiagonal = kCellsToWin <= Math.min(mCols, nRows);
        this.shouldCheckHorizontal = kCellsToWin <= mCols;
        this.shouldCheckVertical = kCellsToWin <= nRows;
        this.cells = createCellsTable(mCols, nRows);
        this.position = new MnkPosition(mCols, nRows, turnCell, cells);
        this.emptyCells = calculateEmptyCells(mCols, nRows);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!position.isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        emptyCells--;

        if (shouldCheckHorizontal && checkHorizontalWin(move)) {
            return Result.WIN;
        }
        if (shouldCheckVertical && checkVerticalWin(move)) {
            return Result.WIN;
        }
        if (shouldCheckDiagonal && checkDiagonalWin(move)) {
            return Result.WIN;
        }

        if (emptyCells == 0) {
            return Result.DRAW;
        }

        turnCell = turnCell == Cell.X ? Cell.O : Cell.X;
        position.setCell(turnCell);
        return Result.UNKNOWN;
    }

    @Override
    public String toString() {
        return position.toString();
    }

    abstract protected int calculateEmptyCells(int mCols, int nRows);

    abstract protected Cell[][] createCellsTable(int mCols, int nRows);

    private boolean checkHorizontalWin(Move move) {
        int inRow = 1;
        inRow += countTurnCells(0, -1, move);
        inRow += countTurnCells(0, 1, move);
        return inRow >= kCellsToWin;
    }

    private boolean checkVerticalWin(Move move) {
        int inColumn = 1;
        inColumn += countTurnCells(-1, 0, move);
        inColumn += countTurnCells(1, 0, move);
        return inColumn >= kCellsToWin;
    }

    private boolean checkDiagonalWin(Move move) {
        int inDiagonal = 1;
        inDiagonal += countTurnCells(-1, -1, move);
        inDiagonal += countTurnCells(1, 1, move);

        if (inDiagonal >= kCellsToWin) {
            return true;
        }

        inDiagonal = 1;
        inDiagonal += countTurnCells(-1, 1, move);
        inDiagonal += countTurnCells(1, -1, move);
        return inDiagonal >= kCellsToWin;
    }

    private int countTurnCells(int ratioRows, int ratioCols, Move move) {
        int offset = 1;
        int result = 0;
        int row = move.getRow();
        int col = move.getColumn();
        while (
                0 <= row + offset * ratioRows
                        && row + offset * ratioRows < nRows
                        && 0 <= col + offset * ratioCols
                        && col + offset * ratioCols < mCols
                        && cells[row + offset * ratioRows][col + offset * ratioCols] == turnCell
        ) {
            result++;
            offset++;
        }
        return result;
    }
}
