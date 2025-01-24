package game.board;

import game.Move;
import game.position.Position;
import game.Result;

public interface Board {
    Position getPosition();

    Result makeMove(Move move);
}
