package game.players;

import game.Move;
import game.position.Position;

public interface Player {
    Move move(Position position);
}
