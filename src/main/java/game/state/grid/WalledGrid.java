package game.state.grid;

import game.state.basic.Index;

public interface WalledGrid extends Grid {

    void connect(Index i, Index j);
    void disconnect(Index i, Index j);
    boolean isConnected(Index i, Index j);


}
