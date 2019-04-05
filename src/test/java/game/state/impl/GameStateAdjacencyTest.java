package game.state.impl;

import game.state.GameState;
import game.state.basic.Index;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateAdjacencyTest {

    @Test
    public void testResetGame(){
        GameState state= new GameStateAdjacency(9);
        state.initGame();
//        assertTrue(state.isTransitivelyConnected(new Index(0,0),new Index(8,8))==17);
//        assertTrue(state.isTransitivelyConnected(new Index(0,0),new Index(0,8))==9);
    }

}