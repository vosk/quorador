package game.state;

import game.state.GameState;
import game.state.Move;
import game.state.Player;

public interface GameStateValidator {

    boolean isValidMove(GameState start,Move move);
    boolean isPlayerClosedOff(GameState state,Player player);
}
