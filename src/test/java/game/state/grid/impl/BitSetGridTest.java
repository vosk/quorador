package game.state.grid.impl;

import static org.junit.Assert.*;

import game.state.basic.Index;
import game.state.grid.Grid;
import game.state.motion.Direction;
import org.junit.Test;

public class BitSetGridTest {
    @Test
    public void TestShifts(){
        int N=3;
        Grid grid = new BitSetGrid(N);
        grid.clearAll();
        grid.set(new Index(1,1));
        System.out.println(grid);
        grid.shift(Direction.UP);
        System.out.println(grid);
    }

}