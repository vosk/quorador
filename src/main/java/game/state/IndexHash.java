package game.state;

import game.state.basic.Index;

public class IndexHash {
    private final GameState gameState;

    public GameState getGameState() {
        return gameState;
    }

    public IndexHash(GameState gameState) {
        this.gameState = gameState;
    }

    public Integer hash(Index index){
        return index.getI()+ gameState.getBoardSize()*index.getJ();
    }

    public  Integer maxHash(){
        return (gameState.getBoardSize()-1)*(gameState.getBoardSize()+1);
    }

}
