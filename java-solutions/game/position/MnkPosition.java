package game.position;

import game.Move;

import java.util.Map;

public final class MnkPosition implements Position{
    private static final Map<Cell, Character> SYMBOLS = Map.of(
        Cell.X, 'X',
        Cell.O, 'O',
        Cell.E, '.',
        Cell.B, ' '
    );
    private final int mCols;
    private final int nRows;
    private Cell turn;
    private final Cell[][] cells;

    public MnkPosition(int m, int n, Cell turn, Cell[][] cells) {
        this.mCols = m;
        this.nRows = n;
        this.turn = turn;
        this.cells = cells;
    }

    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < nRows
            && 0 <= move.getColumn() && move.getColumn() < mCols
            && cells[move.getRow()][move.getColumn()] == Cell.E;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    public void setCell(Cell turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ".repeat(6));
        for (int i = 1; i <= mCols; i++) {
            sb.append(i);
            int numOfDigits = String.valueOf(i).length() - 1;
            if (numOfDigits >= 3) {
                sb.append(" ");
            } else {
                sb.append(" ".repeat(3 - numOfDigits));
            }
        }
        sb.append("\n");
        sb.append("\s\s+");
        sb.append(" ".repeat(3));
        sb.append("-\s\s\s".repeat(Math.max(0, mCols)));
        for (int r = 0; r < nRows; r++) {
            sb.append("\n");
            sb.append(r + 1);
            if (String.valueOf(r + 1).length() == 1) {
                sb.append(" ");
            }
            sb.append("|");
            sb.append(" ".repeat(3));
            for (int c = 0; c < mCols; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
                sb.append(" ".repeat(3));
            }
        }
        return sb.toString();
    }
}
