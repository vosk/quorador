package game.state.grid.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import game.state.basic.Index;
import game.state.grid.Grid;
import org.junit.Test;

public class BitSetGridTest {

    @Test
    public void TestFillFloodAWalls() {

        int N = 100;
        Grid grid = new BitSetGrid(N);
        grid.clearAll();
        grid.fillFlood( Index.get(0, 0));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                assertTrue(grid.get(Index.get(i, j)));
            }
        }

        for (int wall = 0; wall < N; wall++) {
            grid.clearAll();
            for (int i = 0; i < N; i++) {
                grid.set(Index.get(i, wall));
            }
            grid.fillFlood(Index.get(0, 0));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    assertEquals(grid.get(Index.get(i, j)), (j <= wall));
                }
            }
        }
        for (int wall = 0; wall < N; wall++) {
            grid.clearAll();
            for (int i = 0; i < N; i++) {
                grid.set(Index.get(wall, i));
            }
            grid.fillFlood(Index.get(0, 0));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    assertEquals(grid.get(Index.get(i, j)), (i <= wall));
                }
            }
        }
    }
    @Test
    public void TestFillFloodMeander(){
        int N=100;
        Grid grid = new BitSetGrid(N);
        grid.clearAll();


    }
}