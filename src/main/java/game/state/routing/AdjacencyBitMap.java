package game.state.routing;

import game.state.GameState;
import game.state.Index;
import game.state.IndexHash;

public class AdjacencyBitMap implements  AdjacencyData {
    private final static int BOARD_SIZE=9;
    private long horizontalWall;
    private long verticalWall;

    private final  IndexHash hash;

    public AdjacencyBitMap(IndexHash hash ) {
        this.hash=hash;
        if(hash.getGameState().getBoardSize()!=BOARD_SIZE){
            throw new UnsupportedOperationException("BitMaps work only for 9x9 boards");
        }
    }

    @Override
    public void initGame() {
        horizontalWall=0;
        verticalWall=0;
    }

    @Override
    public void addEdge(Index i, Index j) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("AdjacencyBitMap supports only walls");

    }

    @Override
    public void removeEdge(Index i, Index j) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("AdjacencyBitMap supports only walls");
    }

    @Override
    public boolean connected(Index i, Index j) {
        int ii=hash.hash(i);
        int jj=hash.hash(j);
        int small=Math.min(ii,jj);
        int big=Math.max(ii,jj);
        if(big-small==1){//Horizontally connected , vertical wall check
            boolean wall=((verticalWall >> small) & 0b1) >0;
            if(wall){
                return false;
            }
            if(small>=BOARD_SIZE) {
                boolean wallbelow=((verticalWall >> (small-BOARD_SIZE)) & 0b1) >0;
                if(wallbelow){
                    return false;
                }
            }
            return true;

        }
        else if (big-small==9){//Vertically connected, horizontal wall check

            boolean wall=((horizontalWall >> small) & 0b1) >0;
            if(wall){
                return false;
            }
            if(small>=1) {
                boolean wallbelow=((horizontalWall >> (small-1)) & 0b1) >0;
                if(wallbelow){
                    return false;
                }
            }
            return true;
        }
        else if(big==small){
            throw new IllegalArgumentException("i should not be equal to j");
        }
        else
            return true;
    }

    @Override
    public void addWall(Index i1, Index i2) {
        int ii=hash.hash(i1);
        int jj=hash.hash(i2);
        int small=Math.min(ii,jj);
        int big=Math.max(ii,jj);
        if(big-small==1){//Horizontally connected

            horizontalWall=horizontalWall| (1<<small);
        }
        else if (big-small==9){//Vertically connected
            verticalWall=verticalWall| (1<<small);
        }
        else
            throw new IllegalArgumentException("invalid wall points");
    }

    @Override
    public AdjacencyData clone() {
        return null;
    }
}
