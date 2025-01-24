package game.players;

import game.Move;
import game.position.Position;

public class SequentialPlayer implements Player {
    private final int mCols;
    private final int nRows;

    public SequentialPlayer(int m, int n) {
        this.mCols = m;
        this.nRows = n;
    }

    @Override
    public Move move(final Position position) {
        for (int r = 0; r < nRows; r++) {
            for (int c = 0; c < mCols; c++) {
                final Move move = new Move(r, c, position.getCell());
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new IllegalStateException("Invalid game state");
    }
}
