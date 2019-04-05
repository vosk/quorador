package game.state;

import game.state.basic.Move;

public interface GameStateValidator {

    boolean isValidMove(GameState start,Move move);
    boolean isPlayerClosedOff(GameState state,Player player);
}
