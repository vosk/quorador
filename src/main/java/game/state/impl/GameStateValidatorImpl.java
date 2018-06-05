package game.state.impl;

import game.state.GameState;
import game.state.GameStateValidator;
import game.state.Move;
import game.state.Player;

public class GameStateValidatorImpl implements GameStateValidator {
    @Override
    public boolean isValidMove(GameState start, Move move) {
        if(move.getType()==Move.Type.Move)
            return isValidMoveMove(start,move);
        else if(move.getType()==Move.Type.WallHorizontal)
            return isValidMoveWallHorizontal(start,move);
        else if(move.getType()==Move.Type.WallVertical)
            return isValidMoveWallVertical(start,move);
        throw new IllegalArgumentException("invalid move type");
    }

    private boolean isValidMoveWallVertical(GameState start, Move move) {
        return start.isConnected(move.getStart(),move.getStart().right()) &&
                start.isConnected(move.getStart().up(),move.getStart().up().right());
    }

    private boolean isValidMoveWallHorizontal(GameState start, Move move) {
        return start.isConnected(move.getStart(),move.getStart().up()) &&
               start.isConnected(move.getStart().right(),move.getStart().right().up());
    }

    private boolean isValidMoveMove(GameState start, Move move) {
        return start.isConnected(move.getStart(),move.getEnd());
    }

    @Override
    public boolean isPlayerClosedOff(GameState state, Player player) {
        return false;
    }
}
