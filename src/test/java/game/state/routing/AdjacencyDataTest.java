package game.state.routing;

import game.state.basic.Index;

import static org.junit.Assert.*;

public class AdjacencyDataTest {

    protected void testBasicWalls(AdjacencyData data){
        data.initGame();
        data.addWall(Index.ZERO,Index.ZERO.right());
        assertTrue(data.connected(Index.ZERO,Index.ZERO.right()));
        assertTrue(data.connected(Index.ZERO.up(),Index.ZERO.up().right()));
        assertFalse(data.connected(Index.ZERO,Index.ZERO.up()));
        assertFalse(data.connected(Index.ZERO.right(),Index.ZERO.right().up()));
    }

}