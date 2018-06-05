package game.state.routing;

import game.state.Index;

public interface AdjacencyData extends Cloneable {
    void initGame();

    void addEdge(Index i, Index j) throws UnsupportedOperationException;

    void removeEdge(Index i, Index j) throws UnsupportedOperationException;

    boolean connected(Index i, Index j);

    void addWall(Index i1, Index i2);

    AdjacencyData clone();
}
