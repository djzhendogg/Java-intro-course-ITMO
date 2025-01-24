package game.players;

import game.Move;
import game.position.Position;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random;
    private final int mCols;
    private final int nRows;

    public RandomPlayer(int m, int n, final Random random) {
        this.mCols = m;
        this.nRows = n;
        this.random = random;
    }

    public RandomPlayer(int m, int n) {
        this(m, n, new Random());
    }

    @Override
    public Move move(final Position position) {
        while (true) {
            int r = random.nextInt(nRows);
            int c = random.nextInt(mCols);
            final Move move = new Move(r, c, position.getCell());
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
