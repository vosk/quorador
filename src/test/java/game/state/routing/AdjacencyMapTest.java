package game.state.routing;

import game.state.IndexHash;
import game.state.impl.GameStateAdjacency;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacencyMapTest  extends AdjacencyDataTest{
    @Test
    public void doWallTests(){
        testBasicWalls(new AdjacencyMap(new IndexHash(new GameStateAdjacency(9))));
    }

}