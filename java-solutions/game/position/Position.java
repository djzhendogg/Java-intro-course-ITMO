package game.position;

import game.Move;

public interface Position {
    boolean isValid(Move move);

    Cell getCell();
}
